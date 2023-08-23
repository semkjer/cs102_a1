/*
Brandon Emkjer
Professor Turini
CS-102
Due 5.8.20
*/

package TennisDatabase; //part of the package TennisDatabase

//The class is used as a node for the TennisPlayer class
public class TennisPlayerNode implements TennisPlayerNodeInterface {

    private TennisPlayer player; //delcaring a private TennisPlayer value
    private TennisMatchesList list; //declaring a private list value for TennisMatchesList
    private TennisPlayerNode prev; //declaring a private previous value for TennisPlayerNode
    TennisPlayerNode next; //declaring a package-private previous value for TennisPlayerNode

    //Creating a default constructor for TennisPlayerNode
    TennisPlayerNode( TennisPlayer player) {
        this.player = player;
        this.list = new TennisMatchesList();
        //Assigning both previous and next values to null
        this.prev = null;
        this.next = null;
    }
    
    //Overrriden getters for the TennisPlayerNode class the are overriden
    @Override
    public TennisPlayer getPlayer() {return player;}
    @Override
    public TennisPlayerNode getPrev() {return prev;}
    @Override
    public TennisPlayerNode getNext() {return next;}
    
    //Overrriden setters for the TennisPlayerNode class the are overriden
    @Override
    public void setPrev(TennisPlayerNode player) {prev = player;}
    @Override
    public void setNext(TennisPlayerNode node) {next = node;}
    @Override
    public void insertMatch(TennisMatch match) throws TennisDatabaseRuntimeException {list.insertMatch(match);}

    //Overrriden method to print the matches from the list
    @Override
    public void printMatches() throws TennisDatabaseRuntimeException {list.printMatches();}
}
