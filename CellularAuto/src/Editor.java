import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Author: Evan Putnam
 * Description: Editor class that allows to design custom automata's.
 * Pre-Reqs: AsciiPanel from trystan can be found on github https://github.com/trystan/AsciiPanel
 * Extends JFrame, Implements KeyListener
 */
public class Editor extends JFrame implements KeyListener{

    /**
     * Constant key value for left arrow key
     */
    private static final int LEFT_KEY = 37;

    /**
     * Constant key value for up arrow key
     */
    private static final int UP_KEY = 38;

    /**
     * Constant key value for right arrow key
     */
    private static final int RIGHT_KEY = 39;

    /**
     * Constant key value for down arrow key
     */
    private static final int DOWN_KEY = 40;

    /**
     * Constant key value for space key
     */
    private static final int SPACE_KEY = 32;

    /**
     * Constant key value for escape key
     */
    private static final int ESCAPE_KEY = 27;





    /**
     * Grid data structure for storing the individual cells
     */
    private Grid grid;

    /**
     * Width of the grid/screen
     */
    private int width;

    /**
     * Height of the grid/screen
     */
    private int height;

    /**
     * Ascii panel object for displaying grid and cells
     */
    private AsciiPanel panel;

    /**
     * Cursor x location
     */
    private int cursorX = 0;

    /**
     * Cursor y location
     */
    private int cursorY = 0;


    /**
     * Constructor for the editor object.  Takes width and height and creates
     * the panel.
     * @param width
     * @param height
     */
    public Editor(int width, int height){
        this.panel = new AsciiPanel(width+1, height, AsciiFont.DRAKE_10x10);
        this.grid = new Grid(width, height);
        this.width = width;
        this.height = height;

        updateScreen();

        addKeyListener(this);
        add(panel);
        pack();
    }


    /**
     * Updates the screen to represent cursor and alive/dead cells
     */
    private void updateScreen(){
        //Iterates over board and updates the screen
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                if(cursorX == x && cursorY == y) {
                    if (grid.getGrid()[x][y].getAlive()) {
                        panel.write("#", x, y, Color.blue, Color.RED);
                    } else {
                        panel.write("#", x, y, Color.cyan,Color.red);
                    }
                }else{
                    if (grid.getGrid()[x][y].getAlive()) {
                        panel.write("#", x, y, Color.blue);
                    } else {
                        panel.write("#", x, y, Color.cyan);
                    }
                }
            }
        }
        panel.updateUI();
    }



    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Takes a key event processes the input.
     * Left/Right/Up/Down moves the cursor.
     * Space toggles alive/dead
     * Escape key saves the config to text file
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
        if(e.getKeyCode() == UP_KEY){
            cursorY -= 1;
        }else if(e.getKeyCode() == DOWN_KEY){
            cursorY += 1;
        }else if(e.getKeyCode() == LEFT_KEY){
            cursorX -=1;
        }else if(e.getKeyCode() == RIGHT_KEY) {
            cursorX += 1;
        }else if(e.getKeyCode() == SPACE_KEY){
            if(grid.getGrid()[cursorX][cursorY].getAlive()){
                grid.getGrid()[cursorX][cursorY].setAlive(false);
            }else{
                grid.getGrid()[cursorX][cursorY].setAlive(true);
            }
        }else if(e.getKeyCode() == ESCAPE_KEY){
            //Write to some file
            try {
                writeToFile();
                JOptionPane.showConfirmDialog(this, "Write Success");
            }catch (IOException exception){
                JOptionPane.showConfirmDialog(this, "Error Writing Message");
            }

        }

        updateScreen();
    }


    /**
     * Writes the current board config to life.txt file.
     * Writes a 1 for alive and 0 for dead
     * @throws IOException
     */
    private void writeToFile() throws IOException{
        //Create the writer obj
        BufferedWriter writer = new BufferedWriter(new FileWriter("life.txt"));

        //Iterate over the board to get the alive/dead states
        for (int y = 0; y < height; y++) {
            String s = "";
            for (int x = 0; x < width; x++) {
                if(grid.getGrid()[x][y].getAlive()){
                    s = s + "1";
                }else{
                    s = s + "0";
                }
            }
            //Write alive and dead state
            writer.write(s);
            //New line
            writer.write("\n");
        }
        //Close the writer
        writer.close();
    }


    /**
     * Simulator method that starts the editor.
     * @param args
     */
    public static void main(String args[]){
        Editor game = new Editor(Simulator.WIDTH_VAL, Simulator.HEIGHT_VAL);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }
}
