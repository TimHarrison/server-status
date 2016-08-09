package com.serverstatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.serverstatus.domain.Game;
import com.serverstatus.domain.GameRepository;
import com.serverstatus.domain.Status;
import com.serverstatus.domain.StatusRepository;
import com.serverstatus.domain.StatusType;

@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner {
	
	@Autowired
	private StatusRepository statusRepository;
	
	@Autowired
	private GameRepository gameRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	public void run(String... args) throws Exception {
//		statusRepository.deleteAll();
//		gameRepository.deleteAll();
		
//		Game game = new Game("Pokemon Go", "Mobile Pokemon GO app.");
//		
//		gameRepository.save(game);
		
//		statusRepository.save(new Status(StatusType.UP, game));
//		statusRepository.save(new Status(StatusType.DOWN, game));
//		
//		System.out.println("Status found with findAll():");
//		System.out.println("---------------------------");
//		for (Status status : statusRepository.findAll()) {
//			System.out.println(status);
//		}
//		
//		System.out.println();
//		
//		System.out.println("Status found with findByGame(game):");
//		System.out.println("--------------------------------------");
//		System.out.println(statusRepository.findByGame(game.getTitle()));
		System.out.println(String.format("System initiated. Total Games: %s, Total Status: %s", gameRepository.count(), statusRepository.count()));
	}
}
