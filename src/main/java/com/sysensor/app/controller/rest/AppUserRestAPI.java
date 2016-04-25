package com.sysensor.app.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.sysensor.app.controller.json.JsonViews;
import com.sysensor.app.model.AppUser;

/**
 * 
 * Distibution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 18, 2016 1:04:29 PM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
@Controller
@RequestMapping("/appuser")
public class AppUserRestAPI {

	@JsonView(JsonViews.AppUser.class)
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody AppUser index(ModelMap model) {
		AppUser appUser = new AppUser();
		appUser.setUserName("Test");
		appUser.setPassWord("ddd");
		return appUser;
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET)
	public ResponseEntity<MappingJacksonValue> getEmployee(@PathVariable long employeeId) {
		AppUser appUser = new AppUser();
		appUser.setUserName("Test");
		appUser.setPassWord("ddd");
		MappingJacksonValue jacksonValue = new MappingJacksonValue(appUser);

		jacksonValue.setSerializationView(JsonViews.Unauthorized.class);
		return new ResponseEntity<>(jacksonValue, HttpStatus.OK);
	}
}
