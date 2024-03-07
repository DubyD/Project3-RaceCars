public class Destination extends wall {

    private String token;
    public Destination(int x, int y, String display){

        //Setting up baseclass coordinates,
        //toString, isSolid boolean
        super(x,y);
        this.setDisplay(display);
        this.setSolid(false);

        this.token = display;

    }

    public String getToken(){
        return this.token;
    }

    public



}