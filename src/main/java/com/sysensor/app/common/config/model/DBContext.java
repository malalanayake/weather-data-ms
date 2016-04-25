package com.sysensor.app.common.config.model;

/**
 * 
 * Distibution under GNU GENERAL PUBLIC LICENSE Version 2, June 1991
 * 
 * @author dmalalan
 * @created Apr 22, 2016 4:25:46 PM
 * 
 * @blog https://malalanayake.wordpress.com/
 */
public class DBContext {
	public final String DATASOURCE_URL;
	public final String DATABASE_USERNAME;
	public final String DATABASE_PASSWORD;

	private DBContext(DBContextBuilder dbContextBuilder) {
		this.DATASOURCE_URL = dbContextBuilder.B_DATASOURCE_URL;
		this.DATABASE_USERNAME = dbContextBuilder.B_DATABASE_USERNAME;
		this.DATABASE_PASSWORD = dbContextBuilder.B_DATABASE_PASSWORD;
	}

	public static class DBContextBuilder {
		private String B_DATASOURCE_URL;
		private String B_DATABASE_USERNAME;
		private String B_DATABASE_PASSWORD;

		private static DBContextBuilder dbContextBuilder = null;

		public static DBContextBuilder getInstance() {
			if (dbContextBuilder != null) {
				return dbContextBuilder;
			} else {
				return new DBContextBuilder();
			}
		}

		private DBContextBuilder() {
		}

		public DBContextBuilder dataSourceURL(String dataSourceURL) {
			this.B_DATASOURCE_URL = dataSourceURL;
			return this;
		}

		public DBContextBuilder userName(String userName) {
			this.B_DATABASE_USERNAME = userName;
			return this;
		}

		public DBContextBuilder passWord(String passWord) {
			this.B_DATABASE_PASSWORD = passWord;
			return this;
		}

		public DBContext build() {
			return new DBContext(this);
		}
	}
}
