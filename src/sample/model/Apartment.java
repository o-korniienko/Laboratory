package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Apartment implements Serializable {
    private SimpleIntegerProperty id;
    private SimpleStringProperty owner;
    private SimpleIntegerProperty apartmentNumber;
    private SimpleIntegerProperty area;
    private SimpleIntegerProperty floor;
    private SimpleIntegerProperty numberOfRooms;

    public Apartment(int id, String owner, int apartmentNumber, int area, int floor, int numberOfRooms) {
        this.id = new SimpleIntegerProperty(id);
        this.owner = new SimpleStringProperty(owner);
        this.apartmentNumber = new SimpleIntegerProperty(apartmentNumber);
        this.area = new SimpleIntegerProperty(area);
        this.floor = new SimpleIntegerProperty(floor);
        this.numberOfRooms = new SimpleIntegerProperty(numberOfRooms);
    }

    public Apartment() {
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getOwner() {
        return owner.get();
    }

    public SimpleStringProperty ownerProperty() {
        return owner;
    }

    public int getApartmentNumber() {
        return apartmentNumber.get();
    }

    public SimpleIntegerProperty apartmentNumberProperty() {
        return apartmentNumber;
    }

    public int getArea() {
        return area.get();
    }

    public SimpleIntegerProperty areaProperty() {
        return area;
    }

    public int getFloor() {
        return floor.get();
    }

    public SimpleIntegerProperty floorProperty() {
        return floor;
    }

    public int getNumberOfRooms() {
        return numberOfRooms.get();
    }

    public SimpleIntegerProperty numberOfRoomsProperty() {
        return numberOfRooms;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setOwner(String owner) {
        this.owner.set(owner);
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber.set(apartmentNumber);
    }

    public void setArea(int area) {
        this.area.set(area);
    }

    public void setFloor(int floor) {
        this.floor.set(floor);
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms.set(numberOfRooms);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {

        s.writeInt(getId());
        s.writeInt(getApartmentNumber());
        s.writeInt(getArea());
        s.writeInt(getFloor());
        s.writeInt(getNumberOfRooms());
        s.writeUTF(getOwner());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        id = new SimpleIntegerProperty(s.readInt());
        apartmentNumber = new SimpleIntegerProperty(s.readInt());
        area = new SimpleIntegerProperty(s.readInt());
        floor = new SimpleIntegerProperty(s.readInt());
        numberOfRooms = new SimpleIntegerProperty(s.readInt());
        owner = new SimpleStringProperty(s.readUTF());


    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", owner=" + owner +
                ", apartmentNumber=" + apartmentNumber +
                ", area=" + area +
                ", floor=" + floor +
                ", numberOfRooms=" + numberOfRooms +
                '}';
    }
}
