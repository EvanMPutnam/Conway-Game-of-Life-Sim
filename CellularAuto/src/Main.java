import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Author: Evan Putnam
 * Description: Main class for starting the application.  Has two buttons that allow to enter either
 * editor or simulator.  Relies on a text file, life.txt, for the information.  Make sure one window is closed
 * before starting the other.
 */
public class Main extends JFrame{


    /**
     * Button to start the editor
     */
    private JButton editor;

    /**
     * Button to start the simulator
     */
    private JButton simulator;


    /**
     * Main constructor that merely creates the buttons, adds functionality,
     * and puts them on the screen
     */
    public Main(){
        super("Cellular Automata");
        setLayout(new FlowLayout());


        //Function to create the buttons and the logic
        createButtons();

        //Add both emulator and simulator to window
        add(editor);
        add(simulator);

        //Make window appear in center
        setLocationRelativeTo(null);
    }


    /**
     * Void function that creates the buttons and their logic
     */
    private void createButtons(){
        //Editor button and logic
        editor = new JButton("Editor");
        editor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Editor edit = new Editor(Simulator.WIDTH_VAL, Simulator.HEIGHT_VAL);
                edit.setVisible(true);
            }
        });

        //Simulator button and logic
        simulator = new JButton("Simulator");
        simulator.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Simulator sim = new Simulator(Simulator.WIDTH_VAL, Simulator.HEIGHT_VAL);
                sim.setVisible(true);
            }
        });
    }


    /**
     * Main function that merely creates the window with buttons
     * @param args
     */
    public static void main(String args[]){
        Main main = new Main();
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setSize(300, 120);
        main.setVisible(true);
    }





}
