package ru.amir.Entities;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.Calendar;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "log_entry")
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "entry_time")
    private Time time;

    @Column(name = "entry_date")
    private Date date;

    @Column(name = "checkpoint_id")
    private int checkpointId;

    @Column(name = "object_id")
    private String objectId;

    @Column(name = "car_id")
    private int carId;

    @Column(name = "direction")
    private String direction;

    @Column(name = "reg_num")
    private String licensePlate;

    @Column(name = "car_weight")
    private Integer vehicleWeight;

    @Column(name = "magnetic_card")
    private Integer magneticCard;

    @Column(name = "weight_type")
    private String weightType;

    public LogEntry() {
    }

    // Constructor for magnetic cards
    public LogEntry(Date date, Time time, int checkpointId, String objectId, int carId, int magneticCard) {
        this(date, time, checkpointId, objectId, carId);
        this.magneticCard = magneticCard;
    }

    // Constructor for scales
    public LogEntry(Date date, Time time, int checkpointId, String objectId, int carId, int vehicleWeight, String weightType) {
        this(date, time, checkpointId, objectId, carId);
        this.vehicleWeight = vehicleWeight;
        this.weightType = weightType;
    }

    // Constructor for cameras
    public LogEntry(Date date, Time time, int checkpointId, String objectId, int carId, String direction, String licensePlate) {
        this(date, time, checkpointId, objectId, carId);
        this.direction = direction;
        this.licensePlate = licensePlate;
    }

    // common constructor
    public LogEntry(Date date, Time time, int checkpointId, String objectId, int carId) {
        this.time = time;
        this.date = date;
        this.checkpointId = checkpointId;
        this.objectId = objectId;
        this.carId = carId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(int checkpointId) {
        this.checkpointId = checkpointId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getMagneticCard() {
        return magneticCard;
    }

    public void setMagneticCard(Integer magneticCard) {
        this.magneticCard = magneticCard;
    }

    public Integer getVehicleWeight() {
        return vehicleWeight;
    }

    public void setVehicleWeight(Integer vehicleWeight) {
        this.vehicleWeight = vehicleWeight;
    }

    public String getWeightType() { return weightType; }

    public void setWeightType(String weightType) { this.weightType = weightType; }

}


