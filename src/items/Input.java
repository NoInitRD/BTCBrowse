package items;

import java.util.HashSet;
import java.util.Set;

/**
 * @author NoInitRd
 * This class stores information about the transaction's inputs
 * Inputs represent the addresses and amounts of bitcoin spent
 */
public class Input {
	
	private Set<Output> _prevOutput;
	
	/**
	 * Default constructor for input
	 */
	public Input() 
	{
		_prevOutput = new HashSet<Output>();
	}
	
	/**
	 * Adds an output to the input's set of outputs
	 * @param output - The output to be added
	 * @return True if successfully added, false if otherwise
	 */
	public boolean add(Output output)
	{
		return _prevOutput.add(output);
	}
	
	/**
	 * Removes an Output in the input's set of outputs
	 * @param output - The output to be removed
	 * @return True if successfully removed, false if otherwise
	 */
	public boolean remove(Output output)
	{
		return _prevOutput.remove(output);
	}
	
	/**
	 * Returns a set of the previous outputs
	 * @return A set of the previous outputs
	 */
	public Set<Output> getPrevOutput()
	{
		return _prevOutput;
	}

}
