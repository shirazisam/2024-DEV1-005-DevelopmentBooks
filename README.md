# 2024-DEV1-005-DevelopmentBooks

 For brevity, I have named the books simply as Book 1, Book 2, ... Book 5 instead of referring to the book titles.
This application runs on Java 18
## Installing and running the application

- clone the repository in a directory:
```
git clone https://github.com/shirazisam/2024-DEV1-005-DevelopmentBooks.git
cd 2024-DEV1-005-DevelopmentBooks.git
```
- Run Maven (or use the wrapper supplied): 
```
mvnw install
```
make sure your JAVA_HOME environment variable is set correctly

- run the application
```
mvnw spring-boot:run
```
- Run the app from a browser, or Postman using the following URL:
```
http://localhost/bnp-kata/books?nrbooks=12
```
the above command will randomly generate 12 books of Book 1 to Book 5.
The output will be a JSON object with information about:
- Book price
- List of books
- Aggregates by title
- quantity of groupings
- calculation of discount
- calculation of % discount

use the request parameter ```nrbooks``` to randomly change the number of books generated.