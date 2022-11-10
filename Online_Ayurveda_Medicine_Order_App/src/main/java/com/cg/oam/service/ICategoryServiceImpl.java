package com.cg.oam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.oam.dto.CategoryDTO;
import com.cg.oam.entity.Category;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.ICategoryRepository;

@Service(value ="CategoryService")
@Transactional
public class ICategoryServiceImpl implements ICategoryService{
    @Autowired
    private ICategoryRepository categoryRepository;

    
    /** 
     * @param categoryDTO
     * @return CategoryDTO
     * @throws InvalidDataException
     */
    @Override
    public CategoryDTO addCategoryDTO(CategoryDTO categoryDTO) throws InvalidDataException {
        List<Category> optionalCategories = categoryRepository.findByCategoryName(categoryDTO.getCategoryName());
        if(!optionalCategories.isEmpty()){
            throw new InvalidDataException("Service.CATEGORY_FOUND");
        }
        
        Category newCategory = new Category();
        newCategory.setCategoryId(categoryDTO.getCategoryId());
        newCategory.setCategoryName(categoryDTO.getCategoryName());
        categoryRepository.save(newCategory);
        return categoryDTO;
    }

    
    /** 
     * @param categoryId
     * @return CategoryDTO
     * @throws InvalidDataException
     */
    @Override
    public CategoryDTO removeCategoryDTO(Integer categoryId) throws InvalidDataException {
        // TODO Auto-generated method stub
        List<Category> optionalCategories = categoryRepository.findByCategoryId(categoryId);
        if(optionalCategories.isEmpty()){
            throw new InvalidDataException("Service.CATEGORY_NOT_FOUND");
        }
        Category removedCategory = optionalCategories.get(0);
        // Category removedCategory = optionalCategories.orElseThrow(()-> new InvalidDataException("Service.CATEGORY_NOT_FOUND"));
        categoryRepository.deleteById(categoryId);
        CategoryDTO newCategoryDTO = new CategoryDTO(removedCategory.getCategoryId(),removedCategory.getCategoryName());
        
        return newCategoryDTO;
    }

    
    /** 
     * @param categoryId
     * @return CategoryDTO
     * @throws InvalidDataException
     */
    @Override
    public CategoryDTO viewCategory(Integer categoryId) throws InvalidDataException {
        // TODO Auto-generated method stub
        List<Category> optionalCategories = categoryRepository.findByCategoryId(categoryId);
        if(optionalCategories.isEmpty()){
            throw new InvalidDataException("Service.CATEGORY_NOT_FOUND");
        }
        Category category = optionalCategories.get(0);
        // Category category = optionalCategories.orElseThrow(()-> new InvalidDataException("Service.CATEGORY_NOT_FOUND"));
        CategoryDTO categoryDTO = new CategoryDTO(category.getCategoryId(),category.getCategoryName());
        return categoryDTO;
    }


    
    /** 
     * @return List<CategoryDTO>
     * @throws InvalidDataException
     */
    @Override
    public List<CategoryDTO> showAllCategory() throws InvalidDataException {
        // TODO Auto-generated method stub
        Iterable<Category> categories = categoryRepository.findAll();
            List<CategoryDTO> categoryDTOs = new ArrayList<>();
            categories.forEach(category -> {
                CategoryDTO newCategoryDTO = new CategoryDTO(category.getCategoryId(), category.getCategoryName());
                categoryDTOs.add(newCategoryDTO);
            });
        return categoryDTOs;
    }

    
    /** 
     * @param categoryId
     * @param name
     * @return CategoryDTO
     * @throws InvalidDataException
     */
    @Override
    public CategoryDTO updateCategoryDTO(Integer categoryId, String name) throws InvalidDataException {
        // TODO Auto-generated method stub
        List<Category> optionalCategories = categoryRepository.findByCategoryId(categoryId);
        // Category category = optionalCategories.orElseThrow(()-> new InvalidDataException("Service.CATEGORY_NOT_FOUND"));
        
        if(optionalCategories.isEmpty()){
            throw new InvalidDataException("Service.CATEGORY_NOT_FOUND");
        }
        Category category = optionalCategories.get(0);
        category.setCategoryName(name);
        CategoryDTO newCategoryDTO = new CategoryDTO(category.getCategoryId(),category.getCategoryName());
        return newCategoryDTO;
    }





}
