//Author WD


import java.util.*;

    //Back end of all the game components
public class City {

    private Timer clock;

    private List<GamePiece> obstacles;

    private List<GamePiece> board;
    private List<Destination> stops;
    private List<Car> racers;

    private TurnTaker pacer;

    public City(int dimension, int racers, GameGrid gui){

        this.clock = new Timer();
            //Sets the boundaries

        this.racers = new ArrayList<Car>();
        this.stops = new ArrayList<Destination>();
        this.obstacles = new ArrayList<GamePiece>();
        this.pacer = new TurnTaker(this, gui);

            //Methods for initiating GamePieces
        this.makeDestinations(dimension);
        this.addCompetitors(dimension, racers);
        this.addObstacles(dimension);


        this.board = new ArrayList<GamePiece>();
        this.board.addAll(this.stops);
        this.board.addAll(this.obstacles);
        this.board.addAll(this.racers);

        //This sets the Clock to
        this.clock.scheduleAtFixedRate(this.pacer, 0, 3000);


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

        //Creates 4 destinations for travellers
    private void makeDestinations(int dimension){
        //Randomizes their locations
        Random randomizer = new Random();
        char stop = 'A';

        for(int i = 0; i < 4; i++){
                // creates random Coordinates on the gameboard.
                // -2 to exclude borders of the game
            int randomX = randomizer.nextInt(dimension) - 2;
            int randomY = randomizer.nextInt(dimension) -2;

            if(this.stops.size() > 0){
                    //initiates false to start the loop
                boolean freeSpace = false;
                while(freeSpace == false) {

                        //Needed to change to true to compare true && true
                    freeSpace = true;

                    for (Destination next : this.stops) {

                            //checks the spaces availability
                            //if true for all next's it will remain true
                            //if false for even 1 next it will stay false
                            //and reiterates through loop
                        freeSpace = freeSpace && validSpot(randomX, randomY, next);

                    }
                        //Changes Variables to hopefully exit loop
                        //Without having 2 pieces occupying the same space
                    if (freeSpace == false) {
                        randomX = randomizer.nextInt(dimension) - 2;
                        randomY = randomizer.nextInt(dimension) - 2;
                    }
                }

            }
                //creates a new stop and adds it to the List
            Destination adding = new Destination(randomX, randomY, String.valueOf(stop));
            this.stops.add(adding);
                //Changes the stop name A, B, C, D
            stop = (char) (stop + 1);
        }
    }

        //Racer Factory
    private void addCompetitors(int dimension, int numOfCars){

        Random randomizer = new Random();

        for(int i = 0; i < numOfCars; i++){

                // creates random Coordinates on the gameboard.
                // -2 to exclude borders of the game
                //outside of loop to add to Car()
            int randomX = randomizer.nextInt(dimension) - 2;
            int randomY = randomizer.nextInt(dimension) -2;

            for(Destination next : this.stops){
                    //Checks to see if spawn point is taken
                boolean freeSpace = false;
                while(freeSpace == false) {

                        //Needed to change to true to compare true && true
                    freeSpace = true;

                    for (Destination stops : this.stops) {

                            //checks the spaces availability
                            //if true for all next's it will remain true
                            //if false for even 1 next it will stay false
                            //and reiterates through loop
                        freeSpace = freeSpace && validSpot(randomX, randomY, stops);

                    }
                        //Changes Variables to hopefully exit loop
                        //Without having 2 pieces occupying the same space
                    if (freeSpace == false) {
                        randomX = randomizer.nextInt(dimension) - 2;
                        randomY = randomizer.nextInt(dimension) - 2;
                    }
                }
            }
                //adding competitor
            Car next = new Car(randomX, randomY, String.valueOf(i));
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

    private void addObstacles(int dimension) {

        Random randomizer = new Random();

        int numOfObstacles = dimension - 3;
        // creates random Coordinates on the gameboard.
        // -2 to exclude borders of the game
        //outside of loop to add to Car()
        int randomX = randomizer.nextInt(dimension) - 2;
        int randomY = randomizer.nextInt(dimension) - 2;
        for (int i = 0; i < numOfObstacles; i++) {
            for (Destination next : this.stops) {
                //Checks to see if spawn point is taken
                boolean freeSpace = false;
                while (freeSpace == false) {

                    //Needed to change to true to compare true && true
                    freeSpace = true;

                    for (Destination stops : this.stops) {

                        //checks the spaces availability
                        //if true for all next's it will remain true
                        //if false for even 1 next it will stay false
                        //and reiterates through loop
                        freeSpace = freeSpace && validSpot(randomX, randomY, stops);

                    }
                    for (Car players : this.racers) {

                        //checks the spaces availability
                        //if true for all next's it will remain true
                        //if false for even 1 next it will stay false
                        //and reiterates through loop
                        freeSpace = freeSpace && validSpot(randomX, randomY, players);
                    }

                    //Changes Variables to hopefully exit loop
                    //Without having 2 pieces occupying the same space
                    if (freeSpace == false) {
                        randomX = randomizer.nextInt(dimension) - 2;
                        randomY = randomizer.nextInt(dimension) - 2;
                    }
                }
            }
            GamePiece next = new GamePiece(randomX, randomY);
            next.setDisplay("#");
            this.obstacles.add(next);

        }
    }

    //-------------Getters--------------------------------------------------

    public boolean getFinished(){
        return this.pacer.getFinished();
    }

    public List<Car> getRacers(){
        return this.racers;
    }

    public void setRacers(List<Car> nextSpots){
        this.racers = nextSpots;
    }

    public Timer getClock(){
        return this.clock;
    }

    public List<GamePiece> getObstacles(){
        return this.obstacles;
    }

    public List<GamePiece> getBoard(){
        return this.board;
    }

        //String of The Race results
    public String getResults(){
        String reply = "";
        int turns = 0;
        Car winner = new Car();

        for(Car next : this.racers){
            if(turns == 0){
                winner = next;
            }else{
                if(next.getTurns() < winner.getTurns()){
                    winner = next;
                }
            }
        }
        reply = winner.getWheel().getCarNum() + " won the race!\n"+
                "The results are:\n\n";

        for(Car next : this.racers){
            reply = reply + next.results() + "\n";
        }
        return reply;
    }


    //-------------Turn Taking Methods--------------------------------------------------


        //toString() method to complete class
    @Override
    public String toString(){
        String reply = "This class sets up the gameboard and acts as a switchboard to all the different components";
        return reply;
    }
}