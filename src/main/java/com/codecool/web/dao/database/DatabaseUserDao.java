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

    public User findById(int userId, String userRole) throws SQLException {
        String sql;
        if (userRole.equals("Supplier")) {
            sql = "SELECT * FROM suppliers WHERE supplier_id=?";
        }
        else {
            sql = "SELECT * FROM shippers WHERE shipper_id=?";
        }
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet, userRole);
                }
            }
        }
        return null;
    }

    private User fetchUser(ResultSet resultSet, String userRole) throws SQLException {
        int userId;
        String companyName = null;
        String contactName = null;
        String contactTitle = null;
        String address = null;
        String city = null;
        String region = null;
        String postalCode = null;
        String country = null;
        String phone = null;
        String fax = null;
        String homepage = null;

        if (userRole.equals("Supplier")) {
            userId = resultSet.getInt("supplier_id");
            companyName = resultSet.getString("company_name");
            contactName = resultSet.getString("contact_name");
            contactTitle = resultSet.getString("contact_title");
            address = resultSet.getString("address");
            city = resultSet.getString("city");
            region = resultSet.getString("region");
            postalCode = resultSet.getString("postal_code");
            country = resultSet.getString("country");
            phone = resultSet.getString("phone");
            fax = resultSet.getString("fax");
            homepage = resultSet.getString("homepage");
        }
        else {
            userId = resultSet.getInt("shipper_id");
            companyName = resultSet.getString("company_name");
            phone = resultSet.getString("phone");
        }

        return new User(userRole, userId, companyName, contactName, contactTitle, address, city, region, postalCode, country, phone, fax, homepage);
    }
}
