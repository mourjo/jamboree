package me.mourjo.jamboree.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class IDGenerator {

    private final AtomicLong nextId;

    IDGenerator() {
        nextId = new AtomicLong(0);
    }

    public long generate() {
        return nextId.incrementAndGet();
    }
}
