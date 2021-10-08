package com.project.questapp.business;

import com.project.questapp.core.exception.EmailValidator;
import com.project.questapp.core.exception.UserNotFoundException;
import com.project.questapp.dataAccess.UserRepository;
import com.project.questapp.dto.UserDto;
import com.project.questapp.dto.UserRequest;
import com.project.questapp.dto.converter.UserDtoConverter;
import com.project.questapp.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;
    private final EmailValidator emailValidator;


    protected void isValidEmail(String mail) {
        boolean isValidEmail = this.emailValidator.test(mail);
        if(!isValidEmail){
            throw new IllegalStateException("Gecersiz bir email girdiniz!");
        }
    }

    protected void isUserExistsByMail(String mail) {
        boolean userExists = this.userRepository.findByEmail(mail).isPresent();
        if(userExists){
            throw new IllegalStateException("Bu mail zaten kullaniliyor: "+ mail);
        }
    }

    protected User isUserExistsById(Long userId){
        return this.userRepository.findById(userId)
                .orElseThrow(
                () -> new UserNotFoundException("Bu id'ye ait User bulunamadi. UserId: "+userId));
    }


    public List<UserDto> getAllUsers() {
        return this.userDtoConverter.convertToListUserDto(this.userRepository.findAll());
    }


    public UserDto createUser(UserRequest userRequest) {
        this.isValidEmail(userRequest.getEmail());
        this.isUserExistsByMail(userRequest.getEmail());

        User user = this.userRepository.save(this.userDtoConverter.convertToUser(userRequest));

        return this.userDtoConverter.convertToUserDto(user);
    }


    public UserDto getOneUserById(Long userId) {
        User user = this.isUserExistsById(userId);

        return this.userDtoConverter.convertToUserDto(user);
    }


    public UserDto updateOneUserById(Long userId, UserRequest userRequest) {
        this.isValidEmail(userRequest.getEmail());

        Optional<User> userOptional = this.userRepository.findById(userId);
        if(userOptional.isPresent()){
            User foundUser = userOptional.get();

            foundUser.setFirstName(userRequest.getFirstName());
            foundUser.setLastName(userRequest.getLastName());
            foundUser.setEmail(userRequest.getEmail());
            foundUser.setPassword(userRequest.getPassword());

            this.userRepository.save(foundUser);//updated and then saved
            return this.userDtoConverter.convertToUserDto(foundUser);
        }else{
            throw new IllegalStateException("Bu id'ye ait kullanici bulunamadi!");
        }

    }


    public String deleteOneUserById(Long userId) {
        this.isUserExistsById(userId);
        this.userRepository.deleteById(userId);
        return userId+" id'li user silindi.";
    }

}
