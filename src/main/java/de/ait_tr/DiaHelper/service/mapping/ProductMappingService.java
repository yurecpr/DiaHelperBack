package de.ait_tr.DiaHelper.service.mapping;

import de.ait_tr.DiaHelper.domain.dto.ProductDto;
import de.ait_tr.DiaHelper.domain.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMappingService {


    ProductDto mapEntityToDto(Product entity);

    @Mapping(target = "id", ignore = true)

    Product mapDtoToEntity(ProductDto dto);
}
