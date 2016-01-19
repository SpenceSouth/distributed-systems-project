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
        while(true){
            //main processing loop for handling clients
        }
    }

    public void closeServer(){
        //exiting server
    }

    public static void main(String args[]){
        DatabaseServer server = new DatabaseServer();
    }
}
