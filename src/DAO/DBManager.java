package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import user.Droit;
import user.Utilisateur;

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
    public static void addUser(String code, String firstname, String name, String droit, String password, int pay) {
    	try {
			preparedStatement = connect
			          .prepareStatement("INSERT INTO utilisateur VALUES (?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, code);
		    preparedStatement.setString(2, firstname);
		    preparedStatement.setString(3, name);
		    preparedStatement.setString(4, droit);
		    preparedStatement.setString(5, password);
		    preparedStatement.setInt(6, pay);
		    preparedStatement.executeUpdate();
		    System.out.println("Utilisateur ajoute");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //Ajoute un livre
    public static void addBook(String isbn, String author, String title, String type, int available, String resume) {
    	try {
			preparedStatement = connect
			          .prepareStatement("INSERT INTO oeuvre VALUES (NULL, ?, ?, ?, ?, ?, ?)");
		    preparedStatement.setString(1, isbn);
		    preparedStatement.setString(2, author);
		    preparedStatement.setString(3, title);
		    preparedStatement.setString(4, type);
		    preparedStatement.setInt(5, available);
		    preparedStatement.setString(6, resume);
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
				      .prepareStatement("DELETE FROM oeuvre WHERE id_oeuvre= ?;");
			preparedStatement.setInt(1, id);
		    preparedStatement.executeUpdate();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

  //Supprime un utilisateur
    public static void deleteUser(String code) {
    	try {
			preparedStatement = connect
				      .prepareStatement("DELETE FROM utilisateur WHERE codePermanent= ?;");
			preparedStatement.setString(1, code);
		    preparedStatement.executeUpdate();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

  //Ajoute une salle
    public static void addRoom(String number, int size, int table, int projector, int available) {
    	try {
			preparedStatement = connect
			          .prepareStatement("INSERT INTO salle VALUES (?, ?, ?, ?, ?)");
		    preparedStatement.setString(1, number);
		    preparedStatement.setInt(2, size);
		    preparedStatement.setInt(3, table);
		    preparedStatement.setInt(4, projector);
		    preparedStatement.setInt(5, available);
		    preparedStatement.executeUpdate();
		    System.out.println("Salle ajoutee");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public static void deleteRoom(String id) {
    	try {
			preparedStatement = connect
				      .prepareStatement("DELETE FROM salle WHERE numero_salle= ?;");
			preparedStatement.setString(1, id);
		    preparedStatement.executeUpdate();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //----------------------------------------GET ALL USER------------------------------
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

    //----------------------------------------GET ALL BOOK------------------------------
    public static ResultSet getAllBook() {
    	try {
    		try {
				DBManager.connectDataBase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM oeuvre");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
    }

    public static ResultSet getAllRoom() {
    	try {
    		try {
				DBManager.connectDataBase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statement = connect.createStatement();
			resultSet = statement
			          .executeQuery("SELECT * FROM salle");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
    }

    public static ResultSet searchRoom(String size, String table, String projector) {
    	String tableString = "";
    	if (table == "Oui") {
    		tableString = "1";
    	} else if (table == "Non"){
    		tableString = "0";
    	}

    	String projectorString = "";
    	if (projector == "Oui") {
    		projectorString = "1";
    	} else if (projector == "Non"){
    		projectorString = "0";
    	}

    	String requestSize = "nb_place=?";
    	String requestTable = "have_tableau=?";
    	String requestProjector = "have_projecteur=?";
    	String request = "SELECT * FROM salle WHERE ";

    	boolean isrequestSize = false;
    	boolean isrequestTable = false;
    	boolean isrequestProjector = false;

    	int index = 1;

    	if (!size.equals("")){
    		request = request + requestSize;
    		isrequestSize = true;
    	}
    	if (!table.equals("") && !size.equals("")) {
    		request = request + " AND " + requestTable;
    		isrequestTable = true;
    	} else if (!table.equals("")){
    		request = request + requestTable;
    		isrequestTable = true;
    	}
    	if (!projector.equals("") && (!table.equals("") || !size.equals(""))) {
    		request = request + " AND " + requestProjector;
    		isrequestProjector = true;
    	} else if (!projector.equals("")){
    		request = request + requestProjector;
    		isrequestProjector = true;
    	}
    	if (projector.equals("") && table.equals("") && size.equals("")) {
    		request = "SELECT * FROM salle";
    	}

    	System.out.println(request);

    	try {
    		try {
				connectDataBase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		preparedStatement = connect.prepareStatement(request);

    		if (isrequestSize) {
    			preparedStatement.setString(index, size);
    			index++;
    		}
    		if (isrequestTable) {
    			preparedStatement.setString(index, tableString);
    			index++;
    			System.out.println("table : " + tableString);
    		}
    		if (isrequestProjector) {
    			preparedStatement.setString(index, projectorString);
    			System.out.println("projector : " + projectorString);
    		}

    		resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
    }

    public static ResultSet searchBook(String title, String author, String isbn, String type, String keyword) {
    	String requestTitle = "titre=?";
    	String requestAuthor = "auteur=?";
    	String requestIsbn = "isbn=?";
    	String requestType = "type=?";
    	String requestKeyword = "auteur LIKE \"%" + keyword + "%\" OR titre LIKE \"%" + keyword + "%\"";
    	String request = "SELECT * FROM oeuvre WHERE ";

    	boolean isrequestTitle = false;
    	boolean isrequestAuthor = false;
    	boolean isrequestIsbn = false;
    	boolean isrequestType = false;

    	int index = 1;

    	if (!title.equals("")){
    		request = request + requestTitle;
    		isrequestTitle = true;
    	}
    	if (!author.equals("") && !title.equals("")) {
    		request = request + " AND " + requestAuthor;
    		isrequestAuthor = true;
    	} else if (!author.equals("")){
    		request = request + requestAuthor;
    		isrequestAuthor = true;
    	}
    	if (!isbn.equals("") && (!title.equals("") || !author.equals(""))) {
    		request = request + " AND " + requestIsbn;
    		isrequestIsbn = true;
    	} else if (!isbn.equals("")){
    		request = request + requestIsbn;
    		isrequestIsbn = true;
    	}
    	if (!type.equals("") && (!isbn.equals("") || !title.equals("") || !author.equals(""))) {
    		request = request + " AND " + requestType;
    		isrequestType = true;
    	} else if (!type.equals("")){
    		request = request + requestType;
    		isrequestType = true;
    	}
    	if (!keyword.equals("") && (!type.equals("") || !isbn.equals("") || !title.equals("") || !author.equals(""))) {
    		request = request + " AND " + requestKeyword;
    	} else if (!keyword.equals("")){
    		request = request + requestKeyword;
    	}
    	if (isbn.equals("") && title.equals("") && author.equals("") && type.equals("") && keyword.equals("")) {
    		request = "SELECT * FROM oeuvre";
    	}

    	try {
    		try {
				connectDataBase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		preparedStatement = connect.prepareStatement(request);

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
    			index++;
    		}
    		if (isrequestType) {
    			preparedStatement.setString(index, type);
    		}

    		resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
    }

	public static boolean tryUserPassword(String identifiant, String mdp) {
		try {
			connectDataBase();
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM utilisateur WHERE codePermanent LIKE \""+identifiant+"\" AND password LIKE \""+mdp+"\"");

			if(resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDatabase();
		}
    	return false;
	}

	public static Utilisateur getUser(String identifiant) {
		Utilisateur user = null;
		try {
			connectDataBase();
			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM utilisateur WHERE codePermanent LIKE \""+identifiant+"\"");

			if(resultSet.next()) {
				Droit droit = Droit.valueOf(resultSet.getString("droit"));
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String login = resultSet.getString("codePermanent");
				String mdp = resultSet.getString("password");
				float solde = resultSet.getFloat("solde");

				user = new Utilisateur(droit, nom, prenom, login, mdp, solde);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeDatabase();
		}
    	return user;
	}

	public static ResultSet getEmpruntsOfUser(String identification) {
		try {

			DBManager.connectDataBase();

			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM oeuvre, emprunt "
					+ "WHERE emprunt.codePermanent LIKE \""+identification+"\" "
					+ "AND emprunt.id_livre = oeuvre.id_oeuvre");

		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
	}

	public static int getNbEmpruntsOfUser(String identification) {
		int nbEmprunts = 0;
		try {
			DBManager.connectDataBase();

			statement = connect.createStatement();
			resultSet = statement.executeQuery("SELECT COUNT(*) FROM oeuvre, emprunt "
					+ "WHERE emprunt.codePermanent LIKE \""+identification+"\" "
					+ "AND emprunt.id_livre = oeuvre.id_oeuvre");
			if(resultSet.next()) {
				nbEmprunts = resultSet.getInt(1);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return nbEmprunts;
	}

	///////////////////BDD utilisateur /////////////////////////

	public static ResultSet searchUser(String Nom, String Prenom, String CodePerm, String statut, String keyword) {
    	String requestNom = "nom=?";
    	String requestPrenom = "prenom=?";
    	String requestCodePerm = "codePermanent=?";
    	String requestStatut = "droit=?";
    	String requestKeyword = "nom LIKE \"%" + keyword + "%\" OR prenom LIKE \"%" + keyword + "%\"";
    	String request = "SELECT * FROM utilisateur WHERE ";

    	boolean isrequestTitle = false;
    	boolean isrequestAuthor = false;
    	boolean isrequestIsbn = false;
    	boolean isrequestType = false;

    	int index = 1;

    	if (!Nom.equals("")){
    		request = request + requestNom;
    		isrequestTitle = true;
    	}
    	if (!Prenom.equals("") && !Nom.equals("")) {
    		request = request + " AND " + requestPrenom;
    		isrequestAuthor = true;
    	} else if (!Prenom.equals("")){
    		request = request + requestPrenom;
    		isrequestAuthor = true;
    	}
    	if (!CodePerm.equals("") && (!Nom.equals("") || !Prenom.equals(""))) {
    		request = request + " AND " + requestCodePerm;
    		isrequestIsbn = true;
    	} else if (!CodePerm.equals("")){
    		request = request + requestCodePerm;
    		isrequestIsbn = true;
    	}
    	if (!statut.equals("") && (!CodePerm.equals("") || !Nom.equals("") || !Prenom.equals(""))) {
    		request = request + " AND " + requestStatut;
    		isrequestType = true;
    	} else if (!statut.equals("")){
    		request = request + requestStatut;
    		isrequestType = true;
    	}
    	if (!keyword.equals("") && (!statut.equals("") || !CodePerm.equals("") || !Nom.equals("") || !Prenom.equals(""))) {
    		request = request + " AND " + requestKeyword;
    	} else if (!keyword.equals("")){
    		request = request + requestKeyword;
    	}
    	if (CodePerm.equals("") && Nom.equals("") && Prenom.equals("") && statut.equals("") && keyword.equals("")) {
    		request = "SELECT * FROM utilisateur";
    	}

    	try {
    		try {
				connectDataBase();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		preparedStatement = connect.prepareStatement(request);

    		if (isrequestTitle) {
    			preparedStatement.setString(index, Nom);
    			index++;
    		}
    		if (isrequestAuthor) {
    			preparedStatement.setString(index, Prenom);
    			index++;
    		}
    		if (isrequestIsbn) {
    			preparedStatement.setString(index, CodePerm);
    			index++;
    		}
    		if (isrequestType) {
    			preparedStatement.setString(index, statut);
    		}

    		resultSet = preparedStatement.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return resultSet;
    }

	public static String getResume(int id) {
		String result = "";
		try {
			preparedStatement = connect
				      .prepareStatement("SELECT resume FROM oeuvre WHERE id_oeuvre= ?;");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()){
				result = resultSet.getString("resume");
			}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
