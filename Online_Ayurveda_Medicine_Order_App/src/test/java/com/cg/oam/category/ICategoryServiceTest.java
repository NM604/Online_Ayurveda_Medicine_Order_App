package com.cg.oam.category;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.oam.dto.CategoryDTO;
import com.cg.oam.entity.Category;
import com.cg.oam.exception.InvalidDataException;
import com.cg.oam.repository.ICategoryRepository;
import com.cg.oam.service.ICategoryServiceImpl;


@SpringBootTest
@DisplayName(value = "validation for Category Entity")
public class ICategoryServiceTest {
    @Mock 
    ICategoryRepository categoryRepository;

    @InjectMocks 
    ICategoryServiceImpl categoryService;

    
    /** 
     * @throws InvalidDataException
     */
    @Test
    @DisplayName(value = "try adding for new category")
    public void addCategory() throws InvalidDataException{		
		CategoryDTO categoryDTO = new CategoryDTO(1,"harry123");
		List<Category> categories = new ArrayList<>();
		Category newCategory = new Category();
		
		Mockito.when(categoryRepository.findByCategoryId(categoryDTO.getCategoryId())).thenReturn(categories);
		Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(newCategory);
		Assertions.assertEquals(categoryDTO, categoryService.addCategoryDTO(categoryDTO));
	}
    
    /** 
     * @throws InvalidDataException
     */
    @Test
    @DisplayName(value = "try adding for Existing category")
    public void addExistingCategory() throws InvalidDataException{		
		CategoryDTO category = new CategoryDTO(1,"harry123");
		Category dupCategory = new Category(1, "harry123");
		
		List<Category> Categorys = new ArrayList<>();
		List<Category> dupCategorys = new ArrayList<>();
		
		
		dupCategorys.add(dupCategory);
		Category newCategory = new Category();
		
		Mockito.when(categoryRepository.findByCategoryId(category.getCategoryId())).thenReturn(Categorys);
		Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(newCategory);
		categoryService.addCategoryDTO(category);
		
		Mockito.when(categoryRepository.findByCategoryId(category.getCategoryId())).thenReturn(dupCategorys);
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> categoryService.addCategoryDTO(category));
		Assertions.assertEquals("Service.CATEGORY_FOUND", e.getMessage());
	}
    
    /** 
     * @throws InvalidDataException
     */
    @Test
	@DisplayName("Check Updating Existing category")
	public void updateCatCategory() throws InvalidDataException{
		CategoryDTO category = new CategoryDTO(1,"harry123");
		CategoryDTO category1 = new CategoryDTO(1,"harry1234");
		// Optional<Category> dupAdmin = Optional.of(new Category(1, "harry123"));
		
		List<Category> categories = new ArrayList<>();
        Category newCategory = new Category(1,"harry123");
        
		
		Category newAdmin = new Category();
		
		Mockito.when(categoryRepository.findByCategoryId(category.getCategoryId())).thenReturn(categories);
		Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(newAdmin);
		categoryService.addCategoryDTO(category);
		categories.add(newCategory);
		Mockito.when(categoryRepository.findByCategoryId(category.getCategoryId())).thenReturn(categories);
		Assertions.assertEquals(category1, categoryService.updateCategoryDTO(1, "harry1234"));
		}
    
        
        /** 
         * @throws InvalidDataException
         */
        @Test
        @DisplayName("Check Updating Non-Existant category")
        public void updateAdminNotPresent() throws InvalidDataException{
            // CategoryDTO admin = new CategoryDTO("2","harry123");
            InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> categoryService.updateCategoryDTO(1,"asdas"));
            Assertions.assertEquals("Service.CATEGORY_NOT_FOUND", e.getMessage());
        }
        
        
        /** 
         * @throws InvalidDataException
         */
        @Test
	    @DisplayName("Check Removing Existing Category")
	    public void removeCategory() throws InvalidDataException{
		    CategoryDTO categoryDTO = new CategoryDTO(1,"harry123");
		    List<Category> categoryDTOs = new ArrayList<>();
		    Category newCategory = new Category(1,"harry123");
		
            
		    Mockito.when(categoryRepository.findByCategoryId(categoryDTO.getCategoryId())).thenReturn(categoryDTOs);
		    Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(newCategory);
		    categoryService.addCategoryDTO(categoryDTO);
            categoryDTOs.add(newCategory);
		    Mockito.when(categoryRepository.findByCategoryId(categoryDTO.getCategoryId())).thenReturn(categoryDTOs);
		    Assertions.assertEquals(categoryDTO, categoryService.removeCategoryDTO(1));
            Mockito.verify(categoryRepository).deleteById(1);
	}
    @Test
	@DisplayName("Check Removing Non-Existant Category")
	public void removeAdminNotPresent(){
		InvalidDataException e = Assertions.assertThrows(InvalidDataException.class, () -> categoryService.removeCategoryDTO(2));
		Assertions.assertEquals("Service.CATEGORY_NOT_FOUND", e.getMessage());
	}

        
    



}
