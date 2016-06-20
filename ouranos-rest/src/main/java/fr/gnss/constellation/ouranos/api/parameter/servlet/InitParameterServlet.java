package fr.gnss.constellation.ouranos.api.parameter.servlet;


import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.gnss.constellation.ouranos.services.OuranosPropertiesService;


public class InitParameterServlet extends HttpServlet
{

	/**
     * Le logger de la classe.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(InitParameterServlet.class);

    /**
     * private static final String
     */
    private static final String PARAMETRAGE_SERVICE_NAME = "propertiesService";

    /**
    *
    */
    public InitParameterServlet()
    {
        super();
    }

    /*
     * (non-Javadoc)
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException
    {
        super.init();
        try
        {
            initialiserParametres();
        }
        catch (Exception e)
        {
        	String message = "Une erreur est intervenue au chargement de la configuration";
            LOGGER.error(message, e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see javax.servlet.http.HttpServlet.doGet
     */
    @Override
    public void doGet(HttpServletRequest p_Request, HttpServletResponse p_Reponse) throws IOException, ServletException
    {
    	PrintWriter writer = p_Reponse.getWriter();
        String res = "";

        try
        {
            initialiserParametres();
            res = "Rechargement Ok";
        }
        catch (Exception e)
        {
        	String message ="Une erreur est intervenue au chargement de la configuration";
            LOGGER.error(message, e);
            res = "Rechargement Ko";
        }
        
        writer.write(res);
    }

    /*
     * Initilise les paramétres
     */
    private synchronized void initialiserParametres() throws Exception
    {
        try
        {
            LOGGER.info("Chargement de la configuration");

            String nomParametrerepertoireconfiguration = getServletContext().getInitParameter("app.conf.dir");
            String nomfichierconfiguration = getServletContext().getInitParameter("app.conf.file");
            
            String repertoireconfiguration = System.getProperty(nomParametrerepertoireconfiguration);
            
            LOGGER.info("Le répertoire de configuration est  : " + repertoireconfiguration);
            LOGGER.info("Le fichier de configuration est  : " + nomfichierconfiguration);

            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
            OuranosPropertiesService service = (OuranosPropertiesService) context.getBean(PARAMETRAGE_SERVICE_NAME);
            service.initConfiguration(repertoireconfiguration, nomfichierconfiguration);

            LOGGER.info("Servlet : rechargement configuration : OK");
        }
        catch (Exception e)
        {
        	String message = "Une erreur est intervenue au chargement de la configuration";
            LOGGER.error(message, e);
            throw e;
        }
    }
}
