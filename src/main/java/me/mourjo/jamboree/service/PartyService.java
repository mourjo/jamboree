package me.mourjo.jamboree.service;

import me.mourjo.jamboree.data.Party;
import me.mourjo.jamboree.data.PartyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
public class PartyService {
    private final PartyRepository repository;

    private final Logger logger = LoggerFactory.getLogger(getClass().getName());

    PartyService(PartyRepository repository, IDGenerator idGenerator) {
        this.repository = repository;
    }

    public Party add(String name, String location, ZonedDateTime ts) {
        var start = System.nanoTime();
        Party p = new Party(-1L, name, location, ts);
        p = repository.save(p);
        logger.info("Created a party {} with {}", p.getId(), p);
        return p;
    }

    public Optional<Party> find(long id) {
        var party = repository.findById(id);
        if (party.isEmpty()) {
            logger.warn("Requested party does not exist: {}", id);
        }

        return party;
    }

    public Iterable<Party> getAllParties() {
        return repository.findAll();
    }
}
