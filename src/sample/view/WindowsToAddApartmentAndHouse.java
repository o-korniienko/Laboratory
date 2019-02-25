package sample.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import sample.controller.Controller;

import java.io.Serializable;

public class WindowsToAddApartmentAndHouse implements Serializable {
    private Controller controller;

    public WindowsToAddApartmentAndHouse(Controller controller) {
        this.controller = controller;
    }

    public void displayAddApartment() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Додати квартиру");
        window.setWidth(500);
        window.setHeight(315);

        TextField fieldId = new TextField();
        fieldId.setPrefSize(150, 40);
        fieldId.setLayoutX(20);
        fieldId.setLayoutY(5);
        Label labelId = new Label("ID");
        labelId.setPrefSize(200, 40);
        labelId.setLayoutX(180);
        labelId.setLayoutY(5);
        TextField fieldOwner = new TextField();
        fieldOwner.setPrefSize(150, 40);
        fieldOwner.setLayoutX(20);
        fieldOwner.setLayoutY(50);
        Label labelOwner = new Label("Ім'я власника");
        labelOwner.setPrefSize(200, 40);
        labelOwner.setLayoutX(180);
        labelOwner.setLayoutY(50);
        TextField fieldNumberApart = new TextField();
        fieldNumberApart.setPrefSize(150, 40);
        fieldNumberApart.setLayoutX(20);
        fieldNumberApart.setLayoutY(95);
        Label labelNumberApart = new Label("Номер квартири");
        labelNumberApart.setPrefSize(200, 40);
        labelNumberApart.setLayoutX(180);
        labelNumberApart.setLayoutY(95);
        TextField fieldArea = new TextField();
        fieldArea.setPrefSize(150, 40);
        fieldArea.setLayoutX(20);
        fieldArea.setLayoutY(140);
        Label labelArea = new Label("Площа");
        labelArea.setPrefSize(200, 40);
        labelArea.setLayoutX(180);
        labelArea.setLayoutY(140);
        TextField fieldFloor = new TextField();
        fieldFloor.setPrefSize(150, 40);
        fieldFloor.setLayoutX(20);
        fieldFloor.setLayoutY(185);
        Label labelFloor = new Label("Поверх");
        labelFloor.setPrefSize(200, 40);
        labelFloor.setLayoutX(180);
        labelFloor.setLayoutY(185);
        TextField fieldNumberRooms = new TextField();
        fieldNumberRooms.setPrefSize(150, 40);
        fieldNumberRooms.setLayoutX(20);
        fieldNumberRooms.setLayoutY(230);
        Label labelNumberRooms = new Label("Кількість кімнат");
        labelNumberRooms.setPrefSize(200, 40);
        labelNumberRooms.setLayoutX(180);
        labelNumberRooms.setLayoutY(230);

        Button addButton = new Button("Додати");
        addButton.setPrefSize(120, 20);
        addButton.setLayoutX(350);
        addButton.setLayoutY(20);
        addButton.setId("addApart");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String owner;
                owner = fieldOwner.getText();
                int id, numbAp, area, floor, numbRoom;
                try {
                    id = Integer.parseInt(fieldId.getText());
                    numbAp = Integer.parseInt(fieldNumberApart.getText());
                    area = Integer.parseInt(fieldArea.getText());
                    floor = Integer.parseInt(fieldFloor.getText());
                    numbRoom = Integer.parseInt(fieldNumberRooms.getText());
                    controller.addApartment(id, owner, numbAp, area, floor, numbRoom);
                } catch (NumberFormatException e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Помилка");
                    alert.setHeaderText("Поля не заповненні, або містять не коректні дані");
                    alert.setContentText("Поля id, номер квартири, площа, поверх і кількість кімнат - можуть містити лише цифри");
                    alert.showAndWait();
                }
            }
        });

        Button closeButton = new Button("Закрити");
        closeButton.setPrefSize(120, 20);
        closeButton.setLayoutX(350);
        closeButton.setLayoutY(60);
        closeButton.setOnAction(e -> window.close());

        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(fieldId, labelId, fieldOwner, labelOwner, fieldNumberApart, labelNumberApart, fieldArea
                , labelArea, fieldFloor, labelFloor, fieldNumberRooms, labelNumberRooms, addButton, closeButton);
        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.show();

    }

    public void displayAddHouse() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Додати будинок");
        window.setWidth(300);
        window.setHeight(215);

        Label label = new Label("Введіть адресу будинку.");
        label.setLayoutX(70);
        label.setLayoutY(20);
        TextField field = new TextField();
        field.setPrefSize(150, 30);
        field.setLayoutX(65);
        field.setLayoutY(50);

        Button addButton = new Button("Додати");
        addButton.setPrefSize(100, 30);
        addButton.setLayoutX(30);
        addButton.setLayoutY(95);
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String street;
                street = field.getText();
                controller.addHouse(street);
            }
        });

        Button closeButton = new Button("Закрити");
        closeButton.setPrefSize(100, 30);
        closeButton.setLayoutX(145);
        closeButton.setLayoutY(95);
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });

        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(label, field, addButton, closeButton);
        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.show();
    }

    public void displayDelHouse() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Видалити будинок за адресою");
        window.setWidth(320);
        window.setHeight(215);

        Label label = new Label("Введіть адресу будинку, який бажаєте видалити.");
        label.setLayoutX(20);
        label.setLayoutY(20);
        TextField field = new TextField();
        field.setPrefSize(150, 30);
        field.setLayoutX(75);
        field.setLayoutY(50);

        Button delButton = new Button("Видалити");
        delButton.setPrefSize(100, 30);
        delButton.setLayoutX(40);
        delButton.setLayoutY(95);
        delButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String street;
                street = field.getText();
                controller.delHouse(street);
            }
        });

        Button closeButton = new Button("Закрити");
        closeButton.setPrefSize(100, 30);
        closeButton.setLayoutX(155);
        closeButton.setLayoutY(95);
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.close();
            }
        });

        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(label, field, delButton, closeButton);
        Scene scene = new Scene(pane);
        window.setScene(scene);
        window.show();
    }

}
