package parsers;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.json.JSONArray;
import org.json.JSONObject;

import items.Input;
import items.Output;
import items.Transaction;

/**
 * @author NoInitRd
 * This class parses the raw transaction data into JSON objects
 * It then translates the JSON objects into usable objects
 */
public class TxParser implements Iterable<Transaction> {

	private JSONArray _txList;
	
	/**
	 * Default constructor for TxParser
	 */
	public TxParser() 
	{
		_txList = null;
	}
	
	/**
	 * Parameterized constructor for TxParser
	 * @param raw - The raw string acquired from API
	 */
	public TxParser(String raw)
	{
		JSONObject jObj = new JSONObject(raw);
		JSONArray jArray = (JSONArray) jObj.get("txs");
		_txList = jArray;
	}
	
	/**
	 * @author NoInitRd
	 * Iterator class for TxParser
	 * Returns each JSONObject as a Transaction object
	 */
	public class TxParserIterator implements Iterator<Transaction>
	{
		int _index = 0; 
		int _size = 99; //specifies the size of the API's transaction list
		
		/**
		 * @return True if next exists, false otherwise
		 */
		public boolean hasNext() 
		{
			return _index < _size;
		}
		
		/**
		 * @return Transaction object from JSONObject
		 */
		public Transaction next()
		{
			if(!hasNext()) {throw new NoSuchElementException();}
			
			_index++;
			
			JSONObject jsonTransaction = (JSONObject) _txList.get(_index);
			
			String hash = jsonTransaction.getString("hash");
			Double fee = jsonTransaction.getDouble("fee");
			String time = String.valueOf(jsonTransaction.getInt("time"));
			HashSet<Input> inputSet = gatherInputs(jsonTransaction);
			HashSet<Output> outputSet = gatherOutputs(jsonTransaction);
			
			Transaction result = new Transaction(hash, fee, time, inputSet, outputSet);
			
			return result;
			
		}
	}
	
	/**
	 * Extracts the inputs from the transaction JSONObject
	 * @param jsonTransaction - The transaction JSONObject
	 * @return A set of inputs
	 */
	public HashSet<Input> gatherInputs(JSONObject jsonTransaction)
	{
		HashSet<Input> result = new HashSet<Input>(); 
		
		JSONArray jsonInputArray = jsonTransaction.getJSONArray("inputs");
		
		for(int i = 0; i < jsonInputArray.length(); i++)
		{
			JSONObject jsonInput = jsonInputArray.getJSONObject(i);
			JSONObject jsonPrevOutput = jsonInput.getJSONObject("prev_out");
	
			Integer jsonPrevOutputValue = jsonPrevOutput.getInt("value");
			String jsonTxIndex = String.valueOf(jsonPrevOutput.getInt("tx_index"));
			
			Output prevOutput = new Output(jsonPrevOutputValue, jsonTxIndex); 
			Input input = new Input(); 
		
			input.add(prevOutput);
			
			result.add(input);
		}
		
		return result;
		
	}
	
	/**
	 * Extracts the outputs from the transaction JSONObject
	 * @param jsonTransaction - The transaction JSONObject
	 * @return A set of outputs
	 */
	public HashSet<Output> gatherOutputs(JSONObject jsonTransaction)
	{
		HashSet<Output> result = new HashSet<Output>(); 
		
		JSONArray jsonOutputArray = jsonTransaction.getJSONArray("out");
		
		for(int i = 0; i < jsonOutputArray.length(); i++)
		{	
			JSONObject jsonOutput = jsonOutputArray.getJSONObject(i);
			
			Integer jsonValue = jsonOutput.getInt("value");
			String jsonTxIndex = String.valueOf(jsonOutput.getInt("tx_index"));
			
			Output output = new Output(jsonValue, jsonTxIndex);
			
			result.add(output);
			

		}
	
		return result;
	}
	
	/**
	 * Constructor for TxParser's iterator class
	 */
	public Iterator<Transaction> iterator()
	{
		return new TxParserIterator();
	}

}


