# 2024-DEV1-005-DevelopmentBooks

For brevity, I have named the books simply as Book 1, Book 2, ... Book 5 instead of referring to the book titles.
This application runs on Java 18.

The algorithm works by finding as many groups of 5 books first (the largest discount). Then when there are no more groups of 5 books, it looks for groups of 4 (the next biggest discount), and so on until all books have been grouped. A group is a set of unique (different) books. i.e, there are no duplicates. 

## Installing and running the application

- clone the repository in a directory:
```
git clone https://github.com/shirazisam/2024-DEV1-005-DevelopmentBooks.git
cd 2024-DEV1-005-DevelopmentBooks
```
- Run Maven (or use the wrapper supplied): 
```
mvnw install
```
make sure your ```JAVA_HOME``` environment variable is set correctly

- launch the application:
```
mvnw spring-boot:run
```
- Access the app from a browser, or Postman using the following URL:
```
http://localhost/bnp-kata/books?nrbooks=12
```
The above command will randomly generate 12 books of Book 1 to Book 5.
The output will be a JSON object with information about:
- Book price
- List of books
- Aggregates by title
- quantity of groupings
- calculation of discount
- calculation of % discount

use the request parameter ```nrbooks``` to randomly change the number of books generated.

## Running the application as a Docker container

If Docker is installed, the application can be launched as a container.
First, ```package``` the application as a Docker image:
- in the project root folder, run the Maven package phase:
```
mvnv package
```
This will build the application and create the Docker image on the local file system.

- now run the container:
```
docker container run -d -p 80:80 bnp-kata
```
This will launch the application in detached mode, using the default http web port (80).
Now run the application from the browser as before, or Postman using a GET request:
```
http://localhost/bnp-kata/books?nrbooks=10
```
This above example generates 10 random books of Book 1...Book 2. The aggregates,groupings and discounts
are displayed in the output JSON format.
