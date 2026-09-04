package com.example.demo.service;

import java.util.UUID;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.ProfileDTO;
import com.example.demo.entity.ProfileEntity;
import com.example.demo.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;
    public final EmailService emailService;

    public ProfileDTO registerProfile(ProfileDTO profileDTO){

        if(profileRepository.findByEmail(profileDTO.getEmail()).isPresent()){
            throw new RuntimeException("Email is already there");
        }

        String activationToken= UUID.randomUUID().toString();

        ProfileEntity entity=new ProfileEntity();
        entity.setFullName(profileDTO.getFullName());
        entity.setEmail(profileDTO.getEmail());
        entity.setPassword(profileDTO.getPassword());
        entity.setProfileImageUrl(profileDTO.getProfileImageUrl());
        entity.setIsActive(false);
        entity.setActivationToken(activationToken);

        ProfileEntity savedEntity=profileRepository.save(entity);

        emailService.sendActivationEmail(savedEntity.getEmail(), activationToken);

        return ProfileDTO.builder()
                .id(savedEntity.getId())
                .fullName(savedEntity.getFullName())
                .email(savedEntity.getEmail())
                .createdAt(savedEntity.getCreatedAt())
                .updatedAt(savedEntity.getUpdatedAt())
                .build();
    }

    public ProfileEntity login(LoginRequest request){

        ProfileEntity profile=profileRepository.findByEmail(request.getEmail()).orElseThrow(()->new RuntimeException("Invalid Email or Password"));

        if (!profile.getIsActive()){
            throw new RuntimeException("Account is not active.Please check your email");
        }

        if (!profile.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Invalid Email or Password");
        }

        return profile;
    }
}
