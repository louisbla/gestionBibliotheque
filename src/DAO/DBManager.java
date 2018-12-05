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
        	System.out.println("Database is connected");
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
        } finally {
        	System.out.println("Database is disconnected");
        }
    }

    //Ajoute un utilisateur
    public static void addUser(String code, String firstname, String name, String password, int pay) {
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
    public static ResultSet getAllUser() {
    	try {
    		try {
				DBManager.connectDataBase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statement = connect.createStatement();
			resultSet = statement
			          .executeQuery("SELECT * FROM utilisateur");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
    }

    //R�cup�re les livres dans un ResultSet
    public static ResultSet getAllBook() {
    	try {
    		try {
				DBManager.connectDataBase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM livre");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
    }

    public static ResultSet searchBook(String id, String title, String author, String isbn) {
    	String requestId = "id_livre=?";
    	String requestTitle = "titre=?";
    	String requestAuthor = "auteur=?";
    	String requestIsbn = "isbn=?";
    	String request = "SELECT * FROM livre WHERE ";
    	boolean isRequestId = false;
    	boolean isrequestTitle = false;
    	boolean isrequestAuthor = false;
    	boolean isrequestIsbn = false;
    	int index = 1;

    	if (!id.equals("")) {
    		request = request + requestId;
    		isRequestId = true;
    	}
    	if (!title.equals("") && !id.equals("")) {
    		request = request + " AND " + requestTitle;
    		isrequestTitle = true;
    	} else if (!title.equals("")){
    		request = request + requestTitle;
    		isrequestTitle = true;
    	}
    	if (!author.equals("") && (!id.equals("") || !title.equals(""))) {
    		request = request + " AND " + requestAuthor;
    		isrequestAuthor = true;
    	} else if (!author.equals("")){
    		request = request + requestAuthor;
    		isrequestAuthor = true;
    	}
    	if (!isbn.equals("") && (!id.equals("") || !title.equals("") || !author.equals(""))) {
    		request = request + " AND " + requestIsbn;
    		isrequestIsbn = true;
    	} else if (!isbn.equals("")){
    		request = request + requestIsbn;
    		isrequestIsbn = true;
    	}
    	if (isbn.equals("") && id.equals("") && title.equals("") && author.equals("")) {
    		request = "SELECT * FROM livre";
    	}

    	System.out.println("ID :" + isRequestId);
    	System.out.println("AUTEUR :" + isrequestAuthor);
    	System.out.println("TITRE :" + isrequestTitle);
    	System.out.println("ISBN :" + isrequestIsbn);
    	System.out.println(request);

    	try {
    		try {
				DBManager.connectDataBase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		preparedStatement = connect.prepareStatement(request);

    		if (isRequestId) {
    			preparedStatement.setString(index, id);
    			index++;
    		}
    		if (isrequestTitle) {
    			preparedStatement.setString(index, title);
    			index++;
    		}
    		if (isrequestAuthor) {
    			preparedStatement.setString(index, author);
    			index++;
    		}
    		if (isrequestIsbn) {
    			preparedStatement.setString(index, isbn);
    		}

    		System.out.println("INDEX :" + index);
    		resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
    }
}
