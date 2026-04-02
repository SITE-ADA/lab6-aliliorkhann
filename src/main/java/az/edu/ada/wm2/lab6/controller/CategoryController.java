package az.edu.ada.wm2.lab6.controller;

import az.edu.ada.wm2.lab6.model.dto.CategoryRequestDto;
import az.edu.ada.wm2.lab6.model.dto.CategoryResponseDto;
import az.edu.ada.wm2.lab6.model.dto.ProductResponseDto;
import az.edu.ada.wm2.lab6.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDto> createCategory(@RequestBody CategoryRequestDto requestDto) {
        CategoryResponseDto response = categoryService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> fetchAll() {
        List<CategoryResponseDto> categories = categoryService.getAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/{categoryId}/products/{productId}")
    public ResponseEntity<CategoryResponseDto> linkProduct(
            @PathVariable("categoryId") UUID categoryId,
            @PathVariable("productId") UUID productId) {

        try {
            CategoryResponseDto result = categoryService.addProduct(categoryId, productId);
            return ResponseEntity.ok(result);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductResponseDto>> fetchProductsByCategory(
            @PathVariable("categoryId") UUID categoryId) {

        try {
            List<ProductResponseDto> products = categoryService.getProducts(categoryId);
            return ResponseEntity.ok(products);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}