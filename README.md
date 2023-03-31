# Product Search Service

  ### Connections
  By default, this service starts up on 8085.
  By default, this service only accepts cross-origin requests from `localhost:8080`.

  ### Getting Started
  Package the jar and specify flags as desired for your postgres setup.
  
  The `products.number` flag determines how many products are generated and loaded by the service.
  
```  
  java -Dserver.port=8085 -Dspring.datasource.username=postgres -Dspring.datasource.password=root -Dproducts.number=5000 -jar product-search-service-1.0-SNAPSHOT.jar 
```