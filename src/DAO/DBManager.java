package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {

    private static DBManager dbIsntance;
    private static Connection con ;

    private DBManager() {
        // private constructor //
    }

    public static DBManager getInstance(){
        if(dbIsntance==null){
            DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
            dbIsntance= new DBManager();
        }
        return dbIsntance;
    }

    public  Connection getConnection() {

        if (con == null) {
            try {
                String host = "jdbc:derby://localhost:3306/bibliotheque2";
                String username = "root";
                String password = "";
                con = DriverManager.getConnection(host, username, password);
            } catch (SQLException ex) {
                Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return con;
    }
}
