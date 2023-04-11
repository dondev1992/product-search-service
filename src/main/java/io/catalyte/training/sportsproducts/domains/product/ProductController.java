package io.catalyte.training.sportsproducts.domains.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/products")
public class ProductController {

  @Autowired
  private ProductService productService;

  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public Page<Product> getProducts(Product product,
      @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
    return productService.getProducts(product, pageNumber, pageSize);
  }

  @GetMapping(value = "/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<Product> getProductById(@PathVariable Long id) {
    return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
  }

  @GetMapping(value = "/distinctValues/{value}")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<List<String>> getDistinctValues(@PathVariable String value) {
    return new ResponseEntity<>(productService.getDistinctValues(value), HttpStatus.OK);
  }

  @GetMapping(value = "/types/newest")
  @ResponseStatus(value = HttpStatus.OK)
  public ResponseEntity<List<Product>> getNewestByType(@RequestParam(required=false) String demographic, @RequestParam(required=false) String category, @RequestParam(required=false) String type) {
    return new ResponseEntity<>(productService.findNewestByAll(demographic, category, type), HttpStatus.OK);
  }

}
