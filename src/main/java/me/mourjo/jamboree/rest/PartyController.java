package me.mourjo.jamboree.rest;


import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import me.mourjo.jamboree.apischema.PartyRequest;
import me.mourjo.jamboree.apischema.PartyResponse;
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
    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    PartyController(PartyService service) {
        this.service = service;
    }

    @Operation
    @GetMapping("/party/{id}")
    Response<PartyResponse> get(@PathVariable Long id) {
        MDC.put("REQUEST_ID", UUID.randomUUID().toString());
        MDC.put("PARTY_ID", String.valueOf(id));
        logger.info("Reading party {}", id);
        return service.find(id)
                .map(value -> Response.of(HttpStatus.OK, value.toResponse()))
                .orElseGet(() -> Response.error(HttpStatus.NOT_FOUND, "Not found"));
    }

    @Operation
    @PostMapping("/party/")
    Response<PartyResponse> save(@RequestBody PartyRequest params) {
        MDC.put("REQUEST_ID", UUID.randomUUID().toString());
        logger.info("Creating a party with {}", params);
        if (params.name() == null) {
            return Response.error(HttpStatus.BAD_REQUEST, "Name is mandatory.");
        }

        if (params.location() == null) {
            logger.error("Missing parameter: location");
            return Response.error(HttpStatus.BAD_REQUEST, "Location is mandatory.");
        }

        if (params.time() == null) {
            logger.error("Missing parameter: time");
            return Response.error(HttpStatus.BAD_REQUEST, "Time is mandatory.");
        }

        var time = DatetimeFormat.parse(params.time());
        var createdParty = service.add(params.name(), params.location(), time);

        MDC.put("PARTY_ID", String.valueOf(createdParty.getId()));

        return Response.of(HttpStatus.CREATED, createdParty.toResponse());
    }

    @Operation
    @GetMapping("/")
    public Map<String, String> index() {
        logger.info("Reading index path");
        return Map.of("message", "Welcome to Jamboree!");
    }

    @Hidden
    @RequestMapping(value = "*")
    public Response notFound() {
        return Response.error(HttpStatus.NOT_FOUND, "Requested path not found");
    }

}
