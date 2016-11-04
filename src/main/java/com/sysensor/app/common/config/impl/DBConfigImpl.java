package com.sysensor.app.common.config.impl;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import com.sysensor.app.common.config.DBConfig;
import com.sysensor.app.common.config.model.DBContext;
import com.sysensor.app.common.config.model.DBContext.DBContextBuilder;

/**
 * 
 * Distribution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 25, 2016 7:59:40 AM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
public class DBConfigImpl implements DBConfig {

	private Logger log = Logger.getLogger(getClass().getName());

	private final String DATASOURCE_URL;
	private final String DATABASE_USERNAME;
	private final String DATABASE_PASSWORD;
	private DBContext dbContext;

	private static DBConfig dbConfig;

	public static DBConfig getInstance() {
		if (dbConfig != null) {
			return dbConfig;
		} else {
			return new DBConfigImpl();
		}
	}

	private DBConfigImpl() {
		DATASOURCE_URL = System.getProperty("DATASOURCE_URL");
		DATABASE_USERNAME = System.getProperty("DATABASE_USERNAME");
		DATABASE_PASSWORD = System.getProperty("DATABASE_PASSWORD");

		dbContext = DBContextBuilder.getInstance().dataSourceURL(DATASOURCE_URL).userName(DATABASE_USERNAME)
				.passWord(DATABASE_PASSWORD).build();
	}

	public DBContext getDBContext() {
		return dbContext;
	}

	public DataSource dataSource() {
		log.info("[START:Set DataSource]");
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl(DATASOURCE_URL);
		basicDataSource.setUsername(DATABASE_USERNAME);
		basicDataSource.setPassword(DATABASE_PASSWORD);
		log.info("[END:Set DataSource]");
		return basicDataSource;
	}

}
