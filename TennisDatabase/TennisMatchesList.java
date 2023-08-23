/*
Brandon Emkjer
Professor Turini
CS-102
Due 5.8.20
*/

package TennisDatabase; //part of the package TennisDatabase

//Class that serves as a list for all matches
public class TennisMatchesList implements TennisMatchesListInterface {
    private TennisMatchNode head; //establishing head node
    private TennisMatchNode tail; //establishing tail node
    private int sizeLL; //integer for the size of the list

    //Simple constructor for the TennisMatchesList
    TennisMatchesList()
    {
        head = null;
        tail = null;
        sizeLL = 0;
    }
    
    //Method to obtain the size of the list
    public int getSize() {return sizeLL;}
    //Check if the LL is empty
    public boolean isEmpty() {return head == null;}

    //Method to add match to the beginning of the list
    private void prepend(TennisMatchNode m) {
        if (head == null) { //For the case in which the node is the first element in the LL
         //Setting all values to equal the node
         m.setNext(m);
         m.setPrev(m);
         head = m;
         tail = head;
        } 
        else {
         m.setPrev(tail); //Set previous m to tail
         tail.setNext(m); //Set next node of tail to m
         head.setPrev(m); //Set previous m to head
         m.setNext(head); //Set next node of m to head
         head = m; //Assign the head value to the m
        }
        sizeLL++; //increment to update size
    }

    //Method to add match to the end of the list
    private void append(TennisMatchNode m) {
        m.setPrev(tail); //set previous node of m to tail
        tail.setNext(m); //set next node of tail to m
        head.setPrev(m); //set previous node of head to m
        m.setNext(head); //set next node of match to head
        tail = m; //assign the tail to m
        sizeLL++; //increment to update size
    }

    @Override
    public void insertMatch(TennisMatch match) {
        TennisMatchNode newMatch = new TennisMatchNode(match); //create a new instance of TennisMatchNode
        //insert for head and tail if the list is empty
        if (head == null) {
            newMatch.setNext(newMatch);
            newMatch.setPrev(newMatch);
            head = newMatch;
            tail = head;
        }
        //call the prepend method if the head is equal to the new instance
        if (head.equals(newMatch)) {
            prepend(newMatch);
            return;
        }
        //call the append method if the head is equal to the new instance
        else if (tail.equals(newMatch)) {
            append(newMatch);
            return;
        }

        TennisMatchNode node = head; //iterate to the position starting at head
        
        //if the condition is satisfied, get the next node
        while (head != node.getNext() || ((node.getMatch().compareTo(match) != 1) && head != tail)) {node = node.getNext();}
        
        //Call prepend method if the value is equivalent to head
        if (head == node.getNext()) {prepend(newMatch);}
        else {
            TennisMatchNode tmNode = node.getNext();
            node.setNext(newMatch); //set next node of node to newMatch
            newMatch.setPrev(node); //set previous node of newMatch to node
            newMatch.setNext(tmNode); //set next node of newMatch to tmNode
            tmNode.setPrev(newMatch); //set the previous node of tmNode to newMatch
        }
        sizeLL++; //increment the size of the linked list
    }

    //Method to empty all contents of the list
    public void removeAll() {
        head = null;
        tail = null;
        sizeLL = 0;
    }

    //Method to print matches in the linked list
    public void printMatches() {
        TennisMatchNode node; //set the TennisMatchNode to node

        // Check if empty first
        if (sizeLL == 0) {System.out.println("Empty list, no values to print");} 
        else {
            node = head; //set node to the head value
            // Iterate over all matches with while loop, printing each but the last value
            while (node.getNext() != head) {
                node.getMatch().print();
                node = node.getNext();
            }
            node.getMatch().print(); //print the last match
        }
    }
}
