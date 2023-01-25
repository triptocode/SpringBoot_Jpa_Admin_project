package com.example.adminStudy.repository;

import com.example.adminStudy.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long > {
}
