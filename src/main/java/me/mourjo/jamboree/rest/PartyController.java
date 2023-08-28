package me.mourjo.jamboree.rest;


import me.mourjo.jamboree.datetime.DatetimeFormat;
import me.mourjo.jamboree.service.PartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
public class PartyController {

    private final PartyService service;
    private Logger logger = LoggerFactory.getLogger(getClass().getName());

    PartyController(PartyService service) {
        this.service = service;
    }

    @GetMapping("/party/{id}")
    ResponseEntity<Map<String, String>> get(@PathVariable Long id) {
        MDC.put("REQUEST_ID", UUID.randomUUID().toString());
        MDC.put("PARTY_ID", String.valueOf(id));
        logger.info("Reading party {}", id);
        return service.find(id)
                .map(value -> ResponseEntity.status(HttpStatus.OK).body(value.toMap()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Not found")));
    }

    @PostMapping("/party/")
    ResponseEntity<Map<String, String>> save(@RequestBody Map<String, String> params) {
        MDC.put("REQUEST_ID", UUID.randomUUID().toString());
        logger.info("Creating a party with {}", params);
        if (!params.containsKey("name")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Name is mandatory."));
        }

        if (!params.containsKey("location")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Location is mandatory."));
        }

        var time = DatetimeFormat.parse(params.get("time"));
        var createdParty = service.add(params.get("name"), params.get("location"), time);

        MDC.put("PARTY_ID", String.valueOf(createdParty.getId()));
        logger.info("Created a party {} with {}", createdParty.getId(), params);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdParty.toMap());
    }

    @GetMapping("/")
    public Map<String, String> index() {
        logger.info("Reading index path");
        return Map.of("message", "Welcome to Jamboree!");
    }

}
