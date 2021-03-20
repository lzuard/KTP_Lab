/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;

    //Added code
    @Override
    public boolean equals(Object obj){
        Location loc_obj;
        if (obj.getClass()==getClass()){
            loc_obj=(Location)obj;
            if(this.xCoord==loc_obj.xCoord && this.yCoord==loc_obj.yCoord)
                return true;
            else
                return false;
        }
        else
            return false;
    }

    //Added code
    @Override
    public int hashCode(){
        int result =xCoord*31;
        result*=31*yCoord;
        return result;
    }


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }
}