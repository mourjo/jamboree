package me.mourjo.jamboree.data;


import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Party {
    Long id;
    String name;
    String location;
    ZonedDateTime createdAt;

    public Party() {
        this(-1L, "Unknown", "Unknown");
    }

    public Party(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.createdAt = ZonedDateTime.now();
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

    public Map<String, String> toMap() {
        return Map.of(
                "id", String.valueOf(id),
                "name", name,
                "location", location,
                "created_at", createdAt.format(DateTimeFormatter.RFC_1123_DATE_TIME)
        );
    }
}
