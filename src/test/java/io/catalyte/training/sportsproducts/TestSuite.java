package io.catalyte.training.sportsproducts;

import io.catalyte.training.sportsproducts.domains.product.ProductControllerTest;
import io.catalyte.training.sportsproducts.domains.product.ProductServiceImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ProductControllerTest.class, ProductServiceImplTest.class})
public class TestSuite {

}
