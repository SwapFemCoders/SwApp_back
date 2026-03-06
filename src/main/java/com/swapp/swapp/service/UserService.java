package com.swapp.swapp.service;

import java.util.List;

import com.swapp.swapp.dto.request.UserRequestDTO;
import com.swapp.swapp.dto.response.UserBasicResponseDTO;
import com.swapp.swapp.dto.response.UserProfileResponseDTO;
import com.swapp.swapp.entity.User;

public interface UserService {

    public UserProfileResponseDTO createUser (UserRequestDTO user);

    public User getUserById (int id);

    public UserProfileResponseDTO getFullUserById (int id);

    public UserBasicResponseDTO getBasicUserById (int id);

    public List<String> getAllUserNames();

    public UserBasicResponseDTO getUserByEmail(String email);

    public User updateUser (User user);

    public void toFavorites (int articleId, int userId);

    public void deleteUser (int id);

}
