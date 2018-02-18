package fr.gnss.constellation.ouranos.service;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;
import fr.gnss.constellation.ouranos.commons.utils.ParameterUtils;

@Service("ouranosPropertiesService")
public class PropertiesService implements IPropertiesService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesService.class);

	@Autowired
	private Properties s_Properties = null;

	/**
	 * nom du répertoire de configuration
	 */
	private String m_configurationDirectorieName = "config";

	/**
	 * nom du fichier de configuration
	 */
	private String m_configurationFileName = "ouranos-rest-api.properties";

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.gnss.constellation.ouranos.services.OuranosPropertiesService#
	 * initialiserConfiguration(java.lang.String)
	 */
	@Override
	public void initConfiguration(String p_RepertoireConfiguration, String p_fichierProperties) throws BusinessException, TechnicalException {
		s_Properties = null;
		m_configurationDirectorieName = p_RepertoireConfiguration;
		m_configurationFileName = p_fichierProperties;
		s_Properties = ParameterUtils.loadParameters(p_RepertoireConfiguration, p_fichierProperties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.gnss.constellation.ouranos.services.OuranosPropertiesService#
	 * obtenirInteger(java.lang.String)
	 */
	@Override
	public int getInteger(String p_Cle) {

		int res = 0;
		try {

			res = Integer.valueOf(s_Properties.get(p_Cle) + "");

		} catch (Exception e) {
			LOGGER.error("Impossible d'obtenir le int de : " + p_Cle, e);
		}

		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.gnss.constellation.ouranos.services.OuranosPropertiesService#
	 * obtenirBoolean(java.lang.String)
	 */
	@Override
	public boolean getBoolean(String p_Cle) {

		boolean res = false;
		try {

			res = Boolean.valueOf(s_Properties.get(p_Cle) + "");

		} catch (Exception e) {
			LOGGER.error("Impossible d'obtenir le boolean de : " + p_Cle, e);
		}

		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.gnss.constellation.ouranos.services.OuranosPropertiesService#obtenirString
	 * (java.lang.String)
	 */
	@Override
	public String getString(String p_Cle) {

		String res = "";
		try {

			res = s_Properties.get(p_Cle) + "";

		} catch (Exception e) {
			LOGGER.error("Impossible d'obtenir le String de : " + p_Cle, e);
		}

		return res;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.gnss.constellation.ouranos.services.OuranosPropertiesService#exists(java.
	 * lang.String)
	 */
	@Override
	public boolean exists(String p_Cle) {

		return s_Properties.containsKey(p_Cle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.gnss.constellation.ouranos.services.OuranosPropertiesService#
	 * getNomRepertoireConfig()
	 */
	@Override
	public String getNomRepertoireConfiguration() {
		return m_configurationDirectorieName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.gnss.constellation.ouranos.services.OuranosPropertiesService#
	 * getNomRepertoireConfig()
	 */
	@Override
	public String getNomFichierConfiguration() {
		return m_configurationFileName;
	}

	/**
	 * Méthode permettant d'initialiser la valeur de m_NomRepertoireConfiguration.
	 * 
	 * @param m_NomRepertoireConfiguration
	 *            le/la m_NomRepertoireConfiguration à initialiser
	 */
	public void setNomRepertoireConfiguration(String m_NomRepertoireConfiguration) {
		this.m_configurationDirectorieName = m_NomRepertoireConfiguration;
	}

	/**
	 * Méthode permettant d'initialiser la valeur de m_NomFichierConfiguration.
	 * 
	 * @param m_NomFichierConfiguration
	 *            le/la m_NomFichierConfiguration à initialiser
	 */
	public void setNomFichierConfiguration(String m_NomFichierConfiguration) {
		this.m_configurationFileName = m_NomFichierConfiguration;
	}
}
