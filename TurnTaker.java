//Author WD


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import java.util.TimerTask;

    //Separates the Timer Task to tidy up the city class
public class TurnTaker extends TimerTask implements ActionListener {

    private City gotham;
    private boolean finished;
    private int turns;
    private Timer clock;
    private GameGrid guiUpdater;

    public TurnTaker(City gotham, GameGrid guiUpdater){
        this.gotham = gotham;
        this.finished = false;
        this.turns = 0;
        this.clock = new Timer(300, this);
        this.guiUpdater = guiUpdater;
        this.clock.start();

    }

        //Second constructor to complete class
    public TurnTaker(){
        this.gotham = null;
        this.finished = false;
        this.turns = -1;
        this.clock = null;
        this.guiUpdater = null;
    }



        @Override
        public void actionPerformed(ActionEvent e) {
            this.run();
        }

        @Override
    public void run(){


        this.turns = this.turns + 1;
            //moves cars and checks obstacles
        this.startGame();
        boolean gameOver = true;

            //Checks to see if the race is over
        for(Car next : this.gotham.getRacers()){
            gameOver = gameOver && next.getFinished();
            if(next.getFinished()){

                if(next.getTurns() == 0){
                    next.setTurns(this.turns);
                }
            }
        }
        this.finished = gameOver;
        if(this.finished){
            this.clock.stop();
        }

            //Updates Gui
        this.guiUpdater.updateLabels();
    }

    public boolean getFinished(){
        return this.finished;
    }

    private void startGame(){


        List<Car> currentRacers = new ArrayList<Car>();

        List<Car> oldRacers = new ArrayList<>();
        oldRacers.addAll(this.gotham.getRacers());
        for(Car next : oldRacers){

                //Skips any finished racers
            if(next.getFinished()){

                if(next.getTurns() == 0){
                    next.setTurns(this.turns);
                }
                continue;
            }


                //Checks the speed of the vehicle. each vehicle
            if(next.getMotor().getSpeed() == 0){

                    //You need to set the speed before starting their journey
                next.getMotor().setSpeed();
                Car check = next.startMove();

                if(check.gotThere() == true){
                    check.getMotor().stop();
                    check.getWheel().setStop();

                }
                currentRacers.add(check);


            }else{

                    //Gives an opprotunity to speed up
                next.getMotor().setSpeed();
                for(int i = 0; i < next.getMotor().getSpeed(); i++) {
                    Car check = next.keepMove();

                        //Checks if it hit the destination
                    if(check.gotThere()){
                        check.getMotor().stop();
                        check.getWheel().setStop();

                    }
                    currentRacers.add(check);
                }

            }

                //Removes old spot
            this.gotham.removeRacer(next);

        }

        this.gotham.setRacers(currentRacers);


    }

    @Override
    public String toString(){
        String reply = "This is to separate the moving parts of the game";
        return reply;
    }

}
