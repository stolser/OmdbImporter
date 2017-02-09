# Spring MVC application interacting with public API
Allows to fetch data from https://www.omdbapi.com/ and persist it into a db.
A user can submit some search parameters (title, year, video type: movie/series) via a web form. A controller launches a new job. ItemReader
implementation makes a searching request to the API with submitted parameters and converts the results into a list of imdbIds. ItemProcessor 
implementation fetches all the data as JSONs from https://www.omdbapi.com/ and converts into domain objects. If a video is a series than all
episodes from all seasons will be fetched too. ItemWriter persists all the video objects into a MySQL database.
## Used technologies:
* Java SE 8
* Spring Boot, Spring Data JPA, Spring MVC, Spring Batch
* MySQL, Tomcat, Maven, SLF4J + Log4j2
* JUnit, Mockito
* Bootstrap
