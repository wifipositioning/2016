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
        wifiServer.start();
    }
}
