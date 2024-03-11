public class Destination extends GamePiece{

    private String token;


    public Destination(int x, int y, String display){

        //Setting up baseclass coordinates,
        //toString, isSolid boolean
        super(x,y);
        this.setDisplay(display);

        this.token = display;

    }

    public String getToken(){
        return this.token;
    }

    @Override
    public String toString(){
        return this.token;
    }

}