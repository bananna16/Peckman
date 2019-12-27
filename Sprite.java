import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 *  This implements a Sprite, which is represents a moving
 *  item. It will be the backbone for the most of our other
 *  classes.
 *
 *  @author  Anna Qi
 *  @version April 26, 2018
 *  @author  Period: 5
 *  @author  Assignment: APCS_FinalPacMan
 *
 *  @author  Sources: None
 */
public class Sprite
{
    private boolean visible;
    private Image image;
    private int myX;
    private int myY;
    private int height;
    private int width;
    private int dx;
    private int dy;
    private double speed;

    /**
     * default constructor - it makes the sprite
     * visible and sets it at the origin.
     */
    public Sprite()
    {
        visible = true;
        myX = 0;
        myY = 0;
    }
    
    /**
     * constructor to set a sprite
     * @param x  initial x coordinate of its location
     * @param y  initial y coordinate of its location
     * @param changeX  desired change in X coordinate when the
     * sprite moves
     * @param changeY  desired change in Y coordinate when the
     * @param speed  rate at which sprite moves at
     * sprite moves
     */
    public Sprite (int x, int y, int changeX, int changeY, double speed)
    {
        myX = x;
        myY = y;
        dx = changeX;
        dy = changeY;
        visible = true;
        this.speed = speed;
    }
    
    /**
     * 2 variable constructor that will set the coordinates.
     * @param x    initial x coordinate of the sprite's location.
     * @param y    initial y coordinate of the sprite's location.
     */
    public Sprite (int x, int y)
    {
        myX = x;
        myY = y;
        dx = 0;
        dy = 0;
        visible = true;
        speed = 0;
    }
    
    /**
     * Updates the image that the sprite is.
     * @param url   url of the desired image
     */
    public void updateImg(String url)
    {
        ImageIcon ii = new ImageIcon(url);
        image = ii.getImage();
        width = image.getWidth( null );
        height = image.getHeight( null );
    }
    
    /**
     * Returns if the sprite is visible or not
     * @return  boolean, true if sprite is visible and
     * false is sprite is not.
     */
    public boolean isVisible()
    {
        return visible;
    }
    
    //getters
    public int getX()
    {
        return myX;
    }
    
    public int getY()
    {
        return myY;
    }
    
    public int getHeight()
    {
        return height;
    }
    
    public int getWidth()
    {
        return width;
    }
    
    public Image getImg()
    {
        return image;
    }
    
    public double getSpeed()
    {
        return speed;
    }
    
    public int getDx()
    {
        return dx;
    }
    
    public int getDy()
    {
        return dy;
    }
    
    
    /**
     * Needed to detect collisions!
     * @return  the bounds of this sprite
     */
    public Rectangle getBounds() 
    {
        return new Rectangle(myX, myY, width, height);
    }
    
    // setters

    public void setVisible(boolean vis)
    {
        visible = vis;
    }

    public void setDx(int newDx)
    {
        dx = newDx;
    }

    public void setDy(int newDy)
    {
        dy = newDy;
    }
    
    public void setSpeed(double newSpd)
    {
        speed = newSpd;
    }
    
    public void setCoords(int newX, int newY)
    {
        myX = newX;
        myY = newY;
    }
    
    /**
     * moves the sprite in regards to the sprite's 
     * change in x, change in y, and its speed.
     */
    public void move()
    {
        myX += dx * speed;
        myY += dy * speed;
    }
    
    /**
     * returns a string displaying the X coordinate, Y coordinate, change in x
     * and change in y of the sprite.
     */
    public String toString()
    {
        return "X coordinate: " + getX() + " Y coordinate: " + getY() + " Change in x: "
                        + getDx() + " Change in y: " + getDy();
    }
}
