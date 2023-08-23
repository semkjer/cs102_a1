/*
Brandon Emkjer
Professor Turini
CS-102
Due 5.8.20
*/

package TennisDatabase; //part of the package TennisDatabase

//Class serves as a node for the TennisMatch classes
public class TennisMatchNode {

    private TennisMatch match; //declare TennisMatch
    private TennisMatchNode prev; //delcare value for previous node
    private TennisMatchNode next; //declare value for next node

    //Create a default constructor for the LL
    TennisMatchNode( TennisMatch match) {
        this.match = match;
        this.prev = null;
        this.next = null;
    }

    //Getters for the TennisMatchNode class
    public TennisMatch getMatch() {return match;}
    public TennisMatchNode getPrev() {return prev;}
    TennisMatchNode getNext() {return next;}

    //Setters for the TennisMatchNode class
    void setPrev(TennisMatchNode node) {prev = node;}
    void setNext(TennisMatchNode node) {next = node;}
}