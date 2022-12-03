package main;

public class ArgumentContainer {

	protected boolean _max; //keep track of max
	protected boolean _min; //same for min
	protected boolean _mean; //same for mean
	protected boolean _hash; //display hash
	protected boolean _bitcoin; //display # btc
	protected boolean _search; //just search
	protected boolean _verbose; //show all
	
	public ArgumentContainer()
	{
		_max = false;
		_min = false;
		_mean = false;
		_hash = false;
		_bitcoin = false;
		_search = false;
		_verbose = false;
	}
	
	public ArgumentContainer(String[] args)
	{
		stringArgsToBool(args);
	}
	
	private boolean[] stringArgsToBool(String[] args)
	{
		boolean[] result = new boolean[7];
		if(args[0].equals("-max")) _max = true;
		if(args[1].equals("-min")) _min = true;
		if(args[2].equals("-mean")) _mean = true;
		if(args[3].equals("-hash")) _hash = true;
		if(args[4].equals("-bitcoin")) _bitcoin = true;
		if(args[5].equals("-search")) _search = true;
		if(args[6].equals("-verbose")) _verbose = true;

		return result;
	}
	
	

}
