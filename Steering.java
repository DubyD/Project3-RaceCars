//Author WD


    //Separates directionals and Gui display of the Car
public class Steering {

    private char direction;
    private String carNum;
    private String display;

    public Steering(String carNum){
        this.direction = ' ';
        this.carNum = carNum;
        this.display = "|" + this.carNum + "|";
    }

    public String getCarNum(){
        return this.carNum;
    }


        //Sets the Gui display of the car based on direction


        //alters the Cars toString()
    public String getDisplay(){
        return this.display;
    }
    public char getDirection(){
        return this.direction;
    }

        //Used to stop the Car
    public void setStop(){
        this.direction = ' ';
    }

        //Used to set starting movement
    public void setDirection(char direction){
        this.direction = direction;
    }

        //Must turn around an object
    public void turn(boolean leftTurn){

                //Handles turning facing East
        if(this.direction == 'E'){
            if(!leftTurn){
                this.direction = 'N';
            }else{
                this.direction = 'S';
            }
                //Handles turning facing West
        }else if(this.direction == 'W'){
            if(!leftTurn){
                this.direction = 'S';
            }else{
                this.direction = 'N';
            }
                //Handles turning facing North
        }else if(this.direction == 'N'){
            if(!leftTurn){
                this.direction = 'W';
            }else{
                this.direction = 'E';
            }
                //Handles turning facing South
        }else if(this.direction == 'S'){
            if(!leftTurn){
                this.direction = 'E';
            }else{
                this.direction = 'W';
            }
        }
    }


        //toString to complete the class
    @Override
    public String toString(){
        String reply = "this class is used to separate the directional to tidy up the car";
        return reply;
    }

}
