package com.wifipositioning.app;

import com.wifipositioning.service.WifiServer;

/**
 * Hello world!
 *
 */
public class Server 
{
    public static void main( String[] args )
    {
        WifiServer wifiServer = new WifiServer();
        if(wifiServer.start()){
        	System.out.println("WifiServer Start Succeed!");
        }
        else{
        	System.out.println("WifiServer Start Failed!");
        }
    }
}
