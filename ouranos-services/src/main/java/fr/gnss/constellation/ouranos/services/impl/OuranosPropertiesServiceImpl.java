package fr.gnss.constellation.ouranos.services.impl;


import java.io.File;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.commons.utils.ParameterUtils;

public class OuranosPropertiesServiceImpl implements fr.gnss.constellation.ouranos.services.OuranosPropertiesService
{

	/**
     * Le logger de la classe.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OuranosPropertiesServiceImpl.class);

    /** properties */
    private static Properties s_Properties = null;

    /**
     * nom du répertoire de configuration
     */
    private String m_configurationDirectorieName = "config";

    /**
     * nom du fichier de configuration
     */
    private String m_configurationFileName = "services.properties";

    /*
     * (non-Javadoc)
     * @see fr.gouv.finances.douane.referentiel.api.nomenclature.service.ParametreServiceInterface#initialiserConfiguration(java.lang.String)
     */
    @Override
    public void initConfiguration(String p_RepertoireConfiguration, String p_fichierProperties)
            throws BusinessException, TechnicalException
    {
        s_Properties = null;
        m_configurationDirectorieName = p_RepertoireConfiguration;
        m_configurationFileName = p_fichierProperties;
        s_Properties = ParameterUtils.loadParameters(p_RepertoireConfiguration, p_fichierProperties);
    }

    /* (non-Javadoc)
     * @see fr.gouv.finances.douane.referentiel.api.nomenclature.service.ParametreServiceInterface#obtenirInteger(java.lang.String)
     */
    @Override
    public int getInteger(String p_Cle)
    {
        Properties prop = getProperties();

        int res = 0;
        try
        {

            res = Integer.valueOf(prop.get(p_Cle) + "");

        }
        catch (Exception e)
        {
            LOGGER.error("Impossible d'obtenir le int de : " + p_Cle, e);
        }

        return res;
    }

    /*
     * (non-Javadoc)
     * @see fr.gouv.finances.douane.referentiel.api.parametre.service.ParametreServiceInterface#obtenirBoolean(java.lang.String)
     */
    @Override
    public boolean getBoolean(String p_Cle)
    {
        Properties prop = getProperties();

        boolean res = false;
        try
        {

            res = Boolean.valueOf(prop.get(p_Cle) + "");

        }
        catch (Exception e)
        {
            LOGGER.error("Impossible d'obtenir le boolean de : " + p_Cle, e);
        }

        return res;
    }

    /*
     * (non-Javadoc)
     * @see fr.gouv.finances.douane.referentiel.api.parametre.service.ParametreServiceInterface#obtenirString(java.lang.String)
     */
    @Override
    public String getString(String p_Cle)
    {
        Properties prop = getProperties();

        String res = "";
        try
        {

            res = prop.get(p_Cle) + "";

        }
        catch (Exception e)
        {
            LOGGER.error("Impossible d'obtenir le String de : " + p_Cle, e);
        }

        return res;
    }

    /*
     * (non-Javadoc)
     * @see fr.gouv.finances.douane.referentiel.api.parametre.service.ParametreServiceInterface#exists(java.lang.String)
     */
    @Override
    public boolean exists(String p_Cle)
    {
        Properties prop = getProperties();

        return prop.containsKey(p_Cle);
    }

    /**
     * @return les propriétés
     */
    private Properties getProperties()
    {
        if (null == s_Properties)
        {
            try
            {
                s_Properties = loadProperties();
            }
            catch (Exception e)
            {
                LOGGER.error("Erreur lors du chaergement des fichiers de paramètres");
            }
        }
        return s_Properties;
    }

    /**
     * @param p_CheminProperties
     *            le chemin des properties
     * @return les propriétés
     */
    private synchronized Properties loadProperties() throws BusinessException, TechnicalException
    {
        if (s_Properties == null)
        {
            LOGGER.info("DEBUT chargement de la configuration : " + m_configurationDirectorieName + File.separator
                    + m_configurationFileName);
            s_Properties = ParameterUtils.loadParameters(m_configurationDirectorieName, m_configurationFileName);
            LOGGER.info("FIN du chargement de la configuration ROSA_REST_API");
        }
        return s_Properties;
    }

    /* (non-Javadoc)
     * @see fr.gouv.finances.douane.referentiel.api.parametre.service.ParametreServiceInterface#getNomRepertoireConfig()
     */
    @Override
    public String getNomRepertoireConfiguration()
    {
        return m_configurationDirectorieName;
    }

    /* (non-Javadoc)
     * @see fr.gouv.finances.douane.referentiel.api.parametre.service.ParametreServiceInterface#getNomRepertoireConfig()
     */
    @Override
    public String getNomFichierConfiguration()
    {
        return m_configurationFileName;
    }

    /**
     * Méthode permettant d'initialiser la valeur de m_NomRepertoireConfiguration.
     * 
     * @param m_NomRepertoireConfiguration
     *            le/la m_NomRepertoireConfiguration à initialiser
     */
    public void setNomRepertoireConfiguration(String m_NomRepertoireConfiguration)
    {
        this.m_configurationDirectorieName = m_NomRepertoireConfiguration;
    }

    /**
     * Méthode permettant d'initialiser la valeur de m_NomFichierConfiguration.
     * 
     * @param m_NomFichierConfiguration
     *            le/la m_NomFichierConfiguration à initialiser
     */
    public void setNomFichierConfiguration(String m_NomFichierConfiguration)
    {
        this.m_configurationFileName = m_NomFichierConfiguration;
    }
}
