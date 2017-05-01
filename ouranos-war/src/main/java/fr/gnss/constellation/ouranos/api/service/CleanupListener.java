package fr.gnss.constellation.ouranos.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class CleanupListener implements ApplicationListener<ContextClosedEvent> {

	private final static Logger LOGGER = LoggerFactory.getLogger(CleanupListener.class);

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		// TODO Auto-generated method stub

	}
}
