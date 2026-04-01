package az.edu.ada.wm2.lab6.service;

import az.edu.ada.wm2.lab6.dto.CategoryRequestDto;
import az.edu.ada.wm2.lab6.dto.CategoryResponseDto;
import az.edu.ada.wm2.lab6.dto.ProductResponseDto;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponseDto create(CategoryRequestDto dto);
    List<CategoryResponseDto> getAll();
    CategoryResponseDto addProduct(UUID categoryId, UUID productId);
    List<ProductResponseDto> getProducts(UUID categoryId);
}