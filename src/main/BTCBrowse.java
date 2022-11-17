package main;

public class BTCBrowse {


	public static void main(String[] args) 
	{
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			@Override
			public void run()
			{
				System.out.println();
			}
		});
		
		DataDriver dd = new DataDriver(args);
		dd.start();
	
	}

}



//https://www.blockchain.com/btc/tx/78c36aa6a5a9b0c7bc042ede86180ad5741444d299653ace225989877e52468c
//https://blockchain.info/unconfirmed-transactions?format=json