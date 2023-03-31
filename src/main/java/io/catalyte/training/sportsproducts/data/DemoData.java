package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.product.Demographic;
import io.catalyte.training.sportsproducts.domains.product.Product;
import io.catalyte.training.sportsproducts.domains.product.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class DemoData implements CommandLineRunner {

  private static final String SPACE = " ";
  private final Logger logger = LoggerFactory.getLogger(DemoData.class);
  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private Environment env;

  @Override
  public void run(String... strings) {
    String envVarNumber = env.getProperty("products.number");
    final int NUMBER_OF_PRODUCTS = (envVarNumber == null) ? 500 : Integer.parseInt(envVarNumber);

    logger.info("Loading " + NUMBER_OF_PRODUCTS + " products...");

    List<Product> productList = new ArrayList<>();

    for (int i = 0; i < NUMBER_OF_PRODUCTS; i++) {
      productList.add(createRandomProduct());
    }

    productRepository.saveAll(productList);

    logger.info("Data loader completed. You can make requests now.");
  }


  private Product createRandomProduct() {
    Product product = new Product();
    String category = Generators.getCategory();
    String adjective = Generators.getAdjective();
    String type = Generators.getType();
    Demographic demographic = Generators.getDemographic();

    product.setPrice(Generators.getPrice());
    product.setDescription("This " + category.toLowerCase() + SPACE
        + type.toLowerCase()
        + " for " + demographic.toString().toLowerCase()
        + " is extremely " + adjective.toLowerCase()
        + ". You're going to love it!");
    product.setCategory(category);
    product.setName(adjective + SPACE + category + SPACE + type);
    product.setType(type);
    product.setDemographic(demographic);
    product.setPrimaryColorCode(Generators.getColorCode());
    product.setSecondaryColorCode(Generators.getColorCode());
    product.setGlobalProductCode(Generators.getRandomProductId());
    product.setStyleNumber(Generators.getStyleCode());
    product.setReleaseDate(Generators.getReleaseDate().toString());
    return product;
  }

//  private void loadProducts() {
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(125.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with fine mesh.",
//            Demographic.Men,
//            "Golf",
//            "Shoes",
//            309,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2017-02-19T12:12:03 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(133.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with polyester cooling.",
//            Demographic.Men,
//            "Basketball",
//            "Shirt",
//            41,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2018-01-14T11:11:19 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(89.99),
//            "Very Athletic Pants",
//            "Description of very athletic pants with architectural breezeways.",
//            Demographic.Men,
//            "Golf",
//            "Pants",
//            836,
//            "https://cdn11.bigcommerce.com/s-fpinx/images/stencil/1280x1280/products/273550/269680/flamingogardens-mens-pant-hr-1__38475.1554913985.jpg?c=2&imbypass=on",
//            "2017-07-02T05:42:50 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(271.99),
//            "Very Cool Shoe",
//            "Description of very cool shoe with self lacing abilities.",
//            Demographic.Men,
//            "Basketball",
//            "Shoe",
//            234,
//            "https://pmcfootwearnews.files.wordpress.com/2017/03/lebron-soldier-xii-basketball-shoejpg1.jpg?w=683",
//            "2016-03-29T05:28:42 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(389.99),
//            "Very Cool Shirt",
//            "Description of very cool shirt with actual armour underneath",
//            Demographic.Men,
//            "Soccer",
//            "Shirt",
//            577,
//            "https://images-na.ssl-images-amazon.com/images/I/91PmZ4Ay%2BVL._UL1500_.jpg",
//            "2015-05-31T11:58:02 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(53.99),
//            "Very Cool Pants",
//            "Description of very cool pants with structured creases and foldaways.",
//            Demographic.Men,
//            "Golf",
//            "Pants",
//            891,
//            "https://www.tipsyelves.com/mas_assets/cache/image/1/1/3/275/Banana-golf-pants_copy.Jpg",
//            "2018-05-31T02:12:35 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(381.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with turf traction.",
//            Demographic.Men,
//            "Soccer",
//            "Shoes",
//            543,
//            "https://images.nikeshoeszone.com/images/201809/uploaded/7255f783af1f852d3c64a880194e873b.jpg",
//            "2016-03-03T04:44:45 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(91.99),
//            "Very Athletic Shorts",
//            "Description of very athletic shorts with wipeaway abilities for spills during fine dining.",
//            Demographic.Men,
//            "Running",
//            "Pants",
//            872,
//            "https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/w3vefnhstblgfjz4rzzp/phenom-mens-knit-running-pants-B5nhrW.jpg",
//            "2018-01-31T09:45:36 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(299.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with gold encrusted numbers and literal dollars flying out of it.",
//            Demographic.Men,
//            "Running",
//            "Shirt",
//            499,
//            "http://2sa1o8i32q-flywheel.netdna-ssl.com/wp-content/uploads/2014/04/Stayton-Royal-Vented-Merino-Wool-Running-Shirt-510x600.jpg",
//            "2017-05-19T12:32:37 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(423.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that targets a specific demographic with targeted political messaging for post consumerist third stage capitalist rejects",
//            Demographic.Men,
//            "Basketball",
//            "Shoes",
//            406,
//            "https://eyeconicwear.com/wp-content/uploads/2018/05/EyeConicWear-air-jordan-3-katrina-2018-hall-of-fame.jpg",
//            "2017-12-14T04:04:15 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(412.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that helps one jump twice their body height in less than a microsecond.",
//            Demographic.Men,
//            "Golf",
//            "Shoes",
//            943,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2015-11-10T06:02:16 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(285.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt that is nice, breezy, like a beach walk with a pomeranian puppy that won't stop kissing you on the cheek",
//            Demographic.Men,
//            "Basketball",
//            "Shirt",
//            336,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2017-03-26T07:42:34 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(125.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with fine mesh.",
//            Demographic.Men,
//            "Golf",
//            "Shoes",
//            309,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2017-02-19T12:12:03 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(133.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with polyester cooling.",
//            Demographic.Men,
//            "Basketball",
//            "Shirt",
//            41,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2018-01-14T11:11:19 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(89.99),
//            "Very Athletic Pants",
//            "Description of very athletic pants with architectural breezeways.",
//            Demographic.Men,
//            "Golf",
//            "Pants",
//            836,
//            "https://cdn11.bigcommerce.com/s-fpinx/images/stencil/1280x1280/products/273550/269680/flamingogardens-mens-pant-hr-1__38475.1554913985.jpg?c=2&imbypass=on",
//            "2017-07-02T05:42:50 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(271.99),
//            "Very Cool Shoe",
//            "Description of very cool shoe with self lacing abilities.",
//            Demographic.Men,
//            "Basketball",
//            "Shoe",
//            234,
//            "https://pmcfootwearnews.files.wordpress.com/2017/03/lebron-soldier-xii-basketball-shoejpg1.jpg?w=683",
//            "2016-03-29T05:28:42 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(389.99),
//            "Very Cool Shirt",
//            "Description of very cool shirt with actual armour underneath",
//            Demographic.Men,
//            "Soccer",
//            "Shirt",
//            577,
//            "https://images-na.ssl-images-amazon.com/images/I/91PmZ4Ay%2BVL._UL1500_.jpg",
//            "2015-05-31T11:58:02 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(53.99),
//            "Very Cool Pants",
//            "Description of very cool pants with structured creases and foldaways.",
//            Demographic.Men,
//            "Golf",
//            "Pants",
//            891,
//            "https://www.tipsyelves.com/mas_assets/cache/image/1/1/3/275/Banana-golf-pants_copy.Jpg",
//            "2018-05-31T02:12:35 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(381.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with turf traction.",
//            Demographic.Men,
//            "Soccer",
//            "Shoes",
//            543,
//            "https://images.nikeshoeszone.com/images/201809/uploaded/7255f783af1f852d3c64a880194e873b.jpg",
//            "2016-03-03T04:44:45 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(91.99),
//            "Very Athletic Shorts",
//            "Description of very athletic shorts with wipeaway abilities for spills during fine dining.",
//            Demographic.Men,
//            "Running",
//            "Pants",
//            872,
//            "https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/w3vefnhstblgfjz4rzzp/phenom-mens-knit-running-pants-B5nhrW.jpg",
//            "2018-01-31T09:45:36 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(299.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with gold encrusted numbers and literal dollars flying out of it.",
//            Demographic.Men,
//            "Running",
//            "Shirt",
//            499,
//            "http://2sa1o8i32q-flywheel.netdna-ssl.com/wp-content/uploads/2014/04/Stayton-Royal-Vented-Merino-Wool-Running-Shirt-510x600.jpg",
//            "2017-05-19T12:32:37 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(423.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that targets a specific demographic with targeted political messaging for post consumerist third stage capitalist rejects",
//            Demographic.Men,
//            "Basketball",
//            "Shoes",
//            406,
//            "https://eyeconicwear.com/wp-content/uploads/2018/05/EyeConicWear-air-jordan-3-katrina-2018-hall-of-fame.jpg",
//            "2017-12-14T04:04:15 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(412.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that helps one jump twice their body height in less than a microsecond.",
//            Demographic.Men,
//            "Golf",
//            "Shoes",
//            943,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2015-11-10T06:02:16 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(285.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt that is nice, breezy, like a beach walk with a pomeranian puppy that won't stop kissing you on the cheek",
//            Demographic.Men,
//            "Basketball",
//            "Shirt",
//            336,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2017-03-26T07:42:34 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(125.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with fine mesh.",
//            Demographic.Men,
//            "Golf",
//            "Shoes",
//            309,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2017-02-19T12:12:03 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(133.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with polyester cooling.",
//            Demographic.Men,
//            "Basketball",
//            "Shirt",
//            41,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2018-01-14T11:11:19 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(89.99),
//            "Very Athletic Pants",
//            "Description of very athletic pants with architectural breezeways.",
//            Demographic.Men,
//            "Golf",
//            "Pants",
//            836,
//            "https://cdn11.bigcommerce.com/s-fpinx/images/stencil/1280x1280/products/273550/269680/flamingogardens-mens-pant-hr-1__38475.1554913985.jpg?c=2&imbypass=on",
//            "2017-07-02T05:42:50 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(271.99),
//            "Very Cool Shoe",
//            "Description of very cool shoe with self lacing abilities.",
//            Demographic.Men,
//            "Basketball",
//            "Shoe",
//            234,
//            "https://pmcfootwearnews.files.wordpress.com/2017/03/lebron-soldier-xii-basketball-shoejpg1.jpg?w=683",
//            "2016-03-29T05:28:42 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(389.99),
//            "Very Cool Shirt",
//            "Description of very cool shirt with actual armour underneath",
//            Demographic.Men,
//            "Soccer",
//            "Shirt",
//            577,
//            "https://images-na.ssl-images-amazon.com/images/I/91PmZ4Ay%2BVL._UL1500_.jpg",
//            "2015-05-31T11:58:02 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(53.99),
//            "Very Cool Pants",
//            "Description of very cool pants with structured creases and foldaways.",
//            Demographic.Men,
//            "Golf",
//            "Pants",
//            891,
//            "https://www.tipsyelves.com/mas_assets/cache/image/1/1/3/275/Banana-golf-pants_copy.Jpg",
//            "2018-05-31T02:12:35 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(381.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with turf traction.",
//            Demographic.Men,
//            "Soccer",
//            "Shoes",
//            543,
//            "https://images.nikeshoeszone.com/images/201809/uploaded/7255f783af1f852d3c64a880194e873b.jpg",
//            "2016-03-03T04:44:45 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(91.99),
//            "Very Athletic Shorts",
//            "Description of very athletic shorts with wipeaway abilities for spills during fine dining.",
//            Demographic.Men,
//            "Running",
//            "Pants",
//            872,
//            "https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/w3vefnhstblgfjz4rzzp/phenom-mens-knit-running-pants-B5nhrW.jpg",
//            "2018-01-31T09:45:36 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(299.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with gold encrusted numbers and literal dollars flying out of it.",
//            Demographic.Men,
//            "Running",
//            "Shirt",
//            499,
//            "http://2sa1o8i32q-flywheel.netdna-ssl.com/wp-content/uploads/2014/04/Stayton-Royal-Vented-Merino-Wool-Running-Shirt-510x600.jpg",
//            "2017-05-19T12:32:37 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(423.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that targets a specific demographic with targeted political messaging for post consumerist third stage capitalist rejects",
//            Demographic.Men,
//            "Basketball",
//            "Shoes",
//            406,
//            "https://eyeconicwear.com/wp-content/uploads/2018/05/EyeConicWear-air-jordan-3-katrina-2018-hall-of-fame.jpg",
//            "2017-12-14T04:04:15 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(412.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that helps one jump twice their body height in less than a microsecond.",
//            Demographic.Men,
//            "Golf",
//            "Shoes",
//            943,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2015-11-10T06:02:16 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(285.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt that is nice, breezy, like a beach walk with a pomeranian puppy that won't stop kissing you on the cheek",
//            Demographic.Men,
//            "Basketball",
//            "Shirt",
//            336,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2017-03-26T07:42:34 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(218.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with fine mesh.",
//            Demographic.Women,
//            "Golf",
//            "Shoes",
//            315,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2015-11-24T10:50:35 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(353.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with polyester cooling.",
//            Demographic.Women,
//            "Basketball",
//            "Shirt",
//            587,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2015-06-11T03:11:12 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(279.99),
//            "Very Athletic Pants",
//            "Description of very athletic pants with architectural breezeways.",
//            Demographic.Women,
//            "Golf",
//            "Pants",
//            107,
//            "https://cdn11.bigcommerce.com/s-fpinx/images/stencil/1280x1280/products/273550/269680/flamingogardens-mens-pant-hr-1__38475.1554913985.jpg?c=2&imbypass=on",
//            "2019-03-28T04:33:13 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(352.99),
//            "Very Cool Shoe",
//            "Description of very cool shoe with self lacing abilities.",
//            Demographic.Women,
//            "Basketball",
//            "Shoe",
//            169,
//            "https://pmcfootwearnews.files.wordpress.com/2017/03/lebron-soldier-xii-basketball-shoejpg1.jpg?w=683",
//            "2015-03-12T11:22:47 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(206.99),
//            "Very Cool Shirt",
//            "Description of very cool shirt with actual armour underneath",
//            Demographic.Women,
//            "Soccer",
//            "Shirt",
//            986,
//            "https://images-na.ssl-images-amazon.com/images/I/91PmZ4Ay%2BVL._UL1500_.jpg",
//            "2014-10-30T05:46:38 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(52.99),
//            "Very Cool Pants",
//            "Description of very cool pants with structured creases and foldaways.",
//            Demographic.Women,
//            "Golf",
//            "Pants",
//            56,
//            "https://www.tipsyelves.com/mas_assets/cache/image/1/1/3/275/Banana-golf-pants_copy.Jpg",
//            "2018-06-30T11:07:15 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(165.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with turf traction.",
//            Demographic.Women,
//            "Soccer",
//            "Shoes",
//            841,
//            "https://images.nikeshoeszone.com/images/201809/uploaded/7255f783af1f852d3c64a880194e873b.jpg",
//            "2018-04-21T08:24:34 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(387.99),
//            "Very Athletic Shorts",
//            "Description of very athletic shorts with wipeaway abilities for spills during fine dining.",
//            Demographic.Women,
//            "Running",
//            "Pants",
//            566,
//            "https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/w3vefnhstblgfjz4rzzp/phenom-mens-knit-running-pants-B5nhrW.jpg",
//            "2015-12-07T08:11:08 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(52.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with gold encrusted numbers and literal dollars flying out of it.",
//            Demographic.Women,
//            "Running",
//            "Shirt",
//            468,
//            "http://2sa1o8i32q-flywheel.netdna-ssl.com/wp-content/uploads/2014/04/Stayton-Royal-Vented-Merino-Wool-Running-Shirt-510x600.jpg",
//            "2015-07-11T09:34:42 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(88.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that targets a specific demographic with targeted political messaging for post consumerist third stage capitalist rejects",
//            Demographic.Women,
//            "Basketball",
//            "Shoes",
//            482,
//            "https://eyeconicwear.com/wp-content/uploads/2018/05/EyeConicWear-air-jordan-3-katrina-2018-hall-of-fame.jpg",
//            "2019-01-25T08:16:41 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(284.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that helps one jump twice their body height in less than a microsecond.",
//            Demographic.Women,
//            "Golf",
//            "Shoes",
//            397,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2018-09-18T05:49:09 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(186.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt that is nice, breezy, like a beach walk with a pomeranian puppy that won't stop kissing you on the cheek",
//            Demographic.Women,
//            "Basketball",
//            "Shirt",
//            501,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2016-04-13T05:02:46 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(218.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with fine mesh.",
//            Demographic.Women,
//            "Golf",
//            "Shoes",
//            315,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2015-11-24T10:50:35 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(353.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with polyester cooling.",
//            Demographic.Women,
//            "Basketball",
//            "Shirt",
//            587,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2015-06-11T03:11:12 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(279.99),
//            "Very Athletic Pants",
//            "Description of very athletic pants with architectural breezeways.",
//            Demographic.Women,
//            "Golf",
//            "Pants",
//            107,
//            "https://cdn11.bigcommerce.com/s-fpinx/images/stencil/1280x1280/products/273550/269680/flamingogardens-mens-pant-hr-1__38475.1554913985.jpg?c=2&imbypass=on",
//            "2019-03-28T04:33:13 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(352.99),
//            "Very Cool Shoe",
//            "Description of very cool shoe with self lacing abilities.",
//            Demographic.Women,
//            "Basketball",
//            "Shoe",
//            169,
//            "https://pmcfootwearnews.files.wordpress.com/2017/03/lebron-soldier-xii-basketball-shoejpg1.jpg?w=683",
//            "2015-03-12T11:22:47 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(206.99),
//            "Very Cool Shirt",
//            "Description of very cool shirt with actual armour underneath",
//            Demographic.Women,
//            "Soccer",
//            "Shirt",
//            986,
//            "https://images-na.ssl-images-amazon.com/images/I/91PmZ4Ay%2BVL._UL1500_.jpg",
//            "2014-10-30T05:46:38 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(52.99),
//            "Very Cool Pants",
//            "Description of very cool pants with structured creases and foldaways.",
//            Demographic.Women,
//            "Golf",
//            "Pants",
//            56,
//            "https://www.tipsyelves.com/mas_assets/cache/image/1/1/3/275/Banana-golf-pants_copy.Jpg",
//            "2018-06-30T11:07:15 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(165.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with turf traction.",
//            Demographic.Women,
//            "Soccer",
//            "Shoes",
//            841,
//            "https://images.nikeshoeszone.com/images/201809/uploaded/7255f783af1f852d3c64a880194e873b.jpg",
//            "2018-04-21T08:24:34 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(387.99),
//            "Very Athletic Shorts",
//            "Description of very athletic shorts with wipeaway abilities for spills during fine dining.",
//            Demographic.Women,
//            "Running",
//            "Pants",
//            566,
//            "https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/w3vefnhstblgfjz4rzzp/phenom-mens-knit-running-pants-B5nhrW.jpg",
//            "2015-12-07T08:11:08 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(52.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with gold encrusted numbers and literal dollars flying out of it.",
//            Demographic.Women,
//            "Running",
//            "Shirt",
//            468,
//            "http://2sa1o8i32q-flywheel.netdna-ssl.com/wp-content/uploads/2014/04/Stayton-Royal-Vented-Merino-Wool-Running-Shirt-510x600.jpg",
//            "2015-07-11T09:34:42 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(88.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that targets a specific demographic with targeted political messaging for post consumerist third stage capitalist rejects",
//            Demographic.Women,
//            "Basketball",
//            "Shoes",
//            482,
//            "https://eyeconicwear.com/wp-content/uploads/2018/05/EyeConicWear-air-jordan-3-katrina-2018-hall-of-fame.jpg",
//            "2019-01-25T08:16:41 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(284.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that helps one jump twice their body height in less than a microsecond.",
//            Demographic.Women,
//            "Golf",
//            "Shoes",
//            397,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2018-09-18T05:49:09 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(186.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt that is nice, breezy, like a beach walk with a pomeranian puppy that won't stop kissing you on the cheek",
//            Demographic.Women,
//            "Basketball",
//            "Shirt",
//            501,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2016-04-13T05:02:46 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(218.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with fine mesh.",
//            Demographic.Women,
//            "Golf",
//            "Shoes",
//            315,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2015-11-24T10:50:35 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(353.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with polyester cooling.",
//            Demographic.Women,
//            "Basketball",
//            "Shirt",
//            587,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2015-06-11T03:11:12 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(279.99),
//            "Very Athletic Pants",
//            "Description of very athletic pants with architectural breezeways.",
//            Demographic.Women,
//            "Golf",
//            "Pants",
//            107,
//            "https://cdn11.bigcommerce.com/s-fpinx/images/stencil/1280x1280/products/273550/269680/flamingogardens-mens-pant-hr-1__38475.1554913985.jpg?c=2&imbypass=on",
//            "2019-03-28T04:33:13 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(352.99),
//            "Very Cool Shoe",
//            "Description of very cool shoe with self lacing abilities.",
//            Demographic.Women,
//            "Basketball",
//            "Shoe",
//            169,
//            "https://pmcfootwearnews.files.wordpress.com/2017/03/lebron-soldier-xii-basketball-shoejpg1.jpg?w=683",
//            "2015-03-12T11:22:47 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(206.99),
//            "Very Cool Shirt",
//            "Description of very cool shirt with actual armour underneath",
//            Demographic.Women,
//            "Soccer",
//            "Shirt",
//            986,
//            "https://images-na.ssl-images-amazon.com/images/I/91PmZ4Ay%2BVL._UL1500_.jpg",
//            "2014-10-30T05:46:38 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(52.99),
//            "Very Cool Pants",
//            "Description of very cool pants with structured creases and foldaways.",
//            Demographic.Women,
//            "Golf",
//            "Pants",
//            56,
//            "https://www.tipsyelves.com/mas_assets/cache/image/1/1/3/275/Banana-golf-pants_copy.Jpg",
//            "2018-06-30T11:07:15 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(165.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with turf traction.",
//            Demographic.Women,
//            "Soccer",
//            "Shoes",
//            841,
//            "https://images.nikeshoeszone.com/images/201809/uploaded/7255f783af1f852d3c64a880194e873b.jpg",
//            "2018-04-21T08:24:34 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(387.99),
//            "Very Athletic Shorts",
//            "Description of very athletic shorts with wipeaway abilities for spills during fine dining.",
//            Demographic.Women,
//            "Running",
//            "Pants",
//            566,
//            "https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/w3vefnhstblgfjz4rzzp/phenom-mens-knit-running-pants-B5nhrW.jpg",
//            "2015-12-07T08:11:08 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(52.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with gold encrusted numbers and literal dollars flying out of it.",
//            Demographic.Women,
//            "Running",
//            "Shirt",
//            468,
//            "http://2sa1o8i32q-flywheel.netdna-ssl.com/wp-content/uploads/2014/04/Stayton-Royal-Vented-Merino-Wool-Running-Shirt-510x600.jpg",
//            "2015-07-11T09:34:42 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(88.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that targets a specific demographic with targeted political messaging for post consumerist third stage capitalist rejects",
//            Demographic.Women,
//            "Basketball",
//            "Shoes",
//            482,
//            "https://eyeconicwear.com/wp-content/uploads/2018/05/EyeConicWear-air-jordan-3-katrina-2018-hall-of-fame.jpg",
//            "2019-01-25T08:16:41 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(284.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that helps one jump twice their body height in less than a microsecond.",
//            Demographic.Women,
//            "Golf",
//            "Shoes",
//            397,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2018-09-18T05:49:09 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(186.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt that is nice, breezy, like a beach walk with a pomeranian puppy that won't stop kissing you on the cheek",
//            Demographic.Women,
//            "Basketball",
//            "Shirt",
//            501,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2016-04-13T05:02:46 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(241.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with fine mesh.",
//            Demographic.Kids,
//            "Golf",
//            "Shoes",
//            524,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2017-07-28T08:36:00 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(151.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with polyester cooling.",
//            Demographic.Kids,
//            "Basketball",
//            "Shirt",
//            932,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2019-12-16T09:58:43 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(258.99),
//            "Very Athletic Pants",
//            "Description of very athletic pants with architectural breezeways.",
//            Demographic.Kids,
//            "Golf",
//            "Pants",
//            924,
//            "https://cdn11.bigcommerce.com/s-fpinx/images/stencil/1280x1280/products/273550/269680/flamingogardens-mens-pant-hr-1__38475.1554913985.jpg?c=2&imbypass=on",
//            "2019-01-26T06:16:02 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(252.99),
//            "Very Cool Shoe",
//            "Description of very cool shoe with self lacing abilities.",
//            Demographic.Kids,
//            "Basketball",
//            "Shoe",
//            643,
//            "https://pmcfootwearnews.files.wordpress.com/2017/03/lebron-soldier-xii-basketball-shoejpg1.jpg?w=683",
//            "2019-05-23T07:55:05 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(144.99),
//            "Very Cool Shirt",
//            "Description of very cool shirt with actual armour underneath",
//            Demographic.Kids,
//            "Soccer",
//            "Shirt",
//            475,
//            "https://images-na.ssl-images-amazon.com/images/I/91PmZ4Ay%2BVL._UL1500_.jpg",
//            "2017-04-08T03:18:39 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(244.99),
//            "Very Cool Pants",
//            "Description of very cool pants with structured creases and foldaways.",
//            Demographic.Kids,
//            "Golf",
//            "Pants",
//            970,
//            "https://www.tipsyelves.com/mas_assets/cache/image/1/1/3/275/Banana-golf-pants_copy.Jpg",
//            "2016-07-18T05:50:18 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(409.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with turf traction.",
//            Demographic.Kids,
//            "Soccer",
//            "Shoes",
//            244,
//            "https://images.nikeshoeszone.com/images/201809/uploaded/7255f783af1f852d3c64a880194e873b.jpg",
//            "2016-03-20T10:09:39 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(168.99),
//            "Very Athletic Shorts",
//            "Description of very athletic shorts with wipeaway abilities for spills during fine dining.",
//            Demographic.Kids,
//            "Running",
//            "Pants",
//            943,
//            "https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/w3vefnhstblgfjz4rzzp/phenom-mens-knit-running-pants-B5nhrW.jpg",
//            "2018-03-06T10:28:13 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(119.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with gold encrusted numbers and literal dollars flying out of it.",
//            Demographic.Kids,
//            "Running",
//            "Shirt",
//            498,
//            "http://2sa1o8i32q-flywheel.netdna-ssl.com/wp-content/uploads/2014/04/Stayton-Royal-Vented-Merino-Wool-Running-Shirt-510x600.jpg",
//            "2017-12-26T11:03:55 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(171.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that targets a specific demographic with targeted political messaging for post consumerist third stage capitalist rejects",
//            Demographic.Kids,
//            "Basketball",
//            "Shoes",
//            281,
//            "https://eyeconicwear.com/wp-content/uploads/2018/05/EyeConicWear-air-jordan-3-katrina-2018-hall-of-fame.jpg",
//            "2015-02-08T04:49:03 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(425.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that helps one jump twice their body height in less than a microsecond.",
//            Demographic.Kids,
//            "Golf",
//            "Shoes",
//            137,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2014-04-29T06:28:38 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(424.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt that is nice, breezy, like a beach walk with a pomeranian puppy that won't stop kissing you on the cheek",
//            Demographic.Kids,
//            "Basketball",
//            "Shirt",
//            218,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2016-01-30T02:07:29 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(241.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with fine mesh.",
//            Demographic.Kids,
//            "Golf",
//            "Shoes",
//            524,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2017-07-28T08:36:00 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(151.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with polyester cooling.",
//            Demographic.Kids,
//            "Basketball",
//            "Shirt",
//            932,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2019-12-16T09:58:43 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(258.99),
//            "Very Athletic Pants",
//            "Description of very athletic pants with architectural breezeways.",
//            Demographic.Kids,
//            "Golf",
//            "Pants",
//            924,
//            "https://cdn11.bigcommerce.com/s-fpinx/images/stencil/1280x1280/products/273550/269680/flamingogardens-mens-pant-hr-1__38475.1554913985.jpg?c=2&imbypass=on",
//            "2019-01-26T06:16:02 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(252.99),
//            "Very Cool Shoe",
//            "Description of very cool shoe with self lacing abilities.",
//            Demographic.Kids,
//            "Basketball",
//            "Shoe",
//            643,
//            "https://pmcfootwearnews.files.wordpress.com/2017/03/lebron-soldier-xii-basketball-shoejpg1.jpg?w=683",
//            "2019-05-23T07:55:05 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(144.99),
//            "Very Cool Shirt",
//            "Description of very cool shirt with actual armour underneath",
//            Demographic.Kids,
//            "Soccer",
//            "Shirt",
//            475,
//            "https://images-na.ssl-images-amazon.com/images/I/91PmZ4Ay%2BVL._UL1500_.jpg",
//            "2017-04-08T03:18:39 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(244.99),
//            "Very Cool Pants",
//            "Description of very cool pants with structured creases and foldaways.",
//            Demographic.Kids,
//            "Golf",
//            "Pants",
//            970,
//            "https://www.tipsyelves.com/mas_assets/cache/image/1/1/3/275/Banana-golf-pants_copy.Jpg",
//            "2016-07-18T05:50:18 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(409.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with turf traction.",
//            Demographic.Kids,
//            "Soccer",
//            "Shoes",
//            244,
//            "https://images.nikeshoeszone.com/images/201809/uploaded/7255f783af1f852d3c64a880194e873b.jpg",
//            "2016-03-20T10:09:39 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(168.99),
//            "Very Athletic Shorts",
//            "Description of very athletic shorts with wipeaway abilities for spills during fine dining.",
//            Demographic.Kids,
//            "Running",
//            "Pants",
//            943,
//            "https://c.static-nike.com/a/images/t_PDP_1280_v1/f_auto/w3vefnhstblgfjz4rzzp/phenom-mens-knit-running-pants-B5nhrW.jpg",
//            "2018-03-06T10:28:13 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(119.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with gold encrusted numbers and literal dollars flying out of it.",
//            Demographic.Kids,
//            "Running",
//            "Shirt",
//            498,
//            "http://2sa1o8i32q-flywheel.netdna-ssl.com/wp-content/uploads/2014/04/Stayton-Royal-Vented-Merino-Wool-Running-Shirt-510x600.jpg",
//            "2017-12-26T11:03:55 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(171.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that targets a specific demographic with targeted political messaging for post consumerist third stage capitalist rejects",
//            Demographic.Kids,
//            "Basketball",
//            "Shoes",
//            281,
//            "https://eyeconicwear.com/wp-content/uploads/2018/05/EyeConicWear-air-jordan-3-katrina-2018-hall-of-fame.jpg",
//            "2015-02-08T04:49:03 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(425.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe that helps one jump twice their body height in less than a microsecond.",
//            Demographic.Kids,
//            "Golf",
//            "Shoes",
//            137,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2014-04-29T06:28:38 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(424.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt that is nice, breezy, like a beach walk with a pomeranian puppy that won't stop kissing you on the cheek",
//            Demographic.Kids,
//            "Basketball",
//            "Shirt",
//            218,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2016-01-30T02:07:29 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(241.99),
//            "Very Athletic Shoe",
//            "Description of very athletic shoe with fine mesh.",
//            Demographic.Kids,
//            "Golf",
//            "Shoes",
//            524,
//            "https://dks.scene7.com/is/image/dkscdn/17NIKMVPRSTRMBLCKGSH_Dark_Grey_Gym_Red_Pink_is?wid=685&fmt=jpg",
//            "2017-07-28T08:36:00 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(151.99),
//            "Very Athletic Shirt",
//            "Description of very athletic shirt with polyester cooling.",
//            Demographic.Kids,
//            "Basketball",
//            "Shirt",
//            932,
//            "https://images-na.ssl-images-amazon.com/images/I/61TBtEnjzFL._UX342_.jpg",
//            "2019-12-16T09:58:43 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(258.99),
//            "Very Athletic Pants",
//            "Description of very athletic pants with architectural breezeways.",
//            Demographic.Kids,
//            "Golf",
//            "Pants",
//            924,
//            "https://cdn11.bigcommerce.com/s-fpinx/images/stencil/1280x1280/products/273550/269680/flamingogardens-mens-pant-hr-1__38475.1554913985.jpg?c=2&imbypass=on",
//            "2019-01-26T06:16:02 +08:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(252.99),
//            "Very Cool Shoe",
//            "Description of very cool shoe with self lacing abilities.",
//            Demographic.Kids,
//            "Basketball",
//            "Shoe",
//            643,
//            "https://pmcfootwearnews.files.wordpress.com/2017/03/lebron-soldier-xii-basketball-shoejpg1.jpg?w=683",
//            "2019-05-23T07:55:05 +07:00"));
//
//    productRepository.save(
//        new Product(
//            new BigDecimal(144.99),
//            "Very Cool Shirt",
//            "Description of very cool shirt with actual armour underneath",
//            Demographic.Kids,
//            "Soccer",
//            "Shirt",
//            475,
//            "https://images-na.ssl-images-amazon.com/images/I/91PmZ4Ay%2BVL._UL1500_.jpg",
//            "2017-04-08T03:18:39 +07:00"));
//  }
}
