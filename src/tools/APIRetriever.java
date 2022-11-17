package tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author NoInitRd
 * This class retrieves the raw data from Blockchain.info's developer API
 */
public class APIRetriever {

	private URL _url;
	
	/**
	 * Default constructor for the retriever
	 */
	public APIRetriever() 
	{
		_url = null;
	}
	
	/**
	 * Parameterized constructor for the retriever
	 * @param url - the URL of the server
	 */
	public APIRetriever(URL url)
	{		
		_url = url;
	}
	
	/**
	 * Collects the raw data from the API after checking server status
	 * @return Raw string data if server is up, null if otherwise
	 */
	public String getRawFromURL()
	{
		if(!checkWebsiteStatus()) return null;
		try
		{
			InputStream stream = _url.openStream();
			InputStreamReader sReader = new InputStreamReader(stream);
			BufferedReader bReader = new BufferedReader(sReader);
			StringBuilder sBuilder = new StringBuilder();

			String line = "";
			while((line = bReader.readLine()) != null)
			{
				sBuilder.append(line);
			}
			return sBuilder.toString();
		}
		catch(Exception e)
		{
			System.out.println("getRawFromURL failed");
			return null;
		}
	}
	
	/**
	 * Checks the server's status
	 * @return True if up, false if down
	 */
	private boolean checkWebsiteStatus()
	{
		try
		{
			HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
			connection.getContent();
			return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
