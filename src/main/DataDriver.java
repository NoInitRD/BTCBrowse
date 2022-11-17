package main;

import java.util.ArrayList;
import items.Transaction;
import parsers.TxParser;
import tools.Utilities;

public class DataDriver {

	String[] _args;
	boolean _max; 
	boolean _min;
	boolean _avg;
	
	public DataDriver(String[] args) 
	{
		_args = args;
	}
	
	public void start()
	{
		Double max = 0D;
		String maxHash = "";
		
		Double conversionRate = Utilities.grabMoneyData("https://blockchain.info/ticker");
		
		RequestBalancer lb = new RequestBalancer();
		ArrayList<Transaction> arry = new ArrayList<Transaction>(100); 
		
		while(true)
		{
			TxParser list = Utilities.grabTXData("https://blockchain.info/unconfirmed-transactions?format=json");
									
			for(Transaction item: list)
			{
				Double value = item.completeValue();
				
				Double total = value * conversionRate;
				
				arry.add(item);

				if(total > max) {
					max = total;
					maxHash = item.getHash();	
				}
			}

			lb.setList(arry);
			lb.balanceTime();
			lb.setRelevantTx(arry.get(0));
						
			arry.clear();

			System.out.println(lb.getDataRate());
			Utilities.sleep(lb.getDataRate());
			
		}
	}
}
