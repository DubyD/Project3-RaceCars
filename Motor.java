

//Author WD

import java.util.Random;

public class Motor{
    private int speed;

    public Motor(){
        this.speed = 0;
    }

//-------------Setters-----------------------------------------------------


    //When moving the car may speed up
    public void setSpeed(){

        Random randomizer = new Random();

        if(this.speed == 0){
            this.speed = 1;
        }else if(this.speed == 1){

            //Creates a 20% chance to speed up
            int randomNum = randomizer.nextInt(10) + 1;
            if(randomNum > 8){
                this.speed = 2;
            }

        }else if(this.speed == 2){

            //If the tires have friction the Motor will slowdown
            if(this.hasFriction()){
                this.speed = 1;
            }
        }
    }

    public int getSpeed(){
        return this.speed;
    }

    //Stops at each Destination
    public void stop(){
        this.speed = 0;
    }


//------------------exotic-------------------------------------------------

    //When moving fast the car may slowdown
    public boolean hasFriction(){

        Random randomizer = new Random();
        int randomInt = randomizer.nextInt(10) + 1;

        //Has a 20% chance to slowdown Cars moving speed 2
        if(randomInt > 8){
            return true;
        }
        return false;
    }


    //Added this to complete this as a java class
    @Override
    public String toString(){
        String reply = "This handles all of the speed functionality of the Car obj.";
        return reply;
    }


}