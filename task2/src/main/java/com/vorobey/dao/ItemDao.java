package com.vorobey.dao;

import com.vorobey.entity.ItemEntity;
import com.vorobey.util.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDao {
    private static final ItemDao INSTANCE = new ItemDao();

//    private static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS item("
//                + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
//                + "name VARCHAR(128) NOT NULL,"
//                + "cost DECIMAL(5,2) NOT NULL);";

    private static final String CREATE_SQL = """
            CREATE TABLE item(
                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(128) NOT NULL,
                cost DECIMAL(5,2) NOT NULL);
            """;

//    private static final String INSERT_SQL = "INSERT INTO item VALUES"
//            + "('carrot', 10),"
//            + "('potato', 15),"
//            + "('apple', 20),"
//            + "('banana', 7);";

    private static final String INSERT_SQL = """
            INSERT INTO item
            VALUES
            ('carrot', 10),
            ('potato', 15),
            ('apple', 20),
            ('banana', 7);
            """;

    private static final String FIND_ALL_SQL = "SELECT id, name, cost FROM table";

    private static final String FIND_BY_ID = FIND_ALL_SQL + "WHERE id = ?";

    public static ItemDao getInstance() {
        return INSTANCE;
    }

    public void createAndFillTable() {
        try (var connection = ConnectionUtil.get();
             var createStatement = connection.prepareStatement(CREATE_SQL);
             var insertStatement = connection.prepareStatement(INSERT_SQL)) {

            connection.setAutoCommit(false);
            createStatement.executeUpdate();
            insertStatement.executeUpdate();

            connection.commit();
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


    public ItemEntity buildItem(ResultSet resultSet) throws SQLException {
        return new ItemEntity(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getBigDecimal("cost")
        );
    }

    private ItemDao() {
    }

}
