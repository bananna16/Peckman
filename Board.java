import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.util.LinkedList;


/**
 * 
 * Creates a board with a maze for the pacman. It deals with collisions.
 *
 * @author Anna Qi
 * @version May 4, 2018
 * @author Period: 5
 * @author Assignment: Final
 *
 * @author Sources: None
 */
public class Board extends JPanel implements ActionListener, KeyListener
{
    private static final long serialVersionUID = 1L;
    private Timer timer;
    private Pac pacman;
    private List<Ghost> outGhosts; // ghosts that are alive. for now: all of them
    private Cherry cherry;
    private List<PacDots> pacDots; // change later?
    private boolean ingame;
    private int score;
    private final int ICRAFT_X = 450;
    private final int ICRAFT_Y = 500;
    private final int B_WIDTH = 950;
    private final int B_HEIGHT = 750;
    private final int BLOCK_SIZE = 50;
    private final int DELAY = 120;
    private int cherryCount; // timer to tell when cherry appears
    private boolean cherryOut; // if there is a cherry in the board or not
    private final int[][] ghostPos = { { 400, 350 }, { 450, 350 }, { 500, 350 } };

    /**
     * Represents data in the board. If 0: wall If % 2 == 0, has a ghost If 1,
     * has a pacdot. If % 3 == 0, has a large pac dot. If number is negative,
     * that's where pacman is. If & 5 == 0, has a cherry If % 7 ==0, space is
     * empty.
     * 
     * rows = y and columns = x.
     */
    private final int[][] boardData = { 
    	{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0 },
        { 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0 },
        { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
        { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
        { 0, 1, 0, 1, 0, 0, 1, 0, 7, 7, 7, 0, 1, 0, 0, 1, 0, 1, 0 },
        { 0, 1, 0, 1, 0, 0, 1, 0, 2, 2, 2, 0, 1, 0, 0, 1, 0, 1, 0 },
        { 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0 },
        { 0, 1, 0, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0 },
        { 0, 1, 0, 1, 0, 0, 0, 0, 0, -7, 0, 0, 0, 0, 0, 1, 0, 1, 0 },
        { 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0 },
        { 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0 },
        { 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 5, 1, 0 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };


    /**
     * Basic constructor.
     */
    public Board()
    {
        initBoard();
        initVariables();
    }


    /**
     * Initializes the board. A pacman, ghosts, and a timer are created.
     */
    public void initBoard()
    {
        addKeyListener(this);
        setFocusable( true );
        setFocusTraversalKeysEnabled( false );
        setBackground( Color.BLACK );
        setDoubleBuffered( true );
        setPreferredSize( new Dimension( B_WIDTH, B_HEIGHT ) );
    }


    /**
     * initializes all the variables
     */
    private void initVariables()
    {
        ingame = true;
        score = 0;
        pacman = new Pac( ICRAFT_X, ICRAFT_Y, this );
        initGhosts();
        initPacDots();
        timer = new Timer( DELAY, this );
        timer.start();
        cherryCount = 30;
        cherryOut = false;
    }


    /**
     * places the pac dots onto the board.
     */
    private void initPacDots()
    {
        pacDots = new LinkedList<PacDots>();

        for ( int r = 0; r < boardData.length; r++ )
        {
            for ( int c = 0; c < boardData[0].length; c++ )
            {
                if ( boardData[r][c] == 1 || boardData[r][c] == 5 )
                {
                    pacDots
                        .add( new PacDots( 10, c * 50 + BLOCK_SIZE / 2, r * 50 + BLOCK_SIZE / 2 ) );
                }
            }
        }
    }


    /**
     * if timer runs out, the cherry is placed.
     */
    private void checkCherry()
    {
        if ( cherryCount == 0 && !cherryOut )
        {
            for ( int r = 0; r < boardData.length; r++ )
            {
                for ( int c = 0; c < boardData[0].length; c++ )
                {
                    if ( boardData[r][c] == 5 )
                    {
                        cherry = new Cherry( 100,
                            c * 50 + BLOCK_SIZE / 2,
                            r * 50 + BLOCK_SIZE / 2 );
                        cherryOut = true;
                        for ( PacDots pd : pacDots )
                        {
                            if ( pd.getX() == (int)( c * 50 + BLOCK_SIZE / 2 )
                                && pd.getY() == (int)( r * 50 + BLOCK_SIZE / 2 ) )
                            {
                                pd.disappear();
                            }
                        }
                    }
                }
            }
        }
        else
        {
            cherryCount--;
        }
    }


    /**
     * resets the level so that the pacman and ghosts return to their original
     * position but score and pacdots remain the same.
     * 
     */
    public void resetLevel()
    {
        pacman.setCoords( ICRAFT_X, ICRAFT_Y );
        outGhosts = new ArrayList<Ghost>();
        for ( int[] p : ghostPos )
        {
            outGhosts.add( new Ghost( p[0], p[1], this ) );
        }
    }


    /**
     * Initializes the ghosts and places them on the board.
     */
    public void initGhosts()
    {
        outGhosts = new ArrayList<Ghost>();

        for ( int r = 0; r < boardData.length; r++ )
        {
            for ( int c = 0; c < boardData[0].length; c++ )
            {
                if ( boardData[r][c] == 2 )
                {
                    outGhosts.add( new Ghost( c * 50, r * 50, this ) );
                }
            }
        }
    }


    @Override
    /**
     * Updates the game, pac man, and ghosts whenever an action is performed.
     * Also checks for collisions and repaints the board.
     */
    public void actionPerformed( ActionEvent e )
    {
        checkCherry();
        repaint();
    }


    /**
     * Programs the death of the pacman. If no move lives, the game will stop
     * else the game will reset.
     */
    public void death()
    {
        if ( pacman.getLives() == 0 )
        {
            ingame = false;
        }
        else
        {
            resetLevel();
        }
    }


    /**
     * Moves the ghosts.
     */
    private void updateGhosts()
    {
        for ( Ghost ghost : outGhosts )
        {
            ghost.update( pacman.getX(), pacman.getY() );
            ghost.move();
        }
    }


    /**
     * Stops the pacman from moving if it encounters a wall.
     */
    private void updatePacMan()
    {
        if ( !( pacman.getDx() > 0 && canMoveRight( pacman.getX(), pacman.getY() )
            || pacman.getDx() < 0 && canMoveLeft( pacman.getX(), pacman.getY() )
            || pacman.getDy() < 0 && canMoveUp( pacman.getX(), pacman.getY() )
            || pacman.getDy() > 0 && canMoveDown( pacman.getX(), pacman.getY() ) ) )
        {
            pacman.stop();
        }
        pacman.move();

    }


    @Override
    public void paintComponent( Graphics g )
    {
        requestFocus();
        inGame( g );
        updatePacMan();
        updateGhosts();

        checkCollisions();
        Graphics2D g2 = (Graphics2D)g;
        drawMaze( g2 );
        g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        g2.setColor( Color.white );
        Font f = new Font( "SansSerif", Font.BOLD, 14 );
        g2.setFont( f );
        g2.drawString( "Score: " + score, 40, 40 );
        g2.drawString( "Lives: " + pacman.getLives(), 700, 40 );
        pacman.draw_Pac( g2 );
        for ( Ghost ghost : outGhosts )
        {
            ghost.draw_Ghost( g2 );
        }
        for ( PacDots pacdot : pacDots )
        {
            pacdot.drawDot( g2 );
        }
        if ( cherry != null )
        {
            cherry.drawDot( g );
        }
        inGame( g );
    }


    /**
     * draws the maze in areas of the boardData that are equal to 0.
     * 
     * @param g2d
     *            2d graphics
     */
    public void drawMaze( Graphics2D g2d )
    {
        g2d.setColor( Color.GRAY );
        for ( int r = 0; r < boardData.length; r++ )
        {
            for ( int c = 0; c < boardData[0].length; c++ )
            {
                if ( boardData[r][c] == 0 )
                {
                    g2d.fillRect( c * 50, r * 50, BLOCK_SIZE, BLOCK_SIZE );
                }
            }
        }
    }


    /**
     * carries out actions depending on the collisions that take place.
     */
    private void checkCollisions()
    {
        Rectangle r2 = pacman.getBounds();

        for ( int i = 0; i < outGhosts.size(); i++ )
        {
            Ghost ghost = outGhosts.get( i );
            if ( ghost != null )
            {
                Rectangle r1 = ghost.getBounds();

                if ( r2.intersects( r1 ) )
                {
                    pacman.eat( ghost );
                    death();
                }
            }
        }

        for ( PacDots pd : pacDots )
        {
            if ( pd != null )
            {
                Rectangle r1 = pd.getBounds();
                if ( r2.intersects( r1 ) )
                {
                    pacman.eat( pd );
                    pd.disappear();
                }
            }
        }

        if ( cherry != null )
        {
            Rectangle r3 = cherry.getBounds();
            if ( r3.intersects( r2 ) )
            {
                pacman.eat( cherry );
                cherry.disappear();
            }
        }
    }


    /**
     * @param increment
     *            score increment
     */
    public void incrScore( int increment )
    {
        score += increment;
    }


    /**
     * Stops the game if it is no longer in game.
     */
    private void inGame( Graphics g )
    {
        if ( !ingame )
        {
            timer.stop();
            endScreen( (Graphics2D)g );
        }
    }


    /**
     * 
     * Shows the end screen of PacMan.
     * 
     * @param g2d
     *            graphics
     */
    private void endScreen( Graphics g2d )
    {
        g2d.setColor( new Color( 0, 32, 48 ) );
        g2d.fillRect( 150, B_WIDTH / 2 - 150, B_WIDTH - 300, 100 );
        g2d.setColor( Color.white );
        g2d.drawRect( 150, B_WIDTH / 2 - 150, B_WIDTH - 300, 100 );

        String s = "You have lost :( Your final score was " + getScore();
        Font small = new Font( "Helvetica", Font.BOLD, 14 );
        FontMetrics metr = this.getFontMetrics( small );

        g2d.setColor( Color.white );
        g2d.setFont( small );
        g2d.drawString( s, ( B_WIDTH - metr.stringWidth( s ) ) / 2, B_HEIGHT / 2 );
    }


    /**
     * checks if there is a wall on the unit above the given coordinates.
     * 
     * @param x
     *            coordinates of the object
     * @param y
     *            coordinates of the object
     * @return true if can move up, false if can't.
     */
    public boolean canMoveUp( int x, int y )
    {
        if ( y > 50 )
        {
            return boardData[y / 50 - 1][x / 50] != 0;
        }
        return false;
    }


    /**
     * checks if there is a wall on the unit right of the given coordinates.
     * 
     * @param x
     *            coordinates of the object
     * @param y
     *            coordinates of the object
     * @return true if can move right, false if can't.
     */
    public boolean canMoveRight( int x, int y )
    {
        if ( x < 850 )
        {
            return boardData[y / 50][x / 50 + 1] != 0;
        }
        return false;
    }


    /**
     * checks if there is a wall on the unit below the given coordinates.
     * 
     * @param x
     *            coordinates of the object
     * @param y
     *            coordinates of the object
     * @return true if can move down, false if can't.
     */
    public boolean canMoveDown( int x, int y )
    {
        if ( y < 700 )
        {
            return boardData[y / 50 + 1][x / 50] != 0;
        }
        return false;
    }


    /**
     * checks if there is a wall on the unit left of the given coordinates.
     * 
     * @param x
     *            coordinates of the object
     * @param y
     *            coordinates of the object
     * @return true if can move left, false if can't.
     */
    public boolean canMoveLeft( int x, int y )
    {
        if ( x > 50 )
        {
            return boardData[y / 50][x / 50 - 1] != 0;
        }
        return false;
    }


    // getters for testing
    public List<Ghost> getOutGhosts()
    {
        return outGhosts;
    }


    public Cherry getCherry()
    {
        return cherry;
    }


    public List<PacDots> getPacDots()
    {
        return pacDots;
    }


    public boolean getInGame()
    {
        return ingame;
    }


    public int getScore()
    {
        return score;
    }


    public int getCherryCount()
    {
        return cherryCount;
    }


    public Pac getPacMan()
    {
        return pacman;
    }


    public void keyReleased( KeyEvent e )
    {

    }


    public void keyPressed( KeyEvent e )
    {
        pacman.keyPressed( e );
    }


    @Override
    public void keyTyped( KeyEvent arg0 )
    {
        // TODO Auto-generated method stub

    }


    public static void main( String[] args )
    {
        JFrame frame = new JFrame();
        Board board = new Board();
        frame.addKeyListener( board );
        frame.setTitle( "Pacman" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setVisible( true );
        frame.add( board );
        frame.setLocationRelativeTo( null );
        frame.pack();
        Container contentPane = frame.getContentPane();
        contentPane.add( board );
    }
}