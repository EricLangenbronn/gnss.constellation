package fr.gnss.constellation.ouranos.ftp;

public interface IConnection {

	void openConnection();

	boolean isConnectionOpen();

	void closeConnection();

}
