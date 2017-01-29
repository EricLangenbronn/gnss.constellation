package fr.gnss.constellation.ouranos.service;

/**
 * Service d'accès à la configuration du module.
 * 
 * 
 */
public interface IConfigurationService {

	/**
	 * <p>
	 * Initialise les propriétés avec les valeurs issues du parametreService.
	 * </p>
	 * <p>
	 * <strong>Le parametreService doit être initialisé avant !!</strong>
	 */
	void init();

	/**
	 * 
	 * @return Le chemin d'accès au répertoire de fichier SP3
	 */
	String getDirectorySp3();

	/**
	 * @return <code>true</code> si on autorise la connexion au serveur de
	 *         fichier SP3
	 */
	boolean isaccessFtpSp3();
}
