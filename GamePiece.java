

//Author WD

public class GamePiece {
    //Every gamepiece has coordinates
    protected int x;
    protected int y;

    //Every GamePiece has collidable
    private String display;

    //constructor for the Base Class
    public GamePiece(int x, int y){

        this.x = x;
        this.y = y;
        this.display = "#";

    }

    //Second non-paramter constructor to complete the object
    public GamePiece(){
        this.x = -1;
        this.y = -1;

    }

    //--------Getters----------------------------------------------------
    //Returns X coordinates
    public int getX(){
        return this.x;
    }
    //Returns Y coordinates
    public int getY(){
        return this.y;
    }

    //checks if this object is solid


    //--------Setters----------------------------------------------------


    //Setting the String
    public void setDisplay(String display){
        this.display = display;
    }

    //returning the animation
    @Override
    public String toString(){
        return this.display;
    }
}
