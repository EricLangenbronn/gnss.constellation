package fr.gnss.constellation.ouranos.api.resource.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.api.service.ServiceLocator;

public class AboutServlet extends HttpServlet {
	/**
	 * Le logger de la classe.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AboutServlet.class);

	public AboutServlet() {
		super();
	}

	/**
	 * Infos webapps
	 */
	@Override
	protected void doGet(HttpServletRequest p_Req, HttpServletResponse p_Resp) throws ServletException, IOException {
		PrintWriter writer = p_Resp.getWriter();

		String versionService = "Version Services : " + ServiceLocator.getInformationsVersion();
		String versionWebapp = "Version Webapps : " + ServiceLocator
				.getInformationsVersionWebapp(getServletContext().getResourceAsStream("/META-INF/MANIFEST.MF"));

		writer.println(versionService + "\n" + versionWebapp);
	}
}
