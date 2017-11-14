package ehotel.admin.Monitor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class LibraySSH {
	 /**
     * The hostname (or IP address) of the server to connect to
     */
    private String hostname;
    
    /**
     * The username of the user on that server
     */
    private String username;
    
    /**
     * The password of the user on that server
     */
    private String password;
    
    /**
     * A connection to the server
     */
    private Connection connection;
    
    /**
     * Creates a new SSHAgent
     * 
     * @param hostname
     * @param username
     * @param password
     */
    public LibraySSH( String hostname, String username, String password )
    {
        this.hostname = hostname;
        this.username = username;
        this.password = password;
    }
    
    /**
     * Connects to the server
     * 
     * @return        True if the connection succeeded, false otherwise
     */
    public boolean connect() 
    {
        try
        {
            // Connect to the server
            connection = new Connection( hostname );
            connection.connect();
            
            // Authenticate
            boolean result = connection.authenticateWithPassword( username, password );
            System.out.println( "Connection result: " + result );
            return result;
        }
        catch( Exception e )
        {
        
            try {
				//throw new Exception( "An exception occurred while trying to connect to the host: " + hostname + ", Exception=" + e.getMessage(), e );
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			} 
        }
		return false;
    }
    /**
     * Executes the specified command and returns the response from the server
     *  
     * @param command        The command to execute
     * @return               The response that is returned from the server (or null)
     */
    public List<ehotelMonitor> executeCommand( String command ) 
    {
        try
        {
        	int count =0;
        	List<ehotelMonitor> ehotlmonitor =new ArrayList<ehotelMonitor>();
            // Open a session
            Session session = connection.openSession();
            
            // Execute the command
            session.execCommand( command );
            
            // Read the results
            StringBuilder sb = new StringBuilder();
            InputStream stdout = new StreamGobbler( session.getStdout() );
            System.out.print("start ........");
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            String line = br.readLine();
            while( line != null && count<3)
            {
            	count++;
            	
            	if(count==1){
            	  ehotelMonitor _ehotelMonitor =new ehotelMonitor();
                  _ehotelMonitor.setFullname(line);
                  ehotlmonitor.add(_ehotelMonitor);
                  
            	}
            	
               // sb.append( line + "\n" );
                line = br.readLine();  
            }
            
          

            // Close the session
            session.close();
            
            // Return the results to the caller
            return ehotlmonitor;
        }
        catch( Exception e )
        {
        		 System.out.println( "An exception occurred while executing the following command: " + command + ". Exception = " + e.getMessage());
      
        }
		return null;
		
    }
    public List<ehotelMonitor> executeCommandLog( String command ) 
    {
        try
        {
        	int count =0;
        	List<ehotelMonitor> ehotlmonitor =new ArrayList<ehotelMonitor>();
            // Open a session
            Session session = connection.openSession();
            
            // Execute the command
            session.execCommand( command );
            
            // Read the results
            StringBuilder sb = new StringBuilder();
            InputStream stdout = new StreamGobbler( session.getStdout() );
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            String line = br.readLine();
            while( line != null)
            {
            	
            	  ehotelMonitor _ehotelMonitor =new ehotelMonitor();
                  _ehotelMonitor.setFullname(line);
                  ehotlmonitor.add(_ehotelMonitor);
               // sb.append( line + "\n" );
                line = br.readLine();  
            }

            // Close the session
            session.close();
            
            // Return the results to the caller
            return ehotlmonitor;
        }
        catch( Exception e )
        {
        		 System.out.println( "An exception occurred while executing the following command: " + command + ". Exception = " + e.getMessage());
      
        }
		return null;
		
    }
    /**
     * Logs out from the server
     * @throws SSHException
     */
    public void logout()  
    {
        try
        {
            connection.close();
        }
        catch( Exception e )
        {
          System.out.println( "An exception occurred while closing the SSH connection: " + e.getMessage());
        }
    }
    
    /**
     * Returns true if the underlying authentication is complete, otherwise returns false
     * @return
     */
    public boolean isAuthenticationComplete()
    {
        return connection.isAuthenticationComplete();
    }

}
