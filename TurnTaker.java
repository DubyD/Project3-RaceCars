//Author WD


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

    //Separates the Timer Task to tidy up the city class
public class TurnTaker extends TimerTask {

    private City gotham;
    private boolean finished;
    private int turns;
    private Timer clock;
    private GameGrid guiUpdater;

    public TurnTaker(City gotham, GameGrid guiUpdater){
        this.gotham = gotham;
        this.finished = false;
        this.turns = 0;
        this.clock = gotham.getClock();
        this.guiUpdater = guiUpdater;
    }

        //Second constructor to complete class
    public TurnTaker(){
        this.gotham = null;
        this.finished = false;
        this.turns = -1;
        this.clock = null;
        this.guiUpdater = null;
    }

        //Used to optimize turning to the shortest path




    @Override
    public void run(){

        this.turns = this.turns + 1;
            //moves cars and checks obstacles
        this.startGame();
        boolean gameOver = true;

            //Checks to see if the race is over
        for(Car next : this.gotham.getRacers()){
            gameOver = gameOver && next.getFinished();
            if(gameOver = true){
                next.setTurnCount(this.turns);
            }
        }
        this.finished = gameOver;
        if(this.finished){
            this.clock.cancel();
        }
            //Updates Gui
        this.guiUpdater.updateLabels();
    }

    public boolean getFinished(){
        return this.finished;
    }

    public void startGame(){

        List<Car> currentRacers = new ArrayList<Car>();

        for(Car next : this.gotham.getRacers()){

                //Skips any finished racers
            if(next.getFinished()){
                continue;
            }


                //Checks the speed of the vehicle. each vehicle
            if(next.getMotor().getSpeed() == 0){

                    //You need to set the speed before starting their journey
                next.getMotor().setSpeed();
                Car check = next.startMove();


                for(GamePiece wall : this.gotham.getObstacles()){
                    if(wall.getX() == check.getX()){
                        if(wall.getY() == check.getY()){


                            int diff;
                            if(check.getWheel().getDirection() == 'N' || check.getWheel().getDirection() == 'S'){
                                diff = next.getCurrentDestination().getX() - next.getX();
                            }else{
                                diff = next.getCurrentDestination().getY() - next.getY();
                            }



                            next.getMotor().turning();
                            next.collison(this.leftTurn(check.getWheel().getDirection(), diff));
                            check = next;
                        }
                    }
                }

                if(check.gotThere()){
                    check.getMotor().stop();
                    check.getWheel().setStop();

                }
                currentRacers.add(check);


            }else{

                    //Gives an opprotunity to speed up
                next.getMotor().setSpeed();
                for(int i = 0; i < next.getMotor().getSpeed(); i++) {
                    Car check = next.keepMove();


                    for (GamePiece wall : this.gotham.getObstacles()) {

                            //if you work through the nested loops it
                        if (wall.getX() == check.getX()) {
                            if (wall.getY() == check.getY()) {

                                    //slows the car down on collision
                                i = i + 1;
                                int diff;
                                if(check.getWheel().getDirection() == 'N' || check.getWheel().getDirection() == 'S'){
                                    diff = next.getCurrentDestination().getX() - next.getX();
                                }else{
                                    diff = next.getCurrentDestination().getY() - next.getY();
                                }



                                next.getMotor().turning();
                                next.collison(this.leftTurn(check.getWheel().getDirection(), diff));
                                check = next;
                            }
                        }

                    }

                        //Checks if it hit the destination
                    if(check.gotThere()){
                        check.getMotor().stop();
                        check.getWheel().setStop();

                    }
                    currentRacers.add(check);
                }

            }



        }

        this.gotham.setRacers(currentRacers);

    }

        //Used in StartGame for Optimized turning
    private boolean leftTurn(char direction, int diff){
        boolean leftTurn = false;
        //Superior turning mechanism for each direction
        if(direction == 'E'){
            //Turns N or S
            if(diff < 0){
                leftTurn = true;
            }else{
                leftTurn = false;
            }

        }else if(direction == 'W') {
            //Turn S or N
            if(diff > 0){
                leftTurn = true;
            }else{
                leftTurn = false;
            }

        }else if(direction == 'N'){
            //Turns W or E
            if(diff < 0){
                leftTurn = true;
            }else{
                leftTurn = false;
            }

        }else if(direction == 'S'){
            //Turns E or W
            if(diff > 0){
                leftTurn = true;
            }else{
                leftTurn = false;
            }
        }
        return leftTurn;
    }




    @Override
    public String toString(){
        String reply = "This is to separate the moving parts of the game";
        return reply;
    }

}
