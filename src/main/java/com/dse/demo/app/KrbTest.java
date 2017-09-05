package com.dse.demo.app;
import com.datastax.driver.dse.auth.*;
import com.datastax.driver.core.*;

public class KrbTest {

	public static void main(String[] args) {

		String node = "10.0.0.252";
		String sasl = "dse";
		String krbclient = "./DseClient.config";

		Cluster cluster;
		Session session;

		// Connect to the cluster and keyspace "test"
		System.setProperty("java.security.auth.login.config",krbclient);    
		cluster = Cluster.builder().addContactPoint(node).withAuthProvider(new DseGSSAPIAuthProvider(sasl)).build();
		session = cluster.connect();

		// Create keyspace and table
		session.execute("CREATE KEYSPACE IF NOT EXISTS krbtest WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 1};");
		session.execute("CREATE TABLE IF NOT EXISTS krbtest.users (email text PRIMARY KEY, age int, city text, firstname text, lastname text);");

		// Insert one record into the users table
		session.execute("INSERT INTO krbtest.users (lastname, age, city, email, firstname) VALUES ('Jones', 35, 'Austin', 'bob@example.com', 'Bob')");

		// Select from table
		ResultSet results = session.execute("SELECT * FROM krbtest.users");
		for (Row row : results) {
			System.out.format("%s %s %s %s %d\n", row.getString("firstname"), row.getString("lastname"), row.getString("city"), row.getString("email"), row.getInt("age"));
		}

		// Clean up the connection by closing it
	    cluster.close();
	}
}
