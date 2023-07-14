package io;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

public class Jdbc {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/java-cert-db?user=javaCertUser&password=Jc11user.getPlainPwd();";

	public static void main(String[] args) {

		// JDBC = an API that provides interfaces to interact with database defined in java.sql
				//	each database provider, provides a JDBC driver with a specific implementation of the JDBC API
				//	jdbc:provider://host:port/dbName   

		// JDBC Interfaces (under java.sql.*)
			// DriverManager, use DataSource for real life apps(maintains a connection pool)
			// Connection = a session to sends commands to the db
			// Statement = basic sql query, has to be parsed an compilaed before every
			// execution and risk of sql injection
			// PreparedStatement = executes sql queries, (pre-compiled sql statements)
			// CallableStatement = executes sql procedures stored in db
			// ResultSet = reads results of a query

		//TODO : 
			//SetParams, executeStatment stmts order, error detection if placed outside/inside scope
			//setNull param : what happens ?
			//Connect to and perform SQL operations, process results

		try (Connection connection = DriverManager.getConnection(DB_URL)) {
			connectAndInsert(connection);
			performSQLOperations(connection);
			transactions(connection);
			metadata(connection);
		} catch (SQLException e) {
			System.err.println(
					"Database communication error :  " + e.getMessage() + ", error code : " + e.getErrorCode());
		}
	}

	private static void connectAndInsert(Connection connection) {

		final String insertQuery = "INSERT INTO products (name, price) values (?,?)";

		try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {
			ps.setString(1, "iMac");
			ps.setDouble(2, 2699);
			int rowCount = ps.executeUpdate();
			System.out.println("Rows inserted : " + rowCount);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void performSQLOperations(Connection connection) {

		// use PreparedStatement.executeQuery || execute() only for "Select"
		// executeUpdate for the rest queries
			// ResultSet rs = ps.executeQuery();
			// ResultSet rs = ps.execute();
			// int rowCount = ps.executeUpdate();

		final String selectQuery = "SELECT * FROM products";

		try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {

			ResultSet rs = ps.executeQuery();
			List<String> products = new ArrayList<>();

			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				double price = rs.getDouble("price");
				products.add("Product id : " + id + ", " + name + " ==> $" + price);
			}
			System.out.println(products.toString());
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	private static void transactions(Connection connection) {

		// by default JDBC is auto-commit mode (all db transactions are commited)
		final String insertQuery = "INSERT INTO products (name, price) values (?,?)";
		final String updateQuery = "UPDATE products SET price = ? Where name = ?";
		
		Savepoint insertSavepoint = null;
		
		try (PreparedStatement psInsert = connection.prepareStatement(insertQuery);
				PreparedStatement psUpdate = connection.prepareStatement(updateQuery)) {

			// disable auto commit mode
			connection.setAutoCommit(false);

			psInsert.setString(1, "Mac mini");
			psInsert.setDouble(2, 999);
			int rowCount = psInsert.executeUpdate();

			//Save point that will be used if second statement fails
			insertSavepoint = connection.setSavepoint("insertSavepoint");

			psUpdate.setDouble(1, 599);
			psUpdate.setString(2, "iPad");
			rowCount += psUpdate.executeUpdate();

			// autoCommit mode is enabled, no statement is commited until done manually
			connection.commit();

			System.out.println("Rows altered : " + rowCount);
		} catch (SQLException e) {
			try {
				System.err.println("Transaction is being rolled back : " + e.getMessage());
				connection.rollback(insertSavepoint);
			} catch (SQLException transactionEx) {
				System.err.println(transactionEx.getMessage());
			}
		}
	}

	private static void metadata(Connection connection) {

		try {
			DatabaseMetaData md = connection.getMetaData();

			final double maxRowSizeGb = md.getMaxRowSize() / 1024 / 1024 / 1024;

			System.out.println("MySQL database MetaData : ");
			System.out.println("	URL = " + md.getURL());
			System.out.println("	DriverName = " + md.getDriverName());
			System.out.println("	Supported SQL keywords = " + md.getSQLKeywords());
			System.out.println("	Supported system functions = " + md.getSystemFunctions());
			System.out.println("	Current connected user   = " + md.getUserName());
			System.out.println("	Max bytes in a single row  = " + maxRowSizeGb + " GB");
			System.out.println("	Result of isNull(null + nonNull)  = " + md.nullPlusNonNullIsNull());

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}
}