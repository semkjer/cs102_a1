/*
Brandon Emkjer
Professor Turini
CS-102
Due 5.8.20
*/

package TennisDatabase; //part of the package TennisDatabase

import java.util.Scanner;
import static java.lang.Integer.parseInt;

//Class stores data for tennis matches
public class TennisMatch implements TennisMatchInterface {

    //Declare private variables
    private String firstPlayerId;
    private String secondPlayerId;
    private String tennisMatchDate;
    private String locationOfMatch;
    private String winner;
    private String score;
    private TennisMatchSetScore scores;

    //Processes the score of the match
    private boolean processMatchScore( String score, TennisMatchSetScore scores) {
        // score length is 0, base case
        if (score.length() == 0) {return true;} 
        else {
            // standard call for the recursive shrinking of the problem
            Scanner iteratingScore = new Scanner(score).useDelimiter(","); //Use delimeter to separate scores
            String score1 = iteratingScore.next(); //takes value as a score
            Scanner scanner1 = new Scanner(score1).useDelimiter("-"); //uses second delimeter to isolate values
            String player1Games = scanner1.next(); //Use scanner to assign to string value
            int p1Games = parseInt(player1Games); //Convert the string into an integer
            String player2Games = scanner1.next(); //Use scanner to assign to string value
            int p2Games = parseInt(player2Games); //Convert the string into an integer

            // increment scores depending on who won
            if (p1Games > p2Games) {scores.setsPlayer1++;} //increment player1 score if player 1 has higher value
            else if (p1Games < p2Games) {scores.setsPlayer2++;} //increment player1 score if player 2 has higher value
            else {return false;} //returns false for an invalid score entry

            if (score1.length() == score.length()) {return true;}
            else {
                //recursively find the String values for score not processed
                String remainder = score.substring(score1.length() + 1);
                return processMatchScore(remainder, scores);
            }
        }
    }

    //Constructor that creates the tennis match
    public TennisMatch(String firstPlayerId, String secondPlayerId, String tennisMatchDate, String locationOfMatch, String score) throws TennisDatabaseException {
        //Checks if the match information is valid
        isMatchInfoValid(firstPlayerId, secondPlayerId, tennisMatchDate, locationOfMatch, score);
        //if exception is not thrown, set the IDs, date, location, and score
        setFirstPlayerId(firstPlayerId);
        setSecondPlayerId(secondPlayerId);
        setTennisMatchDate(tennisMatchDate);
        setLocationOfMatch(locationOfMatch);
        setScore(score);
        this.scores = new TennisMatchSetScore();

        boolean isScoreValid = processMatchScore(score, scores); //boolean value associated with the validity of the scores
        
        if (!isScoreValid) {throw new TennisDatabaseException("Score of tennis match is invalid");} //throw error is the method returns false
        if (scores.setsPlayer1 > scores.setsPlayer2) {winner = firstPlayerId;} //assign the winner
        else if (scores.setsPlayer1 < scores.setsPlayer2) {winner = secondPlayerId;} //assign the loser
        else {throw new TennisDatabaseException("Score of tennis match is invalid");} //if the score is invalid, throw an error
    }

    //Series of methods to check if the information is valid
    private void isMatchInfoValid(String firstPlayerId, String secondPlayerId, String tennisMatchDate, String locationOfMatch, String score) throws TennisDatabaseException {
        if(firstPlayerId == null) {throw new TennisDatabaseException("firstPlayer Id is not a valid Id");} //for ID1
        else if (secondPlayerId == null) {throw new TennisDatabaseException("Second player Id is not a valid Id");} //for ID2
        else if (tennisMatchDate == null) {throw new TennisDatabaseException("tennisMatchDate is not a valid date");} //for date
        else if (locationOfMatch == null) {throw new TennisDatabaseException("locationOfMatch is not valid");} //for location
        else if (score == null) {throw new TennisDatabaseException("scores is not valid");} //for score
    }

    private void setFirstPlayerId(String firstPlayerId) { this.firstPlayerId = firstPlayerId; } //Method that sets the ID of player 1
    private void setSecondPlayerId(String secondPlayerId) { this.secondPlayerId = secondPlayerId; } //Method that sets the ID of player 2
    private void setTennisMatchDate(String tennisMatchDate) { this.tennisMatchDate = tennisMatchDate; } //Method that sets the date of the match
    private void setLocationOfMatch(String locationOfMatch) { this.locationOfMatch = locationOfMatch; } //Method that sets the location of the match
    private void setScore(String score) { this.score = score; } //method to get the score of the match
    
    private String getTennisMatchDate() { return this.tennisMatchDate; } //returns the date String of the match
    public String getPlayer1Id() { return this.firstPlayerId; } //returns the ID of player 1
    public String getPlayer2Id() { return this.secondPlayerId; } //returns the ID of player 2
    public int getDateMonth() { return parseInt(getTennisMatchDate().substring(4,6)); } //returns 2-digit month in String
    public int getDateYear() { return parseInt(getTennisMatchDate().substring(0,4)); } //returns 4-digit year in String
    public int getDateDay() { return parseInt(getTennisMatchDate().substring(6,8)); } //returns a 2-digit day in String
    public String getTournament() { return this.locationOfMatch; } //returns a String that serves as the match location
    public String getScore() { return scores.setsPlayer1 + "-" + scores.setsPlayer2; } //returns the score in 1-2 format
    public String getWinner() { return winner; } //returns the ID of the winner

    //Method that prints the contents within the tennis match
    public void print() {
        System.out.print( firstPlayerId + ", " );
        System.out.print( secondPlayerId + ", ");
        System.out.print(getDateYear() + "-" + getDateMonth() + "-" + getDateDay() + ", ");
        System.out.print( getTournament() + ", ");
        System.out.print(score + ", ");
        System.out.print("Winner: " + winner);
        System.out.println();
    }

    //Method that compares the tennis match by date
    @Override
    public int compareTo(TennisMatch match) {
        if(getDateYear() < match.getDateYear()) {return 1;} //compares year
        else if(getDateYear() == match.getDateYear() && getDateMonth() < match.getDateMonth()) {return 1;} //compares months
        else if(getDateYear() == match.getDateYear() && getDateMonth() == match.getDateMonth() && getDateDay() < match.getDateDay()) {return 1;} //compares days
        else {return 0;} //if none of the conditions are true, returns 0
    }
}
