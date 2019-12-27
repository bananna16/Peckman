/**
 * 
 * This class describes the cherry that PacMan can eat to score lots of points.
 *
 * @author Michelle Kao
 * @version May 4, 2018
 * @author Period: 5
 * @author Assignment: Pac-Man
 *
 * @author Sources: None
 */
public class Cherry extends PacDots
{
    private String imgString = "cherry40.png";


    /**
     * Constructor for Cherry class.
     */
    public Cherry()
    {
        super();
        initCherry();
    }


    /**
     * Constructor for Cherry class.
     * 
     * @param points
     *            number of points
     * @param x
     *            initial x coordinate
     * @param y
     *            initial y coordinate
     */
    public Cherry( int points, int x, int y )
    {
        super( points, x, y );
        initCherry();
    }


    /**
     * 
     * Updates Cherry’s image.
     */
    public void initCherry()
    {
        updateImg( imgString );
    }


    /**
     * String representation of a Cherry.
     */
    public String toString()
    {
        return imgString;
    }

}
