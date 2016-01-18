
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Brian on 1/17/2016.
 */
public class DatabaseServer extends UnicastRemoteObject implements DatabaseService{

    //database object
    DatabaseStructure database;

    //database server constructor
    public DatabaseServer() throws RemoteException{
        database = new DatabaseStructure();
    }

    //synchronized call to get the number of lines
    @Override
    public int getSize(){
        return database.getSize();
    }

    //synchronized call to return a line from a data structure
    @Override
    public String readLine(int index) throws RemoteException {
        synchronized(this){
            return database.getLine(index);
        }
    }

    //synchronized call to update a line in the database structure
    @Override
    public void updateLine(int index, String newLine) throws RemoteException {
        synchronized (this){
            database.updateLine(index, newLine);
        }
    }

    //synchronized call to get full string listing of data
    @Override
    public String listDatabase() throws RemoteException {
        synchronized (this) {
            return database.getList();
        }
    }

    //call to end server
    @Override
    public void exit() throws RemoteException {

    }

    //main method for running server
    public static void main(String args[]) throws Exception{

    }
}
