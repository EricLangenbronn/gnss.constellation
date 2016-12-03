package fr.gnss.constellation.ouranos.toolbox;

public interface IConnection {

	void openConnection();

	boolean isConnectionOpen();

	void closeConnection();

}
