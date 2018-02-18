package fr.gnss.constellation.ouranos.toolbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe permettant d'initialiser des connexions.
 * 
 * @author eric
 *
 * @param <IConnection>
 */
public class ManagedConnection<E extends IConnection> {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagedConnection.class);

	private static final int NB_TENTATIVE_CONNECTION = 3;

	private int nbTentativeConnection;

	public ManagedConnection() {
		this.nbTentativeConnection = NB_TENTATIVE_CONNECTION;
	}

	public ManagedConnection(int nbTentativeConnection) {
		this.nbTentativeConnection = nbTentativeConnection;
	}

	/**
	 * Fonction permettant d'initialiser une connection en faisant plusieurs
	 * tentatives.
	 * 
	 * @param connection
	 * @return la connection si elle est ouverte, null sinon
	 */
	public E initConnection(E connection) {
		int nbTentative = 0;
		while ((nbTentative < this.nbTentativeConnection) && (!connection.isConnectionOpen())) {
			LOGGER.info("Nombre de tentative de connection : " + nbTentative);
			nbTentative = nbTentative + 1;
			connection.openConnection();
		}

		if (connection.isConnectionOpen()) {
			return connection;
		} else {
			return null;
		}

	}
}
