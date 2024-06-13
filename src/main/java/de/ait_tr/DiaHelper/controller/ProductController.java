package de.ait_tr.DiaHelper.controller;

import de.ait_tr.DiaHelper.domain.dto.ProductDto;
import de.ait_tr.DiaHelper.service.interfaces.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{product-id}")
    public ProductDto getById(@PathVariable("product-id") Long id) {
        return service.getById(id);
    } //  localhost:8080/products/5

    @GetMapping
    public List<ProductDto> getAll() {

        return service.getAll();
    } //  localhost:8080/products/all

    // Сохранять новый продукт в базе данных, может только администратор (пользователь с ролью ADMIN) return service.save(product);
    @PostMapping("/**")
    public ProductDto save(@RequestBody ProductDto product) {//  localhost:8080/products/5
        return service.save(product);
    }

    @PutMapping("/**")
    public void update(@RequestBody ProductDto product) {
        service.update(product);
    }

    @DeleteMapping("/**")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @DeleteMapping("/{productTitle}")
    public void deleteByProductTitle(@PathVariable String productTitle) {
        service.deleteByProductTitle(productTitle);
    }
}