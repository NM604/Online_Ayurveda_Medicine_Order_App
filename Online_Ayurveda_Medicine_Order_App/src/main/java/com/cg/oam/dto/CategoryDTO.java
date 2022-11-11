package com.cg.oam.dto;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CategoryDTO {

    private Integer categoryId;
    @NotNull
    @Size(min = 1)
    private String categoryName;


    public CategoryDTO(){}

    /**
     * @param categoryId
     * @param categoryName
     */
    public CategoryDTO(Integer categoryId, String categoryName) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
    
    /** 
     * @return String
     */
    public Integer getCategoryId() {
        return categoryId;
    }
    
    /** 
     * @param categoryId
     */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    
    /** 
     * @return String
     */
    public String getCategoryName() {
        return categoryName;
    }
    
    /** 
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



    
    /** 
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
        
        return result;
    }

    
    /** 
     * @param obj
     * @return boolean
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CategoryDTO other = (CategoryDTO) obj;
        if (categoryId == null) {
            if (other.categoryId != null)
                return false;
        } else if (!categoryId.equals(other.categoryId))
            return false;
        if (categoryName == null) {
            if (other.categoryName != null)
                return false;
        } else if (!categoryName.equals(other.categoryName))
            return false;
        return true;
    }



  
   
}