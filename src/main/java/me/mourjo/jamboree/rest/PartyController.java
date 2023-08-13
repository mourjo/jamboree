package me.mourjo.jamboree.rest;


import me.mourjo.jamboree.service.PartyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PartyController {

    private final PartyService service;

    PartyController(PartyService service) {
        this.service = service;
    }

    @GetMapping("/party/{id}")
    ResponseEntity<Map<String, String>> get(@PathVariable Long id) {
        return service.find(id)
                .map(value -> ResponseEntity.status(HttpStatus.OK).body(value.toMap()))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Not found")));
    }

    @PostMapping("/party/")
    ResponseEntity<Map<String, String>> save(@RequestBody Map<String, String> p) {
        if (p.containsKey("name") && p.containsKey("location")) {
            var createdParty = service.add(p.get("name"), p.get("location"));
            return ResponseEntity.status(HttpStatus.CREATED).body(createdParty.toMap());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Name and location are mandatory."));
    }

    @GetMapping("/")
    public Map<String, String> index() {
        return Map.of("message", "Welcome to Jamboree!");
    }

}
