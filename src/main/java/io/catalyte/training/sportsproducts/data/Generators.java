package io.catalyte.training.sportsproducts.data;

import io.catalyte.training.sportsproducts.domains.product.Demographic;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.RandomStringUtils;

public class Generators {

  private static final String[] colors = {
      "#000000", // white
      "#ffffff", // black
      "#39add1", // light blue
      "#3079ab", // dark blue
      "#c25975", // mauve
      "#e15258", // red
      "#f9845b", // orange
      "#838cc7", // lavender
      "#7d669e", // purple
      "#53bbb4", // aqua
      "#51b46d", // green
      "#e0ab18", // mustard
      "#637a91", // dark gray
      "#f092b0", // pink
      "#b7c0c7"  // light gray
  };

  private static final String[] categories = {
      "Golf",
      "Soccer",
      "Basketball",
      "Hockey",
      "Football",
      "Running",
      "Baseball",
      "Skateboarding",
      "Boxing",
      "Weightlifting"
  };

  private static final String[] adjectives = {
      "Lightweight",
      "Slim",
      "Shock Absorbing",
      "Exotic",
      "Elastic",
      "Fashionable",
      "Trendy",
      "Next Gen",
      "Colorful",
      "Comfortable",
      "Water Resistant",
      "Wicking",
      "Heavy Duty"
  };

  private static final String[] types = {
      "Pant",
      "Short",
      "Shoe",
      "Glove",
      "Jacket",
      "Tank Top",
      "Sock",
      "Sunglasses",
      "Hat",
      "Helmet",
      "Belt",
      "Visor",
      "Shin Guard",
      "Elbow Pad",
      "Headband",
      "Wristband",
      "Hoodie",
      "Flip Flop",
      "Pool Noodle"
  };

  public static Demographic getDemographic() {
    Random randomGenerator = new Random();
    int x = randomGenerator.nextInt(Demographic.class.getEnumConstants().length);
    return Demographic.class.getEnumConstants()[x];
  }

  public static String getType() {
    Random randomGenerator = new Random();
    return types[randomGenerator.nextInt(types.length)];
  }

  public static String getAdjective() {
    Random randomGenerator = new Random();
    return adjectives[randomGenerator.nextInt(adjectives.length)];
  }

  public static String getCategory() {
    Random randomGenerator = new Random();
    return categories[randomGenerator.nextInt(categories.length)];
  }

  public static String getColorCode() {
    Random randomGenerator = new Random();
    return colors[randomGenerator.nextInt(colors.length)];
  }

  public static BigDecimal getPrice() {
    Random gen = new Random();
    return new BigDecimal(19.99 + (129.99 - 19.99) * gen.nextDouble())
        .setScale(2, BigDecimal.ROUND_HALF_UP);
  }

  public static String getRandomProductId() {
    return "po-" + RandomStringUtils.random(7, false, true);
  }

  public static String getStyleCode() {
    return "sc" + RandomStringUtils.random(5, false, true);

  }

  private static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
    long startEpochDay = startInclusive.toEpochDay();
    long endEpochDay = endExclusive.toEpochDay();
    long randomDay = ThreadLocalRandom
        .current()
        .nextLong(startEpochDay, endEpochDay);

    return LocalDate.ofEpochDay(randomDay);
  }

  public static LocalDate getReleaseDate() {
    LocalDate start = LocalDate.of(2017, Month.JANUARY, 1);
    LocalDate end = LocalDate.now();
    return between(start, end);
  }

}
