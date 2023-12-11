package me.mourjo.jamboree.data;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


public class PartyRepositoryImpl implements PartyRepository {

    private final Map<Long, Party> data;

    public PartyRepositoryImpl() {
        data = new ConcurrentHashMap<>();
    }

    @Override
    public <S extends Party> S save(S entity) {
        data.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends Party> Iterable<S> saveAll(Iterable<S> entities) {
        for (var item : entities) {
            data.put(item.getId(), item);
        }
        return entities;
    }

    @Override
    public Optional<Party> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return data.containsKey(id);
    }

    @Override
    public Iterable<Party> findAll() {
        return data.values();
    }

    @Override
    public Iterable<Party> findAllById(Iterable<Long> ids) {
        var matches = new ArrayList<Party>();
        for (var id : ids) {
            if (data.containsKey(id)) {
                matches.add(data.get(id));
            }
        }
        return matches;
    }

    @Override
    public long count() {
        return data.size();
    }

    @Override
    public void deleteById(Long id) {
        data.remove(id);
    }

    @Override
    public void delete(Party entity) {
        data.remove(entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> ids) {
        for (var id : ids) {
            data.remove(id);
        }
    }

    @Override
    public void deleteAll(Iterable<? extends Party> entities) {
        for (var item : entities) {
            data.remove(item.getId());
        }
    }

    @Override
    public void deleteAll() {
        data.clear();
    }
}
