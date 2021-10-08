package com.project.questapp.api.controller;

import com.project.questapp.business.UserService;
import com.project.questapp.dto.UserDto;
import com.project.questapp.dto.UserRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(this.userService.createUser(userRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable Long userId){
        return ResponseEntity.ok(this.userService.getOneUserById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateOneUser(@PathVariable Long userId, @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(this.userService.updateOneUserById(userId, userRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteOneUser(@PathVariable Long userId){
        return ResponseEntity.ok(this.userService.deleteOneUserById(userId));
    }


}
