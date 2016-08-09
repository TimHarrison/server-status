package com.serverstatus.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.serverstatus.domain.CurrentStatusResponse;
import com.serverstatus.domain.CurrentStatusType;
import com.serverstatus.domain.Game;
import com.serverstatus.domain.Status;
import com.serverstatus.domain.StatusRepository;
import com.serverstatus.domain.StatusType;

public class StatusUtil {
	@Autowired
	private static StatusRepository statusRepository;
	
	public static HashMap<String, Float> getStatusByGame(Game game) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.MINUTE, -30);
		Date thirtyMinutesBack = cal.getTime();
		System.out.println(String.format("Finding all status that is newer than: '%s'", thirtyMinutesBack.toString()));
		System.out.println(statusRepository.findByCreateDateGreaterThan(thirtyMinutesBack));
		List<Status> latestStatusList = statusRepository.findByCreateDateGreaterThan(thirtyMinutesBack);
		
		int statusUp = 0;
		int statusDown = 0;
		int total = 0;
		
		for(Iterator<Status> i = latestStatusList.iterator(); i.hasNext(); ) {
			Status item = i.next();
			System.out.println(item);
			if(item.getStatus().equals(StatusType.UP)) {
				System.out.println("Found an UP report!");
				statusUp++;
			} else {
				System.out.println("Found an DOWN report!");
				statusDown++;
			}
			total++;
		}

		float statusUpPercent = (float)statusUp/total;
		float statusDownPercent = (float)statusDown/total;
		
		HashMap<String, Float> finalData = new HashMap<String, Float>();
		finalData.put("statusUpPercent", statusUpPercent);
		finalData.put("statusDownPercent", statusDownPercent);
		finalData.put("totalUpStatus", (float) statusUp);
		finalData.put("totalDownStatus", (float) statusDown);
		
		return finalData;
	}
	
	public static CurrentStatusType getStringStatusByPercent(float upPercent) {
		if (upPercent >= 0.8) {
			System.out.println("Good!");
			return CurrentStatusType.GOOD;
		} else if (upPercent >= 0.5) {
			System.out.println("Bad");
			return CurrentStatusType.BAD;
		} else {
			System.out.println("Ugly");
			return CurrentStatusType.UGLY;
		}
	}
}
