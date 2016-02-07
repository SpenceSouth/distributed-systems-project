import java.rmi.*;
import java.rmi.registry.*;

public class Server {

    public static void main(String args[]){
        try{
            System.out.println("New database");
            Database database = new DatabaseRemote();
            System.out.println("Binding");
            Naming.rebind("rmi://localhost:7499/database",database);
            System.out.println("Done");

        }

        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    } // end of main
}
