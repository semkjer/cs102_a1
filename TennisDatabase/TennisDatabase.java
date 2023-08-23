/*
Brandon Emkjer
Professor Turini
CS-102
Due 5.8.20
*/

package TennisDatabase; //this class is within the TennisDatabase package

import java.io.File; //to read/manipulate the file
import java.io.IOException; //to catch a file input exception
import java.util.Scanner; //to read from the console

import static java.lang.Integer.parseInt; //to convert a String to an integer

//Used to store an implement information on the document / text file
public class TennisDatabase {

    // Tennis containers to hold information for the TennisPlayersContainer and the TennisMatchesContainer
    private TennisPlayerContainer tpc = new TennisPlayerContainer();
    private TennisMatchContainer tmc = new TennisMatchContainer();

    //File that reads the database
    public void loadFromFile(String s) {
      String inputFileName = s;
      if (s.length() > 0) {
         try (Scanner scanner = new Scanner(new File(inputFileName))) 
         {
           String[] recordInformation; //Array of strings
           while (scanner.hasNextLine()) {
               recordInformation = scanner.nextLine().split("/"); //splits the statements into the individual Strings
               if (recordInformation.length > 0) { 
                   createObjectsFromInput(recordInformation); //creates objects if the input has content
               }
           }
   
         }          
         
         //catch exception for when the input is invalid
         catch (IOException ex) {
             System.out.println("ERROR: Command line argument file may not exist.");
             throw new NullPointerException("ERROR: During reading or writing, command line argument file may not exist: " + s); //throws NullPointerException for entities with null value
         }
       } 
       else 
       {
          System.out.println("Sorry, TennisDatabase has no argument file to read from "); //if file is empty
       }
    }

    //Method that uses a switch statement to filter between cases
    private void createObjectsFromInput(String[] recordedInformation) {
        //testing the first value in the statement that should contain PLAYER or MATCH
        switch (recordedInformation[0]) {
            case "PLAYER":
                //Uses create player method to add to database
                createPlayer(recordedInformation);
                break;
            case "MATCH":
                //Uses create match method to add to database
                createMatch(recordedInformation);
                break;
            default: //If neither player nor match
                System.out.println("Unexpected data type in document" + recordedInformation[0]);
        }
    }

    //Method to create a player based on information from the file or user
    public void createPlayer(String[] playerInformation) {
        try {
            //condtion to test if the array is of the correct length
            if (playerInformation.length == 6) {
                TennisPlayer player = new TennisPlayer(playerInformation[1], playerInformation[2], playerInformation[3], parseInt(playerInformation[4]), playerInformation[5]); //create an instance of the player
                tpc.insertPlayer(player); //calls the TennisPlayersContainer
            } 
            else {
                throw new TennisDatabaseException("Do not have sufficient information for a TennisPlayer"); //Exception if the array is not of the correct length
            }
        } catch (TennisDatabaseException ex) {
            System.out.println("Issue creating the player, invalid input for a field");
            System.out.println("Given Information");
            //Outputs the information that the user had in the file
            for (int index = 0; index < playerInformation.length; index++) {
                System.out.println("Data " + index + ": " + playerInformation[index]);
            }
        }
    }

    //Method to create a match using the match information from the file or user
    public void createMatch(String[] matchInformation) {
        try {
         TennisMatch match = new TennisMatch(matchInformation[1], matchInformation[2], matchInformation[3], matchInformation[4], matchInformation[5]); //creates an instance of the tennis match
            //if the array is the correct length, call the TennisPlayersContainer and the TennisMatchesContainer
            if (matchInformation.length == 6) {
                tpc.insertMatch(match);
                tmc.insertMatch(match);
            } else {
                throw new TennisDatabaseException("Do not have sufficient information for a TennisMatch"); //Throws exception if the data is not sufficient to create a match
            }
        } catch (TennisDatabaseException ex) { //Catches TennisDatabaseException
            System.out.println("Issue creating the match, invalid input for a field");
            System.out.println("Given Information:");
            for(int index = 0; index < matchInformation.length; index++) { //prints the content of the incorrect match
                System.out.println("Data " + index + ": " + matchInformation[index]);
            }
        }
    }

    //Method to call for the creation of a new match, which will have to be associated with both container
    public void userCreateMatch(TennisMatch match) {
        tpc.insertMatch(match);
        tmc.insertMatch(match);
    }

    //Method used to call the tennis player container to print the matches
    public void printAllTennisPlayers() {
        tpc.printAllPlayers();
    }

    //method uses to call the tennis player container to print the matches associated with that player
    public void printTennisMatchesOfPlayer(String playerId) {
        tpc.printMatchesOfPlayer(playerId);
    }

    //Method to call the tennis match container to print the matches
    public void printAllMatches() {
        tmc.printAllMatches();
    }

}
