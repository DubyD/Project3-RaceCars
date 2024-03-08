import java.util.*;


public class City {

    private Timer clock;
    private List<GamePiece> walls;
    private List<Destination> stops;
    private List<Car> racers;

    public City(int dimension){

        this.clock = new Timer();
            //Sets the boundaries
        this.walls = new ArrayList<GamePiece>;
        this.sheetRock(dimension);



    }

    public City(){

    }

        //Sets up borders of the game
    private void sheetRock(int dimension){

            //iterates through the size of the game board
        for(int x = 0; x < dimension; x++){
            for(int y = 0; y < dimension; y++){

                    //Sets the boundaries
                if(x == 0 || x == dimension){
                    GamePiece wall = new GamePiece(x,y);
                    wall.setDisplay("*");
                    this.walls.add(wall);
                }else if(y == 0 || y == dimension){
                    GamePiece wall = new GamePiece(x,y);
                    wall.setDisplay("*");
                    this.walls.add(wall);
                }
            }
        }
    }

        //Used to compare coordinates
    private boolean validSpot(int x, int y, GamePiece one){
        if(x == one.getX()){
            if(y == one.getY()){
                return false;
            }
        }
        return true;
    }

    /*
    private void obstacles(int dimension){

            //doesn't spawn buildings unless the size is greater than 6x6
        if(dimension > 5){
            List<GamePiece> obstacles = new ArrayList<GamePiece>();
            Random randomizer = new Random();

            int numOfBuildings = dimension - 4;
            for(int n = 0; n < numOfBuildings; n++){
                boolean newRandom = true;
                int randomX = randomizer.nextInt(dimension) - 3;
                int randomY = randomizer.nextInt(dimension) - 3;
                for(GamePiece next : obstacles){
                    if(next.getX() == randomX){
                        if(next.getY() == randomY){
                            newRandom = false;
                        }
                    }
                }

            }
        }
    }*/

        //Creates 4 destinations for travellers
    private void makeDestinations(int dimensions){
            //Randomizes their locations
        Random randomizer = new Random();
        char stop = 'A';
        for(int i = 0; i < 4; i++){

                //creates random Coordinates on the gameboard. excluding walls
            int randomX = randomizer.nextInt(dimensions) - 2;
            int randomY = randomizer.nextInt(dimensions) -2;

            if(this.stops.size() > 0){
                    //initiates false to start the loop
                boolean freeSpace = false;
                while(freeSpace == false) {

                        //Needed to change to compare true && true
                    freeSpace = true;

                    for (Destination next : this.stops) {
                            //checks the spaces availability
                        freeSpace = freeSpace && validSpot(randomX, randomY, next);
                    }
                    if (freeSpace == false) {
                            //new spawn points if the space was taken
                        randomX = randomizer.nextInt(dimensions) - 2;
                        randomY = randomizer.nextInt(dimensions) - 2;
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

    private void setDestinations(Car setting){
            //Creates a new list of locations without altering the original
        List<Destination> shuffledList = new ArrayList<>(this.stops);
        Collections.shuffle(shuffledList);
        setting.setDestination(shuffledList);

    }

}