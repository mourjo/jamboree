package me.mourjo.jamboree.rest;


import me.mourjo.jamboree.data.Party;
import me.mourjo.jamboree.data.PartyRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class Controller {

    private final PartyRepository repository;

    Controller(PartyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/party/{id}")
    Party get(@PathVariable Long id) {
        return repository.findById(id).orElse(new Party(-1L, "s", "s"));
    }

    @PostMapping("/party/")
    Party save(@RequestBody Party p) {
        repository.save(p);
        return p;
    }

    @GetMapping("/")
    public Map<String, String> index() {
        return Map.of("message", "Welcome to Jamboree!");
    }

}
