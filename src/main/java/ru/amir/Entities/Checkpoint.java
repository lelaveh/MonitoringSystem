package ru.amir.Entities;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "checkpoint")
public class Checkpoint {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "location")
    @Min(value = 0, message = "Location should not be negative")
    private int location;

    @OneToOne()
    @JoinColumn(name = "entry_camera_id")
    private SecurityCamera entryCamera;

    @OneToOne()
    @JoinColumn(name = "exit_camera_id")
    private SecurityCamera exitCamera;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public SecurityCamera getEntryCamera() {
        return entryCamera;
    }

    public void setEntryCamera(SecurityCamera entryCamera) {
        this.entryCamera = entryCamera;
    }

    public SecurityCamera getExitCamera() {
        return exitCamera;
    }

    public void setExitCamera(SecurityCamera exitCamera) {
        this.exitCamera = exitCamera;
    }
}
