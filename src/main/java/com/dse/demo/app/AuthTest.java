package com.dse.demo.app;
import com.datastax.driver.dse.auth.*;
import com.datastax.driver.core.*;

public class AuthTest {

	public static void main(String[] args) {

		String node = "jphmac";
		String username = "cassandra";
		String upass = "Lucas612";

		Cluster cluster;
		Session session;

		// Connect to the cluster and keyspace "authtest"
		cluster = Cluster.builder().addContactPoint(node).withCredentials(username,upass).build();
		session = cluster.connect();

		// Create keyspace and table
		session.execute("CREATE KEYSPACE IF NOT EXISTS authtest WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};");
		session.execute("CREATE TABLE IF NOT EXISTS authtest.users (email text PRIMARY KEY, age int, city text, firstname text, lastname text);");

		// Insert one record into the users table
		session.execute("INSERT INTO authtest.users (lastname, age, city, email, firstname) VALUES ('Jones', 35, 'Austin', 'bob@example.com', 'Bob')");

		// Select from table
		ResultSet results = session.execute("SELECT * FROM authtest.users");
		for (Row row : results) {
			System.out.format("%s %s %s %s %d\n", row.getString("firstname"), row.getString("lastname"), row.getString("city"), row.getString("email"), row.getInt("age"));
		}

		// Clean up the connection by closing it
	    cluster.close();
	}
}
