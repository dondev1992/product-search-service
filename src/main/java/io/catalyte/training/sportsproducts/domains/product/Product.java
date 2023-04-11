package io.catalyte.training.sportsproducts.domains.product;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private BigDecimal price;

  @NotBlank
  private String name;

  @NotBlank
  private String description;

  @NotBlank
  private String demographic;

  @NotBlank
  private String category;

  @NotBlank
  private String type;

  @NotBlank
  private String releaseDate;

  private String primaryColorCode;

  private String secondaryColorCode;

  private String styleNumber;

  private String globalProductCode;

  public Product() {
  }

  public Product(@NotNull BigDecimal price,
      @NotBlank String name, @NotBlank String description,
      String demographic, @NotBlank String category,
      @NotBlank String type, @NotBlank String releaseDate) {
    this.price = price;
    this.name = name;
    this.description = description;
    this.demographic = demographic;
    this.category = category;
    this.type = type;
    this.releaseDate = releaseDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDemographic() {
    return demographic;
  }

  public void setDemographic(String demographic) {
    this.demographic = demographic;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getPrimaryColorCode() {
    return primaryColorCode;
  }

  public void setPrimaryColorCode(String primaryColorCode) {
    this.primaryColorCode = primaryColorCode;
  }

  public String getSecondaryColorCode() {
    return secondaryColorCode;
  }

  public void setSecondaryColorCode(String secondaryColorCode) {
    this.secondaryColorCode = secondaryColorCode;
  }

  public String getStyleNumber() {
    return styleNumber;
  }

  public void setStyleNumber(String styleNumber) {
    this.styleNumber = styleNumber;
  }

  public String getGlobalProductCode() {
    return globalProductCode;
  }

  public void setGlobalProductCode(String globalProductCode) {
    this.globalProductCode = globalProductCode;
  }

  @Override
  public String toString() {
    return "Product{" +
        "id=" + id +
        ", price=" + price +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", demographic=" + demographic +
        ", category='" + category + '\'' +
        ", type='" + type + '\'' +
        ", releaseDate='" + releaseDate + '\'' +
        ", primaryColorCode='" + primaryColorCode + '\'' +
        ", secondaryColorCode='" + secondaryColorCode + '\'' +
        ", styleNumber='" + styleNumber + '\'' +
        ", globalProductCode='" + globalProductCode + '\'' +
        '}';
  }
}
