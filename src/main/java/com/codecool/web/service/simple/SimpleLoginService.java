package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.exception.ServiceException;
import java.sql.SQLException;

public final class SimpleLoginService implements LoginService {

    private final UserDao userDao;

    public SimpleLoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loginUser(int userId, String userRole) throws SQLException, ServiceException {
        User user = userDao.findById(userId, userRole);
        if (user == null) {
            throw new ServiceException("User not exist");
        }
        return user;
    }
}
