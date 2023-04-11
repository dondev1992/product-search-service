package io.catalyte.training.sportsproducts.domains.product;

import io.catalyte.training.sportsproducts.exceptions.ServiceUnavailable;
import io.catalyte.training.sportsproducts.exceptions.Status404;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService {

  private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

  private ProductRepository productRepository;

  @Autowired
  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public Page<Product> getProducts(Product product, Integer pageNumber, Integer pageSize) {
    Pageable pageRequest = PageRequest.of(pageNumber, pageSize);

    if (product.getCategory() == null && product.getType() == null
        && product.getDemographic() == null) {
      throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED,
          "query expected, server will not return all products");
    }

    try {
      return productRepository.findAll(Example.of(product), pageRequest);
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

  public List<String> getDistinctValues(String value) {

    List<String> distinctFromDb = new ArrayList<>();

    try {
      switch (value) {
        case "demographics":
          distinctFromDb.addAll(productRepository.findDistinctDemographics());
          break;
        case "categories":
          distinctFromDb.addAll(productRepository.findDistinctCategories());
          break;
        case "types":
          distinctFromDb.addAll(productRepository.findDistinctTypes());
          break;
      }
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable();
    }

    Collections.sort(distinctFromDb);
    return distinctFromDb;

  }

  @Override
  public List<Product> findNewestByAll(String demographic, String category, String type) {
    Product product = new Product();

    product.setDemographic(demographic);
    product.setCategory(category);
    product.setType(type);

    List<Product> productList;
    try {
      productList = productRepository.findAll(Example.of(product));
    } catch (DataAccessException e) {
      logger.error(e.getMessage());
      throw new ServiceUnavailable();
    }

    return productList.stream()
        .sorted((Comparator.comparing(Product::getReleaseDate)))
        .limit(5)
        .collect(Collectors.toList());
  }
}