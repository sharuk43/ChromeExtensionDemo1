package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource(name = "app-properties", value = { "classpath:dbservice.properties" }, ignoreResourceNotFound = true)
public class DBService {
	

	@Value("${db.name}")
	String dbName;

	@Value("${db.user}")
	String userName;

	@Value("${db.password}")
	String password;

	@Value("${db.table}")
	String TABLE;

	public void insertRow(String textToBeInserted) throws Exception {
		Connection connection = null;
		Statement statement;
		String insertRecordQuery = "insert into " + TABLE + " values('"
				+ textToBeInserted + "')";
//		initVariables();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager
					.getConnection(dbName, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (connection != null) {
			System.out.println("Successfullly connected to Oracle DB");
			try {
				statement = connection.createStatement();

				System.out.println("Insert Record Query :" + insertRecordQuery);
				statement.execute(insertRecordQuery);
				System.out.println("Record Inserted Successfully");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Failed to connect to Oracle DB");
		}
		
	}
}
