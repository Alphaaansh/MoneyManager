package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.ProfileDTO;
import com.example.demo.entity.ProfileEntity;
import com.example.demo.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/register")
    public ResponseEntity<ProfileDTO> registerProfile(@RequestBody ProfileDTO profileDTO){
        ProfileDTO registerProfile=profileService.registerProfile(profileDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(registerProfile);
    }

    @PostMapping("/login")
    public ResponseEntity<ProfileEntity> login(@RequestBody LoginRequest request){
        ProfileEntity loggedInUser=profileService.login(request);

        return ResponseEntity.ok(loggedInUser);
    }
}
