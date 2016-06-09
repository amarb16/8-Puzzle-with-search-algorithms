import java.util.LinkedList;
import java.util.Arrays;

/**
 * Represents a state (placement of tiles) of the puzzle.
 * Starter code.
 * @author Steven Bogaerts
 */
public class PuzzleState {
    
    /**
     * A 1-D array representation of the tile placement.
     */
    private int[] tiles;
    
    /**
     * Assumes that initialTiles is the valid format:
     * length 9,
     * containing the integers 0 through 8 inclusive in some order
     * with no repeats
     * where 0 represents the blank.
     */
    public PuzzleState(int[] initialTiles) {
        tiles = initialTiles;
    }

    /**
     * Returns the position of a given tile in the puzzle state.
     * Uses 0-based indexing, meaning some number between 0 through 8 inclusive is ordinarily returned.
     * Returns -1 if not found. (Should never happen for valid input.)
     */
    public int posOf(int tile) {
        for(int i = 0; i < tiles.length; i++){ 
            if(tiles[i] == tile){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Returns the tile value at the given index (0 through 8, inclusive).
     * For the blank, returns 0.
     */
    public int getTileAt(int index) {
        return tiles[index];
    }
    
        /**
     * Changes the value at index
     */
    public void setTileAt(int index, int value) {
        tiles[index]= value; //index is the position of 0
                             //value is the # at that position
    }
    
    /**
     * Returns a list of PuzzleStates that can be reached from this PuzzleState
     * in one move. It's easiest to think of this as moving the blank.
     */
    public LinkedList<PuzzleState> expand() {
        LinkedList<PuzzleState> list = new LinkedList<PuzzleState>();
        int pos0 = posOf(0); //makes code run faster
        if(pos0 > -1) {
            if(pos0 > 2)
            {
                int[] newarray = Arrays.copyOf(tiles, tiles.length);
                PuzzleState up = new PuzzleState(newarray);
                
                up.setTileAt(pos0, tiles[pos0 - 3]); //getTileAt(pos0 - 3) could also work here
                up.setTileAt(pos0 - 3, 0);
                
                list.add(up);
                //puzzle state needs an array
            }  
        
            if(pos0 < 6)
            {
                int[] newarray = Arrays.copyOf(tiles, tiles.length);
                PuzzleState down = new PuzzleState(newarray);
                
                down.setTileAt(pos0, tiles[pos0 + 3]); //getTileAt(pos0 + 3) could also work here
                down.setTileAt( pos0 + 3, 0);
                
                list.add(down);
            }

            if(pos0 > 0 && pos0 <3 || pos0 > 3 && pos0 < 6 || pos0 > 6 && pos0 < 9) //(pos0 > 0 && pos0 <= 2) && (pos0 > 3 && pos0 <= 5) && pos0 > 6)
            { 
                int[] newarray = Arrays.copyOf(tiles, tiles.length);
                PuzzleState left = new PuzzleState(newarray);
                
                left.setTileAt(pos0, tiles[pos0 - 1]); //getTileAt(pos0 - 1) could also work here
                left.setTileAt( pos0 - 1, 0);
                
                list.add(left);
            }
            
            if(pos0 < 2 || pos0 > 2 && pos0 < 5 || pos0 > 5 && pos0 < 8)
            {
                int[] newarray = Arrays.copyOf(tiles, tiles.length);
                PuzzleState right = new PuzzleState(newarray);
                
                right.setTileAt(pos0, tiles[pos0 + 1]); //getTileAt(pos0 - 3) could also work here
                right.setTileAt( pos0 + 1, 0);
                
                list.add(right);   
            }
        }
        return list;
    }
    
    //////////////////
    /*public LinkedList<PuzzleState> expandUp() {
        LinkedList<PuzzleState> list = new LinkedList<PuzzleState>();
        int pos0 = posOf(0); //makes code run faster
        if(pos0 > -1) {
            if(pos0 > 2)
            {
                int[] newarray = Arrays.copyOf(tiles, tiles.length);
                PuzzleState up = new PuzzleState(newarray);
                
                up.setTileAt(pos0, tiles[pos0 - 3]); //getTileAt(pos0 - 3) could also work here
                up.setTileAt(pos0 - 3, 0);
                
                list.add(up);
                //puzzle state needs an array
            }


        }
        return list;
    }
    public LinkedList<PuzzleState> expandDown() {
        LinkedList<PuzzleState> list = new LinkedList<PuzzleState>();
        int pos0 = posOf(0); //makes code run faster
        if(pos0 > -1) {

            if(pos0 < 6)
            {
                int[] newarray = Arrays.copyOf(tiles, tiles.length);
                PuzzleState down = new PuzzleState(newarray);
                
                down.setTileAt(pos0, tiles[pos0 + 3]); //getTileAt(pos0 + 3) could also work here
                down.setTileAt( pos0 + 3, 0);
                
                list.add(down);
            }
        

        }
        return list;
    }
    
    public LinkedList<PuzzleState> expandLeft() {
        LinkedList<PuzzleState> list = new LinkedList<PuzzleState>();
        int pos0 = posOf(0); //makes code run faster
        if(pos0 > -1) {
        
            if(pos0 > 0 && pos0 <3 || pos0 > 3 && pos0 < 6 || pos0 > 6 && pos0 < 9) //(pos0 > 0 && pos0 <= 2) && (pos0 > 3 && pos0 <= 5) && pos0 > 6)
            {
                int[] newarray = Arrays.copyOf(tiles, tiles.length);
                PuzzleState left = new PuzzleState(newarray);
                
                left.setTileAt(pos0, tiles[pos0 - 1]); //getTileAt(pos0 - 1) could also work here
                left.setTileAt( pos0 - 1, 0);
                
                list.add(left);
            }

        }
        return list;
    }
    public LinkedList<PuzzleState> expandRight() {
        LinkedList<PuzzleState> list = new LinkedList<PuzzleState>();
        int pos0 = posOf(0); //makes code run faster
        if(pos0 > -1) {
            
            if(pos0 < 2 || pos0 > 2 && pos0 < 5 || pos0 > 5 && pos0 < 8)
            {
                int[] newarray = Arrays.copyOf(tiles, tiles.length);
                PuzzleState right = new PuzzleState(newarray);
                
                right.setTileAt(pos0, tiles[pos0 + 1]); //getTileAt(pos0 - 3) could also work here
                right.setTileAt( pos0 + 1, 0);
                
                list.add(right);   
            }

        }
        return list;
    }*/
    /////////////////
    
    /**
     * Returns true if two PuzzleState objects are equivalent,
     * false otherwise. Required for proper operation of a HashSet of PuzzleStates.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof PuzzleState))
            return false;
        else
            return Arrays.equals(this.tiles, ((PuzzleState) other).tiles);
    }//these are overriding something in the class
    
    /**
     * Required to be able to have a HashSet of PuzzleState objects.
     * Objects for which .equals() returns true must have the same hashCode.
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(tiles);
    }
    
    /**
     * Returns a String representation of the state.
     */
    @Override
    public String toString() {
        String result = "\n";
        for(int i = 0; i < tiles.length; i++) {
            if ((i > 0) && i % 3 == 0)
                result += "\n";
                
            if (tiles[i] == 0)
                result += "_ ";
            else
                result += tiles[i] + " ";
        }
                
        return result + "\n";
    }
}