package com.swapp.swapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.swapp.swapp.dto.request.UserRequestDTO;
import com.swapp.swapp.dto.response.UserBasicResponseDTO;
import com.swapp.swapp.dto.response.UserProfileResponseDTO;
import com.swapp.swapp.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/v1/users")
@Tag(name = "authentication users", 
    description = "controller for authentication users"
)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserProfileResponseDTO> createUSer(
        @RequestPart("user") UserRequestDTO user, @RequestPart("file")MultipartFile file){
        UserProfileResponseDTO newUser = userService.createUser(user, file);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserBasicResponseDTO> getBasicUserById(@PathVariable int id) {
    UserBasicResponseDTO user = userService.getBasicUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

   @GetMapping("/profile/{id}")
   public ResponseEntity<UserProfileResponseDTO> getFullUserById(@PathVariable int id) {
    UserProfileResponseDTO user = userService.getFullUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> updateUSer(@PathVariable int id, 
                                                @Valid @RequestPart("user") UserRequestDTO user,
                                                @RequestPart(value ="file", required = false) MultipartFile file) {
        userService.updateUser(id, user, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
