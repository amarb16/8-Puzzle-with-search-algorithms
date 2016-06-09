import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Comparator;

/**
 * Represents a search problem to be solved.
 * 
 * This is somewhat specific to the 8-Puzzle, since a more general solution would require increased use of
 * generics, which we're avoiding in this course for now.
 * @author Steven Bogaerts
 */
public class SearchProblem {

    // *************************** TO DO - define whatever fields you need here
    private Queue<PuzzlePath> frontier;
    private HashSet<PuzzleState> expanded = new HashSet<PuzzleState>(); //creates an empty expanded
    private PuzzleState goalState;
    private Queue<PuzzleState> queue;
    private int goalCheckLimit;
    private Heuristic h;
    private PuzzleState initState;
    private PuzzlePath path;
    
    /**
     * This Comparator object is an instance of an anonymous class.
     * It compares two paths based on cost, for use in the PriorityQueue, for ordering.
     */
    public static Comparator<PuzzlePath> pathComparator = new Comparator<PuzzlePath>() {
        public int compare(PuzzlePath p1, PuzzlePath p2) {
            return p1.getCost() - p2.getCost();
        }
    };
    
    /**
     * Constructs a new SearchProblem.
     * 
     * @param queueType For our purposes for now, assume that queueType is either "FIFO" (meaning BFS will be done)
     * or "Ordered" (meaning A* will be done).
     * 
     * @param goalCheckLimit Check if some state is a goal up to this many times, after which you give up the search.
     * This is useful for problems that turn out to be impossible to solve.
     * 
     * @param h The Heuristic, should be an instance of the NoHeuristic class if you're using FIFO (BFS) as the queueType.
     * Otherwise pass an appropriate Heuristic object.
     */
    public SearchProblem(PuzzleState initState, PuzzleState goalState, String queueType, int goalCheckLimit, Heuristic h) {
        // *************************** TO DO - do whatever initialization you need here

        LinkedList<PuzzleState> states = new LinkedList<PuzzleState>();
        states.add(initState); //take initial state and store it in states
        this.goalState = goalState;//do we need this?
        this.goalCheckLimit = goalCheckLimit;
        this.h = h;
        this.initState = initState;
        if(queueType.equals("FIFO")){//or you could do queueType == "FIFO"
            this.frontier = new LinkedList<PuzzlePath>(); //frontier is going to be a new linkedlist of puzzlepath
        }else{
            this.frontier = new PriorityQueue<PuzzlePath>(1000, pathComparator); //priority queue for "Ordered"
        }
       
        if(goalCheckLimit == this.goalCheckLimit){
            return;
        }
        //expand = new HashSet<SearchNode>();
        //frontier = new NodeQueue();
        //Check the value of queueType, and set the frontier to the correct type.
        //gets everything set up
        //BFS uses FIFO which uses a queue (we use BFS here)
        //DFS uses a stack
        //informed vs. uninformed search differ in how you add to the frontier
        //informed adds throughout the frontier, keeping it in a sorted order
        //priority queue will give you the thing with the lowest score first
        //if it's Ordered, that means you want to set up a priority queue
    }
    
    /**
     * Solve this search problem.
     */
    public boolean solve() {
        // *************************** TO DO
        //acutally uses the algorithm
        //this is where you use the pseudocode in class
        //addToFrontier we COULD write a helper method for this
        //write this code such that it will work for whatever kind of frontier we will have
        //will be possible because of polymorphism
        //frontier of type queue, work with things available in queue interface
        //need counter for goalCheck
        //put start state on frontier
        //need a field for n, path
        //starting state for frontier is Offer 02/17 notes
        //select and remove first state in frontier is Poll
        //a generic search algorithm 02/05 slide 11
        PuzzlePath currentPath;
        PuzzleState currNode;
        PuzzlePath pathCopy;
        
        this.frontier.offer(new PuzzlePath(initState, h));
        int count = 1;//(if count = 0) it stopped on the first 1 it would say it was 0
        while(!frontier.isEmpty() && count <= goalCheckLimit)
        {
            currentPath = this.frontier.poll();
            currNode = currentPath.stateAtEndOfPath();
            if(currNode.equals(goalState)){//don't do n == goalState here
                currNode.expand();
                expanded.add(currNode);
                System.out.println("States checked: " + count);
                System.out.println("Solution length: " + currentPath.length());
                System.out.println("Solution: " + currentPath);
                return true;
            }
            
            if(!expanded.contains(currNode)) {
                expanded.add(currNode);
                for(PuzzleState state: currNode.expand()){
                    pathCopy = currentPath.makeCopy();
                    pathCopy.addState(state);
                    frontier.offer(pathCopy);
                }
            }
            count++;
        }
        return false;
    }   
}