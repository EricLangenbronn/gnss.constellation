package fr.gnss.constellation.ouranos.services;

import fr.gnss.constellation.ouranos.commons.exception.BusinessException;
import fr.gnss.constellation.ouranos.commons.exception.TechnicalException;

public interface OuranosPropertiesService
{

    /**
     * Réinitialise la configuration. Il faut commencer par cette méthode avant l'utilisation des obtenir.
     * 
     * @param p_CheminProperties
     *            le chemin du properties, exemple : /domaines/ouranos/properties/ouranos-rest-api.properties
     * @throws BusinessException
     *             soucis applicatif
     * @throws TechnicalException
     *             soucis technique
     */
    void initConfiguration(String p_RepertoireConfiguration, String p_fichierProperties)
            throws BusinessException, TechnicalException;

    /**
     * Obtention d'un int
     * 
     * @param p_Cle
     *            la clé
     * @return le int
     */
    int getInteger(String p_Cle);

    /**
     * vérifie l'existance d'une clé dans le fichier de configuration
     * 
     * @param p_Cle
     *            la clé
     * @return le int
     */
    boolean exists(String p_Cle);
    
    /**
     * Obtention d'une string
     * 
     * @param p_Cle
     *            la clé
     * @return le int
     */
    String getString(String p_Cle);

    /**
     * Obtention d'un boolean
     * 
     * @param p_Cle
     *            la clé
     * @return le int
     */
    boolean getBoolean(String p_Cle);

    /**
     * Obtention du nom de fichier de configuration
     * 
     * @param p_Cle
     *            la clé
     * @return le int
     */
    String getNomRepertoireConfiguration();

    /**
     * Obtention du nom du repertoire de configuration
     * 
     * @param p_Cle
     *            la clé
     * @return le int
     */
    String getNomFichierConfiguration();

}
