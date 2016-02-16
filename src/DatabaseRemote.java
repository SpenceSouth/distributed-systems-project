import java.sql.*;
import java.util.Date;
import java.rmi.*;
import java.rmi.server.*;

/**
 * Created by spencesouthard on 2/5/16.
 */
public class DatabaseRemote extends UnicastRemoteObject implements Database{

    private final String url = "jdbc:mysql://localhost:3306/distributed";
    private final String user = "brian";
    private final String password = "maclab";

    DatabaseRemote() throws RemoteException{
      super();
    }

    public int insert(String name, String address) {

        long threadId = Thread.currentThread().getId();
        System.out.println("Thread #" + threadId + " executing insert task");

        int result = -1;
        Connection connection = null;
        PreparedStatement ps = null;

        String query = "INSERT INTO Sample VALUES ('" + name + "', '" + address + "')";

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }

        try {
            //System.out.println("Establishing connection");
            connection = DriverManager.getConnection(url, "brian", "maclab");

            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result=rs.getInt(1);
            }
            else{
                // System.out.println("No Generated Keys Returned");
            }
        }
        catch(SQLException sqlex){
            System.out.println("\nInsert Once:\t" + query + "\n");
            sqlex.printStackTrace();
        }
        finally{
            // System.out.println("Closing database connection");
            if(connection != null){
                try{
                    connection.close();
                    ps.close();
                }
                catch(SQLException ex){
                    System.out.println("Error closing connection");
                }
            }
        }

        return result;

    }

    public String read(int id){

        long threadId = Thread.currentThread().getId();
        System.out.println("Thread #" + threadId + " executing read task");


        String result = "";
        Connection connection = null;
        Statement statement = null;
        String query = "SELECT * FROM Sample WHERE id = " + id;

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }

        try {
            //System.out.println("Establishing connection");
            connection = DriverManager.getConnection(url, "brian", "maclab");

            statement = connection.createStatement();
            ResultSet r = statement.executeQuery(query);
            if(r.next()) {
                result = Integer.toString(r.getInt("id"));
                result += "\t";
                result += r.getString("name");
                result += "\t";
                result += r.getString("address");
            }
            try{
                statement.close();
            }
            catch(SQLException ex){
                System.out.println("Error closing connection");
            }
        }
        catch(SQLException sqlex){
            System.out.println("Get UUID:\t\t" + query);
            sqlex.printStackTrace();
        }
        finally{
            // System.out.println("Closing database connection");
            if(connection != null){
                try{
                    connection.close();
                }
                catch(SQLException ex){
                    System.out.println("Error closing connection");
                }
            }
        }

        return result;
    }

    public void update(int id, String name, String address){

        long threadId = Thread.currentThread().getId();
        System.out.println("Thread #" + threadId + " executing update task");


        int result = -1;
        Connection connection = null;
        PreparedStatement ps = null;

        String query = "UPDATE Sample Set name = '" + name + "', address='" + address + "' WHERE id = " + id;

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }

        try {
            //System.out.println("Establishing connection");
            connection = DriverManager.getConnection(url, user, password);

            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result=rs.getInt(1);
            }
            else{
                // System.out.println("No Generated Keys Returned");
            }
        }
        catch(SQLException sqlex){
            System.out.println("\nInsert Once:\t" + query + "\n");
            sqlex.printStackTrace();
        }
        finally{
            // System.out.println("Closing database connection");
            if(connection != null){
                try{
                    connection.close();
                    ps.close();
                }
                catch(SQLException ex){
                    System.out.println("Error closing connection");
                }
            }
        }
    }

    public String list(){

        long threadId = Thread.currentThread().getId();
        System.out.println("Thread #" + threadId + " executing list task");


        String result = "";
        Connection connection = null;
        Statement statement = null;
        String query = "SELECT * FROM Sample";

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
            System.exit(1);
        }

        try {
            //System.out.println("Establishing connection");
            connection = DriverManager.getConnection(url, "brian", "maclab");

            statement = connection.createStatement();
            ResultSet r = statement.executeQuery(query);
            while(r.next()) {
                result += Integer.toString(r.getInt("id"));
                result += "\t";
                result += r.getString("name");
                result += "\t";
                result += r.getString("address");
                result += "\n";
            }
            try{
                statement.close();
            }
            catch(SQLException ex){
                System.out.println("Error closing connection");
            }
        }
        catch(SQLException sqlex){
            System.out.println("Get UUID:\t\t" + query);
            sqlex.printStackTrace();
        }
        finally{
            // System.out.println("Closing database connection");
            if(connection != null){
                try{
                    connection.close();
                }
                catch(SQLException ex){
                    System.out.println("Error closing connection");
                }
            }
        }

        return result;

    }

}
