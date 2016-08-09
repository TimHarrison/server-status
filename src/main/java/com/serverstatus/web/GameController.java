package com.serverstatus.web;

//import java.util.concurrent.atomic.AtomicLong;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.serverstatus.domain.Game;
import com.serverstatus.domain.GameRepository;

@RestController
@RequestMapping("/")
public class GameController {
	
//	private static final String template = "Hello, %s!";
//	private final AtomicLong counter = new AtomicLong();
	@Autowired
	private GameRepository gameRepository;
	
	@RequestMapping(value = "/get_game", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Game game(@RequestParam(value="title", defaultValue="World") String title) {
		System.out.println(gameRepository.findByTitle(title));
		return gameRepository.findByTitle(title);
	}
	
	@RequestMapping(value = "/get_games", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public List<Game> game() {
		System.out.println(gameRepository.findAll());
		return gameRepository.findAll();
	}
	
	@RequestMapping(value = "/create_game", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@ResponseBody
	public Game game(@ModelAttribute("title") String title, @ModelAttribute("description") String description) {
		System.out.println(String.format("Creating new Game: [title='%s', description='%s']", title, description));
//		
//		Organization latestOrg = orgRepository.findLatestOrganization();
//		counter.set(latestOrg.getPublicId());
		
		Game game = new Game(title, description);
		gameRepository.save(game);
		return game;
	}
}
