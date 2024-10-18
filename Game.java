import java.util.Scanner;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
/**
 * where the game is run 
 *
 * @author Harrison Reed
 * @version 1
 */
public class Game
{
    /**
     * method to run the game if option 1 on the menu is selected
     */
    public static void startGame()
    {
        //initialise variables
        int playerAmount = 2;
        String[] players = {"","","","","","",""};
        int[][] grid = initializeGrid(10, 10);
        int[] playerPosition = {0,1,1,0,0};
        int haggisBitePosition = initializeHaggisBite();
        int haggisBite = 0;
        int nessRevengePosition = initializeNessRevenge();
        int BPDelightPosition = initializeBPDelight();
        int whiskyPosition = initializeWhiskyBoost();
        boolean win = false;
        String userInput = "";
        Scanner s = new Scanner(System.in);
        
        //get the amount of the players
        playerAmount = Players.getPlayers();
        
        //set the player names 
        Players.setPlayerNames(players,playerAmount);
        
        //set the amount of players to the starting place
        //the default amount is 2 so they can be set when initializing
        if(playerAmount == 3){
            playerPosition[3] = 1;
        } else if(playerAmount == 4) {
            playerPosition[3] = 1;
            playerPosition[4] = 1;
        }
        
        while(win == false){
            for(int i = 1; i < (playerAmount + 1); i ++) {
                if(haggisBite == i)
                {
                    //remind player what happened
                    System.out.println(players[i] + " is still recovering from their bite!");
                    //reset haggis bite
                    haggisBite = 0;
                    
                    //break out of current turn so player  misses their turn
                    continue;
                } else {
                    //display the board to the user
                    printGrid(grid,playerPosition);
                    
                    //loop to ensure user enters correct inputs
                    while(true){ 
                        //prompt the user with options and get their input
                        diceMenu(i,players);
                    
                        //get user input
                        userInput = s.nextLine(); 
                    
                    
                        if(userInput.equals("1")){
                            //roll the dice for the user
                            rollDice(i,playerPosition,nessRevengePosition,BPDelightPosition,whiskyPosition,playerAmount);
                            if(playerPosition[i] == haggisBitePosition)
                            {
                                haggisBite = i;
                                System.out.println("You were bit by... a haggis? Lose your next turn!");
                            }
                            break;
                        } else if(userInput.equals("2")){
                
                            saveGame(i,playerPosition,nessRevengePosition,BPDelightPosition,whiskyPosition,playerAmount,haggisBitePosition,players);
                            
                            //tell the user the game was succesfully saved
                            System.out.println("Game has been saved, bye!");
                            
                            //exit current game loop
                            win = true;
                            break;
                        } else {
                            System.out.println("Please type either 1 or 2");
                        }
                    }
                    
                    if(playerPosition[i] >= 100){
                        //set position to 100 to ensure display doesnt break
                        playerPosition[i] = 100;
                        
                        //display the board to the user
                        printGrid(grid,playerPosition);
                        
                        //tell the player they won
                        System.out.println("Game over. " + players[i] + " has won!");
                        
                        //exit the loop
                        win = true;
                        i = 10;
                        
                    }
                }
            }
        }
    }
    
    /**
     * method to load previous game when user selects second option on the menu
     */
    public static void loadGame()
    {
        //initialise variables
        int playerAmount = 2;
        String[] players = {"","","","","","",""};
        int[][] grid = initializeGrid(10, 10);
        int[] playerPosition = {0,1,1,0,0};
        int haggisBitePosition = initializeHaggisBite();
        int haggisBite = 0;
        int nessRevengePosition = 0;
        int BPDelightPosition = 0;
        int whiskyPosition = 0;
        boolean win = false;
        String userInput = "";
        Scanner s = new Scanner(System.in);
        String filePath = "savedGame.txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Assuming you are reading line 2
            String[] lines = new String[7];
            
            for(int i = 0; i < 7; i ++)
            {
                lines[i] = reader.readLine();
            }
            
            //set player amount to previously used one
            playerAmount = Integer.parseInt(lines[0]);
            
            //set player positions
            String[] positions = lines[1].split(" ");
            
            playerPosition[1] = Integer.parseInt(positions[0]);
            playerPosition[2] = Integer.parseInt(positions[1]);
            playerPosition[3] = Integer.parseInt(positions[2]);
            playerPosition[4] = Integer.parseInt(positions[3]);

            //set player names
            String[] names = lines[2].split(" ");

            players[1] = names[0];
            players[2] = names[1];
            if(playerAmount == 3){
                players[3] = names[2];
            } else if (playerAmount == 4) {
                players[3] = names[2];
                players[4] = names[3];
            }
                
            
            //set nessie revenge position
            nessRevengePosition = Integer.parseInt(lines[3]);
            
            //set bag piper delight position
            BPDelightPosition = Integer.parseInt(lines[4]);
            
            //set haggis bite position
            haggisBitePosition = Integer.parseInt(lines[5]);
            
            //set whisky boost position
            whiskyPosition = Integer.parseInt(lines[6]);
            
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle potential IO or parsing exceptions
        }
        
        while(win == false){
            for(int i = 1; i < (playerAmount + 1); i ++) {
                if(haggisBite == i)
                {
                    //remind player what happened
                    System.out.println(players[i] + " is still recovering from their bite!");
                    //reset haggis bite
                    haggisBite = 0;
                    
                    //break out of current turn so player  misses their turn
                    continue;
                } else {
                    //display the board to the user
                    printGrid(grid,playerPosition);
                    
                    //loop to ensure user enters correct inputs
                    while(true){ 
                        //prompt the user with options and get their input
                        diceMenu(i,players);
                    
                        //get user input
                        userInput = s.nextLine(); 
                    
                    
                        if(userInput.equals("1")){
                            //roll the dice for the user
                            rollDice(i,playerPosition,nessRevengePosition,BPDelightPosition,whiskyPosition,playerAmount);
                            if(playerPosition[i] == haggisBitePosition)
                            {
                                haggisBite = i;
                                System.out.println("You were bit by... a haggis? Lose your next turn!");
                            }
                            break;
                        } else if(userInput.equals("2")){
                
                            saveGame(i,playerPosition,nessRevengePosition,BPDelightPosition,whiskyPosition,playerAmount,haggisBitePosition,players);
                            
                            //tell the user the game was succesfully saved
                            System.out.println("Game has been saved, bye!");
                            
                            //exit current game loop
                            win = true;
                            break;
                        } else {
                            System.out.println("Please type either 1 or 2");
                        }
                    }
                    
                    if(playerPosition[i] >= 100){
                        //set position to 100 to ensure display doesnt break
                        playerPosition[i] = 100;
                        
                        //display the board to the user
                        printGrid(grid,playerPosition);
                        
                        //tell the player they won
                        System.out.println("Game over. " + players[i] + " has won!");
                        
                        //exit the loop
                        win = true;
                        i = 10;
                        
                    }
                }
            }
        }
    
    }
    
    /**
     * method to save current details of the users game
     */
    public static void saveGame(int i,int playerPosition[],int nessRevengePosition,int BPDelightPosition,int whiskyPosition,int playerAmount,int haggisBitePosition,String players[])
    {
        //initialize variables
        String filepath = "savedGame.txt";
        
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath)))) {
            //write all info about the game to the file line by line
            writer.println(playerAmount);
            writer.println(playerPosition[1] + " " + playerPosition[2] + " " + playerPosition[3] + " " + playerPosition[4]);
            writer.println(players[1] + " " + players[2] + " " + players[3] + " " + players[4]);
            writer.println(nessRevengePosition);
            writer.println(BPDelightPosition);
            writer.println(haggisBitePosition);
            writer.println(whiskyPosition);
        } catch (IOException e) {
            //handle IO exception errors
            e.printStackTrace();
        }
    }
    
    
    /**
     * method to display options for the user when game has started
     */ 
    public static void diceMenu(int i,String[] players)
    {
        System.out.println("" + players[i] + "'s turn");
        System.out.println("Type 1 to roll the dice");
        System.out.println("Type 2 to save current game and exit");
    }
    
    /**
     * method to roll the dice and correct users position
     */
    public static void rollDice(int i,int playerPosition[],int nessRevengePosition,int BPDelightPosition,int whiskyPosition,int playerAmount)
    {
        int newPosition = 0;
        int min = 1;
        int max = 6;
        
        //create random object
        Random r = new Random();
        
        //assign player position according to their dice roll
        playerPosition[i] += r.nextInt(max - min + 1) + min;
        
        //check to see if the user landed on an activity square
        checkActivitySpaces(i,playerPosition,nessRevengePosition,BPDelightPosition,whiskyPosition,playerAmount);
        
        //update player positions according to selkies and munros landed on
        checkSelkiePosition(i,playerPosition);
        checkMunroPosition(i,playerPosition);
        
    }
    
    /**
     * method to check if user hit a selkie
     */
    public static void checkSelkiePosition(int i, int playerPosition[])
    {
        //check and then set new position if player landed on a selkie
        if (playerPosition[i] == 12){
            playerPosition[i] = 5;
            System.out.println("Oh no you landed on a selkie and slid back a few spaces!");
        } else if (playerPosition[i] == 38){
            playerPosition[i] = 20;
            System.out.println("Oh no you landed on a selkie and slid back a few spaces!");
        } else if (playerPosition[i] == 55){
            playerPosition[i] = 50;
            System.out.println("Oh no you landed on a selkie and slid back a few spaces!");
        } else if (playerPosition[i] == 71){
            playerPosition[i] = 63;
            System.out.println("Oh no you landed on a selkie and slid back a few spaces!");
        } else if (playerPosition[i] == 90){
            playerPosition[i] = 74;
            System.out.println("Oh no you landed on a selkie and slid back a few spaces!");
        }
    }   
    
    /**
     * method to check if user hit a munro
     */
    public static void checkMunroPosition(int i, int playerPosition[])
    {
        //check and set new position if player landed on munro
        if (playerPosition[i] == 6){
            playerPosition[i] = 20;
            System.out.println("You landed on a munro and climbed a few spaces!");
        } else if (playerPosition[i] == 27){
            playerPosition[i] = 36;
            System.out.println("You landed on a munro and climbed a few spaces!");
        } else if (playerPosition[i] == 54){
            playerPosition[i] = 72;
            System.out.println("You landed on a munro and climbed a few spaces!");
        } else if (playerPosition[i] == 61){
            playerPosition[i] = 70;
            System.out.println("You landed on a munro and climbed a few spaces!");
        } else if (playerPosition[i] == 85){
            playerPosition[i] = 96;
            System.out.println("You landed on a munro and climbed a few spaces!");
        }
    }
    
    
     /**
     * method to check if the user is on nessie revenge, whisky boost or bp delight
     */
    public static void checkActivitySpaces(int i,int playerPosition[],int nessRevengePosition,int BPDelightPosition,int whiskyPosition,int playerAmount)
    {
        if(playerPosition[i] == whiskyPosition)
        {
            playerPosition[i] += 5;
            System.out.println("You found the hidden whisky and moved forward 5 spaces!");
        }
        
        if(playerPosition[i] == BPDelightPosition) 
        {
            System.out.println("You found the secret bagpipers delight and got an extra dice roll!");
            rollDice(i,playerPosition,nessRevengePosition,BPDelightPosition,whiskyPosition,playerAmount);
        }
        
        if(playerPosition[i] == nessRevengePosition)
        {
            System.out.println("Nessie has gone on a rampage! Everyones positions have been scrambled!");
            //swap all player values depending on how many players there are
            if(playerAmount == 4){
                int temp = playerPosition[1];
                playerPosition[1] = playerPosition[2];
                playerPosition[2] = playerPosition[3];
                playerPosition[3] = playerPosition[4];
                playerPosition[4] = temp;
            } else if (playerAmount == 3) {
                int temp = playerPosition[1];
                playerPosition[1] = playerPosition[2];
                playerPosition[2] = playerPosition[3];
                playerPosition[3] = temp;
            } else {
                int temp = playerPosition[1];
                playerPosition[1] = playerPosition[2];
                playerPosition[2] = temp;
            }
        }
    }
    
    /**
     * method to set random position for haggis bite
     */
    public static int initializeHaggisBite() {
        //initialize variables
        int haggisBitePosition = 0;
        int max = 100;
        int min = 1;
        
        //create random object
        Random r = new Random();
        
        //make the haggis bite position random on the board
        haggisBitePosition += r.nextInt(max - min + 1) + min;
            
        return haggisBitePosition;
    }
    
    /**
     * method to set random position for nessie revenge
     */
    public static int initializeNessRevenge() {
        //initialize variables
        int nessRevengePosition = 0;
        int max = 100;
        int min = 1;
        
        //create random object
        Random r = new Random();
        
        //make the nessie revenge position random on the board
        nessRevengePosition += r.nextInt(max - min + 1) + min;
            
        return nessRevengePosition;
    }
    
    /**
     * method to set random position for bp delight
     */
    public static int initializeBPDelight() {
        //initialize variables
        int BPDelightPosition = 0;
        int max = 100;
        int min = 1;
        
        //create random object
        Random r = new Random();
        
        //make the BP Delight position random on the board
        BPDelightPosition += r.nextInt(max - min + 1) + min;
            
        return BPDelightPosition;
    }
    
    /**
     * method to set random position for whiskyboost
     */
      public static int initializeWhiskyBoost() {
        //initialize variables
        int WhiskyBoostPosition = 0;
        int max = 100;
        int min = 1;
        
        //create random object
        Random r = new Random();
        
        //make the Whisky Boost position random on the board
        WhiskyBoostPosition += r.nextInt(max - min + 1) + min;
            
        return WhiskyBoostPosition;
    }
    
    /**
     * method to set up 2d array for positions on the board
     */
    public static int[][] initializeGrid(int rows, int columns) {
        int[][] grid = new int[rows][columns];
        int count = rows * columns;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = count--;
            }
        }

        return grid;
    }
    
    /**
     * method to print the games grid to the user
     */
    public static void printGrid(int grid[][],int playerPosition[]) {
        int rows = 10; // Number of rows in the grid
        int columns = 10; // Number of columns in the grid

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                //print the first corner of the grid box
                System.out.print("+"); 

                for (int k = 0; k < 4; k++) {
                    //print the top of the box
                    System.out.print("--"); 
                }
            }

            //print other corner of the box
            System.out.println("+");

            if (i < rows) {
                for (int m = 0; m < columns; m++) {
                    String marker = "";
                    int pos = grid[i][m];
                    
                    marker = Players.getMarker(pos,playerPosition);
                    
                    System.out.print("|" + marker);
                    
                }
                System.out.println("|");
            }
        }
        
        for (int j = 0; j < columns; j++) {
            
            System.out.print("+");
        
            for (int k = 0; k < 4; k++) {
                System.out.print("--");
            }
        }
        
        System.out.println("+");
    }
}

