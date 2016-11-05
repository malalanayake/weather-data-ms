package com.sysensor.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sysensor.app.config.ContextConfig;
import com.sysensor.app.model.AppUser;
import com.sysensor.app.repository.AppUserRepository;
import com.sysensor.app.service.messaging.LogMessageService;

/**
 * 
 * Distribution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author malalanayake
 * @created Nov 8, 2015 7:34:51 PM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
@Controller
@RequestMapping("/test")
public class WeatherDataController {

	private AppUserRepository repository;

	@Autowired
	private ContextConfig contextConfig;
	@Autowired
	private LogMessageService logMessageService;

	@Autowired
	public WeatherDataController(AppUserRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(ModelMap model) {
		model.addAttribute("API_KEY", contextConfig.GOOGLE_API_KEY);
		return "weather";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String insertData(ModelMap model, @ModelAttribute("appUserNew") @Valid AppUser record, BindingResult result) {
		if (!result.hasErrors()) {
			repository.save(record);
		}

		return list(model);
	}
}
