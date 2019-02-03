package sample.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.*;

public class House implements Serializable {
    private ObservableMap<String, ObservableList<Apartment>> apartments = FXCollections.observableHashMap();

    public static House loadFromFile(String fileName) {
        House house = new House();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            ObservableMap<String, ObservableList<Apartment>> map1 = FXCollections.observableHashMap();
            HashMap<String, ArrayList<Apartment>> map2;
            map2 = (HashMap<String, ArrayList<Apartment>>) ois.readObject();

            ObservableList<Apartment> list = FXCollections.observableArrayList();
            for (Map.Entry<String, ArrayList<Apartment>> entry : map2.entrySet()) {
                map1.put(entry.getKey(), FXCollections.observableArrayList(entry.getValue()));
            }
            for (Map.Entry<String, ObservableList<Apartment>> entry : map1.entrySet()) {
                System.out.println(entry);
            }
            house.apartments = map1;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return house;
    }

    public House() {
    }


    public static Comparator<Apartment> BY_APARTMENT_NUMBER;
    public static Comparator<Apartment> BY_ID;
    public static Comparator<Apartment> BY_FLOOR;
    public static Comparator<Apartment> BY_AREA;
    public static Comparator<Apartment> BY_NUMBER_OF_ROOMS;
    public static Comparator<Apartment> BY_OWNER;


    static {
        BY_APARTMENT_NUMBER = new Comparator<Apartment>() {
            @Override
            public int compare(Apartment o1, Apartment o2) {
                return o1.getApartmentNumber() - o2.getApartmentNumber();
            }
        };
    }

    static {
        BY_ID = new Comparator<Apartment>() {
            @Override
            public int compare(Apartment o1, Apartment o2) {
                return o1.getId() - o2.getId();
            }
        };
    }

    static {
        BY_FLOOR = new Comparator<Apartment>() {
            @Override
            public int compare(Apartment o1, Apartment o2) {
                return o1.getFloor() - o2.getFloor();
            }
        };
    }

    static {
        BY_AREA = new Comparator<Apartment>() {
            @Override
            public int compare(Apartment o1, Apartment o2) {
                return (int) (o1.getArea() - o2.getArea());
            }
        };
    }

    static {
        BY_NUMBER_OF_ROOMS = new Comparator<Apartment>() {
            @Override
            public int compare(Apartment o1, Apartment o2) {
                return o1.getNumberOfRooms() - o2.getNumberOfRooms();
            }
        };
    }

    static {
        BY_OWNER = new Comparator<Apartment>() {
            @Override
            public int compare(Apartment o1, Apartment o2) {
                return o1.getOwner().compareTo(o2.getOwner());
            }
        };
    }


    public ObservableMap<String, ObservableList<Apartment>> getApartments() {
        return apartments;
    }

    public void addApartment(String key, int id, String owner, int numbA, int area, int floor, int numbRooms) {
        apartments.get(key).add(new Apartment(id, owner, numbA, area, floor, numbRooms));
    }

    public void delApartment(String key, int id) {
        int ind = -1;
        System.out.println(key);
        ObservableList<Apartment> list1= FXCollections.observableArrayList(apartments.get(key));
        ObservableList<Apartment> list2 = FXCollections.observableArrayList(list1);
        for (Apartment a : list2) {
            if (a.getId()==id){
                list1.remove(a);
                ind++;
            }
        }
        if (ind < 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Видалення квартири");
            alert.setHeaderText("Не вдалося видалити квартиру");
            alert.setContentText("Квартири з вказаним id немає");
            alert.showAndWait();

        }
        apartments.get(key).setAll(list1);
        ind = -1;

    }

    public void sort(Comparator<Apartment> comparator, String key) {
        Collections.sort(apartments.get(key), comparator);
    }

    public ObservableList<Apartment> filterByArea(String key, int area) {
        ObservableList<Apartment> list1 = FXCollections.observableArrayList(apartments.get(key));
        ObservableList<Apartment> list2 = FXCollections.observableArrayList();
        for (Apartment a : list1) {
            if (a.getArea() >= area) list2.add(a);
        }
        return list2;
    }

    public ObservableList<Apartment> filterByFloor(String key, int floor) {
        ObservableList<Apartment> list1 = FXCollections.observableArrayList(apartments.get(key));
        ObservableList<Apartment> list2 = FXCollections.observableArrayList();
        for (Apartment a : list1) {
            if (a.getFloor() == floor) list2.add(a);
        }
        return list2;
    }

    public ObservableList<Apartment> filterByRooms(String key, int rooms) {
        ObservableList<Apartment> list1 = FXCollections.observableArrayList(apartments.get(key));
        ObservableList<Apartment> list2 = FXCollections.observableArrayList();
        for (Apartment a : list1) {
            if (a.getNumberOfRooms() == rooms) list2.add(a);
        }
        return list2;
    }

    public ObservableList<Apartment> findById(String key, int id) {
        ObservableList<Apartment> list1 = FXCollections.observableArrayList(apartments.get(key));
        ObservableList<Apartment> list2 = FXCollections.observableArrayList();
        for (Apartment a : list1) {
            if (a.getId() == id) list2.add(a);
        }
        return list2;
    }

    public ObservableList<Apartment> findByOwner(String key, String owner) {
        ObservableList<Apartment> list1 = FXCollections.observableArrayList(apartments.get(key));
        ObservableList<Apartment> list2 = FXCollections.observableArrayList();
        for (Apartment a : list1) {
            if (a.getOwner().equals(owner)) list2.add(a);
        }
        return list2;
    }

    public ObservableList<Apartment> findByNumberApartment(String key, int numbApart) {
        ObservableList<Apartment> list1 = FXCollections.observableArrayList(apartments.get(key));
        ObservableList<Apartment> list2 = FXCollections.observableArrayList();
        for (Apartment a : list1) {
            if (a.getApartmentNumber() == numbApart) list2.add(a);
        }
        return list2;
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            Map<String, ArrayList<Apartment>> map = new HashMap<>();
            for (Map.Entry<String, ObservableList<Apartment>> entry : apartments.entrySet()) {
                map.put(entry.getKey(), new ArrayList<>(entry.getValue()));
            }
            oos.writeObject(new HashMap<>(map));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Зберігання");
            alert.setHeaderText("");
            alert.setContentText("Успішно збережено");
            alert.showAndWait();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Помилка зберігання");
            alert.setHeaderText("");
            alert.setContentText("Файл не знайдено");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Помилка зберігання");
            alert.setHeaderText("");
            alert.setContentText("Помилка ініціфлізації запису");
            alert.showAndWait();
        }
    }

    public void setTestApartments() {
        apartments.put("Schevchenko 10A", FXCollections.observableArrayList(new Apartment(01, "Tetyana", 34, 40, 3, 4)));
        apartments.put("Schevchenko 10B", FXCollections.observableArrayList(new Apartment(02, "Petro", 11, 34, 9, 3)));
        apartments.put("Schevchenko 10C", FXCollections.observableArrayList(new Apartment(03, "Olga", 12, 45, 1, 2)));
        apartments.put("Schevchenko 10D", FXCollections.observableArrayList(new Apartment(04, "Stas", 13, 56, 5, 5)));
        apartments.put("Schevchenko 10E", FXCollections.observableArrayList(new Apartment(05, "Daria", 14, 67, 7, 1)));
    }


    @Override
    public String toString() {
        return "House{" +
                "apartments=" + apartments +
                '}';
    }
}
