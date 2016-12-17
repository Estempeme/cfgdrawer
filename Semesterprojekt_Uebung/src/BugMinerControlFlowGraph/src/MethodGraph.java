package BugMinerControlFlowGraph.src;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;
// import java.util.HashSet;

/**
 * Class that defines a MethodGraph object
 */
public class MethodGraph {

    String methodId;

    private int methodCalls;

    // private HashSet<Instruction> instructions;

    private HashMap<Integer,Transition> transitions = new HashMap<>();

    MethodGraph(String methodId){
        this.methodId = methodId;
        this.methodCalls = 0;
    }

    void update(Edge edge){
        if(edge.isFirstLine()){
            methodCalls++;
            return;
        }
        int pairedHash = hashSourceAndTarget(edge.lineFrom, edge.lineTo);
        Transition transition = transitions.computeIfAbsent(pairedHash, k -> new Transition(edge.lineFrom, edge.lineTo));
        transition.update();
    }

    @Override
    public int hashCode(){
        return methodId.hashCode();
    }

    @Contract(pure = true)
    private int hashSourceAndTarget(int source, int target){
        // implements Cantor's pairing function
        int sum = source + target;
        return ((sum)*(sum + 1)/2) + target;
    }

    Transition getTransition(int source, int target){
        int pairedHash = hashSourceAndTarget(source, target);
        return transitions.get(pairedHash);
    }

    void printAllTransitions(){
        for(Transition transition: transitions.values()){
            System.out.print(transition.getSource() + "->" + transition.getTarget() + " : ");
            System.out.println(transition.getCount());
        }
    }
}
