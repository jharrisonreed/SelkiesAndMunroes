import java.util.Scanner;
/**
 * Class which deals with player related methods
 *
 * @author Harrison Reed
 * @version 1
 */
public class Players
{
    /**
     * method to get the player names from the user
     */
    public static void setPlayerNames(String[] players,int playerAmount)
    {
        //initialise variables
        Scanner s = new Scanner(System.in);
        
        for (int i = 1; i < (playerAmount + 1); i++) {
            System.out.println("Please enter the name of player " + (i));
            players[i] = s.nextLine();
        }
    }
    
    /**
     * method to decide what is displayed in each square of the game grid
     */
    public static String getMarker(int pos,int playerPosition[])
    {
        String marker = "    ";
        
        if(pos == 12 || pos == 38 || pos == 55 || pos == 71 || pos == 90)
        {   
            //change default marker spacing to not ruin aligment
            marker = "   ";
            
            //make marker a selkie
            marker = marker + "S->";
            
            //set specific selkie tiles to their needed output square namees
            if(pos == 12)
            {
                marker = marker + "5 ";
            } else if(pos == 38){
                marker = marker + "20";
            } else if(pos == 55){
                marker = marker + "50";
            } else if(pos == 71){
                marker = marker + "63";  
            } else if(pos == 90){
                marker = marker + "74";
            }
        } else if(pos == 6 || pos == 27 || pos == 54 || pos == 61 || pos == 85){
            //change default marker spacing to not ruin aligment
            marker = "   ";
            
            //make marker a munro
            marker = marker + "M->";
            
            //set specific munro tiles to their needed output square namees
            if(pos == 6)
            {
                marker = marker + "20";
            } else if(pos == 27){
                marker = marker + "36";
            } else if(pos == 54){
                marker = marker + "72";
            } else if(pos == 61){
                marker = marker + "70";  
            } else if(pos == 85){
                marker = marker + "96";
            }
        
        } else {
            //change the position marker to add which players are on the current square
            for (int i = 1; i <= 4; i++) {
                if (playerPosition[i] == pos){
                    marker = marker + i;
                }
            }
        
            //ensure that the grid allignment isnt broken by multiple players being on the same square
            if (marker.equals("    123") || marker.equals("    234") || marker.equals("    134")) {
                marker = marker + " ";
            }   else if (marker.equals("    12") || marker.equals("    13") || marker.equals("    14") || marker.equals("    23") || marker.equals("    24") || marker.equals("    34")){
                marker = marker + "  ";
            } else if (marker.equals("    1") || marker.equals("    2") || marker.equals("    3") || marker.equals("    4")) {
                marker = marker + "   ";
            } else if (!marker.equals("    1234")){
                marker = marker + "    ";
            }
        }
    
        return marker;
    }
    
    /**
     * method to get player amount from the user
     */
    public static int getPlayers()
    {
        //initialise variables
        String userInput =  "";
        int userAmount = 0;
        
        //get the user input for the amount of players
        Scanner s = new Scanner(System.in);
        
        //set the user input to an integer
        while(userAmount != 2 && userAmount != 3 && userAmount !=4){
            System.out.println("Please enter the amount of players (2-4)");
            userInput = s.nextLine();
            if (userInput.equals("2")){
                userAmount = 2;
            } else if (userInput.equals("3")) {
                userAmount = 3;
            } else if (userInput.equals("4")) {
                userAmount = 4;
            } else {
                //default error message
                System.out.println("Input wasn't recognised please try again");
            }
        }
        return userAmount;
    }
}
