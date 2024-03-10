import java.util.ArrayList;
import java.util.List;

public class Car extends GamePiece{


    private Car prevSpot;
    private List<Destination> map;
    private Engine motor;
    private Steering wheel;
    private List<String> passport;
    private boolean finished;
    private boolean collided;
    private boolean turnLeft;
    private int turns;




    public Car(int x, int y, String carNum){

            //Base class
        super(x,y);
        this.setSolid(true);

            //Sub class capabilities
        this.prevSpot = null;
        this.map = new ArrayList<Destination>();
        this.passport = new ArrayList<String>();

            //For ending the game
        this.finished = false;
        this.turns = 0;

            //if collision occurs turnleft activates
            //if turnleft is false turn right if true turn left
        this.collided = false;
        this.turnLeft = false;

            //complex parts
        this.motor = new Engine();
        this.wheel = new Steering(carNum);
    }

        //parameter free constructor to complete the class
    public Car(){
        super(-1,-1);

        this.prevSpot = null;
        this.map = null;
        this.motor = null;
        this.wheel = null;
        this.passport = null;
        this.finished = null;
        this.collided = null;
        this.turnLeft = null;
    }

        //Used to stamp passport with destinations
    private void getNextDestination(){
        this.passport.add(this.map.get(0).getToken());
        this.map.remove(0);

            //Once the passport is completely stamped the next turn will end this cars race
        if(this.map.size() == 0){
            this.finished = true;
        }
    }


    //------public---------------------------------------------------------
    //------Setters and then Getters---------------------------------------

        //Used to set a random order of objectives
    public void setDestination(List<Destination> map){
        this.map = map;
    }
        //Used in case the car speeds through an obstacle
    public void setX(int x){
        this.x = x;

    }

    public void setY(int y){
        this.y = y;
    }


        //In case of collision saves old space
    public void setPrev(Car past){
        this.prevSpot = past;
    }

        //Used to save how much time this car took to finish
    public void setTurnCount(int turns){
        this.turns = turns;
    }

        //Used to get around obstacles
    public Destination getCurrentDestination(){
        return this.map.get(0);
    }

    public boolean gotThere(){
        if(this.map.get(0).getX() == this.getX()){
            if(this.map.get(0).getY() == this.getY()){

                this.getNextDestination();
                return true;
            }
        }
        return false;
    }


    public boolean getFinished(){
        return this.finished;
    }


        //exports the complex pieces of a Car
    public Engine getMotor(){
        return this.motor;
    }

    public Steering getWheel(){
        return this.wheel;
    }



    //-----------Exotic Methods--------------------------------



        //used if vehicle stops at a Destination and needs to initate movement
    public Car startMove(){

            //Escapes the loop
        if(this.finished){
            return this;
        }

        int nextX = this.getX();
        int nextY = this.getY();
        char whichAxis;

            //Where are we moving
        int goalX = this.map.get(0).getX();
        int goalY = this.map.get(0).getY();

            //shortens the largest gap
        int xDiff = this.getX() - goalX;
        int yDiff = this.getY() - goalY;

            //If xDiff  > yDiff EW
            //If yDiff => xDiff NS
        if(Math.abs(xDiff) > Math.abs(yDiff)){
                //Sets the direction from motionless
                //+ or - 1 from the X axis
            if(xDiff > 0){
                nextX = nextX + 1;
                whichAxis = 'E';
            }else{
                nextX = nextX - 1;
                whichAxis = 'W';
            }
        }else{
                //Sets the direction from motionless
                //+ or - 1 from the Y axis
            if(yDiff > 0){
                nextY = nextY + 1;
                whichAxis = 'S';
            }else{
                nextY = nextY - 1;
                whichAxis = 'N';
            }
        }

        Car nextCar = new Car(nextX, nextY, this.wheel.getCarNum());
        nextCar.setPrev(this);
        nextCar.getWheel().setDirection(whichAxis);
    }


        //used to move the Car
    public Car keepMove(){

        char direction = this.wheel.getDirection();
        int nextX = this.getX();
        int nextY = this.getY();
        char whichAxis;

            //Adds new coordinates to next iteration of Car
        if(direction == 'E'){
            nextX = nextX + 1;
            whichAxis = 'E';
        } else if(direction == 'W'){
            nextX = nextX - 1;
            whichAxis = 'W';
        }else if(direction == 'N'){
            nextY = nextY - 1;
            whichAxis = 'N';
        }else if(direction == 'S'){
            nextY = nextY + 1;
            whichAxis = 'S';
        }

        Car next = new Car(nextX, nextY, this.wheel.getCarNum());
        next.setPrev(this);
        next.getWheel().setDirection(whichAxis);2
            //Continues the direction and fixes the wheel if turning
        if(this.collided){
                //Resets the direction if this car turned around an obstacle
            if(this.turnLeft){
                //if it turned left it will redirect back right
                next.getWheel().turn(false);

            }else{
                //if it turned right it will redirect back left
                next.getWheel().turn(true);
            }
        }

        return next;

    }

        //For object interaction. that isnt a Destination
    public void collison(boolean tryLeft){

        if(tryLeft){
            this.collided = true;
            this.turnLeft = true;

        }else{
            this.collided = true;
        }

        this.wheel.turn(this.turnLeft);

    }

    //--Finishing the Race Methods---------------------------------------------------------
        //when finished going places
        //will print out Passport results

    @Override
    public String toString(){
        String reply = "You have collected Destinations: ";
        for(String next : this.passport){
            reply = reply + next + "\n";
        }
        reply = reply + " in " + this.turns + " amount of turns";
        return reply;
    }

}
