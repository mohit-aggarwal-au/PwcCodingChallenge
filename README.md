# PwcCodingChallenge

System requirements to run application - 

1. Application has been developed using Springboot2 and runs on Java 8.
2. Gradle is used to build and run application.
3. Code has been built and tested on a mac machine. Some configuration changes may be required for code to run on a windows machine, for instance - how gradle commands are invoked.

Some details about the program -

1. Application has exposed following Rest API endpoints
    1. Add entry in address book - post request on URL - http://localhost:8090/ms-address-book/address    
    2. Get all the entries in address book - get request on URL http://localhost:8090/ms-address-book/address
    3. Find unique friends - post request on URL - http://localhost:8090/ms-address-book/address/unique
    4. Delete all entries in address book - delete request on URL - http://localhost:8090/ms-address-book/address
2. Application can be invoked by running command - "./gradlew bootRun". It will spin up application and will run on the embedded tomcat server. 
3. To stop application, please use command - "./gradlew -stop"
4. Rest APIs can be invoked using postman or through curl command in terminal. I have added postman script in postman folder in project directory that contains all the Rest API endpoints along with request body. Request bodies can also be found in - src/test/resources/request folder
5. Data is persisted using H2 database in a file on disk. Data file will be saved in project folder in database directory.
6. There is a separate database file configured to run test cases. Database files can be deleted. Once application runs, it will create new database file if not available.
7. H2 database console can be accessed through link - http://localhost:8090/ms-address-book/h2-console. URL, username, password and driverClassName are configured in application.yml file in resources
8. Code can be built by running following command on terminal - "./gradlew clean build"
9. Jaccoco code coverage tool has been used to assess the code coverage through tests. Jacoco runs and generates report at location - "build/reports/JacocoHtml/index.html". 
10. Code coverage stands at 100% for branch and line
11. Code quality tools such as Checkstyle and FindBugs have been added and runs as a part of code build.

Assumptions -

1. Address book will store unique names. Application will override an existing entry if same name is saved again in database with a different phone number.
2. Name is case sensitive, therefore 2 entries such as "Mike" and "mike" will be considered unique and will be saved in address book.
3. Since name is case sensitive, while finding unique names, all the unique strings will be added, for example, both entries - "Mike" and "mike" will be part of unique list. This behavior can be easily changed by using TreeSet with case insensitive order.
4. When trying to get a list of unique friends, all the null and blank entries will be removed from the incoming request body and database list.
5. While sorting names from an address book, sorting will be case insensitive.
6. Basic regex check has been used on name string. Also the name string size has been restricted to a maximum of 100 characters. 
7. It is assumed that name is mandatory for an entry to be saved in address book, null or blank name will throw an exception.
8. Phone number can accept a null or empty value.
9. For the sake of simplicity, phone number has been restricted to save only digits and maximum size has been restricted to 10 digits. This can be changed to accommodate international numbers.
10. Although not mentioned in requirement, an additional delete endpoint has been configured to erase all the entries in database to start afresh.

Further improvements -

1. Blackbox tests can be written to test rest apis. BDD approach can be used to write blackbox test cases using Cucumber. 
2. Test cases can be broken down in following way 
    1. Unit : to test line and branch coverage without invoking a spring application. All the dependencies are mocked.
    2. Component: to test the integration between different spring components such as service and repository
    3. Blackbox: to test an API end to end. Call to other microservices are mocked
    4. Integration: to test integration of microservice with other microservices
    5. Performance: to test the performance of microservice under load
 
3. Swagger file can be written to give details about rest endpoints. Further request and response objects can be validated against swagger file to check object structure
4. Line and branch code coverage can be enforced using jacocoTestCoverageVerification
5. Hystrix can be added to check for error rate and enforce fallback method
6. Logging can be implemented to troubleshoot application
7. Project dependencies can be checked against vulnerabilities

