import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Author: Evan Putnam
 * Description: Simulator class that allows to run automata's.
 * Pre-Reqs: AsciiPanel from trystan can be found on github https://github.com/trystan/AsciiPanel
 * Extends JFrame, Implements KeyListener
 */
public class Simulator extends JFrame{


    /**
     * Integer value for height in charachters
     */
    public static final int HEIGHT_VAL = 50;

    /**
     * Integer value for width in charachters
     */
    public static final int WIDTH_VAL = 50;

    /**
     * Grid data structure for cells
     */
    private Grid grid;


    /**
     * Constructor that takes in width and height
     * and draws the automata from text file life.txt
     * @param width
     * @param height
     */
    public Simulator(int width, int height){

        //Create grid object
        grid = new Grid(width, height);

        //Create ascii panel object
        AsciiPanel panel = new AsciiPanel(width+1, height, AsciiFont.DRAKE_10x10);

        //Try loading the text file
        try{
            loadTextFile();
        }catch (Exception e){
            JOptionPane.showConfirmDialog(this, "Error Reading File.  Try making one in the editor.");
        }




        //Timer logic
        ActionListener task = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                // Do stuff
                grid.updateGrid();
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                       if(grid.getGrid()[x][y].getAlive()){
                           panel.write("#", x, y, Color.blue);
                       }else{
                           panel.write("#", x, y, Color.cyan);
                       }
                    }
                }
                panel.updateUI();
            }
        };

        //Create and start timer
        Timer timer = new Timer(1000 ,task); // Execute task each 100 miliseconds
        timer.setRepeats(true);
        timer.start();


        //Add the panel and pack
        add(panel);
        pack();

    }


    /**
     * Function to load in the life.txt file to draw the
     * starting structure to the screen.
     * @throws IOException if can't read the file
     */
    private void loadTextFile() throws IOException{
        //Create reader obj
        BufferedReader reader = new BufferedReader(new FileReader("life.txt"));

        //Create string object to store each line.
        String line;

        //y location count
        int yCount = 0;

        //Iterate until null
        while((line = reader.readLine()) != null){
            //x location count
            int xCount = 0;
            for (String s: line.trim().split("")){
                //If a 1 then set corresponding tile alive
                if(s.equals("1")){
                    grid.getGrid()[xCount][yCount].setAlive(true);
                }
                //Increment x count
                xCount += 1;
            }
            //Increment y count
            yCount += 1;
        }
        //Close the reader
        reader.close();
    }


    /**
     * Simulator function that runs the simulation
     * @param args
     */
    public static void main(String[] args) {
        // write your code here
        Simulator game = new Simulator(WIDTH_VAL, HEIGHT_VAL);
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setVisible(true);
    }



}
