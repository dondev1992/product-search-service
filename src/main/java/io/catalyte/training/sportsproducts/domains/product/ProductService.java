package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import org.springframework.data.domain.Page;

public interface ProductService {

  Page<Product> getProducts(Product product, Integer pageNumber, Integer pageSize);

  Product getProductById(Long id);

  List<String> getDistinctValues(String value);

  List<Product> findNewestByAll(String demographic, String category, String type);

  Page<Product> searchProducts(String query, Integer pageNumber, Integer pageSize);
}
