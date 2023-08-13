package me.mourjo.jamboree.data;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Party {

    Long id;
    String name;
    String location;
    ZonedDateTime time;

    public Party() {
        this(-1L, "Unknown", "Unknown");
    }

    public Party(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.time = LocalDateTime.now().atZone(ZoneId.of("Etc/UTC"));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }
}
