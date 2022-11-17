package tools;

import java.net.URL;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import parsers.TxParser;

/**
 * @author NoInitRd
 * This class contains basic utility methods
 */
public final class Utilities {

	/**
	 * Suspends the thread
	 * @param ms - Time in milliseconds for thread to sleep
	 */
	public static void sleep(int ms) 
	{
			try {
				Thread.currentThread().sleep(ms);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	
	public static TxParser grabTXData(String url)
	{
		try
		{
			APIRetriever conn = new APIRetriever(new URL(url));
			String raw = conn.getRawFromURL();
			TxParser jp = new TxParser(raw);
			return jp;
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
			return null;
		}
	}
	


	public static Double grabMoneyData(String url)
	{
		try
		{
			APIRetriever conn = new APIRetriever(new URL(url));
			String raw = conn.getRawFromURL();
			return currencyParser(raw);
			
		}
		catch(Exception e)
		{
			System.out.println("Error: " + e.toString());
			return null;
		}
	}
	
	
	public static Double currencyParser(String raw)
	{		
		JSONObject currencyInfo = new JSONObject(raw).getJSONObject("USD");
		return currencyInfo.getDouble("last");
	}
	
	public static Double currencyParser(String raw, String currency)
	{		
		JSONObject currencyInfo = new JSONObject(raw).getJSONObject(currency);
		return currencyInfo.getDouble("last");
	}
	
}
