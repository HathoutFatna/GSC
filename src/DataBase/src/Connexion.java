package DataBase.src;

import java.sql.*;

public class Connexion {

    private static Connexion connex = null;
    private static Connection conn = null;
    private static Statement stmt = null;

    public static void main(String[] args) {
        Connection cnx= connecterDB();

    }
    public static Connection connecterDB(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver ok");
            String url="jdbc:mysql://localhost/BDD";
            String user="root";
            String password="";
            Connection cnx=DriverManager.getConnection(url,user,password);
            System.out.println("connexion bien etablie");
            return cnx;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }
    public static Connexion getInstance() {
        if (connex == null) {
            connex = new Connexion();
        }
        return connex;
    }


}

