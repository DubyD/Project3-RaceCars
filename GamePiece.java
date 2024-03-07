public class Gamepiece{

    //Every gamepiece has coordinates
    private int x;
    private int y;

    //Every GamePiece has collidable
    private boolean solid;
    private String display;

    //constructor for the Base Class
    public GamePiece(int x, int y){

        this.x = x;
        this.y = y;
        this.solid = true;
        this.display = "";

    }

    //Second non-paramter constructor to complete the object
    public GamePiece(){
        this.x = -1;
        this.y = -1;
        this.solid = false;
    }

    //Returns X coordinates
    public int getX(){
        return this.x;
    }
    //Returns Y coordinates
    public int getY(){
        return this.y;
    }

    //checks if this object is solid
    public boolean getSolid(){
        return this.solid;
    }

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