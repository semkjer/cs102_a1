package TennisDatabase;

//This should be package-private
public class TennisPlayerQueue implements TennisPlayerQueueInterface {
   
   private int maxCount; //Maximum number of players in the queue
   private TennisPlayer[] players; //Array storing players in the queue
   private int front; //Index of the queue front
   private int back; //Index of the queue back
   private int count; //Number of players in the queue
   
   //Default constructor
   public TennisPlayerQueue() {
      this.maxCount = 4;
      this.players = new TennisPlayer[maxCount];
      this.front = 0;
      this.back = this.maxCount - 1;
      this.count = 0;
   }
   
   
   public boolean isEmpty() {
      return (this.count == 0); //TODO
   }
   
   // Desc.: Insert a tennis player at the back of this queue.
   // Input: A tennis player.
   // Output: Throws a checked (critical) exception if the insertion fails.
   public void enqueue( TennisPlayer p ) throws TennisDatabaseException {
      //Check if queue is full
      if(this.count == this.maxCount) {
         //Queue is full, resize it on-the-fly
         //Create a new, bigger array
         int newMaxCount = this.maxCount * 2; //New physical size of the queue
         TennisPlayer[] newArray = new TennisPlayer[newMaxCount]; //New larger array
         //Transfer data from old small array to new big array, from front to back
         int i = 0; //Destination index in new bigger array
         while (i < this.count) {
            int sourceIndex = (this.front + i) % this.maxCount;
            newArray[i] = this.players[sourceIndex];
            i++;
         }
         //Trnasfer complete, swap old small array with new big array
         this.players = newArray;
         this.maxCount = newMaxCount;
         this.front = 0;
         this.back = this.maxCount - 1;
      }
      //Queue not full, perform insertion at back
      this.back = (this.back + 1) % this.maxCount; //Update back index considering circular array
      this.players[this.back] = p; //Store input player at new back index.
      this.count++; //Update number of player
   }

   // Desc.: Extract (return and remove) a tennis player from the front of this queue.
   // Output: Throws a checked (critical) exception if the extraction fails.
   public TennisPlayer dequeue() throws TennisDatabaseException {
      if( !isEmpty() ) {
         TennisPlayer frontPlayer = this.players[this.front]; //Getting front player from queue
         this.players[this.front] = null; //Cleaning front cell
         this.front = (this.front + 1) % this.maxCount; //Update front index for circular array
         this.count--; //decrements counter
         return frontPlayer;
      }
      else {
         throw new TennisDatabaseException("TennisDatabseException on dequeue: queue empty");
      }
   }

   // Desc.: Return (without removing) the tennis player at the front of this queue.
   // Output: Throws a checked (critical) exception if the queue is empty.
   public TennisPlayer peek() throws TennisDatabaseException {
      if( !isEmpty() ) { 
         return this.players[this.front]; 
      }
      else {
         throw new TennisDatabaseException("TennisDatabseException on peek: queue empty");
      }
   }
}