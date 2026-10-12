package org.example.lab12.Service;

import lombok.RequiredArgsConstructor;
import org.example.lab12.API.ApiException;
import org.example.lab12.Model.User;
import org.example.lab12.Repository.AuthRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    public void register(User user){
        user.setRole("USER");
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hash);
        authRepository.save(user);
    }

    public List<User> getUsers(){
        List<User> users = authRepository.findAll();
        if(users.isEmpty()){
            throw new ApiException("there is no users in the system");
        }
        return users;
    }

    public void deleteUser(Integer user_id){
        User user =authRepository.findUserById(user_id);
        if(user==null){
            throw new ApiException("wrong user id");
        }

        authRepository.delete(user);
    }
}
