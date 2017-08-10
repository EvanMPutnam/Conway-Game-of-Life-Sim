/**
 * Author: Evan Putnam
 * Description: Data structure to represent the grid and its required functions.
 * Upon init it creates grid, adds neighbors to each cell.  Then in display/main class
 * calls the update grid function.
 */
public class Grid {

    /**
     * Value to represent the width of the grid structure
     */
    private int width;

    /**
     * Value to represent the height of the grid structure
     */
    private int height;

    /**
     * Simulator data structure for grid.  2d array of cells.
     */
    private Cell[][] grid;





    /**
     * Constructor that takes in a width and height to create a blank grid
     * @param width
     * @param height
     */
    public Grid(int width, int height){
        this.width = width;
        this.height = height;
        this.grid = new Cell[width][height];
        createGrid();
        updateNeighbors();
    }

    /**
     * Creates the cell objects for the grid and puts them in
     */
    private void createGrid(){
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++){
                this.grid[x][y] = new Cell(x, y, false);
            }
        }
    }


    /**
     * Updates the neighbors of each respective cell in the grid.
     */
    private void updateNeighbors(){
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++){

                //To left
                if(!(x-1 < 0)){
                    grid[x][y].addNeighbor(grid[x-1][y]);
                }

                //To Right
                if(!(x+1>=width)){
                    grid[x][y].addNeighbor(grid[x+1][y]);
                }

                //Up
                if(!(y-1 < 0)){
                    grid[x][y].addNeighbor(grid[x][y-1]);
                }

                //Down
                if(!(y+1>=height)){
                    grid[x][y].addNeighbor(grid[x][y+1]);
                }

                //Upper left
                if(!(x-1 < 0) && !(y-1 < 0)){
                    grid[x][y].addNeighbor(grid[x-1][y-1]);
                }

                //Upper right
                if(!(x+1>=width) && !(y-1 < 0)){
                    grid[x][y].addNeighbor(grid[x+1][y-1]);
                }

                //Lower left
                if(!(x-1 < 0) && !(y+1 >= height)){
                    grid[x][y].addNeighbor(grid[x-1][y+1]);
                }

                //Lower right
                if(!(x+1>=width) && !(y+1 >= height)){
                    grid[x][y].addNeighbor(grid[x+1][y+1]);
                }

            }
        }
    }


    /**
     * Called each generation to update the grid structure to see which cells live/die
     */
    public void updateGrid(){

        //Get next state of cells
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                //Dies from underpop
                if(grid[x][y].getNumAlive() < 2 && grid[x][y].getAlive()){
                    grid[x][y].setWillLive(false);
                }
                //Lives if 2 or 3 neighbors
                else if((grid[x][y].getNumAlive() == 2 || grid[x][y].getNumAlive() == 3)
                        && grid[x][y].getAlive()){
                    grid[x][y].setWillLive(true);
                }
                //Dies from overpop
                else if(grid[x][y].getNumAlive() > 3 && grid[x][y].getAlive()){
                    grid[x][y].setWillLive(false);
                }
                //Resurrects if 3 neighbors
                else if(grid[x][y].getNumAlive() == 3 && !grid[x][y].getAlive()){
                    grid[x][y].setWillLive(true);
                }
                else{
                    grid[x][y].setWillLive(false);
                }

            }
        }

        //Update them to default
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < width; x++) {
                grid[x][y].updateCell();
            }
        }
    }


    /**
     * Get the width of the grid
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the height of the grid
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the grid 2d array of cells
     * @return
     */
    public Cell[][] getGrid() {
        return grid;
    }



    /**
     * Test main method for building a grid
     * @param args
     */
    public static void main(String args[]){
        Grid grid = new Grid(50, 50);

    }

}
