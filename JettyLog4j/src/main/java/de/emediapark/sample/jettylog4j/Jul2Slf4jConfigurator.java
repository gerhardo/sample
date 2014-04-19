package de.emediapark.sample.jettylog4j;

import org.slf4j.bridge.SLF4JBridgeHandler;

public abstract class Jul2Slf4jConfigurator {

	public static void init() {
		// Optionally remove existing handlers attached to j.u.l root logger
		SLF4JBridgeHandler.removeHandlersForRootLogger(); // (since SLF4J 1.6.5)

		// add SLF4JBridgeHandler to j.u.l's root logger, should be done once
		// during
		// the initialization phase of your application
		SLF4JBridgeHandler.install();
	}
}