package items;

/**
 * @author NoInitRd
 * This class stores information about the transaction's outputs
 * outputs represent the addresses and amounts of received bitcoin
 */
public class Output {
	
	private Integer _value;
	private String _txIndex;

	/**
	 * Default constructor for output
	 */
	public Output() 
	{
		_value = null;
		_txIndex = null;
	}
	
	/**
	 * Parameterized constructor for output
	 * @param value - Value of the output
	 */
	public Output(Integer value)
	{
		_value = value; 
	}
	
	/**
	 * Parameterized constructor for output
	 * @param value - Value of the output
	 * @param txIndex - The transaction index
	 */
	public Output(Integer value, String txIndex)
	{
		_value = value;
		_txIndex = txIndex; 
	}

	/**
	 * @return The transaction index
	 */
	public String getTxIndex() 
	{
		return _txIndex;
	}

	/**
	 * Sets the transaction index
	 * @param txIndex - The transaction index
	 */
	public void setTxIndex(String txIndex) 
	{
		_txIndex = txIndex;
	}
	
	/**
	 * @return The value of the Output
	 */
	public Integer getValue()
	{
		return _value;
	}
	
	/**
	 * Sets the value of the Output
	 * @param value - The value of the Output
	 */
	public void setValue(int value)
	{
		_value = value;
	}

}
