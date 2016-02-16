import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;

public class Server{

    public static void main(String args[]){
        try{

            System.out.println("New Stub");
            Database stub=new DatabaseRemote();
            System.out.println("Binding");
            //System.setProperty("java.rmi.server.hostname","192.34.59.109");
            Naming.rebind("rmi://192.34.59.109:7499/database", stub);
            System.out.println("Done");

        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    } // end of main
}