package biz.heiges.java.h2;
/*
 * Copyright 2004-2019 H2 Group. Multiple-Licensed under the MPL 2.0,
 * and the EPL 1.0 (https://h2database.com/html/license.html).
 * Initial Developer: H2 Group
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.h2.tools.DeleteDbFiles;

/**
 * A very simple class that shows how to load the driver, create a database,
 * create a table, and insert some data.
 */
public class H2DB {

	/**
	 * Called when ran from command line.
	 *
	 * @param args ignored
	 */
	public static void main(String... args) throws Exception {
		// delete the database named 'test' in the user home directory
		DeleteDbFiles.execute("~", "test", true);

		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:~/test");
		Statement stat = conn.createStatement();

		stat.execute("runscript from 'classpath:/sql/init.sql'");

		ResultSet rs;
		rs = stat.executeQuery("select * from person");
		while (rs.next()) {
			System.out.println(rs.getString("surname") + " " +  rs.getString("familyname"));
		}
		stat.close();
		conn.close();
	}

}