package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;

public interface ProductService {

  List<Product> getProducts(Product product);

  Product getProductById(Long id);
}
