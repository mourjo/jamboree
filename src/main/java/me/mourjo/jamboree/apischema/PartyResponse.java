package me.mourjo.jamboree.apischema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PartyResponse(
        String id,
        String name,
        String location,
        String time,
        @JsonProperty("created_at") String createdAt,
        String error) {
}
