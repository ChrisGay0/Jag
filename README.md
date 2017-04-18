# Jag

Spring boot web service to manage a package of products

<h3>Requires</h3>
Java 1.8
Maven

To package run a maven install to generate a jar file and run java -jar jag-0.1.0.jar

<h3>Example usage</h3>

Exposed endpoints

To find all packages
GET http://localhost:8080/productPackage/findAll

To find a specific package
GET http://localhost:8080/productPackage/find/{packageId}

To find a specific package in a currency
GET http://localhost:8080/productPackage/find/{packageId}/{CurrencyCode}

To create a package
POST http://localhost:8080/productPackage/create?name={name}&description={description}&products={product1}&products={productX}

To update a package
POSThttp://localhost:8080/productPackage/update?id={id}name={name}&description={description}&products={product1}&products={productX}

To delete a package
DELETE http://localhost:8080/productPackage/delete?id={id}
