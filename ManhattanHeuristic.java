/**
 * This defines the Manhattan heuristic for the 8-Puzzle.
 * @author Steven Bogaerts
 */
public class ManhattanHeuristic implements Heuristic {
    
    private PuzzleState goalState;
    
    public ManhattanHeuristic(PuzzleState goalState) {
        this.goalState = goalState;
    }
    
    public int getRow(int index){
        return index/3;
    }
    
    public int getColumn(int index){
        return index%3;
    }
    
    public int distance(PuzzleState state) {
        int count = 0;
        for(int index = 0; index < 9; index++)
        {
            if(state.getTileAt(index) != goalState.getTileAt(index) && state.getTileAt(index) != 0)
            {
                   /* int wrong = state.getTileAt(index);
                    int row1 = getRow(wrong);
                    int column1 = getColumn(wrong);
                    int row2= getRow(goalState.posOf(index));
                    int column2 = getColumn(goalState.posOf(index));
                    count = Math.abs((row1- row2)+(column1-column2));
                    System.out.println("state.getTileAt(index) " + state.getTileAt(index));
                    System.out.println();
                    System.out.println("-----------------------------");
                    System.out.println("goalState.getTileAt(index) " + goalState.getTileAt(index));*/
                    
                    int stateWrongValue = state.getTileAt(index); //Gets the number in state that is in the wrong place
                    int goalStateIndex = goalState.posOf(stateWrongValue); // Takes that number, and finds the position where it should be in the goalState
                    
                    int stateRow = getRow(index); //gets row of wrong number in state
                    int stateColumn = getColumn(index); //gets column of wrong number in state
                    int goalStateRow= getRow(goalStateIndex); //gets row of right number
                    int goalStateColumn = getColumn(goalStateIndex); //gets column of right number
                    count = count + Math.abs((stateRow - goalStateRow)) + Math.abs((stateColumn - goalStateColumn)); //computes the differences between the two rows and columns to get the distance
                    
            }
        }
        return count;
    }
}