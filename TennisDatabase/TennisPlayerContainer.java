/*
Brandon Emkjer
Professor Turini
CS-102
Due 5.8.20
*/

package TennisDatabase; //part of the package TennisDatabase

//Class serves as a container for all tennis players
public class TennisPlayerContainer implements TennisPlayerContainerInterface {

    private TennisPlayerNode head; //declaring a TennisPlayerNode head value
    private int numPlayers; //declaring an integer value to keep track of the number of players

    //Default constructor for TennisPlayerContainer
    TennisPlayerContainer() {
        head = null; //head is assigned to null
        numPlayers = 0; //numPlayers is assigned to 0
    }

    //Method to locate the node of a player given an ID
    private TennisPlayerNode getPlayerNode(String id) {
        TennisPlayerNode currNode = head; //assign the current node as head
        int indexCurrNode = 0; //create an index value starting at 0
        //if the conditions are true, the method will step through the linked list
        while ((indexCurrNode < numPlayers) && (currNode.getPlayer().getId().compareTo(id) < 0)) {
            currNode = currNode.getNext();
            indexCurrNode++;
        }
        if (currNode.getPlayer().getId().equals(id)) {return currNode;} //return the node if the conditions are valid
        else {return null;} //return a null value if conditions are unsatisfied
    }

    //Method to get a player with a certain ID
    private TennisPlayer getPlayer(String id) {
      TennisPlayerNode currNode = head; //assign a TennisPlayerNode value to head
      for (int i = 0; i < numPlayers; i++) {
          if (currNode.getPlayer().getId().equals(id)) {return currNode.getPlayer();} //if the ID is equal, returns the node of the player
          else {currNode = currNode.getNext();} //steps through the linked list
      }
      return null; //returns null if the if statement is left unsatisfied
    }


    //Overidden method to insert the player into the list in the appropriate position
    @Override
    public void insertPlayer(TennisPlayer player) throws TennisDatabaseRuntimeException {  
        TennisPlayerNode newNode = new TennisPlayerNode(player); //create new instance of TennisPlayerNode
        TennisPlayer sameId = getPlayer(player.getId()); //create another instance of TennisPlayer
        if (sameId == null) {
            // List is empty. Assign all values to newNode and increment.
            if (head == null) {
                head = newNode;
                newNode.setNext(newNode);
                newNode.setPrev(newNode);
                numPlayers++;
            } 
            else {
                //List is not empty
                TennisPlayerNode nodeAfterIns = head; //assign TennisPlayerNode value to head
                TennisPlayerNode nodeBeforeIns = head.getPrev(); //assign TennisPlayerNode value to node before head
                int nodeAfterInsIdx = 1; //creating an integer for the index
                //while the condition is true, the nodes are assigned to different values and the index is incremented
                while (nodeAfterInsIdx <= numPlayers && nodeAfterIns.getPlayer().compareTo(player) <= 0) {
                    nodeBeforeIns = nodeAfterIns;
                    nodeAfterIns = nodeAfterIns.getNext();
                    nodeAfterInsIdx++;
                }
                //if the nodeAfterInsIdx is equal to 1, assign head to newNode and increment
                if (nodeAfterInsIdx == 1) {
                    head = newNode;
                    newNode.setNext(nodeAfterIns); //set next node of newNode to nodeAfterIns
                    newNode.setPrev(nodeBeforeIns); //set previous node of newNode to nodeBeforeIns
                    nodeBeforeIns.setNext(newNode); //set next node of nodeBeforeIns to newNode
                    nodeAfterIns.setPrev(newNode); //set previous node of nodeAfterIns to newNode
                    numPlayers++; //increment the number of players
                } 
                //no head assignment in else statement
                else {
                    newNode.setNext(nodeAfterIns); //set next node of newNode to nodeAfterIns
                    newNode.setPrev(nodeBeforeIns); //set previous node of newNode to nodeBeforeIns                    
                    nodeBeforeIns.setNext(newNode); //set next node of nodeBeforeIns to newNode
                    nodeAfterIns.setPrev(newNode); //set previous node of nodeAfterIns to newNode
                    numPlayers++; //increment the number of players
                }
            }
        } 
        else {
            try {
                throw new TennisDatabaseRuntimeException("Duplicate player"); //throw a TennisDatabaseRuntimeException error for a duplicate player
            } catch (TennisDatabaseRuntimeException ex) {
                System.out.println("Duplicate player found, continuing"); //output and continue program since it is a non-critical error
            }
            //calls setters with the getters as inputs
            sameId.setFirstName(player.getFirstName()); 
            sameId.setLastName(player.getLastName());
            sameId.setBirthYear(player.getBirthYear());
            sameId.setCountry(player.getCountry());
            sameId.setDummy(false); //turns dummy to false
        }
    }

    //Overriden method to insert match into the linked list
    @Override
    public void insertMatch(TennisMatch match) throws TennisDatabaseRuntimeException {
        TennisPlayerNode nodePlayer1 = getPlayerNode(match.getPlayer1Id()); //declaring a variable for the node with player 1's ID
        TennisPlayerNode nodePlayer2 = getPlayerNode(match.getPlayer2Id()); //declaring a variable for the node with player 2's ID
        if (nodePlayer1 == null) {
            TennisPlayer player = new TennisPlayer(match.getPlayer1Id(), "", "", 0, ""); //Tennis player default given ID
            player.setDummy(true); //set dummy to true
            try {
                insertPlayer(player); //call insertPlayer method
            } 
            //if exception is caught, problem statement is outputted
            catch (TennisDatabaseRuntimeException ex) {
                System.out.println("ERROR: Problem inserting player 1.");
                player.print();
            }
            nodePlayer1 = getPlayerNode(match.getPlayer1Id()); //nodePlayer1 is assigned to the node of the match containing player 1's ID
        }
        if (nodePlayer2 == null) {
            TennisPlayer player2 = new TennisPlayer(match.getPlayer2Id(), "", "", 0, ""); //Tennis player default given ID
            player2.setDummy(true); //set dummy to true
            try {
                insertPlayer(player2); //call insertPlayer method
            } 
            //if exception is caught, problem statement is outputted
            catch (TennisDatabaseRuntimeException ex) {
                System.out.println("ERROR: Problem inserting player 2."); 
                player2.print();
            }
            nodePlayer2 = getPlayerNode(match.getPlayer2Id()); //nodePlayer2 is assigned to the node of the match containing player 2's ID
        }
        if (nodePlayer1 != null) {
            nodePlayer1.insertMatch(match); //call insertMatch method if the node is not null
        }
        if (nodePlayer2 != null) {
            nodePlayer2.insertMatch(match); //call insertMatch method if the node is not null
        }
    }

    //Overriden method to output all the players in the linked list
    @Override
    public void printAllPlayers() throws TennisDatabaseRuntimeException {
        TennisPlayerNode currNode = head; //assign currNode to head
        //Steps through the linked list and prints information
        for (int i = 0; i < numPlayers; i++) {
            currNode.getPlayer().print();
            currNode = currNode.next;
        }
    }

    //Print the matches of a player with a given ID
    public void printMatchesOfPlayer(String playerId) throws TennisDatabaseRuntimeException {
        TennisPlayerNode player = getPlayerNode(playerId); //Declare a TennisPlayerNode value equal to the node of the player's ID
        //if the condition is not null, the matches will be printed containing the player
        if (player != null) {
            player.printMatches();
        } 
        //player is equal to null, and a try-catch loop will catch an exception
        else {
            try {
                throw new TennisDatabaseRuntimeException(playerId + "does not exist."); //throws the TennisDatabaseRuntimeException exception for null value
            } 
            catch (TennisDatabaseRuntimeException ex) {
                System.out.println("No player with selected id, continuing."); //Non-critical error, so the program continues
            }
        }
    }
}
