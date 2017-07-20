package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.stage.Window;


public class Main extends Application {
    private Stage primaryStage;
    private BorderPane mainWindow;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("DBConnector V1-m");

        initRootLayout();
        showDataBaseWindow();

    }
    public void initRootLayout(){   //Metoda inicjalizująca główny wygląd okna aplikacji
        try{
            FXMLLoader loader = new FXMLLoader(); //Utworzenie handlera do pliku fxml opisującego wygląd okna
            loader.setLocation(getClass().getResource("/sample/RootLayout.fxml")); //Ustawienie położenia pliku z wyglądem
            mainWindow = loader.load(); //Załadowanie wyglądu okna aplikacji

            Scene scene = new Scene(mainWindow);
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void showDataBaseWindow() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/DataBaseFrom.fxml"));
            AnchorPane dbWindow = (AnchorPane) loader.load(); //Whitout cast window is not loaded to root layout

            mainWindow.setCenter(dbWindow);
            DBController controller = loader.getController();
            controller.setMain(this);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Window getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
