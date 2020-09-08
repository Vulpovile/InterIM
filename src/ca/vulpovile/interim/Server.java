package ca.vulpovile.interim;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class Server {
	public static final Logger logger = Logger.getLogger("InterIM Logger");	
	static
	{
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new LogFormatter());
		logger.setUseParentHandlers(false);
		logger.addHandler(handler);
		
	}
}
