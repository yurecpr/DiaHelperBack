package de.ait_tr.DiaHelper.service;

import de.ait_tr.DiaHelper.domain.dto.ProductDto;
import de.ait_tr.DiaHelper.domain.entity.Product;
import de.ait_tr.DiaHelper.exception_handling.exceptions.ProductNotFoundException;
import de.ait_tr.DiaHelper.repository.ProductRepository;
import de.ait_tr.DiaHelper.service.interfaces.ProductService;
import de.ait_tr.DiaHelper.service.mapping.ProductMappingService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;
    private ProductMappingService mappingService;

    public ProductServiceImpl(ProductRepository repository, ProductMappingService mappingService) {
        this.repository = repository;
        this.mappingService = mappingService;
    }
    private List<ProductDto> products = new ArrayList<>();

    @Override
    public ProductDto save(ProductDto productDto) {

        Product entyti = mappingService.mapDtoToEntity(productDto);
        repository.save(entyti);
        return mappingService.mapEntityToDto(entyti);
    }

    @Override
    public List<ProductDto> getAll() {
        return repository.findAll()
                .stream()
                .map(x -> mappingService.mapEntityToDto(x))
                .toList();
    }

    @Override
    public ProductDto getById(Long id) {
//        Optional<ProductDto> product = products.stream()
//                .filter(p -> p.getId().equals(id))
//                .findFirst();
//        return product.orElse(null);
        return repository.findById(id).map(p -> mappingService.mapEntityToDto(p))
                .orElseThrow(()-> new ProductNotFoundException(id));
    }

    @Override
    public void update(ProductDto product) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            products.set(index, product);
        }
    }

    @Override
    public void deleteById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    @Override
    public void deleteByProductTitle(String productTitle) {
        products.removeIf(p -> p.getProductTitle().equalsIgnoreCase(productTitle));
    }

 }