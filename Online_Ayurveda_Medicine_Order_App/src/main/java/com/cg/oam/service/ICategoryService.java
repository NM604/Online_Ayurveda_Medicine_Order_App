package com.cg.oam.service;

import java.util.List;

import com.cg.oam.dto.CategoryDTO;
import com.cg.oam.exception.InvalidDataException;

public interface ICategoryService {
    /**
     * @param categoryDTO
     * @return
     * @throws InvalidDataException
     */
    public CategoryDTO addCategoryDTO(CategoryDTO categoryDTO) throws InvalidDataException;
    /**
     * @param categoryId
     * @return
     * @throws InvalidDataException
     */
    public CategoryDTO removeCategoryDTO(String categoryId) throws InvalidDataException;
    /**
     * @param categoryId
     * @param nameToBeUpdated
     * @return
     * @throws InvalidDataException
     */
    public CategoryDTO updateCategoryDTO(String categoryId, String nameToBeUpdated) throws InvalidDataException;
    /**
     * @param categoryId
     * @return
     * @throws InvalidDataException
     */
    public CategoryDTO viewCategory(String categoryId) throws InvalidDataException;
    /**
     * @return
     * @throws InvalidDataException
     */
    public List<CategoryDTO> showAllCategory() throws InvalidDataException;

}
