package main;

import java.util.ArrayList;
import items.Transaction;

/**
 * @author NoInitRd
 * This class balances the frequency of API connections
 */
public class RequestBalancer {
	
	Integer _dataRate;
	ArrayList<Transaction> _list;
	Transaction _relevantTx;
	
	public RequestBalancer() 
	{
		_dataRate = 8000; //default 8000ms delay
		_list = new ArrayList<Transaction>();
		_relevantTx = null;
	}
	
	public Integer getDataRate() { return _dataRate; }
	
	public void setDataRate(Integer datarate) { _dataRate = datarate; }
	
	public ArrayList<Transaction> getList() { return _list; }
	
	public void setList(ArrayList<Transaction> list) { _list = list; }
	
	public Transaction getRelevantTx() { return _relevantTx; }
	
	public void setRelevantTx(Transaction relevantTx) { _relevantTx = relevantTx; }
	
	public Integer balanceTime()
	{
		if(_relevantTx == null) return _dataRate;
		
		Integer index = indexOfRelevantTx();
		
		System.out.println("Last first's index in new list: " + index);
		
		if(index <= 20 && index >= 0) return addTime(10);
		if(index >= 75) return removeTime(10);
		if(index == -1) return removeTime(1000);
		
		return _dataRate;
	}
	
	public void clearList() { _list.clear(); }
	
	private Integer addTime(Integer distance)
	{
		return _dataRate += distance * 10;
	}
	
	private Integer removeTime(Integer distance) 
	{
		if(_dataRate - distance * 10 >= 0) return _dataRate -= distance * 10;
		return _dataRate;
	}
	
	private Integer indexOfRelevantTx()
	{
		for(int i = 0; i < _list.size(); i++)
		{
			if(_relevantTx.getHash().equals(_list.get(i).getHash())) return i;
		}
		return -1;
	}
}
