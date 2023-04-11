package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

  Page<Product> findAll(Example<Product> productExample, Pageable page);

  @Query("SELECT DISTINCT demographic from Product")
  List<String> findDistinctDemographics();

  @Query("SELECT DISTINCT category from Product")
  List<String> findDistinctCategories();

  @Query("SELECT DISTINCT type from Product")
  List<String> findDistinctTypes();

  List<Product> findAll(Example<Product> productExample);
}
