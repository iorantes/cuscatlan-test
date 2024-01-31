<h1 align="center">Cuscatlan Test</h1>

###

<h2 align="left">Instrucciones:</h2>

###

<h4 align="left">-Clone the project.<br>-Import the collection located at the root of the project named "Cuscatlan.postman_collection" into Postman.<br>-Open the project using your IDE; in this case, Eclipse will be used.<br>-Select "Run As," followed by "Spring Boot Application" to run the project.<br>-In Postman, test the various endpoints of the API.<br>-If you need a new token, you can obtain one at the "http://localhost:8080/api/clients/auth" endpoint of the API.<br>-Use the new token for all requests; it should be placed in the Headers, replacing the existing one. Always ensure that it is preceded by "Bearer " followed by the new token.</h4>

###

<h2 align="left">Swagger</h2>

###

<h4 align="left">To access Swagger, you need to enter the following link in your browser: http://localhost:8080/api/swagger-ui/index.html#/<br>
This will allow you to view each endpoint of the API.</h4>

###

<h2 align="left">Data Base</h2>

###

<h4 align="left">
The database configuration is set up in H2, which uses the "shoppingcart.mv" file in the root of the project to store each piece of data in the database. Since it's an embedded database, you just need to start the API, and it should work seamlessly. If you encounter any issues, uncomment "spring.jpa.hibernate.ddl-auto=create" in the project properties, and it will recreate the tables.</h4>

###