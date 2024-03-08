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
    private char workAround;


    public Car(int x, int y, String display){

            //Base class
        super(x,y);
        this.setSolid(true);

            //Sub class capabilities
        this.prevSpot = null;
        this.map = new ArrayList<Destination>();
        this.passport = new ArrayList<String>();
        this.finished = false;
        this.collided = false;

            //complex parts
        this.motor = new Engine();
        this.wheel = new Steering(display);
        this.setDisplay(this.wheel.setDisplay());

    }

    public Car(){
        super(-1,-1);

        this.prevSpot = null;
        this.map = null;
        this.motor = null;
        this.wheel = null;
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

        //Crash course
    public void setCollided(){
        this.collided = true;
        this.motor.stop();
        char whichAxis = this.wheel.getDirection();

        if(whichAxis == 'E' || whichAxis == 'W'){
            this.workAround = 'X';
        }else{
            this.workAround = 'Y';
        }

    }

        //exports the complex pieces of a Car
    public Engine getMotor(){
        return this.motor;
    }

    public Steering getWheel(){
        return this.wheel;
    }

    //-----------Exotic Methods--------------------------------



        //used if vehicle initates movement
    public void startingMovement(){

        this.collided = false;
        this.motor.setSpeed();
            //Where are we moving
        int goalX = this.map.get(0).getX();
        int goalY = this.map.get(0).getY();

            //shortens the largest gap
        int xDiff = this.getX() - goalX;
        int yDiff = this.getY() - goalY;
        
            //For collisions with none destinations
        if(this.workAround == 'X'){
            xDiff = xDiff * 0;
            this.workAround = ' ';
        }
        if(this.workAround == 'Y'){
            yDiff = yDiff * 0;
            this.workAround = ' ';
        }

            //If xDiff  > yDiff EW
            //If yDiff => xDiff NS
        if(Math.abs(xDiff) > Math.abs(yDiff)){
                //Sets the direction from motionless
                //+ or - 1 from the X axis
            this.wheel.setDirection(this.getX(), goalX, "X");
            this.setX(this.getX() + (xDiff / Math.abs(xDiff)));

        }else{
                //Sets the direction from motionless
                //+ or - 1 from the Y axis
            this.wheel.setDirection(this.getY(), goalY, "Y");
            this.setY(this.getY() + (yDiff / Math.abs(yDiff)));

        }

    }

    public void keepMoving(){

        this.motor.setSpeed();
        char direction = this.wheel.getDisplay();

    }

}
