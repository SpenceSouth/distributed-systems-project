import java.rmi.*;

public class Client {
    public static void main(String args[]){

        try{

            Database database = (Database)Naming.lookup("rmi://10.0.1.2:7499/database");
            System.out.println(database.list());

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    } //End of main

} //End of class
