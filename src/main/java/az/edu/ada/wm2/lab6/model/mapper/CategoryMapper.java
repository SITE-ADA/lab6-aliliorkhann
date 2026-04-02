package az.edu.ada.wm2.lab6.mapper;

import az.edu.ada.wm2.lab6.dto.CategoryRequestDto;
import az.edu.ada.wm2.lab6.dto.CategoryResponseDto;
import az.edu.ada.wm2.lab6.model.Category;

import java.util.UUID;

public class CategoryMapper {

    public static Category toEntity(CategoryRequestDto dto) {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName(dto.getName());
        return category;
    }

    public static CategoryResponseDto toResponseDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        return dto;
    }
}