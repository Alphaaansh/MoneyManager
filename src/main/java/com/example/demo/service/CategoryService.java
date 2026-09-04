package com.example.demo.service;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.entity.ProfileEntity;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProfileRepository profileRepository;

    public CategoryEntity saveCategory(Long profileId, CategoryRequest request){
        ProfileEntity profile=profileRepository.findById(profileId).orElseThrow(()->new RuntimeException("Profile not fount"));

        CategoryEntity category= CategoryEntity.builder()
                .name(request.getName())
                .icon(request.getIcon())
                .type(request.getType())
                .profile(profile)
                .build();

        return categoryRepository.save(category);
    }

    public List<CategoryEntity> getCategories(Long profileId){
        return categoryRepository.findByProfileId(profileId);
    }
}
