package de.ait_tr.DiaHelper.repository;

import de.ait_tr.DiaHelper.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByProductTitle(String productTitle);

}
