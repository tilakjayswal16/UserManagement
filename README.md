**UserManagement**

**Technological stack**

- Spring Boot
- Spring Security- Basic Authentication
- Spring JPA
- Java 8
- JUnit
- Mockito
- H2 Database (Can be accessed at : http://localhost:9090/api/h2console/)



**Endpoints**

**1. Endpoint for user to 'create' an account**


`POST /api/register
{
username,
password
}`


![img_9.png](img/img_9.png)




**2. Endpoint for user to log in**


`POST /api/login
{
username,
password
}`



![img_2.png](img/img_2.png)




**3. Secure endpoint for user to get their personal information**


`GET /api/users/{uuid}
`

![img_4.png](img/img_4.png)



**4. Secure endpoint for user to update their personal information**

   
`POST /api/users/{uuid}
`   
`{
   name,
   email,
   phone
}`



![img_5.png](img/img_5.png)


`

**Data Verification in H2**



![img_11.png](img/img_11.png)