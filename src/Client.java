import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * Created by Brian on 1/19/2016.
 */
public class Client {

    private UserMenu userMenu;


    public Client(){
        //initialize user menu object and url object
        userMenu = new UserMenu();

    }
    public void runClient(DatabaseService dbs){
        while(true){

            //print menu then get user input
            userMenu.printMenu();
            int input = userMenu.getMenuInput();

            switch(input){
                case 1: //Read Command Chosen

                    int dbSize = dbs.getSize();     //get the database size
                    userMenu.getTableID(dbSize);    //get address id from input



                    break;
                case 2: //Update Command Chosen
                    break;
                case 3: //List Command Chosen
                    break;
                case 4: //Exit Command Chosen
                    break;

            }

            //print leaving message and exit system
            System.out.println("Exiting Client....");
            System.exit(0);
        }
    }

    public static void main(String argsp[]){
        Client c = new Client();
        try {
            //set RMI security manager
            System.setSecurityManager(new RMISecurityManager());

            //lookup and init Database
            DatabaseService dbs = (DatabaseService) Naming.lookup("http://blah:" + "database");

            //run main loop
            c.runClient(dbs);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
