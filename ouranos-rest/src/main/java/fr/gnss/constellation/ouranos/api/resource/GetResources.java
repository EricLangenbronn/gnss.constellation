package fr.gnss.constellation.ouranos.api.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

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
    
    @GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
    	LOGGER.info("param");
		String output = "Jersey say : " + msg;
 
		return Response.status(200).entity(output).build();
 
	}

}
