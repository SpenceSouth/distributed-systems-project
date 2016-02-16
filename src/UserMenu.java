import java.util.Scanner;
import java.rmi.*;
import java.net.MalformedURLException;

/**
 * Created by Brian on 1/17/2016.
 */
public class UserMenu {

    private Scanner scannerIn;
    private Database database;

    public UserMenu() throws RemoteException, NotBoundException, MalformedURLException {
        scannerIn = new Scanner(System.in);
        database = (Database)Naming.lookup("rmi://192.34.59.109:7499/database");
    }

    public void printMenu(){
        //print user menu on client side
        System.out.println("=== USER MENU ===");
        System.out.println("1. READ");
        System.out.println("2. UPDATE");
        System.out.println("3. LIST");
        System.out.println("4. QUIT");
    }

    public void read() throws RemoteException {

        System.out.print("Enter an ID number to read from the database: ");
        int userInput = scannerIn.nextInt();
        System.out.println();

        while(userInput <= -1 || userInput >= 10){
            System.out.println("Please enter a number between 1 and 10");
            userInput = scannerIn.nextInt();
        }

        String response = database.read(userInput);
        System.out.println(response);
        System.out.println();
    }

    public void read(int id) throws RemoteException {
        database.read(id);
    }

    public void list() throws RemoteException {
        System.out.println();
        System.out.println(database.list());
        System.out.println();
    }

    public void list(boolean b) throws RemoteException{
        database.list();
    }

    public void update() throws RemoteException {

        System.out.print("Enter an ID number to update in the database: ");
        int userInput = scannerIn.nextInt();
        System.out.println();

        while(userInput <= -1 || userInput >= 10){
            System.out.println("Please enter a number between 1 and 10");
            userInput = scannerIn.nextInt();
        }

        System.out.print("Enter a name for the updated record: ");
        String name = scannerIn.nextLine();
        System.out.println();

        while(name.isEmpty()){
            System.out.println("Please enter a string for the name...");
            name = scannerIn.nextLine();
        }

        System.out.print("Enter an address for the updated record: ");
        String address = scannerIn.nextLine();
        System.out.println();

        while(name.isEmpty()){
            System.out.println("Please enter a string for the address...");
            address = scannerIn.nextLine();
        }

        database.update(userInput, name, address);

    }

    public void update(int id, String name, String address) throws RemoteException{
        database.update(id, name, address);
    }

    public int getMenuInput(){
        //return user input from Menu
        System.out.print(":> ");
        int userInput = scannerIn.nextInt();

        while(userInput <=0 || userInput >= 5){
            System.out.println("Please enter a number between 1 and 4");
            userInput = scannerIn.nextInt();
        }
        return userInput;
    }

    public int getTableID(){
        //prompts user and gets table ID
        System.out.println("Enter ID number\n:>");
        int userInput = scannerIn.nextInt();
        while(userInput <0){
            System.out.println("Enter a valid ID number above 0");
            userInput = scannerIn.nextInt();
        }
        return userInput;
    }
}
