package com.codecool.web.dao.database;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseUserDao extends AbstractDao implements UserDao {

    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT id, email, password FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(fetchUser(resultSet));
            }
            return users;
        }
    }

    public User findById(int supplierId) throws SQLException {
        String sql = "SELECT supplier_id FROM suppliers WHERE supplier_id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, supplierId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }

    private User fetchUser(ResultSet resultSet) throws SQLException {
        int supplierId = resultSet.getInt("supplier_id");
//        String companyName = resultSet.getString("company_name");
//        String contactName = resultSet.getString("contact_name");
//        String contactTitle = resultSet.getString("contact_title");
//        String address = resultSet.getString("address");
//        String city = resultSet.getString("city");
//        String region = resultSet.getString("region");
//        String postal_code = resultSet.getString("postal_code");
//        String country = resultSet.getString("country");
//        String phone = resultSet.getString("phone");
//        String fax = resultSet.getString("fax");
//        String homepage = resultSet.getString("homepage");
        return new User(supplierId);
//        return new User(supplierId, companyName, contactName, contactTitle, address, city, region, postal_code, country, phone, fax, homepage);
    }
}
