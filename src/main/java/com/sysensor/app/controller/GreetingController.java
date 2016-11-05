package com.sysensor.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sysensor.app.common.messaging.model.SYSContainer;
import com.sysensor.app.config.ContextConfig;
import com.sysensor.app.model.Greeting;

/**
 * 
 * Distribution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Feb 22, 2016 11:40:11 PM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
@Controller
public class GreetingController {

	@Autowired
	private ContextConfig contextConfig;

	@MessageMapping("/hello")
	@SendTo("/topic/SYSENSOR.WEATHER.APP.LOG.TOPIC")
	public Greeting greeting(SYSContainer message) throws Exception {
		return new Greeting("Hello, " + message.getMessage() + "!");
	}

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String list(ModelMap model) {
		model.addAttribute("UI_LOG_TOPIC", contextConfig.LOG_TOPIC_NAME);
		return "greeting";
	}

}
