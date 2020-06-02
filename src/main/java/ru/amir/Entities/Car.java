package ru.amir.Entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "car")
public class Car {

    public Car() {
        currentLocation = 0;
        magneticCard = 0;
    }

    public Car(String regNum, int weight) {
        setRegNum(regNum);
        this.weight = weight;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Pattern(regexp = "[A-Z]\\d\\d\\d[A-Z][A-Z]\\d\\d?",
            message = "Entered number doesn't correspond with Russian Federation license plate system")
    @Column(name = "reg_num")
    private String regNum;

    @NotNull(message = "Shouldn't be empty")
    @Min(value = 700, message = "Minimal weight = 700kg")
    @Column(name = "weight")
    private Integer weight;

    @Column(name = "destination_id")
    private Integer destinationId;

    @Column(name = "current_location")
    private Integer currentLocation;

    @Min(value = 0, message = "Card id number should not be negative")
    @Column(name = "magnetic_card")
    private Integer magneticCard;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public Integer getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Integer currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Integer getMagneticCard() {
        return magneticCard;
    }

    public void setMagneticCard(Integer magneticCard) {
        this.magneticCard = magneticCard;
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNum='" + regNum + '\'' +
                ", weight=" + weight +
                '}';
    }
}


