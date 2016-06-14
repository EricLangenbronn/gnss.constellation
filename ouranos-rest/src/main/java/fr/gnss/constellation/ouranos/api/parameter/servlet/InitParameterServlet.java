package fr.gnss.constellation.ouranos.api.parameter.servlet;


import java.io.BufferedOutputStream;
import java.io.IOException;

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
     * peivate static final String
     */
    private static final String PARAMETRAGE_SERVICE_NAME = "parametreService";

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
            LOGGER.error("Une erreur est intervenue au chargement de la configuration", e);
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
        String res = "Rechargement Ok";

        try
        {
            initialiserParametres();
        }
        catch (Exception e)
        {
            LOGGER.error("Une erreur est intervenue au chargement de la configuration", e);
            res = "Rechargement Ko";
        }

        BufferedOutputStream out = null;

        try
        {
            out = new BufferedOutputStream(p_Reponse.getOutputStream());
            p_Reponse.setContentType("text/plain");
            // prepare output stream
            out.write(res.getBytes());
        }
        catch (Exception e)
        {
            LOGGER.error("Une erreur est intervenue à l'affichage de la réponse", e);
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }

    /*
     * Initilise les paramétres
     */
    private synchronized void initialiserParametres() throws Exception
    {
        try
        {
            LOGGER.info("Chargement de la configuration");

            String nomParametrerepertoireconfiguration = getServletContext().getInitParameter("property.app.conf.dir");
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
            LOGGER.error("Une erreur est intervenue au chargement de la configuration", e);
            throw e;
        }
    }
}
