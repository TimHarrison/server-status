package com.serverstatus.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.*;

import com.serverstatus.domain.Status;
import com.serverstatus.domain.StatusRepository;

@Controller
@RequestMapping("/")
public class MainController {
	
	@Autowired
	private StatusRepository taskRepository;
	
	@RequestMapping("/")
	public ModelAndView dashboard(HttpServletRequest request) {
		ModelAndView result = new ModelAndView();
		
		List<Status> games = taskRepository.findAll();
		
		result.addObject("name", "Tim");
		result.addObject("games", games);
		result.setViewName("home");
		
		return result;
	}
	
}
