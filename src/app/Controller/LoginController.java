package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Database.DBConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.ToggleGroup;

public class LoginController {

	@FXML
	private AnchorPane ach;

	@FXML
	private PasswordField pf_passRegister;

	@FXML
	private Button btn_showRegister;

	@FXML
	private RadioButton rb_regDoctor;

	@FXML
	private RadioButton rb_regPatient;

	@FXML
	private Button btn_Register;

	@FXML
	private TextField tf_login;

	@FXML
	private PasswordField pf_pass;

	@FXML
	private Button btn_show;

	@FXML
	private RadioButton rb_logDoctor;

	@FXML
	private RadioButton rb_logPatient;

	@FXML
	private Button btn_login;

	@FXML
	private Label lbl_wrongPass;

	@FXML
	private Label lbl_userExists;

	@FXML
	private Button btnExit;

	static boolean flag = true;
	static String login;
	static String pass;
	static String rola;
	// obiekt po³¹czenia z db
	DBConnector db;

	@FXML
	ToggleGroup rbg_register;

	@FXML
	ToggleGroup rbg_login;

	@FXML
	TextField tf_loginRegister;

	@FXML
	TextField tf_specRegister;

	@FXML
	TextField tf_lastRegister;

	@FXML
	TextField tf_nameRegister;

	@FXML
	Label lbl_specRegister;

	@FXML
	TextField tf_showPassRegister;

	@FXML
	TextField tf_showPassLogin;

	@FXML
	// LOGOWANIE
	void buttonLogin(MouseEvent event) throws SQLException, IOException {
		Connection conn1 = db.Connection();
		Statement stmt = conn1.createStatement();
		// logowanie konta lekarza
		if (rb_logDoctor.isSelected()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM LOGOWANIE WHERE LOGIN = '" + tf_login.getText()
					+ "' and HASLO = '" + pf_pass.getText() + "' AND ROLA = 'lekarz'");
			if (rs.next()) {
				lbl_wrongPass.setVisible(false);
				login = tf_login.getText();
				pass = pf_pass.getText();
				rola = "lekarz";
				Stage stage = new Stage();
				Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/TableView.fxml"));
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.setTitle("Menu LEKARZ");
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
			} else {
				lbl_wrongPass.setVisible(true);
			}
		}
		// logowanie konta pacjenta
		if (rb_logPatient.isSelected()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM LOGOWANIE WHERE LOGIN = '" + tf_login.getText()
					+ "' and HASLO = '" + pf_pass.getText() + "' AND ROLA = 'pacjent'");
			if (rs.next()) {
				lbl_wrongPass.setVisible(false);
				login = tf_login.getText();
				pass = pf_pass.getText();
				rola = "pacjent";
				Stage stage = new Stage();
				Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/TableView.fxml"));
				Scene scene = new Scene(parent);
				stage.setScene(scene);
				stage.setTitle("Menu PACJENT");
				stage.show();
				((Node) (event.getSource())).getScene().getWindow().hide();
			} else {
				lbl_wrongPass.setVisible(true);
			}
		}

	}

	@FXML
	// REJESTRACJA
	void buttonRegister(MouseEvent event) throws SQLException {
		// zabezpieczenie przed prób¹ rejestracji dla pustych pól
		if (tf_loginRegister.getText().isEmpty() || pf_passRegister.getText().isEmpty()
				|| tf_nameRegister.getText().isEmpty() || tf_lastRegister.getText().isEmpty()) {
			Alert newUser_info = new Alert(AlertType.WARNING);
			newUser_info.setContentText("Wprowadz wszystkie dane");
			newUser_info.setTitle("B³¹d.");
			newUser_info.showAndWait();
		} else {

			Connection conn1 = db.Connection();
			Statement stmt = conn1.createStatement();

			// rejestracja konta lekarza
			if (rb_regDoctor.isSelected()) {
				// odkrywanie pola i labelki dla specjalizacji lekarza
				lbl_specRegister.setVisible(true);
				tf_specRegister.setVisible(true);
				// zabezpieczenie przed prób¹ rejestracji nowego lekarza dla pustego pola
				// specjalizacja
				if (tf_specRegister.getText().isEmpty()) {
					Alert newUser_info = new Alert(AlertType.WARNING);
					newUser_info.setContentText("Wprowadz wszystkie dane");
					newUser_info.setTitle("B³¹d.");
					newUser_info.showAndWait();
				} else {
					ResultSet rs = stmt.executeQuery("SELECT * FROM LOGOWANIE WHERE LOGIN = '"
							+ tf_loginRegister.getText() + "' AND ROLA = 'lekarz'");
					// sprawdzanie istnienia loginu w bazie
					if (rs.next()) {
						lbl_userExists.setVisible(true);
					} else {
						lbl_userExists.setVisible(false);
						// insert danych nowego uz¿ytkownika do tabeli Logowanei i Lekarze
						stmt.executeUpdate(
								"insert into Logowanie (login, haslo, rola) values ('" + tf_loginRegister.getText()
										+ "', " + "'" + pf_passRegister.getText() + "', 'lekarz');");
						stmt.executeUpdate("insert into Lekarze (imie, nazwisko, specjalizacja, login) values ('"
								+ tf_nameRegister.getText() + "', '" + tf_lastRegister.getText() + "', '"
								+ tf_specRegister.getText() + "', '" + tf_loginRegister.getText() + "');");
						// nowe okno z informacj¹ o pomyœlnym za³o¿eniu konta
						Alert newUser_info = new Alert(AlertType.INFORMATION);
						newUser_info.setContentText("Konto " + tf_loginRegister.getText()
								+ " zosta³o pomyslnie zarejestrowane. \nTeraz mo¿esz siê zalogowaæ.");
						newUser_info.setTitle("Zarejestrowano nowe konto.");
						newUser_info.showAndWait();
						// wyczyszczenie pól do wprowadzania danych
						tf_nameRegister.clear();
						tf_lastRegister.clear();
						tf_loginRegister.clear();
						pf_passRegister.clear();
						tf_specRegister.clear();
					}
				}
			}
			// rejestracja konta pacjenta
			if (rb_regPatient.isSelected()) {
				// chowanie pola i labelki specjalizacja
				ResultSet rs = stmt.executeQuery("SELECT * FROM LOGOWANIE WHERE LOGIN = '" + tf_loginRegister.getText()
						+ "' AND ROLA = 'pacjent'");
				// sprawdzanie istnienia loginu w bazie
				if (rs.next()) {
					lbl_userExists.setVisible(true);
				} else {
					lbl_userExists.setVisible(false);
					// insert danych nowego uz¿ytkownika do tabeli Logowanei i Pacjenci
					stmt.executeUpdate("insert into Logowanie (login, haslo, rola) values ('"
							+ tf_loginRegister.getText() + "', " + "'" + pf_passRegister.getText() + "', 'pacjent');");
					stmt.executeUpdate(
							"insert into Pacjenci (imie, nazwisko, login) values ('" + tf_nameRegister.getText()
									+ "', '" + tf_lastRegister.getText() + "', '" + tf_loginRegister.getText() + "');");
					// nowe okno z informacj¹ o pomyœlnym za³o¿eniu konta
					Alert newUser_info = new Alert(AlertType.INFORMATION);
					newUser_info.setContentText("Konto " + tf_loginRegister.getText()
							+ " zosta³o pomyslnie zarejestrowane. \nTeraz mo¿esz siê zalogowaæ.");
					newUser_info.setTitle("Zarejestrowano nowe konto.");
					newUser_info.showAndWait();
					// wyczyszczenie pól do wprowadzania danych
					tf_nameRegister.clear();
					tf_lastRegister.clear();
					tf_loginRegister.clear();
					pf_passRegister.clear();
					tf_specRegister.clear();

				}

			}
		}

	}

	@FXML
	// Przycisk X - wyjœcie
	public void buttonExit(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	// obs³uga radio button-ów dla wyboru rodzaju konta dla akcji logowania
	public void radioPatient(MouseEvent event) {
		lbl_specRegister.setVisible(false);
		tf_specRegister.setVisible(false);
		tf_specRegister.clear();

	}

	@FXML
	// obs³uga radio button-ów dla wyboru rodzaju konta dla akcji rejestracji konta
	public void radioDoctor(MouseEvent event) {
		lbl_specRegister.setVisible(true);
		tf_specRegister.setVisible(true);
		tf_specRegister.clear();
	}

	@FXML
	// obs³uga przycisku show dla akcji logowania
	public void showPassLogin(MouseEvent event) {
		// obs³uga przycisku show przy pustym polu
		if (pf_pass.getText().isEmpty()) {
			// nie rób nic
		} else {

			if (flag == true) {
				String pf_text = pf_pass.getText();
				pf_pass.setVisible(false);
				tf_showPassLogin.setVisible(true);
				tf_showPassLogin.setText(pf_text);
				flag = false;
				btn_show.setText("hide");
			} else {
				String pf_text = pf_pass.getText();
				pf_pass.setVisible(true);
				tf_showPassLogin.setVisible(false);
				pf_pass.setText(pf_text);
				flag = true;
				btn_show.setText("show");
			}
		}
	}

	@FXML
	// obs³uga przycisku show dla akcji rejestrowania konta
	public void showPassRegister(MouseEvent event) {
		// obs³uga przycisku show przy pustym polu
		if (pf_passRegister.getText().isEmpty()) {
			// nie rób nic
		} else {

			if (flag == true) {
				String pf_text = pf_passRegister.getText();
				pf_passRegister.setVisible(false);
				tf_showPassRegister.setVisible(true);
				tf_showPassRegister.setText(pf_text);
				flag = false;
				btn_showRegister.setText("hide");
			} else {
				String pf_text = pf_passRegister.getText();
				pf_passRegister.setVisible(true);
				tf_showPassRegister.setVisible(false);
				pf_passRegister.setText(pf_text);
				flag = true;
				btn_showRegister.setText("show");
			}
		}
	}
	
	public void initialize() {
		db = new DBConnector();
	}

}
