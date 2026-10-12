package org.example.lab12.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.lab12.API.ApiResponse;
import org.example.lab12.Model.User;
import org.example.lab12.Service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body(new ApiResponse("user registered successfully"));
    }


    @GetMapping("/get")
    public ResponseEntity<?> getUsers(){
        List<User> users = authService.getUsers();
        return ResponseEntity.status(200).body(users);
    }

    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer user_id){
        authService.deleteUser(user_id);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
    }
}
