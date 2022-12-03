package items;

import java.util.Set;

/**
 * @author NoInitRd
 * This class stores information about each bitcoin transaction
 */
public class Transaction {
	
	private String _hash;
	private Double _fee;
	private String _time;
	
	private Set<Input> _inputArray;
	private Set<Output> _outputArray;
	
	/**
	 * Default constructor for transaction
	 */
	public Transaction()
	{
		_hash = null;
		_fee = null;
		_time = null;
		_inputArray = null;
		_outputArray = null;
	}
	
	/**
	 * Parameterized constructor for Transaction
	 * @param hash - The transaction's hash
	 * @param fee - The transaction's fee
	 * @param time - Time of the transaction
	 * @param inputArray - The input set
	 * @param outputArray - The output set
	 */
	public Transaction(String hash, Double fee, String time,
			Set<Input> inputArray, Set<Output> outputArray)
	{
		_hash = hash;
		_fee = fee;
		_time = time;
		_inputArray = inputArray;
		_outputArray = outputArray;
	}
	
	/**
	 * Returns the hash associated with the transaction
	 * @return The transaction's hash
	 */
	public String getHash() {return _hash;}
	
	/**
	 * Returns a set of the inputs associated with the transaction
	 * @return A set of the transaction's inputs
	 */
	public Set<Input> getInput(){ return _inputArray;}
	
	/**
	 * Returns a set of the outputs associated with the transaction
	 * @return A set of the transaction's outputs
	 */
	public Set<Output> getOutput(){ return _outputArray;}
	
	/**
	 * Returns the time of the transaction
	 * @return The time of the transaction
	 */
	public String getTime() {return _time;}
	
	/**
	 * Returns the complete value of the bitcoin sent
	 * @return An integer representation of bitcoin sent
	 */
	public Double completeValue()
	{
		Double totalValueInputs = 0D;
		
		if(_inputArray == null) return 0D;
		
		for(Input input: _inputArray)
		{
			for(Output prevOutput: input.getPrevOutput())
			{
				totalValueInputs += prevOutput.getValue();
			}
		}
		
		return Math.abs((totalValueInputs - _fee) * .00000001); //Conversion from JSON # to a Double
	}
	
	//TODO check later
	public String getSenderAddress()
	{
		if(_inputArray.size() == 0) return null;
		Output biggestContributor = new Output(); 
		
		for(Input inp: _inputArray)
		{
			for(Output out: inp.getPrevOutput())
			{
				Integer value = out.getValue();
				if(value > biggestContributor.getValue())
				{
					biggestContributor = out;
				}
			}
		}
		return biggestContributor.getAddress();
	}
	
	//TODO check later
	public String getRecipientAddress()
	{
		Output biggestRecipient = new Output();
		
		for(Output out: _outputArray)
		{
			Integer value = out.getValue();
			if(value > biggestRecipient.getValue())
			{
				biggestRecipient = out;
			}
		}
		return biggestRecipient.getAddress();
	}
}
