/*
Brandon Emkjer
Professor Turini
CS-102
Due 5.8.20
*/

package TennisDatabase; //part of the package TennisDatabase

import static java.lang.Integer.parseInt; //to convert a String into an integer


public class TennisPlayer implements TennisPlayerInterface {
   
    // Values stored for a tennis player
    private String Id;
    private String firstName;
    private String lastName;
    private String country;
    private boolean dummy;
    private int birthYear;
   
    //Constructor for the TennisPlayer class that calls the various setters
    public TennisPlayer(String playerId, String firstName, String lastName, int year, String country) {
        setId(playerId);
        setFirstName(firstName);
        setLastName(lastName);
        setBirthYear(year);
        setCountry(country);
    }
   
    //Setters for the TennisPlayer class
    private void setId(String Id) {this.Id = Id;}
    void setFirstName(String firstName) {this.firstName = firstName;}
    void setBirthYear(int year) {this.birthYear = year;}
    void setLastName(String lastName) {this.lastName = lastName;}
    void setCountry(String country) {this.country = country;}
      
    //Getters for the TennisPlayer class
    public String getId() {return this.Id;}
    public String getFirstName() {return this.firstName;}
    public String getLastName() {return this.lastName;}
    public int getBirthYear() {return this.birthYear;}
    public String getStringBirthYear() {return Integer.toString(this.birthYear);} //Converts from Integer class to String
    public String getCountry() {return this.country;}
   
    //Method that prints the important player values: ID, first name, last name, and country
    public void print() {
        System.out.println("Id: " + getId());
        System.out.println("FirstName: " + getFirstName());
        System.out.println("LastName: " + getLastName());
        System.out.println("Country: " + getCountry());
    }
   
    //boolean value to test for dummy
    boolean isDummy() {return dummy;}
    
    //setter for dummy value
    void setDummy(boolean dummy) {this.dummy = dummy;}
   
    //Method to compare the IDs of the players
    @Override
    public int compareTo(TennisPlayer player) {
        return getId().compareTo(player.getId());
    }
   }