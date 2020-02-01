# PwcCodingChallenge

System requirements to run application - 

1. Application has been developed using Springboot2 and runs on Java 8
2. Gradle is required to build and run application
3. Code has been built and tested on a mac machine. Some configuration changes may be required for code to run on a windows machine. 

Some details about the program -

1. Program has been designed using spring boot application to expose Rest API endpoints to call the function to add entries in address book, get all the enteries, find unique friends and delete all friends
2. Data is persisted using H2 database in a memory on disk. Data file will be saved in project folder in database directory.
3. H2 database console can be accessed through link - http://localhost:8090/ms-address-book/h2-console. URL, username, password and driverClassName are configured in application.yml file in resources
3. Program can be run by running command - "./gradlew bootRun". It will spin up application and will run on the embedded tomcat server. To stop application, please use command - "./gradlew -stop"
4. Rest APIs can be invoked using postman or through curl command in terminal. I have added postman script that contains all the Rest API endpoints along with request body. Request bodies can also be found in - src/test/resources/request folder
5. Code can be built by running following command on terminal - "./gradlew clean build"
5. Jaccoco code coverage tool has been used to assess the code coverage through tests. Jacoco runs and generates report at location - "build/reports/JacocoHtml/index.html". Code coverage stands at 100% for branch and line
6. Code quality tool such as Checkstyle and FindBugs have been added and runs as a part of code build.

Assumptions -

1. Address book will store unique names. Application will override an existing entry if same name is saved again in database with a different phone number.
2. While sorting names from an address book, sorting will be case insensitive.
2. Basic regex check has been used on name string. Also the name string size has been restricted to a maximum of 100 characters. Also, it is assumed that name is mandatory for an entry to be saved, null or blank name will throw an exception.
4. Phone number can accept a null or empty value.
3. For the sake of simplicity, phone number has been restricted to save only digits and maximum size has been restricted to 10 digits. This can be changed to accommodate international numbers.
4. When trying to get a list of unique friends, all the null and blank entries will be removed from the request body and database list.
6. Although not mentioned in requirement, an additional delete endpoint has been configured to erase all the entries in database to start afresh.  
7. Although not mentioned in requirement, list of unique friends will be sorted

Further improvements -

1. Blackbox tests can be written to test rest apis. I use BDD approach to write blackbox test cases using Cucumber. 
2. Swagger file can be written to give details about rest endpoints. Further request and response objects can be validated against swagger file to check object structure.
3. Line and branch code coverage can be enforced using jacocoTestCoverageVerification.
4. Hystrix can be added to check for error rate and enforce fallback method. 
5. Application can be bundled to run on a docker image 
6. Logging can be implemented to troubleshoot application
7. Toggle can be added on api endpoints
