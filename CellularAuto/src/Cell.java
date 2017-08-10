import java.util.ArrayList;

/**
 * Author: Evan Putnam
 * Description: Data structure to represent the cells.  Neighbors in array list.
 */
public class Cell {


    /**
     * X location on the grid
     */
    private int x;

    /**
     * Y location on the grid
     */
    private int y;

    /**
     * ArrayList of neighbors for cells
     */
    private ArrayList<Cell> neighbors;

    /**
     *  Whether or not the cell is alive
     */
    private boolean alive;

    /**
     *  Boolean to indicate if whether or not the cell will die
     */
    private boolean willLive;



    /**
     * Constructor for cell.
     * X, Y, and alive condition.
     * @param x
     * @param y
     * @param alive
     */
    public Cell(int x, int y, boolean alive){
        this.x = x;
        this.y = y;
        this.alive = alive;
        this.neighbors = new ArrayList<>();

    }



    /**
     * Add neighbors to the arraylist of neighbors
     * @param neighbor
     */
    public void addNeighbor(Cell neighbor){
        this.neighbors.add(neighbor);
    }



    /**
     * Get the x position on the grid
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y position on the grid
     * @return
     */
    public int getY() {
        return y;
    }





    /**
     * Set the object to be either alive or dead
     * @param alive
     */
    public void setAlive(boolean alive){
        this.alive = alive;
    }

    /**
     * Gets if alive or dead.
     * @return
     */
    public boolean getAlive(){
        return this.alive;
    }





    /**
     * If it will die in next iteration
     * @return
     */
    public boolean isWillLive() {
        return willLive;
    }

    /**
     * Sets to see if will die next round
     * @param willLive
     */
    public void setWillLive(boolean willLive) {
        this.willLive = willLive;
    }





    /**
     * Get number of cars that are alive.
     * @return
     */
    public int getNumAlive(){
        //Set default num of neighbors
        int neighs = 0;

        //Loop through number of neighbors
        for (Cell c: neighbors) {
            //If neighbor is alive increment
            if(c.getAlive()){
                neighs += 1;
            }
        }
        //Return number of alive neighbors
        return neighs;
    }



    /**
     * Updates the cell with the results.
     */
    public void updateCell(){
        //If the cell will live then set alive to true
        if(this.willLive == true){
            this.alive = true;
            //If the neighbor will not live then false
        }if(this.willLive == false) {
            this.alive = false;
        }
    }


}
