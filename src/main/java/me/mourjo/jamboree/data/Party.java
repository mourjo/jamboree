package me.mourjo.jamboree.data;


import me.mourjo.jamboree.apischema.PartyResponse;
import me.mourjo.jamboree.datetime.DatetimeFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
@Table
public class Party {
    @Id
    Long id;
    String name;
    @Column("party_time")
    OffsetDateTime time;
    String location;
    @Column("created_at")
    OffsetDateTime createdAt;

    public Party() {
        this( "Unknown", "Unknown", ZonedDateTime.now());
    }

    public Party(String name, String location, ZonedDateTime time) {
        this.name = name;
        this.location = location;
        this.time = time.toOffsetDateTime();
        this.createdAt = ZonedDateTime.now().toOffsetDateTime();
    }

    public ZonedDateTime getTime() {
        return time.toZonedDateTime();
    }

    public void setTime(ZonedDateTime time) {
        this.time = time.toOffsetDateTime();
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
        return createdAt.toZonedDateTime();
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt.toOffsetDateTime();
    }

    public PartyResponse toResponse() {
        return new PartyResponse(String.valueOf(id), name, location, DatetimeFormat.unparse(time.toZonedDateTime()), DatetimeFormat.unparse(createdAt.toZonedDateTime()), null);
    }
}
