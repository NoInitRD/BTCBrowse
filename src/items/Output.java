package items;

/**
 * @author NoInitRd
 * This class stores information about the transaction's outputs
 * outputs represent the addresses and amounts of received bitcoin
 */
public class Output {
	
	private Integer _value;
	private String _txIndex;
	private String _address;

	/**
	 * Default constructor for output
	 */
	public Output() 
	{
		_value = 0;
		_txIndex = null;
		_address = null;
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
	
	public Output(Integer value, String txIndex, String address)
	{
		_value = value;
		_txIndex = txIndex; 
		_address = address;
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
	
	public String getAddress()
	{
		return _address;
	}
	
	public void setAddress(String address)
	{
		_address = address;
	}

}
