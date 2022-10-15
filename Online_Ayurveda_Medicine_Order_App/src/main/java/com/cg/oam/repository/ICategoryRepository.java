package com.cg.oam.repository;
import org.springframework.data.repository.CrudRepository;
import com.cg.oam.entity.Category;

public interface ICategoryRepository extends CrudRepository<Category ,String>{
    
}
