import java.rmi.*;

public class Client {
    public static void main(String args[]){

        try{

            UserMenu userMenu = new UserMenu();

            int option = -1;
            while(option != 4){
                userMenu.printMenu();
                option = userMenu.getMenuInput();

                // Blank space
                System.out.println();

                // Check
                if(option < 1 || option > 4){
                    continue;
                }
                else{
                    switch(option){
                        case(1):
                            userMenu.read();
                            break;
                        case(2):
                            userMenu.update();
                            break;
                        case(3):
                            userMenu.list();
                            break;
                        case(4):
                            System.out.println("Terminating program...");
                            System.exit(0);
                        default:
                            System.out.println("Not implemented");
                    }
                }

            }

        }
        catch(Exception ex){
            ex.printStackTrace();
            System.exit(1);
        }

    } //End of main

} //End of class
