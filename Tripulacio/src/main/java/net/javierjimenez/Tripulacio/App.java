package net.javierjimenez.Tripulacio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author Surrui
 *
 */
public class App extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/tripulacio.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/tripulacio.css").toExternalForm());
			primaryStage.setTitle("Generació de vaixells amb tripulació");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}