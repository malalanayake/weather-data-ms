package com.sysensor.app.common.messaging.model;

import java.io.Serializable;

/**
 * 
 * Distibution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 25, 2016 8:00:28 AM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
public class SYSContainer implements Serializable {

	private static final long serialVersionUID = 1L;

	private static SYSContainer sysContainer = null;
	private String message;

	private SYSContainer() {

	}

	public static SYSContainer getInstance() {
		if (sysContainer != null) {
			return sysContainer;
		} else {
			return new SYSContainer();
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
