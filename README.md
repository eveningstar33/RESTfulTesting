# RESTfulTesting
Testing RESTful Services with Spring Boot and Mockito

In this project I’m building unit tests for REST Web Services with JUnit4, Mockito and Spring Boot Starter Test. 
I’m building independent unit tests for REST services talking with multiple layers: data, web and business. 
Also I’m building integration tests using H2 in memory database.

In this project I’m using Spring Boot, Spring Core, Spring Data JPA, H2 in memory database, Maven and Tomcat Embedded Web Server. 

I’m using all the frameworks from Spring Boot Starter Test – JUnit4, Mockito, Hamcrest, Spring Test, Spring Boot Test, AssertJ, 
JsonPath and JSONassert.

Also I’m using the following Unit Testing Annotations: @RunWith(SpringRunner.class), @SpringBootTest, @MockBean, @DataJpaTest and
@WebMvcTest to create unit tests for GET, POST, DELETE and PUT methods.
