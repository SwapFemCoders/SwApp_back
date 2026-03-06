package com.swapp.swapp.service;

import com.swapp.swapp.entity.User;

public interface UserService {

    public User createUser (User user);

    public User getUserById (int id);

    public User updateUser (User user);

    public void toFavorites (int articleId, int userId);

    public void deleteUser (int id);

}
