package org.mycelium.mycelium;

import java.io.IOException;

import jline.ConsoleReader;
import jline.Terminal;

import org.mycelium.mycelium.io.Log;
import org.mycelium.mycelium.io.ServerSettings;
import org.mycelium.mycelium.io.net.Listener;
import org.mycelium.mycelium.io.net.PacketHandler;

public class Main {
	
	private static Log				log;
	private static Listener			server;
	
	public static boolean			isDebug	= java.lang.management.ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;
	public static ServerSettings	serverSettings;
	public static ConsoleReader		reader;
	
	public static void main(String[] args) {
		
		if (isDebug) new eric.Console();
		
		for (String arg : args) {
			if (arg.toLowerCase().contains("-debug"))
				isDebug = true;
		}
		
		Terminal.setupTerminal();
		try {
			reader = new ConsoleReader();
		} catch (IOException e) {
			log.Severe("Failed to make an instante of jline.ConsoleReader, aborting!");
			System.exit(-700);
		}

		log = Log.getLog();
		log.Info("Mycelium is initializing...");
		PacketHandler.Init();
		serverSettings = new ServerSettings();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			
			@Override
			public void run() {
				server.Stop();
				Log.getLog().Info("Server is stopped");
			}
		});
		
		log.Info("Starting the server...");
		server = new Listener(serverSettings.getPort());
		server.Start();
		commandline();
	}
	
	private static void commandline() {
		String command = null;
		reader.setBellEnabled(false);
		reader.setUseHistory(true);
		reader.setUsePagination(true);
		while (server.IsRunning()) {
			try {
				command = reader.readLine(">", null);
				if (command != null) {
					if (command.trim().equalsIgnoreCase("help"))
						log.Info("Help - help menu :P\nStop - stops the server");
					else if (command.trim().equalsIgnoreCase("stop")) {
						server.Stop();
						return;
					}
				}
			} catch (IOException e) {
				log.Warning(e.getLocalizedMessage());
			}
		}
	}
}
