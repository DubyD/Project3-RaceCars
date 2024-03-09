import java.util.Timer;
import java.util.TimerTask;

public class TurnTaker extends TimerTask {

    private City gotham;
    private boolean finished;
    private int turns;
    private Timer clock;

    public TurnTaker(City gotham){
        this.gotham = gotham;
        this.finished = false;
        this.turns = 0;
        this.clock = gotham.getClock();
    }

        //Second constructor to complete class
    public TurnTaker(){
        this.gotham = null;
        this.finished = false;
        this.turns = -1;
        this.clock = null;
    }

    public boolean getFinished(){
        return this.finished;
    }

    @Override
    public void run(){

        this.turns = this.turns + 1;
            //Checks obstacles and
        this.startGame();
        boolean gameOver = true;

        for(Car next : this.gotham.getRacers()){
            gameOver = gameOver && next.getFinished();
            if(gameOver = true){
                next.setTurnCount(this.turns);
            }
        }
        this.finished = gameOver;
        if(this.finished()){
            this.clock.cancel();
        }
    }

    public void startGame(){

        for(Car next : this.gotham.getRacers()){

            //Checks the speed of the vehicle. each vehicle
            if(next.getMotor().getSpeed() == 0){

                //You need to set the speed before starting their journey
                next.getMotor().setSpeed();
                next.startMove();
                for(GamePiece wall : this.gotham.getObstacles()){
                    if(wall.getX() == next.getX()){
                        if(wall.getY() == next.getY()){
                            //------------------------------------need to creating a turning method
                            next = next.getPrevSpot();
                        }
                    }
                }


            }else{

                //Gives an opprotunity to speed up
                next.getMotor().setSpeed();
                for(int i = 0; i < next.getMotor().getSpeed(); i++) {
                    next.keepMove();
                    for (GamePiece wall : this.gotham.getObstacles()) {
                        if (wall.getX() == next.getX()) {
                            if (wall.getY() == next.getY()) {
                                //-------------------------------------need to create a turning method
                                next = next.getPrevSpot();
                            }
                        }
                    }
                }

            }
        }

    }



    @Override
    public String toString(){
        String reply = "This is to separate the moving parts of the game";
        return reply;
    }

}
