package ru.amir.Entities;

import javax.persistence.*;
import javax.validation.Constraint;

@Entity
@Table(name = "security_camera")
public class SecurityCamera {

    @Id()
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "checkpoint_id")
    private int checkpointId;

    @Column(name = "power")
    private boolean power;

    @Column(name = "is_entry_camera")
    private Boolean isEntryCamera;

    public SecurityCamera() {
    }

    public SecurityCamera(int checkpointId, boolean power) {
        this.checkpointId = checkpointId;
        this.power = power;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCheckpointId() {
        return checkpointId;
    }

    public void setCheckpointId(int checkpointId) {
        this.checkpointId = checkpointId;
    }

    public boolean isPower() {
        return power;
    }

    public String checkPower() {
        if (power)
            return "On";
        else
            return "Off";
    }

    public void setPower(boolean power) {
        this.power = power;
    }

    public Boolean isEntryCamera() {
        return isEntryCamera;
    }

    public void setEntryCamera(Boolean entryCamera) {
        isEntryCamera = entryCamera;
    }

    @Override
    public String toString() {
        return "SecurityCamera{" +
                "id=" + id +
                ", power=" + power +
                '}';
    }
}
