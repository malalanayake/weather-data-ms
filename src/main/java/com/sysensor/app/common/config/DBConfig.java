package com.sysensor.app.common.config;

import javax.sql.DataSource;

import com.sysensor.app.common.config.model.DBContext;

/**
 * 
 * Distibution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 22, 2016 4:57:10 PM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
public interface DBConfig {

	public DBContext getDBContext();

	public DataSource dataSource();
	
}
