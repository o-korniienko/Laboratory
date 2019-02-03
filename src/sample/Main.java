package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.Controller;
import sample.model.House;
import sample.view.WindowsToAddApartmentAndHouse;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("view/sample.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Лабораторна");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        House house = new House().loadFromFile("housesdata1.dat");
        //House house = new House();
        Controller controller = loader.getController();
        controller.setHouse(house);
        WindowsToAddApartmentAndHouse windows = new WindowsToAddApartmentAndHouse(controller);
        controller.setWindows(windows);
    }

    public static void main(String[] args) {
        launch(args);
    }


}
