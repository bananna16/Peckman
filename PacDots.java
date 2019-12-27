import java.awt.Graphics;


/**
 * 
 * This class describes the Pac Dot that PacMan can eat to score points.
 *
 * @author Michelle Kao
 * @version May 4, 2018
 * @author Period: 5
 * @author Assignment: Pac-Man
 *
 * @author Sources: None
 */

public class PacDots extends Sprite
{
    private int myPoints;


    /**
     * Constructor for PacDots
     */
    public PacDots()
    {
        super();
        initDot();
    }


    /**
     * Constructor for PacDots
     * 
     * @param points
     *            number of points
     * @param x
     *            initial x coordinate
     * @param y
     *            initial y coordinate
     */
    public PacDots( int points, int x, int y )
    {
        super( x, y );
        myPoints = points;
        initDot();
    }


    /**
     * 
     * Updates the image for PacDot.
     */
    public void initDot()
    {
        updateImg( "PacDot2.png" );
    }


    /**
     * Returns the points.
     * 
     * @return points of the PacDot
     */
    public int getPoints()
    {
        return myPoints;
    }


    /**
     * Sets the PacMan to invisible and moves it off of the board
     */
    public void disappear()
    {
        setVisible( false );
        setCoords( -100, -100 );
    }


    /**
     * 
     * Draws a PacDot
     * 
     * @param g
     *            Graphics
     */
    public void drawDot( Graphics g )
    {
        g.drawImage( getImg(),
            getX() - getWidth() / 2,
            getY() - getHeight() / 2,
            getWidth(),
            getHeight(),
            null );
    }
}
