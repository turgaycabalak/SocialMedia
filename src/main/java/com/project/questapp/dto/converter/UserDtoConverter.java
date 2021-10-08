package com.project.questapp.dto.converter;

import com.project.questapp.dto.UserDto;
import com.project.questapp.dto.UserRequest;
import com.project.questapp.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public List<UserDto> convertToListUserDto(List<User> from) {
        List<UserDto> converted = from
                .stream()
                .map(t -> new UserDto(
                        t.getFirstName(),
                        t.getLastName(),
                        t.getEmail()))
                .collect(Collectors.toList());

        return converted;
    }

    public UserDto convertToUserDto(User from){
        return new UserDto(
                from.getFirstName(),
                from.getLastName(),
                from.getEmail());
    }

    public User convertToUser(UserRequest request){
        return new User(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword());
    }





}
