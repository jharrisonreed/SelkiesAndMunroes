import java.util.Scanner;
/**
 * This is where the menu and user input is handled
 *
 * @author Harrison Reed
 * @version 1
 */
public class Menu
{
    /**
     * Main method for the program
     */
    public static void main(String[] args)
    {
        processUserChoices();
    }
    
    /**
     * method to process user's input
     */
    public static void processUserChoices()
    {
        //variable to hold the user input
        String userInput;
        
        //call the display menu method
        displayMenu();
            
        //Scanner to get user input
        Scanner s = new Scanner(System.in);
        
        //get the user input
        userInput = s.nextLine();
        
            if (userInput.equals("1")) {
                //call the start game method
                Game.startGame();
            } else if (userInput.equals("2")){
                 //call the load game method
                Game.loadGame();
            } else if (userInput.equals("3")) {
                //call the instructions method
                instructions();
            } else if (userInput.equals("0")) {
                //close the program
                System.out.println("Goodbye");
                System.exit(0);
            } else {
                //default error message for the user
                System.out.println("Your input wasn't recognised");
            }
    }
     
    /**
     * method to display option menu to the user
     */
    public static void displayMenu()
    {
        //blank line to help user readability when menu is reprinted
        System.out.println("");
        
        //display the menu for the user
        System.out.println("Please select one of the options below");
        System.out.println("1. Start new game");
        System.out.println("2. Load previous game");
        System.out.println("3. Help");
        System.out.println("0. Exit");
    }
    
    /**
     * method to display instructions to the user
     */
    public static void instructions()
    {
        System.out.println("Hello and welcome to selkies and munros! The scottish twist of a classic game.");
        System.out.println("The aim of this game is to roll the dice so that you manage to reach the finish before the other players.");
        System.out.println("But be careful, along the way selkies will try and trip you up.");
        System.out.println("Don't worry though, munros are also there to help you climb back up on lost space!");
        System.out.println("Good luck!");
        
        //display menu to the user again
        processUserChoices();
    }
}
