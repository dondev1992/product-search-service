package io.catalyte.training.sportsproducts.domains.product;

import static io.catalyte.training.sportsproducts.domains.product.Demographic.Men;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

  private static final ObjectMapper om = new ObjectMapper();
  @Autowired
  private ProductRepository productRepository;
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private WebApplicationContext wac;
  private Product product;

  @Before
  public void before() {
    mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    product = new Product(BigDecimal.ONE, "a", "a", Men, "a", "a", "a");
    product = productRepository.save(product);
  }

  @Test
  public void getProductByIdStatusTest() throws Exception {
    mockMvc.perform(get("/products/" + product.getId()))
        .andExpect(jsonPath("$.id").value(product.getId()))
        .andExpect(jsonPath("$.name").value("a"))
        .andExpect(jsonPath("$.description").value("a"))
        .andExpect(jsonPath("$.category").value("a"))
        .andExpect(jsonPath("$.type").value("a"))
        .andExpect(jsonPath("$.releaseDate").value("a"))
        .andExpect(status().isOk()) // STATUS
        .andExpect(content().contentType("application/json;charset=UTF-8")); // CONTENT TYPE;
  }

  @Test
  public void getProductByInvalidIdStatusTest() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/products/111111"))
        .andReturn();

    String type = mvcResult.getResponse().getContentType();
    MockHttpServletResponse response = mvcResult.getResponse();
    assertEquals(404, response.getStatus()); // STATUS
    assertNull(type); // CONTENT TYPE
  }
}