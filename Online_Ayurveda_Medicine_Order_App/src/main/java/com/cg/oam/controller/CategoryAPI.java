package com.cg.oam.controller;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.dto.CategoryDTO;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.ICategoryRepository;
import com.cg.oam.service.ICategoryService;

@CrossOrigin(maxAge = 3600)
@RestController
@Validated
@RequestMapping(value = "/oam/userinterface")
public class CategoryAPI{

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private Environment environment;


    
    /** 
     * @return ResponseEntity<List<CategoryDTO>>
     * @throws InvalidDataException
     */
    @GetMapping(value = "/category")
    public ResponseEntity<List<CategoryDTO>> viewAllCategories() throws InvalidDataException{
        List<CategoryDTO> categories = categoryService.showAllCategory();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    
    /** 
     * @param categoryId
     * @return ResponseEntity<CategoryDTO>
     * @throws InvalidDataException
     */
    @GetMapping(value = "/category/{categoryId}")
    public ResponseEntity<CategoryDTO> viewCaregory(@Min(value = 0)@PathVariable Integer categoryId) throws InvalidDataException{
        CategoryDTO categoryDTO = categoryService.viewCategory(categoryId);
        return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
    }
    
    /** 
     * @param categoryDto
     * @return ResponseEntity<String>
     * @throws InvalidDataException
     */
    @PostMapping(value = "/category")
    public ResponseEntity<String> addCategory(@Valid @RequestBody CategoryDTO categoryDto) throws InvalidDataException{
        categoryService.addCategoryDTO(categoryDto);
        String successMessage = environment.getProperty("API.INSERT_SUCCESS") + categoryDto.getCategoryId();
        return new ResponseEntity<String>(successMessage, HttpStatus.OK);
    }
    
    /** 
     * @param categoryDTO
     * @return ResponseEntity<String>
     * @throws InvalidDataException
     */
    @PutMapping(value = "/category/{categoryId}")
    public ResponseEntity<String> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws InvalidDataException{
        categoryService.updateCategoryDTO(categoryDTO.getCategoryId(), categoryDTO.getCategoryName());
        String successMessage = environment.getProperty("API.UPDATE_SUCCESS") + categoryDTO.getCategoryId();
        return new ResponseEntity<String>(successMessage, HttpStatus.OK);
    }
    
    /** 
     * @param categoryId
     * @return ResponseEntity<String>
     * @throws InvalidDataException
     */
    @DeleteMapping(value = "/category/{categoryId}")
    public ResponseEntity<String> deleleCategory(@Min(value = 0) @PathVariable Integer categoryId ) throws InvalidDataException{
        categoryService.removeCategoryDTO(categoryId);
        String successMessage = environment.getProperty("API.DELETE_SUCCESS")+ categoryId;
        return new ResponseEntity<String>(successMessage,HttpStatus.OK);
    }
    
}