package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;


public class DBController {

    private Main mainApp;  //Reference to main App

    @FXML
    private Label userNameLabel;
    @FXML
    private Label passwordNameLabel;
    @FXML
    private Label databaseNameLabel;
    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField databaseNameField;

    public DBController(){} //Main Constructor of DBController class

    @FXML
    public void initialize(){
        showDBLayout();
    }

    private void showDBLayout(){
        userNameLabel.setText("Database user name: ");
        passwordNameLabel.setText("Database Password: ");
        databaseNameLabel.setText("Database name: ");
    }

    public void setMain(Main mainApp){
        this.mainApp = mainApp;
    }

    @FXML
    private void handleConnect(){
        Alert alertDB = new Alert(Alert.AlertType.ERROR);
        SQLConnect sql = new SQLConnect(
                "localhost",
                "1025",
                databaseNameField.getText(),
                userNameField.getText(),
                passwordField.getText()
        );
        try{
            sql.Connect();
            if( sql.checkConn() ){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.initOwner(mainApp.getPrimaryStage());
                alert.setTitle("Connection to DB");
                alert.setHeaderText("Connecting to DB...");
                alert.setContentText("Connection is successful");
                alert.show();
            }
            sql.getData();

        }catch ( SQLException se){
            alertDB.initOwner(mainApp.getPrimaryStage());
            alertDB.setTitle("Błąd bazy danych!");
            alertDB.setHeaderText("Błąd połączenia!");
            alertDB.setContentText(se.getMessage());
            alertDB.showAndWait();
        }catch (Exception e) {
            e.printStackTrace();
            alertDB.initOwner(mainApp.getPrimaryStage());
            alertDB.setTitle("Błąd bazy danych!");
            alertDB.setHeaderText("Błąd połączenia!");
            alertDB.setContentText(e.getMessage());
            alertDB.showAndWait();
        }
    }
}
