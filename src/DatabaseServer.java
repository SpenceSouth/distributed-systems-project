import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

/**
 * Created by Brian on 1/19/2016.
 */
public class DatabaseServer {
    //Actual Server running
    //All calls will be processed from clients and run on database object.

    private DatabaseImpl database;


    public DatabaseServer(){
        initServer();
        runServer();
        closeServer();
    }

    public void initServer(){
        try{
            //initialize DatabaseImpl object with new Database Structure
            this.database = new DatabaseImpl(new DatabaseStructure());
        }catch(RemoteException ex){ex.printStackTrace();}
    }

    public void runServer(){
        try {
            //install a security manager
            System.setSecurityManager(new RMISecurityManager());


            //register it with the local naming registry
            Naming.rebind("Datatbase", database);
            System.out.println("Registered database");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void closeServer(){
        //exiting server
    }

    public static void main(String args[]){
        DatabaseServer server = new DatabaseServer();
    }
}
