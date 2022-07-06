ToDo Web Application to display Angular and Spring Concepts




Stack Used to build this application:

Spring Boot 2.7
Spring JPA
Spring Web Services
Spring Security
JWT
Gradle
Angular 8
Angular Routing
HTTP Client
RxJS
H2 Database


Service-Side(Springboot) Dev Setup: 

1. Java 8 is required.

2. To build run - ./gradlew clean build

3. To build excluding test - ./gradlew clean build -x test

4. To run in local - ./gradlew bootRun.


Client side (Angular) Dev Setup:

1. Node version 14.15 or above.

2. Run - npm install

3. npm run-script build

4. To run in local- ng serve --proxy-config proxy.conf.json (proxy is configured in this file)



Note: After npm run-script build is run, if you run ./gradlew clean build all the client folders will serve as a client from the resources folder in the applicaiton.
