//Author WD

import java.util.Random;

    //Handles Speed of moveable objects
public class Engine {

    private int speed;

    public Engine(){
        this.speed = 0;
    }

        //Speed Setters
    public void setSpeed(){

            //If car is stopped speed up
        if(this.speed == 0){
            this.speed = 1;

        }else if(this.speed == 1) {

            //Car has a 20% to speed up
            Random random = new Random();
            int randomNumber = random.nextInt(10) + 1;
            //Double Speed
            if (randomNumber > 7) {
                this.speed = 2;
            }
        }
    }

        //Slow down before you turn
    public void turning(){
        if(this.speed == 2){
            this.speed = 1;
        }
    }


        //emergency brakes speed setter
    public void stop(){
        this.speed = 0;
    }

        //used for iterations of movement
    public int getSpeed(){
        return this.speed;
    }

        //this toString is used to complete the class
    @Override
    public String toString(){
        String reply = "Engine class is used to separate the speed change methods to tidy up the car";
        return reply;
    }

}
