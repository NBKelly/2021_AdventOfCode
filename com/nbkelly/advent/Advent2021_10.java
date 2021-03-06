package com.nbkelly.advent;

/* imports */
import com.nbkelly.drafter.Drafter;
import com.nbkelly.drafter.Command;
import com.nbkelly.drafter.FileCommand;
import com.nbkelly.drafter.Timer;

/* imports from file */
import java.util.ArrayList;

/* my imported libs */
import com.nbkelly.lib.Util;
import com.nbkelly.drafter.BooleanCommand; //visualize cmd
import com.nbkelly.lib.Image; //visualizer lib
import java.util.LinkedList;
import java.math.BigInteger;
import com.nbkelly.lib.Pair;
import java.util.stream.Collectors;
/**
 * Extension of Drafter directed towards a general case.
 *
 * @see <a href="https://nbkelly.github.io/Drafter/com/nbkelly/package-summary.html" target="_top">
 * here</a> for the up to date online javadocs
 */
public class Advent2021_10 extends Drafter {
    /* WORKFLOW:
     *  set all needed commands with setCommands()
     *  post-processing can be performed with actOnCommands()
     *  the rest of your work should be based around the solveProblem() function
     */
    
    /* params injected from file */
    ArrayList<String> lines;
    
    //generate output
    boolean generate_output = false;
    
    /* solve problem here */
    @Override public int solveProblem() throws Exception {
	Timer t = makeTimer();

	BigInteger score = BigInteger.ZERO;	
	var comp_scores = new ArrayList<BigInteger>();

	for(var line : lines) {
	    LinkedList<Character> stack  = new LinkedList<Character>();
	    long line_score = 0;
	    outer: for(int i = 0; i < line.length(); i++) {
		var token = line.charAt(i);
		switch(token) {
		case '(': case '{':
		case '[': case '<':
		    stack.push(token);
		    continue outer;
		}
		
		if((1 + token - stack.pop())/2 != 1)
		    switch(token) {
		    case '>': line_score += 23940;
		    case '}': line_score += 1140;
		    case ']': line_score += 54;
		    case ')': line_score += 3;
			score = score.add(BigInteger.valueOf(line_score));
			break outer;
		    }
	    }	    	    

	    if(line_score == 0) {
		var m_score = BigInteger.ZERO;
		BigInteger five = BigInteger.valueOf(5);
		for(char token : stack) {
		    m_score = m_score.multiply(five);
		    switch(token) {
		    case '<': m_score = m_score.add(BigInteger.ONE);
		    case '{': m_score = m_score.add(BigInteger.ONE);
		    case '[': m_score = m_score.add(BigInteger.ONE);
		    case '(': m_score = m_score.add(BigInteger.ONE);
		    }
		}
		comp_scores.add(m_score);
	    }

	}	
	
	comp_scores.sort((left, right) -> left.compareTo(right));
		
        DEBUGF(1, "PART ONE: "); println(score);
        DEBUGF(1, "PART TWO: "); println(comp_scores.get(comp_scores.size()/2));
        
        /* visualize output here */
        generate_output();
	
	return DEBUG(1, t.split("Finished Processing"));
    }

    /* code injected from file */
    public void generate_output() throws Exception {
    	if(!generate_output)
    	    return;

	/* todo */
	
    	println(">generating output");
    }

    /* set commands */
    @Override public Command[] setCommands() {
	//do you want paged input to be optional? This is mainly a debugging thing,
	//or a memory management/speed thing
	_PAGE_OPTIONAL = false; //page does not show up as a user input command
	_PAGE_ENABLED = false;  //page is set to disabled by default
	
        /* code injected from file */
        FileCommand fc = new FileCommand("Input File", "The input file for this program",
        	       	     		 true, "--input-file", "--file");
        
        /* visualizer */
        BooleanCommand vc = new BooleanCommand("Visualize Output",
        				       "The visualized output for this program", 
        				       false, "--out-file", "--output-file", "--out-image");
        
        return new Command[]{fc, vc};
        
        
    }
    
    /* act after commands processed - userCommands stores all the commands set in setCommands */
    @Override public int actOnCommands(Command[] userCommands) throws Exception {
	//do whatever you want based on the commands you have given
	//at this stage, they should all be resolved
        /* code injected from file */
        lines = readFileLines(((FileCommand)userCommands[0]).getValue());
        setSource(((FileCommand)userCommands[0]).getValue());
        
        generate_output = ((BooleanCommand)userCommands[1]).matched();
	return 0;
    }

    /**
     * Creates and runs an instance of your class - do not modify
     */
    public static void main(String[] argv) {
        new Advent2021_10().run(argv);
    }
}
