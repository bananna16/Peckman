import java.awt.Graphics;

/**
 *  This class describes a Ghost that chases the Pacman
 *  around the board. 
 *
 *  @author  Anna Qi
 *  @version April 26, 2018
 *  @author  Period: 5
 *  @author  Assignment: APCS_FinalPacMan
 *
 *  @author  Sources: None
 */
public class Ghost extends Sprite
{
    private static int dx = 25;
    private static int dy = 25;
    private final static double regSpeed = 1;
    private Board myB;

    /**
     * Constructor that sets
     * the speed to a default. 
     */
    public Ghost(int x, int y, Board b)
    {
        super(x, y, dx, dy, regSpeed);
        initGhost();
        myB = b;
    }

    /**
     * Updates the image to a ghost, sets the ghost to chasing,
     * and initializes it as unedible.
     */
    public void initGhost()
    {
        updateImg("ghost.gif");
        setVisible( true );
    }


    /**
     * Moves the ghost depending on where the pacman is.
     * It will take the shortest route to get closer to
     * the pacman.
     * @param pacX
     * @param pacY
     */
    public void update(int pacX, int pacY)
    { 
        int horizontalDifference = getX() - pacX;
        int verticalDifference = getY()- pacY;
        boolean verticalMoreImportant = Math.abs(verticalDifference) > Math.abs(horizontalDifference);
        String preferredHorizontal;
        String preferredVertical;
        if (horizontalDifference > 0)
        {
            preferredHorizontal = "LEFT";
        }
        else
        {
            preferredHorizontal = "RIGHT";
        }

        if (verticalDifference > 0)
        {
            preferredVertical = "UP";
        }
        else
        {
            preferredVertical = "DOWN";
        }

        if (getDx() > 0 && myB.canMoveRight(getX(), getY()) && Math.random() > .5)
        {
            setDx(dx);
            setDy(0);
            return;
        }
        else if (getDx() < 0 && myB.canMoveLeft( getX(), getY() ) && Math.random() > .5)
        {
            setDx(-dx);
            setDy(0);
            return;
        }
        else if (getDy() < 0 && myB.canMoveUp( getX(), getY() ) && Math.random() > .5)
        {
            setDy(-dy);
            setDx(0);
            return;
        }
        else if (getDy() > 0 && myB.canMoveDown( getX(), getY() ) && Math.random() > .5)
        {
            setDy(dy);
            setDx(0);
            return;
        }

        if (!verticalMoreImportant)
        {
            if (preferredHorizontal.equals( "LEFT" ) && myB.canMoveLeft(getX(), getY()))
            {
                setDx(-dx);
                setDy(0);
                return;
            }
            else if (preferredHorizontal.equals( "RIGHT" ) && myB.canMoveRight(getX(), getY()))
            {
                setDx(dx);
                setDy(0);
                return;
            }
            else if (preferredVertical.equals( "UP" ) && myB.canMoveUp(getX(), getY()) )
            {
                setDy(-dy);
                setDx(0);
                return;
            }
            else if (preferredVertical.equals( "DOWN" ) && myB.canMoveDown(getX(), getY()) )
            {
                setDy(dy);
                setDx(0);
                return;
            }
        }
        else if (verticalMoreImportant)
        {
            if (preferredVertical.equals( "UP" ) && myB.canMoveUp(getX(), getY()) )
            {
                setDy(-dy);
                setDx(0);
                return;
            }
            else if (preferredVertical.equals( "UP" ) && myB.canMoveDown(getX(), getY()) )
            {
                setDy(dy);
                setDx(0);
                return;
            }
            else if (preferredHorizontal.equals( "LEFT" ) && myB.canMoveLeft(getX(), getY()) )
            {
                setDx(-dx);
                setDy(0);
                return;
            }
            else if (preferredHorizontal.equals( "RIGHT" ) && myB.canMoveRight(getX(), getY()) )
            {
                setDx(dx);
                setDy(0);
                return;
            }
        }
    }

    /**
     * draws a ghost
     * @param g   where to draw the desired ghost
     */
    public void draw_Ghost(Graphics g)
    {
        g.drawImage( getImg(), getX(), getY(), getWidth(), getHeight(), null);
    }

    /**
     * returns a string representation of the ghost
     */
    public String toString()
    {
        return "X coordinate: " + getX() + " Y coordinate: " + getY() + " Change in x: "
                        + getDx() + " Change in y: " + getDy();
    }

    public void stop()
    {
        setDx(0);
        setDy(0);
    }

}
