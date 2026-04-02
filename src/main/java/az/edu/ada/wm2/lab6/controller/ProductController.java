package az.edu.ada.wm2.lab6.controller;

import az.edu.ada.wm2.lab6.model.dto.ProductRequestDto;
import az.edu.ada.wm2.lab6.model.dto.ProductResponseDto;
import az.edu.ada.wm2.lab6.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> create(@RequestBody ProductRequestDto request) {
        return ResponseEntity.ok(productService.createProduct(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> update(
            @PathVariable UUID id,
            @RequestBody ProductRequestDto request
    ) {
        return ResponseEntity.ok(productService.updateProduct(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/expiring")
    public ResponseEntity<List<ProductResponseDto>> expiringBefore(@RequestParam LocalDate date) {
        return ResponseEntity.ok(productService.getProductsExpiringBefore(date));
    }

    @GetMapping("/price-range")
    public ResponseEntity<List<ProductResponseDto>> byPriceRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max
    ) {
        return ResponseEntity.ok(productService.getProductsByPriceRange(min, max));
    }
}