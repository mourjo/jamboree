package me.mourjo.jamboree.data;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


public class PartyRepositoryImpl implements PartyRepository {

    private final Map<Long, Party> data;
    private final HikariDataSource ds;

    public PartyRepositoryImpl() {

        data = new ConcurrentHashMap<>();

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/jamboree");
        config.setUsername("postgres");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    @Override
    public <S extends Party> S save(S entity) {
        try (Connection conn = ds.getConnection()){
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            conn.setAutoCommit(false);
            PreparedStatement st1 = conn.prepareStatement("SELECT id FROM party ORDER BY id DESC LIMIT 1");
            ResultSet rs1 = st1.executeQuery();
            long id = 1L;
            while (rs1.next()) {
                id = rs1.getLong(1);
            }
            PreparedStatement st2 = conn.prepareStatement("""
                    INSERT INTO party (id, name, location, party_time, created_at) VALUES
                    (?,?,?,?,?)
                    """);
            st2.setObject(1, ++id);
            st2.setObject(2, entity.getName());
            st2.setObject(3, entity.getLocation());
            st2.setObject(4, Timestamp.from(entity.getTime().toInstant()));
            st2.setObject(5, Timestamp.from(entity.getCreatedAt().toInstant()));
            int res = st2.executeUpdate();
            conn.commit();
            st1.close();
            rs1.close();
            st2.close();
            entity.setId(id);
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
        try (Connection conn = ds.getConnection()){
            Properties props = new Properties();
            props.setProperty("user", "postgres");
            PreparedStatement st = conn.prepareStatement("SELECT * FROM party WHERE id = ? LIMIT 1");
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            Party entity = new Party();
            if (!rs.isBeforeFirst()) {
                return Optional.empty();
            }
            while (rs.next()) {
                entity.setId(rs.getLong(1));
                entity.setName(rs.getString(2));
                entity.setLocation(rs.getString(3));
                entity.setTime(rs.getTimestamp(4).toInstant().atZone(ZoneId.systemDefault()));
                entity.setCreatedAt(rs.getTimestamp(5).toInstant().atZone(ZoneId.systemDefault()));
            }
            rs.close();
            st.close();
            return Optional.of(entity);
        } catch (Exception e) {
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
        try(Connection conn = ds.getConnection()){
            PreparedStatement st = conn.prepareStatement("DELETE FROM party WHERE id = ?");
            st.setLong(1, id);
            st.executeUpdate();
            st.close();
        }
        catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

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
