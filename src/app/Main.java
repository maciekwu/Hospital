package app;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			//Dodanie zasobu widoku apliakcji
			Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LoginView.fxml"));
			//Osadzenie zasobu na scenie
			Scene scene = new Scene(parent);
			//Podpi�cie sty�w CSS
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//Osadzenie sceny na stage
			primaryStage.setScene(scene);
			//Dodanie tytu�u
			primaryStage.setTitle("Logowanie / Rejestracja");
			//Ustawienie widoczno�ci aplikacji
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
