import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Car extends GamePiece{


    private Car prevSpot;
    private List<Destination> map;
    private Engine motor;
    private Steering wheel;
    private List<String> passport;
    private boolean finished


    public Car(int x, int y, String display){

            //Base class
        super(x,y);
        this.setSolid(true);

            //Sub class capabilities
        this.prevSpot = null;
        this.map = new ArrayList<Destination>();
        this.passport = new ArrayList<String>();
        this.finished = false;

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

        //Used to match tokens
    private void getNextDestination(){
        this.passport.add(this.map.get(0).getToken());
        this.map.remove(0);
        if(this.map.size() == 0){

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


    //-----------Exotic Methods--------------------------------

        //used if vehicle collides or needs to U-turn

    public void movement(){

            //Where are we moving
        int goalX = this.map.get(0).getX();
        int goalY = this.map.get(0).getY();

            //shortens the largest gap
        int xDiff = this.getX() - goalX;
        int yDiff = this.getY() - goalY;



    }


}
