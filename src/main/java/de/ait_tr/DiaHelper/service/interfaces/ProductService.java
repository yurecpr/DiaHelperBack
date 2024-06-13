package de.ait_tr.DiaHelper.service.interfaces;

import de.ait_tr.DiaHelper.domain.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    ProductDto save(ProductDto product);

    List<ProductDto> getAll();

    ProductDto getById(Long id);

    void update(ProductDto product);

    void deleteById(Long id);

    void deleteByProductTitle(String productTitle);


}

