package fr.gnss.constellation.ouranos.api;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

public class OuranosRestApplicationClosingListener implements ApplicationListener<ContextClosedEvent> {

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {
		// TODO Auto-generated method stub

	}

}
