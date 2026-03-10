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
import com.swapp.swapp.mapper.UserMapper;
import com.swapp.swapp.repository.UserRepository;
import com.swapp.swapp.utils.FileUtil;

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
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty())
            throw new RuntimeException("The user not exist");

        return optionalUser.get();
    }

    @Override
    public UserProfileResponseDTO getFullUserById(int id) {
        return userMapper.toProfileResponse(getUserById(id));
    }

    @Override
    public UserBasicResponseDTO getBasicUserById (int id){
        return userMapper.toBasicResponse(getUserById(id));
    }



    //A PARTIR D AQUI HAY Q REVISAR

    @Override
    public User updateUser(User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    @Override
    public void toFavorites(int articleId, int userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toFavorites'");
    }

    @Override
    public void deleteUser(int id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
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
