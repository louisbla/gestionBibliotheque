package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class DBManager {

	private static Connection connect = null;
	private static Statement statement = null;
	private static PreparedStatement preparedStatement = null;
	private static ResultSet resultSet = null;

    public DBManager() {

    }

    public static Connection connectDataBase() throws Exception {
    	try {
    		Class.forName("com.mysql.jdbc.Driver");

        	connect = DriverManager
        	          .getConnection("jdbc:mysql://localhost:3306/bibliotheque2?autoReconnect=true&useSSL=false","root", "");
        	System.out.println("Database is connected !");
    	} catch (Exception e) {
    		e.printStackTrace();
        }
		return connect;
    }

    public static void closeDatabase() {
    	try {
    		if (resultSet != null) {
    			resultSet.close();
    		}
    		if (statement != null) {
    			statement.close();
    		}
    		if (connect != null) {
    			connect.close();
    		}
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    //Ajoute un utilisateur
    public void addUser(String code, String firstname, String name, String password, int pay) {
    	try {
			preparedStatement = connect
			          .prepareStatement("INSERT INTO utilisateur VALUES (?, ?, ?, ?, ?)");
			preparedStatement.setString(1, code);
		    preparedStatement.setString(2, firstname);
		    preparedStatement.setString(3, name);
		    preparedStatement.setString(4, password);
		    preparedStatement.setInt(5, pay);
		    preparedStatement.executeUpdate();
		    System.out.println("Utilisateur ajoute");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //Ajoute un livre
    public static void addBook(String isbn, String author, String title, int available) {
    	try {
			preparedStatement = connect
			          .prepareStatement("INSERT INTO livre VALUES (NULL, ?, ?, ?, ?)");
		    preparedStatement.setString(1, isbn);
		    preparedStatement.setString(2, author);
		    preparedStatement.setString(3, title);
		    preparedStatement.setInt(4, available);
		    preparedStatement.executeUpdate();
		    System.out.println(preparedStatement.toString());
		    System.out.println("Livre ajoute");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //Supprime un livre
    public static void deleteBook(int id) {
    	try {
			preparedStatement = connect
				      .prepareStatement("DELETE FROM livre WHERE id_livre= ?;");
			preparedStatement.setInt(1, id);
		    preparedStatement.executeUpdate();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //R�cup�re les utilisateur dans un ResultSet
    public ResultSet getAllUser() {
    	try {
			statement = connect.createStatement();
			resultSet = statement
			          .executeQuery("SELECT * FROM utilisateur");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
    }
    
    public ResultSet getBookByKeyword(String keyword) {
    	try {
			statement = connect.createStatement();
			resultSet = statement
			          .executeQuery("SELECT * FROM livre WHERE auteur LIKE \"%?%\" OR titre LIKE \"%?%\"");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
    }

    //R�cup�re les livres dans un ResultSet
    public ResultSet getAllBook() {
    	try {
			statement = connect.createStatement();
			resultSet = statement
			          .executeQuery("SELECT * FROM livre");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
    }
}
