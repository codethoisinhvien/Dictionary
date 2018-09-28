package dictionary;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javazoom.jl.decoder.JavaLayerException;

public class Test {
 
	public static void main(String[] args) {
		try {
			MySQLDatabase a = MySQLDatabase.getInstance("jdbc:mysql://localhost:3306/entries", "test", "12345678" );
			a.insertWord("giang", "coder", "hhhh");
			a.insertWord("giang", "coder", "hhhh");
			a.updatetWord("giang", "wordtype", "bad");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
