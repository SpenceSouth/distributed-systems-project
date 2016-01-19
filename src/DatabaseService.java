import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Brian on 1/17/2016.
 */
public interface DatabaseService extends Remote {

    int getSize();
    String readLine(int index) throws RemoteException;
    void updateLine(int index, String newLine)throws RemoteException;
    String listDatabase() throws RemoteException;
    void exit() throws RemoteException;

}
