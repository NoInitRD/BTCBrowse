package main;

public class BTCBrowse {

	public static void main(String[] args) 
	{
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			@Override
			public void run()
			{

			}
		});
		
		//This is outside because eclipse can be weird about the shutdown hook
		DataDriver dd = new DataDriver();
		dd.start();
	}
}