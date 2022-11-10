package com.cg.oam.repository;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.cg.oam.entity.Category;

public interface ICategoryRepository extends CrudRepository<Category ,Integer>{
    public List<Category> findByCategoryName(String CategoryName);

    public List<Category> findByCategoryId(Integer categoryId);
}
