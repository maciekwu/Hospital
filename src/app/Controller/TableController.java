package app.Controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import app.Database.DBConnector;
import app.Model.Wizyty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

public class TableController {

	@FXML
	AnchorPane achPane_tableView;
	@FXML
	Button btn_logout;
	@FXML
	Button btn_showAppointment;
	@FXML
	Button btn_setAppointment;
	@FXML
	Button btn_cancelAppointment;
	@FXML
	Button btn_rescheduleAppointment;
	@FXML
	TableView<Wizyty> table_visits;
	@FXML
	private JFXDatePicker dp_appDate;
	@FXML
	private TableColumn<Wizyty, Date> col_AppDate;

	@FXML
	private TableColumn<Wizyty, Time> col_AppTime;

	@FXML
	private TableColumn<Wizyty, String> col_Doctor;

	@FXML
	private TableColumn<Wizyty, String> col_DocSpec;

	@FXML
	private TableColumn<Wizyty, Integer> col_AppId;

	DBConnector db;
	public ObservableList<Wizyty> data;
	@FXML
	TextField tf_filterSpec;
	static String filterSpec;

	@FXML
	private JFXTimePicker tp_appTime;

	@FXML
	public void buttonLogout(MouseEvent event) throws IOException {
		Stage stage = new Stage();
		Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/app/View/LoginView.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		stage.setTitle("Logowanie / Rejestracja");
		stage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();

	}

	@FXML
	public void showPatientVisits(ActionEvent event) throws SQLException {
		filterSpec = tf_filterSpec.getText();
		Connection conn = db.Connection();
		data = FXCollections.observableArrayList();

		if (!tf_filterSpec.getText().isEmpty()) {
			ResultSet rs = conn.createStatement().executeQuery(
					"select w.id, data_w, godzina_w, concat(l.imie, \", \", l.nazwisko), l.specjalizacja from wizyty w \r\n"
							+ "join lekarze l on l.id = w.id_l\r\n"
							+ "where w.id_p = (select id from pacjenci where login = '" + LoginController.login
							+ "') and l.specjalizacja = '" + filterSpec.toString() + "';");
			while (rs.next()) {
				data.add(new Wizyty(rs.getInt(1), rs.getDate(2), rs.getTime(3), rs.getString(4), rs.getString(5)));

			}
		} else {
			ResultSet rs = conn.createStatement().executeQuery(
					"select w.id, data_w, godzina_w, concat(l.imie, \", \", l.nazwisko), l.specjalizacja from wizyty w \r\n"
							+ "join lekarze l on l.id = w.id_l\r\n"
							+ "where w.id_p = (select id from pacjenci where login = '" + LoginController.login
							+ "');");
			while (rs.next()) {
				data.add(new Wizyty(rs.getInt(1), rs.getDate(2), rs.getTime(3), rs.getString(4), rs.getString(5)));

			}
		}
		col_AppId.setCellValueFactory(new PropertyValueFactory<Wizyty, Integer>("id"));
		col_AppDate.setCellValueFactory(new PropertyValueFactory<Wizyty, Date>("data_w"));
		col_AppTime.setCellValueFactory(new PropertyValueFactory<Wizyty, Time>("godzina_w"));
		col_Doctor.setCellValueFactory(new PropertyValueFactory<Wizyty, String>("lekarz"));
		col_DocSpec.setCellValueFactory(new PropertyValueFactory<Wizyty, String>("specjalizacja"));
		table_visits.setItems(null);
		table_visits.setItems(data);

	}

	@FXML
	public void deleteVisit(ActionEvent event) throws SQLException {
		Connection conn = db.Connection();
		int id_selected = table_visits.getSelectionModel().getSelectedItem().getId();
		PreparedStatement ps = conn.prepareStatement("delete from wizyty where id = " + id_selected);
		ps.executeUpdate();

		showPatientVisits(event);
	}

	@FXML
	void scheduleVisit(ActionEvent event) throws SQLException {
		Connection conn = db.Connection();
		System.out.println(tp_appTime.getValue());
		if (dp_appDate.getValue() == null || tp_appTime.getValue() == null) {
			Alert newUser_info = new Alert(AlertType.WARNING);
			newUser_info.setContentText("Wprowadz datê i godzinê wizyty.");
			newUser_info.setTitle("B³¹d.");
			newUser_info.showAndWait();
		}

		PreparedStatement ps = conn.prepareStatement("insert into wizyty(data_w, godzina_w, id_p, id_l)  values ('"
				+ dp_appDate.getValue().toString() + "', '" + tp_appTime.getValue().toString() +  "', 1, 1);");
		ps.executeUpdate();

		showPatientVisits(event);

	}

	@FXML
	public void initialize() throws SQLException {
		db = new DBConnector();
		ActionEvent event = null;
		showPatientVisits(event);
	}
}
