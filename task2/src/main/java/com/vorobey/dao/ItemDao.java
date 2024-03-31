package com.vorobey.dao;

import com.vorobey.entity.ItemEntity;
import com.vorobey.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ItemDao {
    private static final ItemDao INSTANCE = new ItemDao();
    private static final String CREATE_SQL = """
            CREATE TABLE item(
                id BIGSERIAL PRIMARY KEY,
                name VARCHAR(128) NOT NULL,
                cost DECIMAL(5,2) NOT NULL);
            """;
    private static final String INSERT_SQL = """
            INSERT INTO item (name, cost)
            VALUES
            ('carrot', 10),
            ('potato', 15),
            ('apple', 20),
            ('banana', 7);
            """;

    private static final String FIND_ALL_SQL = "SELECT id, name, cost FROM item";

    private static final String FIND_BY_ID = FIND_ALL_SQL + "WHERE id = ?";

    public static ItemDao getInstance() {
        return INSTANCE;
    }

    public void createAndFillTable() {
        try (var connection = ConnectionUtil.get();
             var createStatement = connection.prepareStatement(CREATE_SQL);
             var insertStatement = connection.prepareStatement(INSERT_SQL)) {
            createStatement.executeUpdate();
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ItemEntity> findAll() {
        try (var connection = ConnectionUtil.get();
             var preparedStatement = connection.prepareStatement(FIND_ALL_SQL)) {
            List<ItemEntity> itemEntities = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                itemEntities.add(buildItem(resultSet));
            }
            return itemEntities;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ItemEntity> findById(List<Long> idList) throws SQLException {
        int size = idList.size();
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionUtil.get();
            StringBuilder sql = new StringBuilder("SELECT * FROM item WHERE id IN (");
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    sql.append(", ");
                }
                sql.append("?");
            }
            sql.append(")");

            statement = connection.prepareStatement(sql.toString());

            for (int i = 1; i <= size; i++) {
                statement.setLong(i, idList.get(i - 1));
            }

            var resultSet = statement.executeQuery();
            List<ItemEntity> itemEntities = new ArrayList<>();
            while (resultSet.next()) {
                itemEntities.add(buildItem(resultSet));
            }

            return itemEntities;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    public ItemEntity buildItem(ResultSet resultSet) throws SQLException {
        return new ItemEntity(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getDouble("cost")
        );
    }

    private ItemDao() {
    }

}
