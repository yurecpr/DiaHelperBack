package de.ait_tr.DiaHelper.exception_handling.exceptions;


public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String productName) {
        super(String.format("Product with name %s not found", productName));
    }

    public ProductNotFoundException(Long productId) {

        super(String.format("Product with ID %d not found", productId));
    }
}
