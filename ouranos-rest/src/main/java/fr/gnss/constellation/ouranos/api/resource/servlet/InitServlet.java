package fr.gnss.constellation.ouranos.api.resource.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.gnss.constellation.ouranos.api.service.ServiceLocator;

public class InitServlet extends HttpServlet {

	/**
     * Le logger de la classe.
     */
	private static final Logger LOGGER = LoggerFactory.getLogger(InitServlet.class);

	/**
    *
    */
    public InitServlet()
    {
        super();
    }

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	public void init() throws ServletException {
		super.init();

		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		ServiceLocator.getServiceFactory(context);

		LOGGER.info("Version Webapps : " + ServiceLocator.getServiceFactory()
				.getInformationsVersionWebapp(getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF")));

	}

}
