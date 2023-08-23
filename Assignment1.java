
/*
X Emkjer
Professor Turini
CS-102
Due 5.8.20
*/

import TennisDatabase.TennisDatabase; //to use TennisDatabase
import TennisDatabase.TennisPlayer; //to use TennisPlayer
import TennisDatabase.TennisMatch; //to use TennisMatch

import java.text.SimpleDateFormat; //For date verification
import java.util.Date; //For date verification
import java.util.Scanner; //To read inputs
import static java.lang.Integer.parseInt; //To change Strings into integers
import java.lang.String; //To convert to upper case

//The main class Assignment1 interacrs with the database and displays options to the user 
public class Assignment1 {
    public static void main(String[] args) {
        Assignment1 assignment1 = new Assignment1(); //Create an instance of assignment 1
        TennisDatabase database = new TennisDatabase(); //Create an instance of TennisDatabase
        System.out.println("Enter filename: ");
        Scanner fileNameScanner = new Scanner(System.in);
        String fileName = fileNameScanner.nextLine(); //Create an instance of scanner to read from console
        database.loadFromFile(fileName); //call the database to load information from given file
        assignment1.displayOptions(database); //call to display options
    }

    //controls the primary functions of the database, as the user input determines which methods will be implemented
    private void displayOptions(TennisDatabase database) {
        System.out.println("Welcome to my first CS-102 assignment, a TennisDatabase Manager");
        int val;
        String id;
        Scanner scan = new Scanner(System.in);
        do {
             System.out.println("Brandon Emkjer's CS-102 Tennis Manager - Available commands:"); //Create a menu displayed to user
             System.out.println("1 --> Print All Players");
             System.out.println("2 --> Print All Matches of a Player");  
             System.out.println("3 --> Print All Matches");   
             System.out.println("4 --> Insert New Player");   
             System.out.println("5 --> Insert New Match");  
             System.out.println("6 --> Exit");
             System.out.print("Enter user's choice: ");
             val = scan.nextInt(); //Assign val to user's selection
             while (val > 6 || val < 1) { //while loop for input validation
               System.out.print("Invalid input. Enter user's choice: ");
               val = scan.nextInt();
             }

            switch (val) { //switch statement for all 8 cases in the assignment
               case 1: //for print all players
                  database.printAllTennisPlayers(); //Calls the database to print all tennis players 
                  break;
               case 2:
                  id = enterUserId(scan); //Calls a method to enter and verify the user ID
                  database.printTennisMatchesOfPlayer(id); //Calls the database to print the matches of the player with the corresponding ID
                  break;
               case 3:
                  database.printAllMatches(); //Calls the database to print all matches for a player
                  break;
               case 4:
                  boolean isValidPlayer; //boolean to test validity
                  String [] playerInformation; //an array of strings containing the player information
                  do {
                    System.out.println("Enter the player information that contains the ID, first name, last name, year, and country of birth.");
                        System.out.println("EXAMPLE: /BE123/BRANDON/EMKJER/2001/USA");
                        String pInfo = scan.next(); //reads the user's input
                        pInfo = pInfo.toUpperCase(); //Converting the string to upper case
                        playerInformation = pInfo.split("/"); //splits the input into separate strings
                        isValidPlayer = isValidPlayer(playerInformation);
                    } while (!isValidPlayer);
                    database.createPlayer(playerInformation);
                    break;
                case 5:
                    boolean isValidMatch; //boolean to test validity
                    String [] matchInformation; //an array of strings containing the match information
                    do {
                        System.out.println("Enter the match information that contains the first player's ID, the second player's ID, the date, the location, and the scores.");
                        System.out.println("Response must be in format: /BE123/SA987/20150815/OAKLAND/7-6,6-4/");
                        String mInfo = scan.next(); //reads the user's input
                        mInfo = mInfo.toUpperCase(); //Converting the string to upper case
                        matchInformation = mInfo.split("/"); //splits the input into separate strings
                        //for (int i = 0; i < matchInformation.length; i++) System.out.println(matchInformation[i]);
                        isValidMatch = isValidMatch(matchInformation);
                    } while (!isValidMatch);
                    database.createMatch(matchInformation);
                    break;
                case 6:
                    System.out.print("Thank you for using my program!");
                    break;
                default:
                    System.out.println("ERROR: Input is not valid."); //For inputs x < 1 or x > 6
            }

        } while (val != 6);

    }

    
    //Method to input the player's ID
    private String enterUserId(Scanner input) {
      String id;
      do {
         System.out.print("Enter the player's ID: ");
         id = input.next(); //Reads input from scanner
      } while (!isValidId(id)); //calls the isValidId method to verify length
      return id;
    }

    //Method to verify if the player is valid
    private boolean isValidPlayer(String [] player) {
        return isValidId(player[1]) && isValidFirstOrLastName(player[2]) && isValidFirstOrLastName(player[3]) && isValidYear(player[4]) && isValidCountry(player[5]);
    }
    
    //Verifying if the length of the ID is proper
    private boolean isValidId(String id) {
        if(!id.isEmpty() && id.length() != 5) {
            System.out.println("The ID (" + id + ") must be 5 alphanumeric characters in length.");
            return false;
        }
        return true;
    }

    //Verifying if the names are not empty
    private boolean isValidFirstOrLastName(String fLName) {
        if(fLName.isEmpty()) {
            System.out.println(fLName + " is not a valid input.");
            return false;
        }
        return true;
    }

    //Verifying if the year is the proper digits
    private boolean isValidYear(String year) {
        if(!isNumeric(year) || year.length() != 4) {
            System.out.println("Year must have 4 digits!");
            return false;
        }
        return true;
    }

    //Verifying that the string is numeric
    private boolean isNumeric(String str)
    {
        try
        {
            double valueToCheckNumeric = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe) //Catching exception for number validity
        {
            return false;
        }
        return true;
    }

    //Verifying if the location is valid
    private boolean isValidCountry(String country) {
        return !country.isEmpty();
    }

    //Verifying if the match is valid by other methods
    private boolean isValidMatch(String [] match) {
        return isValidId(match[1]) && isValidId(match[2]) && isValidDate(match[3]) && isValidCountry(match[4]) && isValidScore(match[5]);
    }
   
    //Uses simpleDateFormat to verify structure of the date
    private boolean isValidDate(String date) {
        try {
            Date date1 = new SimpleDateFormat("YYYYMMDD").parse(date); //setting a date in set format
        }
        catch (Exception e) {
            System.out.println("Date is not valid, " + date); //catching exception if the date is not valid
            return false;
        }
        if (date.length() != 8) {
            System.out.println("Date must be in YYYYMMDD format. You entered: " + date); //If result is not 8
            return false;
        }
        return true;
    }

   //Verifying if there are enough digits for score
    private boolean isValidScore(String score) {
        return !score.isEmpty() && score.length() >= 3;
    }
}
