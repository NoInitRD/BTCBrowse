package main;

import java.util.ArrayList;
import items.Transaction;
import parsers.TxParser;
import tools.Utilities;

public class DataDriver {

	ArrayList<Transaction> _currentTxList;
	Double _conversionRate;
	Transaction _currentMax;
	
	public DataDriver()
	{
		_currentTxList = new ArrayList<Transaction>(100); 
		_conversionRate = Utilities.grabMoneyData("https://blockchain.info/ticker"); //get conversion rate
		_currentMax = new Transaction(); 
	}
	
	public void start()
	{
		RequestBalancer lb = new RequestBalancer();
		lb.setRelevantTxIndex(99); //Makes sure to display first set of results
		
		while(true)
		{
			TxParser list = Utilities.grabTXData("https://blockchain.info/unconfirmed-transactions?format=json");
									
			for(Transaction item: list) 
			{
				_currentTxList.add(item);
				if (item.completeValue() > _currentMax.completeValue())
				{
					_currentMax = item;
				}
			}

			lb.setList(_currentTxList);
			lb.balanceTime();
			lb.setRelevantTx(_currentTxList.get(0));
			showTransactions(lb.getRelevantTxIndex());
			showCurrentMax();
						
			_currentTxList.clear();
			
			lb.sleep(lb.getDataRate());
		}
	}
	
	private void showCurrentMax()
	{
		System.out.println("-------------------The Current Max is-------------------");
		System.out.println(txToString(_currentMax));
	}
	
	private void showTransactions(int stop)
	{		
		for(int i = 0; i < stop; i++)
		{
			Transaction current = _currentTxList.get(i);
			System.out.printf(txToString(current));
		}
	}
	
	private String txToString(Transaction tx)
	{	
		
		Double value = tx.completeValue();
		Double total = value * _conversionRate;
		String totalS = String.format("%.02f", total);
		
		return "(" + 
				hashShortener(tx.getSenderAddress()) + " " +
				"  ----" +
				"$" + totalS +
				"--->  " +
				hashShortener(tx.getRecipientAddress()) +
				") " +
				"TX HASH: " +
				tx.getHash() + "\n";
		
	}
	
	private String hashShortener(String hash)
	{
		if(hash == null) return "Missing..."; //TODO
		return hash.substring(0, 7) + "...";
	}
}
