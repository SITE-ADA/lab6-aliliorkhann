package az.edu.ada.wm2.lab6.service;

import az.edu.ada.wm2.lab6.model.Category;
import az.edu.ada.wm2.lab6.model.Product;
import az.edu.ada.wm2.lab6.model.dto.CategoryRequestDto;
import az.edu.ada.wm2.lab6.model.dto.CategoryResponseDto;
import az.edu.ada.wm2.lab6.model.dto.ProductResponseDto;
import az.edu.ada.wm2.lab6.model.mapper.ProductMapper;
import az.edu.ada.wm2.lab6.repository.CategoryRepository;
import az.edu.ada.wm2.lab6.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public CategoryResponseDto create(CategoryRequestDto dto) {
        Category category = new Category();
        category.setName(dto.getName());

        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponseDto(savedCategory.getId(), savedCategory.getName());
    }

    @Override
    public List<CategoryResponseDto> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> new CategoryResponseDto(category.getId(), category.getName()))
                .toList();
    }

    @Override
    public CategoryResponseDto addProduct(UUID categoryId, UUID productId) {
        Category category = categoryRepository.findById(categoryId)
            .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

    List<Product> updatedProducts = new java.util.ArrayList<>(category.getProducts());
    updatedProducts.add(product);
    category.setProducts(updatedProducts);

    List<Category> updatedCategories = new java.util.ArrayList<>(product.getCategories());
    updatedCategories.add(category);
    product.setCategories(updatedCategories);

        categoryRepository.save(category);
        productRepository.save(product);

    return new CategoryResponseDto(category.getId(), category.getName());
    }

    @Override
    public List<ProductResponseDto> getProducts(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));

        return category.getProducts()
                .stream()
                .map(productMapper::toResponseDto)
                .toList();
    }
}