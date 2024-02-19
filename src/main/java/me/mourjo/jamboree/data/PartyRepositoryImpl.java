package me.mourjo.jamboree.data;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


public class PartyRepositoryImpl implements PartyRepository {

    private final Map<Long, Party> data;

    public PartyRepositoryImpl() {
        data = new ConcurrentHashMap<>();
    }

    @Override
    public <S extends Party> S save(S entity) {
        data.put(entity.getId(), entity);
        try {
            String url = "jdbc:postgresql://localhost:5432/jamboree";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            Connection conn = DriverManager.getConnection(url, props);
            PreparedStatement st = conn.prepareStatement("""
                    INSERT INTO party (id, name, location, party_time, created_at) VALUES
                    (?,?,?,?,?)
                    """);
            st.setObject(1, entity.getId());
            st.setObject(2, entity.getName());
            st.setObject(3, entity.getLocation());
            st.setObject(4, Timestamp.from(entity.getTime().toInstant()));
            st.setObject(5, Timestamp.from(entity.getCreatedAt().toInstant()));
            int res = st.executeUpdate();
            st.close();
            conn.close();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
        try{String url = "jdbc:postgresql://localhost:5432/jamboree";
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            Connection conn = DriverManager.getConnection(url, props);
            PreparedStatement st = conn.prepareStatement("SELECT * FROM party WHERE id = ?");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            Party entity = new Party();
            boolean emptyResult = true;
            while (rs.next()) {
                emptyResult = false;
                entity.setId(rs.getLong(1));
                entity.setName(rs.getString(2));
                entity.setLocation(rs.getString(3));
                entity.setTime(rs.getTimestamp(4).toInstant().atZone(ZoneId.systemDefault()));
                entity.setCreatedAt(rs.getTimestamp(5).toInstant().atZone(ZoneId.systemDefault()));
            }
            rs.close();
            st.close();
            if(emptyResult){
                return Optional.empty();
            }
            return Optional.of(entity);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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
