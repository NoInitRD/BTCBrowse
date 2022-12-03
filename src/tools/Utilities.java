package tools;

import java.net.URL;

import org.json.JSONObject;

import parsers.TxParser;

/**
 * @author NoInitRd
 * This class contains basic utility methods
 */
public final class Utilities {
	
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
