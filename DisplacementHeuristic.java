/**
 * This defines the displacement heuristic for the 8-Puzzle.
 * @author Steven Bogaerts
 */
public class DisplacementHeuristic implements Heuristic {
    
    private PuzzleState goalState;
    
    public DisplacementHeuristic(PuzzleState goalState) {
        this.goalState = goalState;
    }
    
    public int distance(PuzzleState state) {
         int count = 0;
          for(int i = 0; i < 8; i++)//not nested loops because then it would compare the first bit of the first state with everything in the second state and then move on
        {
            if(state.getTileAt(i) != goalState.getTileAt(i)) 
            {
                count++; 
            }    
        }
        return count;
    }
}