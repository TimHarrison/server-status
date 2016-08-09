package com.serverstatus.web;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.serverstatus.domain.ArchivedStatus;
import com.serverstatus.domain.CurrentStatusResponse;
import com.serverstatus.domain.CurrentStatusType;
import com.serverstatus.domain.Game;
import com.serverstatus.domain.GameRepository;
import com.serverstatus.domain.Status;
import com.serverstatus.domain.StatusRepository;
import com.serverstatus.domain.StatusType;
import com.serverstatus.exceptions.GameNotFoundException;

@Controller
@RequestMapping("/")
public class TaskController {
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private ArchivedStatus archivedStatusRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@RequestMapping(value = "/get_status", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public CurrentStatusResponse status(@RequestParam(value="game", required=true) String game) {
		Game setGame = gameRepository.findByTitle(game);
		if (setGame == null) {
			throw new GameNotFoundException();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, -2);
		Date thirtyMinutesBack = cal.getTime();
//		System.out.println(String.format("Finding all status that is newer than: '%s'", thirtyMinutesBack.toString()));
//		List<Status> latestStatusList = statusRepository.findByCreateDateGreaterThan(thirtyMinutesBack);
		List<Status> latestStatusList = statusRepository.findByGameAndByCreateDateGreaterThan(thirtyMinutesBack, setGame.getId());
		
		int statusUp = 0;
		int statusDown = 0;
		int total = 0;
		
		for(Iterator<Status> i = latestStatusList.iterator(); i.hasNext(); ) {
			Status item = i.next();
//			System.out.println(item);
			if(item.getStatus().equals(StatusType.UP)) {
//				System.out.println("Found an UP report!");
				statusUp++;
			} else {
//				System.out.println("Found an DOWN report!");
				statusDown++;
			}
			total++;
		}

		float statusUpPercent = (float)statusUp/total*100;
		float statusDownPercent = (float)statusDown/total*100;
		
//		System.out.println(String.format("Total UP status: '%s'", statusUp));
//		System.out.println(String.format("Total DOWN status: '%s'", statusDown));
//		System.out.println(String.format("Percentage UP: '%s'", statusUpPercent));
//		System.out.println(String.format("Percentage DOWN: '%s'", statusDownPercent));
		
		CurrentStatusResponse currentStatus = new CurrentStatusResponse(setGame);
		
		if (total == 0) {
			statusUpPercent = archivedStatusRepository.getUpPercent();
		}
		
		if (statusUpPercent >= 80) {
//			System.out.println("Good!");
			currentStatus.setStatus(CurrentStatusType.GOOD);
		} else if (statusUpPercent >= 50) {
//			System.out.println("Bad");
			currentStatus.setStatus(CurrentStatusType.BAD);
		} else {
//			System.out.println("Ugly");
			currentStatus.setStatus(CurrentStatusType.UGLY);
		}
		
		currentStatus.setPercentageUp(statusUpPercent);
		
		return currentStatus;
	}
	
	@RequestMapping(value = "/create_status", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Status status(@ModelAttribute("status") StatusType setStatus, @ModelAttribute("game") String game) {
		//Figure out game = game class
//		System.out.println(String.format("Creating status for game: %s", game));
		Game setGame = gameRepository.findByTitle(game);
//		System.out.println(String.format("Creating status for game: %s", setGame));
		
		//Create status
//		System.out.println(String.format("Creating new Status: [status='%s', game='%s']", setStatus, game));
		Status status = new Status(setStatus, setGame);
		statusRepository.save(status);
		return status;
	}
	
}
