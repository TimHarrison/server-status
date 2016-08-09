package com.serverstatus.scheduled;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.serverstatus.domain.ArchivedStatus;
import com.serverstatus.domain.ArchivedStatusRepository;
import com.serverstatus.domain.CurrentStatusResponse;
import com.serverstatus.domain.CurrentStatusType;
import com.serverstatus.domain.Game;
import com.serverstatus.domain.GameRepository;
import com.serverstatus.domain.Status;
import com.serverstatus.domain.StatusRepository;
import com.serverstatus.domain.StatusType;
import com.serverstatus.exceptions.GameNotFoundException;
import com.serverstatus.util.StatusUtil;

@Component
public class ScheduledTasks {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private ArchivedStatusRepository archivedStatusRepository;
	
	@Autowired
	private GameRepository gameRepository;

    @Scheduled(fixedRate = 1000*60*5)
    public void archiveCurrentStatus() {
        System.out.println("Archiving current status at " + dateFormat.format(new Date()));
        List<Game> allGames = gameRepository.findAll();
        
        for(Iterator<Game> i = allGames.iterator(); i.hasNext(); ) {
        	ArchivedStatus currentArchivedStatus = new ArchivedStatus();
        	
			Game game = i.next();
			Game setGame = gameRepository.findByTitle(game.getTitle());
			if (setGame == null) {
				throw new GameNotFoundException();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.MINUTE, -1);
			Date thirtyMinutesBack = cal.getTime();
//			System.out.println(String.format("Finding all status that is newer than: '%s'", thirtyMinutesBack.toString()));
//			List<Status> latestStatusList = statusRepository.findByCreateDateGreaterThan(thirtyMinutesBack);
			List<Status> latestStatusList = statusRepository.findByGameAndByCreateDateGreaterThan(thirtyMinutesBack, setGame.getId());
			
			int statusUp = 0;
			int statusDown = 0;
			int total = 0;
			
			for(Iterator<Status> i1 = latestStatusList.iterator(); i1.hasNext(); ) {
				Status item = i1.next();
				if(item.getStatus().equals(StatusType.UP)) {
//					System.out.println("Found an UP report!");
					statusUp++;
				} else {
//					System.out.println("Found an DOWN report!");
					statusDown++;
				}
				total++;
			}
			
			if (statusUp == 0 && statusDown == 0) {
				ArchivedStatus lastArchivedStatus = archivedStatusRepository.findLastByOrderByCreateDateDesc();
				currentArchivedStatus.setStatus(lastArchivedStatus.getStatus());
				archivedStatusRepository.save(currentArchivedStatus);
				return;
			}
				

			float statusUpPercent = (float)statusUp/total*100;
			float statusDownPercent = (float)statusDown/total*100;
			
//			System.out.println(String.format("Total UP status: '%s'", statusUp));
//			System.out.println(String.format("Total DOWN status: '%s'", statusDown));
//			System.out.println(String.format("Percentage UP: '%s'", statusUpPercent));
//			System.out.println(String.format("Percentage DOWN: '%s'", statusDownPercent));
			
			CurrentStatusResponse currentStatus = new CurrentStatusResponse(setGame);
			
			if (statusUpPercent >= 80) {
//				System.out.println("Good!");
				currentStatus.setStatus(CurrentStatusType.GOOD);
			} else if (statusUpPercent >= 50) {
//				System.out.println("Bad");
				currentStatus.setStatus(CurrentStatusType.BAD);
			} else {
//				System.out.println("Ugly");
				currentStatus.setStatus(CurrentStatusType.UGLY);
			}
			//Archive Status under new timestamp
			currentArchivedStatus.setUpPercent(statusUpPercent);
			currentArchivedStatus.setStatus(currentStatus.getStatus());
			currentArchivedStatus.setGame(setGame);
			archivedStatusRepository.save(currentArchivedStatus);
			}
        }
    }
