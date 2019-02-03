package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import sample.model.Apartment;
import sample.model.House;
import sample.view.WindowsToAddApartmentAndHouse;

import java.io.Serializable;


public class Controller implements Serializable {
    @FXML
    TableView<Apartment> table;
    @FXML
    TableColumn<Apartment, String> id;
    @FXML
    TableColumn<Apartment, String> owner;
    @FXML
    TableColumn<Apartment, String> apartmentNumber;
    @FXML
    TableColumn<Apartment, String> floor;
    @FXML
    TableColumn<Apartment, String> area;
    @FXML
    TableColumn<Apartment, String> numberOfRooms;
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    Button toAddHouse;
    @FXML
    Button delHouse;
    @FXML
    Button toAddApartment;
    @FXML
    Button delApartment;
    @FXML
    MenuButton sortBy;
    @FXML
    Button saveButton;
    @FXML
    RadioMenuItem byId;
    @FXML
    RadioMenuItem byNumbAp;
    @FXML
    RadioMenuItem byArea;
    @FXML
    RadioMenuItem byFloor;
    @FXML
    RadioMenuItem byNumbRooms;
    @FXML
    RadioMenuItem byOwner;
    @FXML
    SplitMenuButton filterBy;
    @FXML
    TextField filterField;
    @FXML
    RadioMenuItem filterByArea;
    @FXML
    RadioMenuItem filterByFloor;
    @FXML
    RadioMenuItem filterByRooms;
    @FXML
    RadioMenuItem filterShowAll;
    @FXML
    SplitMenuButton findBy;
    @FXML
    TextField findField;
    @FXML
    RadioMenuItem findById;
    @FXML
    RadioMenuItem findByOwner;
    @FXML
    RadioMenuItem findByNumbApart;
    @FXML
    RadioMenuItem findShowAll;

    private House houses;
    private WindowsToAddApartmentAndHouse windows;
    private String target;
    private int filterIndex;
    private int findIndex;

    public Controller() {

    }

    @FXML
    public void initialize() {
        setToTable();
        setSort();
        setFilter();
        setFindMenu();

        choiceBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                target = choiceBox.getValue();
                table.setItems(houses.getApartments().get(target));
            }
        });

        toAddHouse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                windows.displayAddHouse();
            }
        });

        delHouse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                windows.displayDelHouse();
            }
        });

        toAddApartment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                target = choiceBox.getValue();
                windows.displayAddApartment();
            }

        });

        delApartment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                target = choiceBox.getValue();
                windows.dispalyDelApartment();
            }
        });

        filterBy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                target = choiceBox.getValue();
                if (filterIndex == 3) {
                    table.setItems(houses.getApartments().get(target));
                    filterField.setText("");
                } else {
                    try {
                        int ind = Integer.parseInt(filterField.getText());
                        if (filterIndex == 0) table.setItems(houses.filterByArea(target, ind));
                        if (filterIndex == 1) table.setItems(houses.filterByFloor(target, ind));
                        if (filterIndex == 2) table.setItems(houses.filterByRooms(target, ind));
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Помилка фільтрації");
                        alert.setHeaderText("Поле для фільтрації пусте або містить некоректні дані");
                        alert.setContentText("Для фільтрації вводьте лише цифри");
                        alert.showAndWait();
                    }
                }

            }
        });

        findBy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                target = choiceBox.getValue();
                if (findIndex == 3) {
                    table.setItems(houses.getApartments().get(target));
                    findField.setText("");
                } else {
                    String name = findField.getText();
                    if (findIndex == 1) table.setItems(houses.findByOwner(target, name));
                    try {
                        int ind = Integer.parseInt(findField.getText());
                        if (findIndex == 0) table.setItems(houses.findById(target, ind));
                        if (findIndex == 2) table.setItems(houses.findByNumberApartment(target, ind));
                    } catch (NumberFormatException e) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Помилка пошуку");
                        alert.setHeaderText("Поле для пошуку пусте або містить некоректні символи");
                        alert.setContentText("Для пошуку по id або номеру квартири вводьте лише цифри");
                        alert.showAndWait();
                    }
                }

            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                houses.saveToFile("housesdata1.dat");

            }
        });
    }

    public void setToTable() {
        id.setCellValueFactory(param -> param.getValue().idProperty().asString());
        owner.setCellValueFactory(param -> param.getValue().ownerProperty());
        apartmentNumber.setCellValueFactory(param -> param.getValue().apartmentNumberProperty().asString());
        floor.setCellValueFactory(param -> param.getValue().floorProperty().asString());
        area.setCellValueFactory(param -> param.getValue().areaProperty().asString());
        numberOfRooms.setCellValueFactory(param -> param.getValue().numberOfRoomsProperty().asString());
    }

    public void addApartment(int id, String owner, int numbA, int area, int floor, int numbRooms) {

        houses.addApartment(target, id, owner, numbA, area, floor, numbRooms);
    }

    public void delApartment(int id) {
        houses.delApartment(target, id);
    }

    public void sortApartment(int s) {
        if (s == 0) houses.sort(House.BY_ID, target);
        if (s == 1) houses.sort(House.BY_APARTMENT_NUMBER, target);
        if (s == 2) houses.sort(House.BY_AREA, target);
        if (s == 3) houses.sort(House.BY_FLOOR.thenComparing(House.BY_ID), target);
        if (s == 4) houses.sort(House.BY_NUMBER_OF_ROOMS.thenComparing(House.BY_ID), target);
        if (s == 5) houses.sort(House.BY_OWNER, target);
    }

    public void setHouse(House house) {
        this.houses = house;
       // house.setTestApartments();
        choiceBox.getItems().setAll(houses.getApartments().keySet());
        choiceBox.setValue(getRandomKey());
        target = choiceBox.getValue();
        table.setItems(houses.getApartments().get(target));

    }

    public void setWindows(WindowsToAddApartmentAndHouse windows) {
        this.windows = windows;
    }

    private void setSort() {
        ToggleGroup group = new ToggleGroup();
        byId.setToggleGroup(group);
        byNumbAp.setToggleGroup(group);
        byArea.setToggleGroup(group);
        byFloor.setToggleGroup(group);
        byNumbRooms.setToggleGroup(group);
        byOwner.setToggleGroup(group);
        sortBy.getItems().setAll(byId, byNumbAp, byArea, byFloor, byNumbRooms, byOwner);
        for (int i = 0; i < 6; i++) {
            int r = i;
            sortBy.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    sortApartment(r);
                }
            });
        }
    }

    private void setFilter() {
        ToggleGroup group = new ToggleGroup();
        filterByArea.setToggleGroup(group);
        filterByFloor.setToggleGroup(group);
        filterByRooms.setToggleGroup(group);
        filterShowAll.setToggleGroup(group);
        filterBy.getItems().setAll(filterByArea, filterByFloor, filterByRooms, filterShowAll);
        for (int i = 0; i < 4; i++) {
            int r = i;
            filterBy.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    filterIndex = r;
                }
            });
        }
    }

    public void addHouse(String street) {
        ObservableList<Apartment> list = FXCollections.observableArrayList();
        houses.getApartments().put(street, list);
        choiceBox.getItems().add(street);
        choiceBox.setValue(street);
        target = street;
        table.setItems(houses.getApartments().get(target));
    }

    public void delHouse(String street) {
        houses.getApartments().remove(street);
        choiceBox.getItems().setAll(houses.getApartments().keySet());
        choiceBox.setValue(getRandomKey());
        target = choiceBox.getValue();
        table.setItems(houses.getApartments().get(target));
    }

    private String getRandomKey() {
        String key = null;
        ObservableMap<String, ObservableList<Apartment>> map = FXCollections.observableHashMap();
        map = houses.getApartments();
        for (String s : map.keySet()) {
            key = s;
        }
        return key;
    }

    private void setFindMenu() {
        ToggleGroup group = new ToggleGroup();
        findById.setToggleGroup(group);
        findByOwner.setToggleGroup(group);
        findByNumbApart.setToggleGroup(group);
        findShowAll.setToggleGroup(group);
        findBy.getItems().setAll(findById, findByOwner, findByNumbApart, findShowAll);
        for (int i = 0; i < 4; i++) {
            int r = i;
            findBy.getItems().get(i).setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    findIndex = r;
                }
            });
        }
    }

}
