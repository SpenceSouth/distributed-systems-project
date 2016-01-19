import java.util.Scanner;

/**
 * Created by Brian on 1/17/2016.
 */
public class UserMenu {

    private Scanner scannerIn;

    public UserMenu(){
        scannerIn = new Scanner(System.in);
    }

    public void printMenu(){
        //print user menu on client side
        System.out.println("=== USER MENU ===");
        System.out.println("1. READ");
        System.out.println("2. UPDATE");
        System.out.println("3. LIST");
        System.out.println("4. QUIT");
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

    public int getTableID(int tableSize){
        //prompts user and gets table ID
        System.out.println("Enter ID number (0 - " + tableSize +  ") \n:>");
        int userInput = scannerIn.nextInt();
        while(userInput <0 || userInput > tableSize){
            System.out.println("Enter a valid ID number between 0 and " + tableSize);
            userInput = scannerIn.nextInt();
        }
        return userInput;
    }

    public String getUpdateLine(){

    }
}
