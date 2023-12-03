package me.mourjo.jamboree.rest;


import me.mourjo.jamboree.data.Response;
import me.mourjo.jamboree.datetime.DatetimeFormat;
import me.mourjo.jamboree.service.PartyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
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
    Response get(@PathVariable Long id) {
        MDC.put("REQUEST_ID", UUID.randomUUID().toString());
        MDC.put("PARTY_ID", String.valueOf(id));
        logger.info("Reading party {}", id);
        return service.find(id)
                .map(value -> Response.of(HttpStatus.OK, value.toMap()))
                .orElseGet(() -> Response.of(HttpStatus.NOT_FOUND, Map.of("error", "Not found")));
    }

    @PostMapping("/party/")
    Response save(@RequestBody Map<String, String> params) {
        MDC.put("REQUEST_ID", UUID.randomUUID().toString());
        logger.info("Creating a party with {}", params);
        if (!params.containsKey("name")) {
            return Response.of(HttpStatus.BAD_REQUEST, Map.of("error", "Name is mandatory."));
        }

        if (!params.containsKey("location")) {
            logger.error("Missing parameter: location");
            return Response.of(HttpStatus.BAD_REQUEST, Map.of("error", "Location is mandatory."));
        }

        if (!params.containsKey("time")) {
            logger.error("Missing parameter: time");
            return Response.of(HttpStatus.BAD_REQUEST, Map.of("error", "Time is mandatory."));
        }

        var time = DatetimeFormat.parse(params.get("time"));
        var createdParty = service.add(params.get("name"), params.get("location"), time);

        MDC.put("PARTY_ID", String.valueOf(createdParty.getId()));

        return Response.of(HttpStatus.CREATED, createdParty.toMap());
    }

    @GetMapping("/")
    public Map<String, String> index() {
        logger.info("Reading index path");
        return Map.of("message", "Welcome to Jamboree!");
    }

    @RequestMapping(value = "*")
    public Response notFound() {
        return Response.of(HttpStatus.NOT_FOUND, Map.of("error", "Requested path not found"));
    }

}
