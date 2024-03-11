//Author WD

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Makes a moveable piece on the board
public class Car extends GamePiece{


    private Car prevSpot;
    private List<Destination> map;
    private List<String> passport;
    private boolean finished;
    private int turns;
    private String display;
    private String carNum;
    private int speed;
    private char direction;



    public Car(int x, int y, String carNum){

            //Base class
        super(x,y);

            //Sub class capabilities
        this.prevSpot = null;
        this.map = new ArrayList<Destination>();
        this.passport = new ArrayList<String>();

            //For ending the game/ set at the end of the game
        this.finished = false;
        this.turns = 0;
        this.speed = 0;

            //For gui display
        this.setDisplay("|" + carNum + "|");
        this.carNum = carNum;
        this.direction = ' ';

    }

        //parameter free constructor to complete the class
    public Car(){
        super(-1,-1);

        this.prevSpot = null;
        this.map = null;
        this.passport = null;
        this.finished = false;
        this.turns = -1;
        this.display = " ";
        this.speed = -1;
    }

        //Used to speed up or slow down
    private void setSpeed(int speed){

        //If car is stopped speed up
        if(speed == 0){
            this.speed = 1;

        }
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

        //Used to determine whether to turn or not
    private boolean atDestinationY(){
        if((this.getY() - this.getCurrentDestination().getY()) == 0){
            return true;
        }
        return false;
    }
        //Used to determine whether to turn or not
    private boolean atDestinationX(){
        if((this.getX() - this.getCurrentDestination().getX()) == 0){
            return true;
        }
        return false;
    }


    //------Public Methods-------------------------------------------------
    //------Setters and then Getters---------------------------------------

        //Sets the goals of this particular instance
    public void setDestination(List<Destination> map){
        this.map.addAll(map);
    }

        //Sets how long it took to complete the race
    public void setTurns(int x){
        this.turns = x;
    }
        //In case of collision saves old space
    public void setPrev(Car past){
        this.prevSpot = past;
    }

    public void setDirection(char xy){
        this.direction = xy;
    }

    //emergency brakes speed setter
    public void stop(){
        this.speed = 0;
    }

        //Used to get around obstacles
    public Destination getCurrentDestination(){
        return this.map.get(0);
    }

        //Gets how many turns it took to finish
    public int getTurns(){
        return this.turns;
    }

        //Checks to see if the car is at the destination
    public boolean gotThere(){
        if(this.getX() == this.map.get(0).getX()){
            if(this.map.get(0).getY() == this.getY()){


                this.getNextDestination();
                return true;
            }
        }
        return false;
    }

        //used for iterations of movement
    public int getSpeed(){
        return this.speed;
    }

        //gets whether this instance finished racing or not
    public boolean getFinished(){
        return this.finished;
    }


        //when finished going places
        //will print out Passport results
    public String getResults(){
        String reply = "Racer: "+ this.getCarNum() + " finished in " + this.getTurns() + " turns";
        return reply;
    }


    public String getCarNum(){
        return this.carNum;
    }

    public char getDirection(){
        return this.direction;
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
                    nextX = nextX - 1;

                }else{
                    nextX = nextX + 1;

                }
            }else{
                //Sets the direction from motionless
                //+ or - 1 from the Y axis
                if(yDiff > 0){
                    nextY = nextY - 1;

                }else{
                    nextY = nextY + 1;

                }
            }


            Car nextCar = new Car(nextX, nextY, this.getCarNum());
            nextCar.setSpeed(this.speed);
            nextCar.setPrev(this);
            nextCar.setDestination(this.map);

            return nextCar;
        }


        //used to move the Car
        public Car keepMove(){


            int nextX = this.getX();
            int nextY = this.getY();




                //Adds new coordinates to next iteration of Car
            if(this.direction == 'X'){
                if(this.atDestinationX()){
                    return this.startMove();

                }
                nextX = nextX + 1;

            } else if(this.direction == 'X'){
                if(this.atDestinationX()){
                    return this.startMove();
                }
                nextX = nextX - 1;

            }else if(this.direction == 'Y'){
                if(this.atDestinationY()){
                    return this.startMove();

                }
                nextY = nextY - 1;

            }else if(this.direction == 'Y'){
                if(this.atDestinationY()){

                    return this.startMove();

                }
                nextY = nextY + 1;

            }

            Car next = new Car(nextX, nextY, this.getCarNum());
            next.setPrev(this);
            next.setSpeed(this.speed);
            next.setDestination(this.map);

            return next;

        }




}
