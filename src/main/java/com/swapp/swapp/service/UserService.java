package com.swapp.swapp.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import com.swapp.swapp.dto.request.UserRequestDTO;
import com.swapp.swapp.dto.response.UserBasicResponseDTO;
import com.swapp.swapp.dto.response.UserProfileResponseDTO;
import com.swapp.swapp.entity.User;

public interface UserService {

    public UserProfileResponseDTO createUser (UserRequestDTO user, MultipartFile file);

    public User getUserById (int id);

    public UserProfileResponseDTO getFullUserById (int id);

    public UserBasicResponseDTO getBasicUserById (int id);

    public void updateUser (int id, UserRequestDTO user, MultipartFile file);

    public void deleteUser (int id);

    UserDetails loadUserByUsername(String username);

}
