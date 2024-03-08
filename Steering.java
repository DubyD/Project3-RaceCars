public class Steering {

    private char direction;
    private String carNum;
    private String display;

    public Steering(String carNum){
        this.direction = ' ';
        this.display = " ";
        this.carNum = carNum;
    }




        //Sets the Gui display of the car based on direction
    private void setDisplay(){
            //If the car isn't moving
        if(this.direction = ' '){
            this.display = "|" + this.carNum + "|";

            //if the car is moving
        }else if(this.direction == 'E'){
            this.display = this.carNum + ">"
        }else if(this.direction == 'W'){
            this.display = "<" + this.carNum;
        }else if(this.direction == 'N'){
            this.display = "^\n" + this.carNum;
        }else if(this.direction == 'S'){
            this.display = this.carNum + "\nv"
        }
    }

        //alters the Cars toString()
    public String getDisplay(){
        return this.display;
    }
    public char getDirection(){
        return this.direction;
    }

        //Basic deconstruction piece of NS or EW and determines if the car U-turns
    private boolean directional(int one, int two){
        if((one - two) > 0){
            return true;
        }
        return false;
    }

        //Used to stop the Car
    public void setStop(){
        this.direction = ' ';
        this.setDisplay();
    }

        //Used to set moving direction
    public void setDirection(int current, int goal, String xy){
            //East South directionals
        if((current - goal) > 0){
            if(xy.equals("X")){
                this.direction = 'E';
                this.setDisplay();
            }else{
                this.direction = 'S'
            }
            //West North directionals
        }else{
            if(xy.equals("X")){
                this.direction = 'W';
            }else{
                this.direction = 'N'
            }
        }
        this.setDisplay();
    }

    public void leftTurn(){

    }
        //Checks to see if the car needs to turn true = noTurns false = needs to turn;
        //Used from after it reaches a Destination or getting around obstacles
    public boolean noTurns(int current, int goal, String xy){

        boolean reply;
            //early escape. if they are equal then it will return false
        if(current == goal){
            return false;
        }

            //if true the number is positive
            //'>' moving east or
            //'v' moving south
        if(this.directional(current, goal) > 0){

            if(xy.equals("X")){
                    //if it's X
                if(this.direction == 'E'){
                    reply = true;
                }else if(this.direction == 'W'){
                    reply = false;
                }

            }else{
                    //if it's Y
                if(this.direction == 'S'){
                    reply = true;
                }else if(this.direction == 'N'){
                    reply = false;
                }

            }

            //if false the number is negative
            //'<' moving west or
            //'^' moving north
        }else{
            if(this.directional(current, goal)){
                if(xy.equals("X")) {
                        //if it's X
                    if (this.direction == 'E') {
                        reply = false;
                    } else if (this.direction == 'W') {
                            //if they are equal it must turn either N or S
                        reply = true;
                    }

                }else{
                        //if it's Y
                    if(this.direction == 'S'){
                        reply = false;
                    }else if(this.direction == 'N'){
                        reply = true;
                    }
                }
            }

        }
        return reply;
    }

        //toString to complete the class
    @Override
    public String toString(){
        String reply = "this class is used to separate the directional to tidy up the car";
        return reply;
    }

}
