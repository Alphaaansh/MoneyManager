package com.example.demo.controller;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.entity.CategoryEntity;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryEntity> createCategory(
            @RequestParam Long profileId,
            @RequestParam CategoryRequest request
            ){
        CategoryEntity createdCategory=categoryService.saveCategory(profileId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryEntity>> getCategories(@RequestParam Long profileId){
        List<CategoryEntity> categories=categoryService.getCategories(profileId);

        return ResponseEntity.ok(categories);
    }
}
