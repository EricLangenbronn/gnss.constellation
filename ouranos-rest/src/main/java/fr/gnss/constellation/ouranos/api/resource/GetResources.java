package fr.gnss.constellation.ouranos.api.resource;

import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
public class GetResources {
	
	/**
     * Le logger de la classe.
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(GetResources.class);

    /**
     * Creates a new instance of RelationResource
     */
    public GetResources()
    {
    	LOGGER.info("charger");
    }

}
