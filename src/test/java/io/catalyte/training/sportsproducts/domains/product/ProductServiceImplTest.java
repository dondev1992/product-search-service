package io.catalyte.training.sportsproducts.domains.product;

import static io.catalyte.training.sportsproducts.domains.product.Demographic.Women;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import io.catalyte.training.sportsproducts.exceptions.ServiceUnavailable;
import io.catalyte.training.sportsproducts.exceptions.Status404;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

  @Mock
  ProductRepository mockProductRepository;

  ProductServiceImpl productService;

  @Before
  public void before() {
    productService = new ProductServiceImpl(mockProductRepository);
  }

  @After
  public void after() {
    mockProductRepository.deleteAll();
  }

  @Test
  public void getProductsTest() {
    List<Product> products = new ArrayList<>();
    Product product = new Product(BigDecimal.ONE, "a", "a", Women, "a", "a", "a");
    product.setId(1L);
    products.add(product);
    when(mockProductRepository.findAll(Example.of(product))).thenReturn(products);
    List<Product> productsTest = productService.getProducts(product);
    assertEquals(products, productsTest);
  }

  @Test
  public void getProductByIdTest() {
    Long id = 1L;
    when(mockProductRepository.findById(id)).thenReturn(Optional.of(new Product()));
    Product productTest = productService.getProductById(id);
    assertNotNull(productService);
  }

  @Test(expected = Status404.class)
  public void getProductByIdThrowsExceptionTest() {
    Long id = 1L;
    Optional<Product> product = mockProductRepository.findById(id);
    when(mockProductRepository.findById(id)).thenReturn(product);
    Product productTest = productService.getProductById(id);
  }

  @Test(expected = ServiceUnavailable.class)
  public void getProductsTestDAE() {
    Product product = new Product();
    when(mockProductRepository.findAll(Example.of(product)))
        .thenThrow(new DataAccessException("...") {
        });
    productService.getProducts(product);
  }

  @Test(expected = ServiceUnavailable.class)
  public void getProductsByIdTestDAE() {
    when(mockProductRepository.findById(any(Long.class))).thenThrow(new DataAccessException("...") {
    });
    productService.getProductById(1L);
  }
}