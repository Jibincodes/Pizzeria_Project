# Pizzeria Project

This is a minimal project that supports all the minimal CRUD functions needed to run a pizzeria.

[![License](https://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

> 🚧: **This is a template project**: Make sure you adapt this documentation and the source code in this project according to your needs and use case. Comments are provided with "🚧:". Do not leave these comments in your final submission!

#### Contents:
- [Analysis](#analysis)
  - [Scenario](#scenario)
  - [User Stories](#user-stories)
  - [Use Case](#use-case)
- [Design](#design)
  - [Prototype Design](#prototype-design)
  - [Domain Design](#domain-design)
  - [Business Logic](#business-logic)
- [Implementation](#implementation)
  - [Backend Technology](#backend-technology)
  - [Frontend Technology](#frontend-technology)
- [Project Management](#project-management)
  - [Roles](#roles)
  - [Milestones](#milestones)

## Analysis
> 🚧: You can reuse the analysis (you made) from other projects (e.g., requirement engineering), but it must be submitted according to the following template. 



### Scenario

As a group work, we decided to create an extended form of the pizzeria web application where 
customers can easily browse through the pizzeria menu and place an order. It will include 
additional features such as login for both admin and customers, bonus points for each order 
sent by the customer and finally payment for their order. The bonus points mentioned above can 
give the customer certain discounts for their orders, and these bonus points would be shown on 
their profile. The menu, which contains all the information about the pizza and toppings, is 
maintained by the admin.

### User Stories
1. As a user, I want to see the menu so that I can choose my favorite pizza and toppings.
2. As a user, I want to create my own profile so that I can save my details and view my previous orders.
3. As a user, I want to log in so that I can authenticate myself and see my bonus points.
4. As a user, I want to make the payment so that I can complete the order.
5. As a User, I want to use list views so that I can access public pages.
6. As an Admin, I want to use list views to explore and read my business data.
7. As an Admin, I want to use edit and create views to maintain my business data. For 
example, the ability to add new pizzas or update existing pizzas on the menu.
8. As an Admin, I want a consistent and simple web application so that it is easy to use and 
maintain.

### Use Case

![](images/Usecasefinal.png)

- UC-1 [View all the Pizza on the menu]: Admin can view all available information about pizzas and toppings.
- UC-2 [View a single Pizza]: Admin can retrieve the information on a specific pizza.
- UC-3 [Edit a Pizza]: Admin can create, update, and delete pizzas from the menu.
- UC-4 [Show Current Location Offer]: User can retrieve special menu by location
- UC-5 [Place an order]: Customers can browse the pizzeria menu and place an order.
- UC-6 [Authenticate by logging in]: Customers can authenticate by logging in and check the available bonus points.
- UC-7 [Complete order with payment]: Customers can complete their order by making payment.

## Design

![](images/myorder.png)
**My order page design**

![](images/orderconfirmed.png)
**Order confirmation page design**

![](images/login.png)
**Login page design**

![](images/customerprofile.png)
**Customer profile page design**

Here are some of the page design we have created for our pizzeria web application.

### Wireframe

![](images/sitemap.png)

Here is the planned sitemap for our pizzeria web application. It would consist of a home page, menu, about us page, order and confirmation page, and also user pages.

### Prototype
> 🚧: A prototype can be designed using placeholder text/figures in Budibase. You don't need to connect the front-end to back-end in the early stages of the project development.

### Domain Design

The `ch.fhnw.pizza.data.domain` package contains the following domain objects / entities including getters and setters:


![](images/final_domainmodel.png)

### Business Logic 
> 🚧: Describe the business logic for **at least one business service** in detail. If available, show the expected path and HTPP method. The remaining documentation of APIs shall be made available in the swagger endpoint. The default Swagger UI page is available at /swagger-ui.html.

Based on the UC-4, there will be two offers and a standard offer. Given a location, a message is shown accordingly:

- If the location is "Basel", the message is "10% off on all large pizzas!!!"
- If the location is "Brugg", the message is "two for the price of One on all small pizzas!!!"
- Otherwise, the message is "No special offer".

**Path**: [`/api/menu/?location="Basel"`] 

**Param**: `value="location"` Admitted value: "Basel","Brugg".

**Method:** `GET`

## Implementation
> 🚧: Briefly describe your technology stack, which apps were used and for what.

### Backend Technology
> 🚧: It is suggested to clone this repository, but you are free to start from fresh with a Spring Initializr. If so, describe if there are any changes to the PizzaRP e.g., different dependencies, versions & etc... Please, also describe how your database is set up. If you want a persistent or in-memory H2 database check [link](https://github.com/FHNW-INT/Pizzeria_Reference_Project/blob/main/pizza/src/main/resources/application.properties). If you have placeholder data to initialize at the app, you may use a variation of the method **initPlaceholderData()** available at [link](https://github.com/FHNW-INT/Pizzeria_Reference_Project/blob/main/pizza/src/main/java/ch/fhnw/pizza/PizzaApplication.java).

This Web application is relying on [Spring Boot](https://projects.spring.io/spring-boot) and the following dependencies:

- [Spring Boot](https://projects.spring.io/spring-boot)
- [Spring Data](https://projects.spring.io/spring-data)
- [Java Persistence API (JPA)](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html)
- [H2 Database Engine](https://www.h2database.com)

To bootstrap the application, the [Spring Initializr](https://start.spring.io/) has been used.

Then, the following further dependencies have been added to the project `pom.xml`:

- DB:
```XML
<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
</dependency>
```

- SWAGGER:
```XML
   <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.3.0</version>
   </dependency>
```

### Frontend Technology
> 🚧: Describe your views and what APIs is used on which view. If you don't have access to the Internet Technology class Budibase environment(https://inttech.budibase.app/), please write to Devid on MS teams.

This Web application was developed using Budibase and it is available for preview at https://inttech.budibase.app/app/brugg5pizzeria#/home. 

## Execution
> 🚧: Please describe how to execute your app and what configurations must be changed to run it. 

The codespace URL of this Repo is subject to change. Therefore, the Budibase PizzaRP webapp is not going to show any data in the view, when the URL is not updated or the codespace is offline. Follow these steps to start the webservice and reconnect the webapp to the new webservice url. 

> 🚧: This is a shortened description for example purposes. A complete tutorial will be provided in a dedicated lecture.

1. Clone PizzaRP in a new repository.
2. Start your codespace (see video guide at: [link](https://www.youtube.com/watch?v=_W9B7qc9lVc&ab_channel=GitHub))
3. Run the PizzaRP main available at PizzaApplication.java on your own codespace.
4. Set your app with a public port, see the guide at [link](https://docs.github.com/en/codespaces/developing-in-a-codespace/forwarding-ports-in-your-codespace).
5. Create an own Budibase app, you can export/import the existing Pizzeria app. Guide available at [link](https://docs.budibase.com/docs/export-and-import-apps).
6. Update the pizzeria URL in the datasource and publish your app.

## Project Management

As described below, we had divided our team into 2 parts. Since Jibin has more experience in the software development background, he happily accepted the part of working in the backend. And the rest of us mainly worked on the domain design and front-end implementations using Budibase.

### Roles
- Back-end developer: Jibin Mathew Peechatt
- Front-end developer: Leon Bytyqi, Jevoen Jenifar, and Marko Jurcevic
- Of course, credit also goes to Devid Montecchiari and Charuta Pandey, as we used their template to further develop the application.

### Milestones
1. **Analysis**: Scenario ideation, use case analysis and user story writing.
2. **Prototype Design**: Creation of wireframe and prototype.
3. **Domain Design**: Definition of domain model.
4. **Business Logic and API Design**: Definition of business logic and API.
5. **Data and API Implementation**: Implementation of data access and business logic layers, and API.
6. **Security and Frontend Implementation**: Integration of security framework and frontend realisation.
7.  **Deployment**: Deployment of Web application on cloud infrastructure.

All of these milestones were successfully achieved during the implementation of the project.

#### Maintainer
- Jibin Mathew Peechatt
- Leon Bytyqi
- Marko Jurcevic
- Jevoen Jenifar

#### License
- [Apache License, Version 2.0](blob/master/LICENSE)
