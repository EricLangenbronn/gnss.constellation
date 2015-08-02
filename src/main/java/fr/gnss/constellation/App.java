package fr.gnss.constellation;

import java.io.IOException;

import fr.gnss.constellation.librairy.coordinate.CartesianCoordinate;
import fr.gnss.constellation.librairy.coordinate.CoordinateFunction;
import fr.gnss.constellation.librairy.coordinate.GeodeticCoordinate;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {

	public static void main(String[] args) {
		launch(args);

		double lat = 38.889139;
		double lon = -77.049;
		double alt = 130.049;

		double[] res = new double[3];
		res[0] = lat;
		res[1] = lon;
		res[2] = alt;
		GeodeticCoordinate cg = new GeodeticCoordinate(
				CoordinateFunction.degreeToRadian(res));
		System.out.println("res : " + cg);

		CartesianCoordinate cc = CoordinateFunction.geodeticToCartesian(cg);
		System.out.println("cc : " + cc);
		cc.normalize();
		System.out.println("cc normalize : " + cc);
		System.out.println("cc length : " + cc.magnitude() );

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("GNSS Constellation");
		primaryStage.setScene(new Scene((Parent) App
				.loadFxml("/fr/gnss/constellation/gui/fx/Main.fxml"), 1024, 968));
		primaryStage.show();
	}

	public static Node loadFxml(String fxml) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(App.class.getResource(fxml));
			Node root = (Node) loader.load(App.class.getResource(fxml)
					.openStream());
			return root;
		} catch (IOException e) {
			throw new IllegalStateException(
					"cannot load FXML screen : " + fxml, e);
		}
	}
}
