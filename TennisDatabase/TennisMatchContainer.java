/*
Brandon Emkjer
Professor Turini
CS-102
Due 5.8.20
*/

package TennisDatabase; //part of the package TennisDatabase

//Class used as the container for all tennis matches
public class TennisMatchContainer implements TennisMatchContainerInterface{

    private TennisMatch[] matches; //array of tennis matches
    private int numberOfMatches; //integer value representing the number of matches

    //Constructor for TennisMatches
    TennisMatchContainer() {
        matches = new TennisMatch[3]; //creates an instance of TennisMatch array with size 3
        numberOfMatches = 0; //numberOfMatches starts at 0
    }

    //method to insert a match into the array
    @Override
    public void insertMatch(TennisMatch match) throws TennisDatabaseRuntimeException {
        //if numberOfMatches is equal to the size of the array, the array should dynamically resize
        if (numberOfMatches == matches.length) {
            TennisMatch[] temporaryMatchHolder = new TennisMatch[matches.length * 2]; //doubles the size of the array
            for (int index = 0; index < matches.length; index++) //assign original data to the larger array
                temporaryMatchHolder[index] = matches[index];
            matches = temporaryMatchHolder; //matches is assigned to the larger array
        }
        matches[numberOfMatches] = match; //new value is assigned to the array
        numberOfMatches++; //number is incremented
    }

    //sort and print the array
    @Override
    public void printAllMatches() {
        sortByDate(matches); //calls the method sortDate to sort the array
        for(int index = 0; index < numberOfMatches; index++) {
            matches[index].print(); //print the sorted array
        }
    }

    //Method to sore the array by date
    private void sortByDate(TennisMatch [] arrayUnsorted)
    {
        //using nested for loops to compare all values after element for each element
        for(int passes = 0; passes < numberOfMatches; passes++)
        {
            int minimumIndex = passes; //placeholding integer to hold number of passes
            for(int i = passes + 1; i< numberOfMatches; i++)
            {
                if(arrayUnsorted[i].compareTo(arrayUnsorted[minimumIndex]) != 0) //uses compareTo to compare the compare the dates
                {
                    minimumIndex = i; //placeholder assigned to the index
                }
            }
            swap(arrayUnsorted,minimumIndex,passes);
        }
    }

    //swaps the array elemetnts
    private void swap(TennisMatch [] array, int firstIndex, int secondIndex)
    {
        TennisMatch placeHolder = array[firstIndex]; //placeholder used to swap and hold first data
        array[firstIndex] = array[secondIndex]; //first data assigned second data
        array[secondIndex] = placeHolder; //second data assigned first data
    }
}
