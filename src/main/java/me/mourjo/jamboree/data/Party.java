package me.mourjo.jamboree.data;


import me.mourjo.jamboree.apischema.PartyResponse;
import me.mourjo.jamboree.datetime.DatetimeFormat;

import java.time.ZonedDateTime;

public class Party {
    Long id;
    String name;
    ZonedDateTime time;
    String location;
    ZonedDateTime createdAt;

    public Party() {
        this(-1L, "Unknown", "Unknown", ZonedDateTime.now());
    }

    public Party(Long id, String name, String location, ZonedDateTime time) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.time = time;
        this.createdAt = ZonedDateTime.now();
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
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

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public PartyResponse toResponse() {
        return new PartyResponse(String.valueOf(id), name, location, DatetimeFormat.unparse(time), DatetimeFormat.unparse(createdAt), null);
    }
}
