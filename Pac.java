import java.awt.*;
import java.awt.event.*;


/**
 * User-controlled sprite that earns points by eating pac-dots, fruits, and
 * vulnerable ghosts. Has lives and dies when out of lives. Lives are lost from
 * interaction with aggressive ghosts.
 *
 * @author Angela Zhao
 * @version May 7, 2018
 * @author Period: 5
 * @author Assignment: APCSFinalProject_PacMan
 *
 * @author Sources: None
 */

public class Pac extends Sprite
{
    private int lives;

    private Board b;


    /**
     * Constructor that sets initial speed as 6 and sets initial direction to
     * the left. Sets number of lives to 2.
     * 
     * @param x
     *            the x coordinate of PacMan's initial location
     * @param y
     *            the y coordinate of PacMan's initial location
     * 
     * @param b
     *            a board for pacman to be shown on
     */
    public Pac( int x, int y, Board b )
    {
        super( x, y, 0, 0, 1 );
        lives = 3;
        this.b = b;
        updateImg( "PacManClosed.png" );
    }


    /**
     * Controls PacMan's movement when the arrow keys are pressed
     * 
     * @param k
     *            KeyEvent
     */
    public void keyPressed( KeyEvent k )
    {
    	int key = k.getKeyCode();
        if ( key == KeyEvent.VK_LEFT && b.canMoveLeft( getX(), getY() ) )
        {
            setDx( -50 );
            setDy( 0 );
            updateImg( "PacManLeft.png" );
        }
        else if ( key == KeyEvent.VK_RIGHT && b.canMoveRight( getX(), getY() ) )
        {
            setDx( 50 );
            setDy( 0 );
            updateImg( "PacManRight.png" );
        }
        else if ( key == KeyEvent.VK_UP && b.canMoveUp( getX(), getY() ) )
        {
            setDx( 0 );
            setDy( -50 );
            updateImg( "PacManUp.png" );
        }
        else if ( key == KeyEvent.VK_DOWN && b.canMoveDown( getX(), getY() ) )
        {
            setDx( 0 );
            setDy( 50 );
            updateImg( "PacManDown.png" );
        }
    }


    /**
     * Determines PacMan's responses to collisions with different objects
     * 
     * @param o
     *            object that PacMan collides with
     * 
     * @return boolean
     */
    public boolean eat( Object o )
    {
        if ( o instanceof PacDots )
        {
            b.incrScore( ( (PacDots)o ).getPoints() );
        }
        if ( o instanceof Cherry )
        {
            b.incrScore( 100 );
            ( (Cherry)o ).disappear();
        }
        else if ( o instanceof Ghost )
        {
            lives--;
            return false;
        }
        return false;
    }


    /**
     * Returns number of lives PacMan currently has
     * 
     * @return lives
     */
    public int getLives()
    {
        return lives;
    }


    /**
     * Sets PacMan's number of lives
     * 
     * @param l
     *            number of lives to set PacMan's lives to
     */
    public void setLife( int l )
    {
        lives = l;
    }


    /**
     * Subtracts from PacMan's lives
     * 
     * @param l
     *            lives to be subtracted
     */
    public void loseLife( int l )
    {
        lives = lives - l;
    }


    /**
     * Draws PacMan
     * 
     * @param g
     *            Graphics
     */
    public void draw_Pac( Graphics g )
    {
        g.drawImage( getImg(), getX(), getY(), getWidth(), getHeight(), null );
    }


    /**
     * Stops PacMan
     */
    public void stop()
    {
        setDx( 0 );
        setDy( 0 );
    }


    /**
     * returns string representation of object
     * 
     * @return string
     */
    public String toString()
    {
        return "X coordinate: " + getX() + " Y coordinate: " + getY() + " Lives: " + lives;
    }
}
