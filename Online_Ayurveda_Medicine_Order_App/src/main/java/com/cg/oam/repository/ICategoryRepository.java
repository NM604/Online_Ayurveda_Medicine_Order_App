package com.cg.oam.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.cg.oam.entity.Category;

public interface ICategoryRepository extends CrudRepository<Category ,String>{
    public List<Category> findByCategoryId(String CategoryId);
}
