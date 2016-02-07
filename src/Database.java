import java.rmi.*;

/**
 * Created by spencesouthard on 2/5/16.
 */
public interface Database extends Remote {

    public int insert(String name, String address) throws RemoteException;

    public String read(int id) throws RemoteException;

    public void update(int id, String name, String address) throws RemoteException;

    public String list() throws RemoteException;

}
