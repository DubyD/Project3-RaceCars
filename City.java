//Author WD


import java.util.*;
import javax.swing.Timer;

    //Back end of all the game components
public class City {

    private Timer clock;

    private List<GamePiece> board;
    private List<Destination> stops;
    private List<Car> racers;

    private TurnTaker pacer;

    public City(int dimension, int racers, GameGrid gui){

            //Sets the boundaries

        this.racers = new ArrayList<Car>();
        this.stops = new ArrayList<Destination>();

            //Methods for initiating GamePieces
            //Board which holds all the pieces
        this.board = new ArrayList<GamePiece>();
        this.makeDestinations(dimension);
        this.board.addAll(this.stops);
        this.addCompetitors(dimension, racers);
        this.board.addAll(this.racers);

        //This sets the Clock to
        this.pacer = new TurnTaker(this, gui);


    }

    public City(){
        this.clock = null;
        this.stops = null;
        this.racers = null;

    }
//----------------Factory Methods--------------------------------------------------
        //Used to compare coordinates to an Object in following methods
    private boolean validSpot(int x, int y, GamePiece one){
        if(x == one.getX()){
            if(y == one.getY()){
                return false;
            }
        }
        return true;
    }


            //Loops through all GamePieces
        private boolean isFreeSpace(int x, int y) {
            for (Destination stop : this.stops) {
                if (!validSpot(x, y, stop)) {
                    return false;
                }
            }

            for (Car player : this.racers) {
                if (!validSpot(x, y, player)) {
                    return false;
                }
            }

            return true;
        }

        //Creates 4 destinations for travellers
    private void makeDestinations(int dimension){
        //Randomizes their locations
        Random randomizer = new Random();
        char stop = 'A';

        for(int i = 0; i < 4; i++){
                // creates random Coordinates on the gameboard.
            int randomX;
            int randomY;

            // Randomizes coordinates
            do {
                randomX = randomizer.nextInt(dimension);
                randomY = randomizer.nextInt(dimension);
            } while (!this.isFreeSpace(randomX, randomY));


            // Creates a new destination and adds it to the list
            Destination adding = new Destination(randomX, randomY, String.valueOf(stop));
            this.stops.add(adding);

            // Changes the stop name A, B, C, D
            stop = (char) (stop + 1);
        }
    }

        //Racer Factory
    private void addCompetitors(int dimension, int numOfCars){

        Random randomizer = new Random();
        for(int i = 0; i < numOfCars; i++){

                // creates random Coordinates on the gameboard.
                //outside of loop to add to Car()
            int randomX;
            int randomY;

                    //Checks to see if spawn point is taken
            do {
                    randomX = randomizer.nextInt(dimension);
                    randomY = randomizer.nextInt(dimension) ;
            } while (!this.isFreeSpace(randomX, randomY));

                //adding competitor
            Car next = new Car(randomX, randomY, String.valueOf(i + 1));
            this.setDestinations(next);
            this.racers.add(next);
        }
    }
        //used in method above
    private void setDestinations(Car setting){
        //Creates a new list of locations without altering the original
        List<Destination> shuffledList = new ArrayList<>(this.stops);
        Collections.shuffle(shuffledList);
        setting.setDestination(shuffledList);
    }

    //-------------Getters--------------------------------------------------

    public boolean getFinished(){
        return this.pacer.getFinished();
    }

    public List<Car> getRacers(){
        return this.racers;
    }

    public void removeRacer(Car remove){

        if(this.racers.contains(remove)){
            this.racers.remove(remove);
            this.board.remove(remove);
        }
    }
    public void setRacers(List<Car> nextSpots){
        this.racers = nextSpots;
        this.board.addAll(nextSpots);
    }

    public List<GamePiece> getBoard(){
        return this.board;
    }


        //Array of Ranking order to be iterated onto JLables
    public Car[] getResults(){


            //Sorts from smallest to largest
        int x = 0;
        int n = this.racers.size();
        boolean swapped;

            //sets up an array for easier handling and less memory
        Car[] arr = new Car[this.racers.size()];
            //iterates the List into an array
        for(Car next : this.racers){
            arr[x] = next;
            x++;
        }
            //do loop to swap lowest into array[0]
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1].getTurns() > arr[i].getTurns()) {
                    // swap arr[i-1] and arr[i]
                    Car temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;
                    swapped = true;
                }
            }
        } while (swapped);

        return arr;
    }


    //-------------Turn Taking Methods--------------------------------------------------

        //toString() method to complete class
    @Override
    public String toString(){
        String reply = "This class sets up the gameboard and acts as a switchboard to all the different components";
        return reply;
    }
}