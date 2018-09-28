package dictionary;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLDatabase {
	private static  MySQLDatabase mySQL = null;
	private  String connectionURL;
	private  String userName;
	private  String password;
	private Connection connectDatabase = null;
	private  MySQLDatabase(String connectionURL, String userName,String password) throws SQLException {
		this.connectionURL=connectionURL;
		this.userName = userName;
		this.password = password;
		
	}
	public synchronized static MySQLDatabase getInstance(String connectionURL, String userName,String password) throws SQLException {

		if (mySQL== null) {
			mySQL  = new MySQLDatabase(connectionURL, userName,password);
		}
		return mySQL;
	}
	//Singleton parttern
	
	public void selectWord(String word) throws SQLException {
		 connectDatabase = DriverManager.getConnection(connectionURL, userName,password);
		Statement stmt = connectDatabase.createStatement();
	    ResultSet rs = stmt.executeQuery("SELECT * FROM entries WHERE word='"+word+"';");// cau lenh try van 
	    ResultSetMetaData rsmd = rs.getMetaData();
	    for (int i = 1; i <= rsmd.getColumnCount(); i++) {
	        System.out.println(
	            "Column " + i + " [name: " + rsmd.getColumnName(i) + " - type: " + rsmd.getColumnTypeName(i) + "]");
	      }
	      System.out.println("--------------------");
	      while (rs.next())
	        System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3));
	      connectDatabase.close();
	    }
	public void insertWord(String word,String wordtype,String definition) throws SQLException {
		 connectDatabase = DriverManager.getConnection(connectionURL, userName,password);
		Statement stmt = connectDatabase.createStatement();
	    Integer rs = stmt.executeUpdate("INSERT INTO entries VALUES ('"+word+"', '"+wordtype+"','"+definition+"');");// cau lenh try van 
	    System.out.println(rs);
	      connectDatabase.close();
	}
	public void detetetWord(String word) throws SQLException {
		 connectDatabase = DriverManager.getConnection(connectionURL, userName,password);
		Statement stmt = connectDatabase.createStatement();
	    int rs = stmt.executeUpdate("DELETE FROM entries WHERE word='"+word+"';");// cau lenh try van 
	    System.out.println(rs);
	      connectDatabase.close();
	}
	public void updatetWord(String word, String col,String change) throws SQLException {
		connectDatabase = DriverManager.getConnection(connectionURL, userName,password);
		Statement stmt = connectDatabase.createStatement();
	System.out.println("UPDATE entries SET "+ col+" = '"+change+"'WHERE word='"+word+"';");
	    int rs = stmt.executeUpdate("UPDATE entries SET "+ col+" = '"+change+"'WHERE word='"+word+"';");// cau lenh try van 
	    System.out.println(rs);
	      connectDatabase.close();
	}
}
