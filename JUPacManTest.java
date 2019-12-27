import org.junit.jupiter.api.Test;
import junit.framework.JUnit4TestAdapter;
import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Pacman tests: 
 * PacDots //michelle
 * Pacman //angela
 * Board //anna
 * Ghost // anna
 * Sprite // anna
 * Cherry //michelle
 *
 * @author Angela Zhao
 * @author Anna Qi
 * @author Michelle Kao
 * @version 5/31/2018
 * @author Assignment: Final Project - Pacman
 * 
 * @author Sources: None
 *
 */
public class JUPacManTest
{
    /**
     * Pac tests:
     *  pacConstructor - constructs Pac and compares toString
     *  pacEat - compares results returned by different calls to Pac's eat method
     *  to expected values
     *  pacGetLives - compares value returned to expected value
     *  pacSetLife - calls method and compares returned value to expected value
     *  pacLoseLife - calls method and compares returned value to expected value
     *  pacStop - calls method and compares returned value to expected value
     *  pacToString - compares value returned to expected value
     */
    
    private Board b = new Board();
    @Test
    public void pacConstructor()
    {
        Pac p = new Pac(0, 0, b);
        String test = p.toString();
        assertTrue( "<< Invalid Pac Constructor >>",
            test.contains( "X coordinate: " + p.getX() )
            && test.contains( "Y coordinate: " + p.getY())
            && test.contains( "Lives: " + p.getLives() ));
    }
    
    @Test
    public void pacEat()
    {
        Pac p = new Pac(0, 0, b);
        int score = b.getScore();
        PacDots pd2 = new PacDots( 10, 0, 0);
        p.eat( pd2 );
        assertFalse( "<< Score did not increase >>",
            score == b.getScore());
        
        int lives = p.getLives();
        p.eat( new Ghost(0, 0, b) );
        assertFalse( "<< PacMan did not lose lives >>",
            lives == p.getLives());
        
        p = new Pac(0, 0, b);
        score = b.getScore();
        Cherry c = new Cherry(100, 0, 0 );
        p.eat( c );
        assertFalse( "<< Score did not increase >>" ,
            score == b.getScore());
    }
    
    @Test
    public void pacGetLives()
    {
        Pac p = new Pac(0, 0, b);
        int lives = p.getLives();
        assertEquals( "<< Lives should be " + p.getLives() + " >>",
            lives, p.getLives());
    }
    
    @Test
    public void pacLoseLife()
    {
        Pac p = new Pac(0, 0, b);
        p.loseLife( 1 );
        assertEquals( "<< Lives did not decrease >>",
            p.getLives(), 2);
    }
    
    @Test
    public void pacsetLife()
    {
        Pac p = new Pac(0, 0, b);
        p.setLife( 4 );
        assertEquals( "<< Lives did not change >>",
            p.getLives(), 4);
    }
    
    @Test
    public void pacStop()
    {
        Pac p = new Pac(0, 0, b);
        p.stop();
        assertEquals( "<< Pac did not stop >>",
            p.getDx(), 0);
        assertEquals( "<< Pac did not stop >>",
            p.getDy(), 0);
    }
    
    @Test
    public void pacToString()
    {
        Pac p = new Pac(0, 0, b);
        String test = p.toString();
        assertNotNull( "<< Invalid Pac toString method >>",
            test);
    }

    /**
     * PacDots tests:
     * 
     * PacDotsConstructor1() - makes sure a PacDots is constructed
     * 
     * PacDotsConstructor2() - constructs constructor with 3 variables and
     * compares points
     * 
     * PacDotsInitDot() - makes sure that the PacDot is initialized correctly
     * 
     * PacDotsGetPoints() - checks to see if the correct number of points is
     * given
     * 
     * PacDotsDisappear() - makes sure to get the PacDot off of the board
     * 
     * PacDotsDrawDot() - makes sure that a PacDot is drawn on the board
     */

    @Test
    public void PacDotsConstructor1()
    {
        PacDots pd = new PacDots();
        pd.updateImg( "PacDot2.png" );
        ImageIcon test = new ImageIcon( "PacDot2.png" );
        Image img = test.getImage();
        assertTrue( "<< Invalid PacDots constructor >>",
            img.equals( pd.getImg() ) && img.getWidth( null ) == pd.getWidth()
                && img.getHeight( null ) == pd.getHeight() );
    }


    @Test
    public void PacDotsConstructor2()
    {
        PacDots pd = new PacDots( points, x, y );
        pd.updateImg( "PacDot2.png" );
        ImageIcon test = new ImageIcon( "PacDot2.png" );
        Image img = test.getImage();
        assertTrue( "<< Invalid PacDots constructor >>",
            img.equals( pd.getImg() ) && img.getWidth( null ) == pd.getWidth()
                && img.getHeight( null ) == pd.getHeight() && pd.getX() == x && pd.getY() == y
                && pd.getPoints() == points );
    }


    @Test
    public void PacDotsInitDot()
    {
        PacDots pd = new PacDots( points, x, y );
        pd.updateImg( "PacDot2.png" );
        ImageIcon test = new ImageIcon( "PacDot2.png" );
        Image img = test.getImage();
        assertTrue( "<< Image for PacDots is " + test.toString() + " should be PacDot2.png >>",
            img.equals( pd.getImg() ) && img.getWidth( null ) == pd.getWidth()
                && img.getHeight( null ) == pd.getHeight() );
    }


    @Test
    public void PacDotsGetPoints()
    {
        PacDots pd = new PacDots( points, x, y );
        assertEquals( "<< PacDots: " + pd.getPoints() + " should be " + points + " >>",
            points,
            pd.getPoints() );
    }


    @Test
    public void PacDotsDisappear()
    {
        PacDots pd = new PacDots( points, x, y );
        pd.disappear();
        assertTrue( "<< PacDots has not disappeared >>",
            pd.getX() == -100 && pd.getY() == -100 && pd.isVisible() == false );
    }


    public void PacDotsDrawDot()
    {
        PacDots pd = new PacDots( points, x, y );
        Graphics g = null;
        Graphics2D g2 = (Graphics2D)g;
        pd.drawDot( g2 );
        ImageIcon test = new ImageIcon( "PacDot2.png" );
        Image img = test.getImage();

        g.drawImage( pd.getImg(),
            pd.getX() - pd.getWidth() / 2,
            pd.getY() - pd.getHeight() / 2,
            pd.getWidth(),
            pd.getHeight(),
            null );

    }


    /**
     * Cherry tests:
     * 
     * CherryConstructor1() - makes sure a Cherry is constructed
     * 
     * CherryConstructor2() - constructs a Cherry and compares variables
     * 
     * CherryInitCherry() - makes sure that a Cherry is initialized
     * 
     * CherryToString() - makes sure it is not null
     */

    @Test
    public void CherryConstructor1()
    {
        Cherry c = new Cherry();
        c.updateImg( "cherry40.png" );
        ImageIcon test = new ImageIcon( "cherry40.png" );
        Image img = test.getImage();
        assertTrue( "<< Invalid Cherry constructor >>",
            img.equals( c.getImg() ) && img.getWidth( null ) == c.getWidth()
                && img.getHeight( null ) == c.getHeight() );
    }


    @Test
    public void CherryConstructor2()
    {
        Cherry c = new Cherry( points, x, y );
        c.updateImg( "cherry40.png" );
        ImageIcon test = new ImageIcon( "cherry40.png" );
        Image img = test.getImage();
        assertTrue( "<< Invalid Cherry constructor >>",
            img.equals( c.getImg() ) && img.getWidth( null ) == c.getWidth()
                && img.getHeight( null ) == c.getHeight() && c.getX() == x && c.getY() == y
                && c.getPoints() == points );
    }


    @Test
    public void CherryInitCherry()
    {
        Cherry c = new Cherry( points, x, y );
        c.updateImg( "cherry40.png" );
        ImageIcon test = new ImageIcon( "cherry40.png" );
        Image img = test.getImage();
        assertTrue( "<< Image for Cherry is " + test.toString() + " should be cherry40.png >>",
            img.equals( c.getImg() ) && img.getWidth( null ) == c.getWidth()
                && img.getHeight( null ) == c.getHeight() );
    }


    @Test
    public void CherryToString()
    {
        Cherry c = new Cherry( points, x, y );
        assertNotNull( "<< Cherry toString not working >> ", c.toString() );
    }

    int x = 100;

    int y = 244;

    int dx = 5;

    int dy = 13;

    double speed = 0.9;

    int points = 10;


    // -- Test Board
    /**
     * Board tests:
     * boardConstructor() - constructs Constructor and compares variables
     * boardResetLevel() - makes sure board is reset and compares variables
     * boardDeath() - checks that pacman dies when life is set to 0
     * boardIncrScore() - checks that score is increased when method is called.
     * boardCanMoveUp() - checks that correct boolean is returned in regards to a wall.
     * boardCanMoveRight() - checks that correct boolean is returned in regards to a wall.
     * boardCanMoveLeft() - checks that correct boolean is returned in regards to a wall.
     * boardCanMoveDown() - checks that correct boolean is returned in regards to a wall.
     */
    
    @Test
    public void boardConstructor()
    {
        Board b = new Board();
        assertTrue("<< Invalid board constructor >>", b.getOutGhosts().size() == 3
                        && b.getCherry() == null
                        && b.getPacDots() != null
                        && b.getInGame() == true
                        && b.getPacMan() != null);
    }
    
    @Test
    public void boardResetLevel()
    {
        Board b = new Board();
        b.resetLevel();
        assertTrue("<< Invalid reset level >>", b.getOutGhosts() != null);
    }
    
    @Test
    public void boardDeath()
    {
        Board b = new Board();
        b.getPacMan().setLife( 0 );
        b.death();
        assertTrue("<< Invalid Board death method >>", b.getInGame() == false);
    }
    
    @Test
    public void boardIncrScore()
    {
        Board b = new Board();
        b.incrScore( 20 );
        assertEquals("<< Invalid board increase score method >>", b.getScore(), 20);
    }
    
    @Test
    public void boardCanMoveUp()
    {
        Board b = new Board();
        assertTrue("<<Invalid canMoveUp method >>", b.canMoveUp( 75, 75 ) == false
                        && b.canMoveUp( 75, 175 ) == true
                        && b.canMoveUp( 25, 25 ) == false);  //boundary checking
    }
    
    @Test
    public void boardCanMoveRight()
    {
        Board b = new Board();
        assertTrue("<< Invalid canMoveRight method >>", b.canMoveRight( 75, 75 ) == true
                        && b.canMoveRight(  75, 125 ) == false
                        && b.canMoveRight( 950, 75 ) == false);
    }
    
    @Test
    public void boardCanMoveLeft()
    {
        Board b = new Board();
        assertTrue("<< Invalid canMoveLeft >>", b.canMoveLeft( 75, 75 ) == false
                        && b.canMoveLeft( 150, 50 ) == true
                        && b.canMoveLeft( 0, 75 ) == false);
    }
    
    @Test
    public void boardCanMoveDown()
    {
        Board b = new Board();
        assertTrue("<< Invalid canMoveDown >>", b.canMoveDown( 60, 60 ) == true
                        && b.canMoveDown( 120, 60 ) == false
                        && b.canMoveDown( 75, 840 ) == false);
    }
    
      // -- Test Ghost
    /**
     * Ghost tests:
     * GhostConstructor - constructs Ghost and compares image, x, y, and visibility
     * GhostMove - makes sure ghost moves when move is called.
     * GhostToString - makes sure it is not null.
     */
    
    @Test
    public void ghostConstructor()
    {
        Ghost g = new Ghost(x, y, new Board());
        ImageIcon test = new ImageIcon("ghost.gif");
        Image img = test.getImage();
        assertTrue("<< Invalid Ghost Constructor >> ", 
            g.getX() == x
            && g.getY() == y
            && g.isVisible() == true
            && g.getImg().equals( img ));
    }
    
    @Test
    public void ghostMove()
    {
        Ghost g = new Ghost(x, y, new Board());
        g.update( x + 100, y );
        g.move();
        assertTrue("<< Ghost does not move >> ", g.getX() != x
                        || g.getY() != y);
    }
    
    @Test
    public void ghostToString()
    {
        Ghost g = new Ghost(x, y, new Board());
        assertNotNull("<<Ghost toString not working>> ", g.toString());
    }

    // -- Test Sprite
    /**
     * Sprite tests:
     * SpriteConstructorEmpty - constructs empty constructor and compares X, Y, and visiblity
     * SpriteConstructor2Var - constructs constructor with 2 variables and compares X, Y, and visibility
     * SpriteConstructor5Var - constructs constructor with 5 variables and compares X, Y, dx, dy, speed, and visibility.
     * SpriteUpdateImg - compares image returned to constructed image
     * SpriteSetCoords - compares new coordinates to passed coordinates.
     * SpriteMove - compares new coordinates after moving to x + dx * speed
     * SpriteToString - constrcuts 5 variable sprite then compare toString
     */

    @Test
    public void spriteConstructorEmpty()
    {
        Sprite s = new Sprite();
        assertTrue ("<< Invalid Sprite Empty Constructor >>",
            s.getX() == 0
            && s.getY() == 0
            && s.isVisible() == true);
    }

    @Test
    public void spriteConstructor2Var()
    {
        Sprite s = new Sprite(x, y);
        assertTrue ("<< Invalid Sprite Constructor 2 variables>>",
            s.getX() == x
            && s.getY() == y
            && s.isVisible() == true);
    }

    @Test
    public void spriteConstructor5Var()
    {
        Sprite s = new Sprite(x, y, dx, dy, speed);
        assertTrue ("<< Invalid Sprite Constructor 5 variables >>",
            s.getX() == x
            && s.getY() == y
            && s.getDx() == dx
            && s.getDy() == dy
            && s.getSpeed() == speed
            && s.isVisible() == true);
    }

    @Test
    public void spriteUpdateImg()
    {
        Sprite s = new Sprite(x, y);
        s.updateImg( "ghost.gif" );
        ImageIcon test = new ImageIcon("ghost.gif");
        Image img = test.getImage();
        assertTrue("<< Invalid Sprite updateImg method >>", img.equals( s.getImg() )
            && img.getWidth( null ) == s.getWidth()
            && img.getHeight( null ) == s.getHeight());
    }

    @Test
    public void spriteSetCoords()
    {
        Sprite s = new Sprite();
        s.setCoords( x, y );
        assertTrue ("<< Invalid set coordinates method >>", s.getX() == x
                        && s.getY() == y);
    }

    @Test
    public void spriteMove()
    {
        Sprite s = new Sprite(x, y, dx, dy, speed);
        s.move();
        assertTrue("<< Invalid Sprite move method >>", s.getX() == (int)(x + dx * speed)
                        && s.getY() == (int)(y + dy * speed));
    }

    @Test
    public void spriteToString()
    {
        Sprite s = new Sprite(x, y, dx, dy, speed);
        String toStr = s.toString();
        assertNotNull(toStr);
        assertTrue( "<< Invalid Sprite toString method >>",
            toStr.contains( "X coordinate: " + x )
            && toStr.contains( "Y coordinate: " + y )
            && toStr.contains( "Change in x: " + dx )
            && toStr.contains( "Change in y: " + dy ));
    }


    // Remove block comment below to run JUnit test in console
    // this can be commented out i think
    public static junit.framework.Test suite()
    {
        return new JUnit4TestAdapter( JUPacManTest.class );
    }


    public static void main( String args[] )
    {
        org.junit.runner.JUnitCore.main( "JUPacManTest" );
    }

}
