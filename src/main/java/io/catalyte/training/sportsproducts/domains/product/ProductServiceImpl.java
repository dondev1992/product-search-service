package io.catalyte.training.sportsproducts.domains.product;

import io.catalyte.training.sportsproducts.exceptions.ServiceUnavailable;
import io.catalyte.training.sportsproducts.exceptions.Status404;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
  @Autowired
  ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getProducts(Product product) {
    try {
      return productRepository.findAll(Example.of(product));
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable();
    }
  }

  public Product getProductById(Long id) {
    try {
      Optional<Product> product = productRepository.findById(id);
      if (product.isPresent()) {
        return product.get();
      }
      throw new Status404();
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable();
    }
  }
}