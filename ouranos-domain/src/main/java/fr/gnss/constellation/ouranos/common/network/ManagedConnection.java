package fr.gnss.constellation.ouranos.common.network;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class ManagedConnection<E extends IConnection> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ManagedConnection.class);

    private static final int NB_TENTATIVE_CONNECTION = 3;

    private final int nbTentativeConnection;

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
            LOGGER.info("Nombre de tentative de connexion : " + nbTentative);
            nbTentative = nbTentative + 1;
            try {
                connection.openConnection();
            } catch (IOException e) {
                LOGGER.warn("Impossible d'ouvrir la connexion à la tentative : " + nbTentative);
            }
        }

        if (connection.isConnectionOpen()) {
            return connection;
        } else {
            connection.closeConnection();
            return null;
        }

    }
}
