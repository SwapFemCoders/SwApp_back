package com.swapp.swapp.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.swapp.swapp.dto.request.UserRequestDTO;
import com.swapp.swapp.dto.response.UserBasicResponseDTO;
import com.swapp.swapp.dto.response.UserProfileResponseDTO;
import com.swapp.swapp.entity.User;
import com.swapp.swapp.exception.BadIdException;
import com.swapp.swapp.mapper.UserMapper;
import com.swapp.swapp.repository.UserRepository;
import com.swapp.swapp.utils.FileUtil;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserProfileResponseDTO createUser(UserRequestDTO userIn, MultipartFile file){
        byte[] picture = FileUtil.convertPicture(file);
        User user = userMapper.toEntity(userIn, picture);
        user.setPoints(3);
        userRepository.save(user);
        return userMapper.toProfileResponse(user);
    }
    @Override
    public User getUserById (int id){
        return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("The user does not exist"));
    }

    @Override
    public UserProfileResponseDTO getFullUserById(int id) {
        return userMapper.toProfileResponse(getUserById(id));
    }

    @Override
    public UserBasicResponseDTO getBasicUserById (int id){
        return userMapper.toBasicResponse(getUserById(id));
    }

    @Override
    @Transactional
    public void updateUser(int id, UserRequestDTO user, MultipartFile file) {
        if(!user.id().equals(getUserById(id).getId())){
            throw new BadIdException("Problems with id autentication");
        }
        User updatedUser = getUserById(id);
        userMapper.updateEntityFromDto(user, updatedUser);

        if (file != null && !file.isEmpty()) {
        updatedUser.setPicture(FileUtil.convertPicture(file));
        userRepository.save(updatedUser);
    }
       
       
    }

    @Override
    public void toFavorites(int articleId, int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toFavorites'");
    }

    @Override
    public void deleteUser(int id) {
      User user = getUserById(id);
      userRepository.delete(user);
    }

    @Override
    public List<String> getAllUserNames() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUserNames'");
    }

    @Override
    public UserBasicResponseDTO getUserByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserByEmail'");
    }

}
