#	Just another API in the world

##	I follow this steps to do my best and create this application amazing!

1.	Using [Spring Initializr](https://start.spring.io/) to create a new Spring Boot application with required dependencies;
	1.	Spring Data JPA
	1.	h2 database
	1.  Validation
	1.  Spring web

1.	Create the JPA entities
1.	Create the JPA repository
1.	Create the required DTOs
1.	Create the REST Controller for expose CRUD operations

### Important notes...

1.	The database is the h2 and the URL connection the application is: /tmp/h2/test
1.	Where the  all resources living on this application? [Here](http://localhost:8080/api/v1)


##	How to execute the application?

1.	Open the terminal
1.  Run the command:
```
./gradlew bootRun
```

##	How do I was make some test?

I use the best tool do call a API... my bro: "curl"!

```
#	- - - - - - - - - - - - - - - - - - -
#	HTTP GET: Use this verb to view all resources
#	- - - - - - - - - - - - - - - - - - -
curl -s -X GET "http://localhost:8080/api/v1" -H "accept: application/json" | jq .
curl -s -X GET "http://localhost:8080/api/v1/1001/documents" -H "accept: application/json" | jq .




#	- - - - - - - - - - - - - - - - - - -
#	HTTP POST: Use this verb to create a new resource
#	- - - - - - - - - - - - - - - - - - -
curl -s -X POST \
"http://localhost:8080/api/v1" -H "accept: application/json" -H "Content-Type: application/json" \
-d '{"name":"Create a new resource - POST", "phone":"11321987456","dateOfBirth":"2008-01-01T12:00:00","documents":[{"type":"1RG","description":"1Registro Geral Estadual"},{"type":"2RG","description":"2Registro Geral Estadual"},{"type":"3RG","description":"3Registro Geral Estadual"},{"type":"4RG","description":"4Registro Geral Estadual"}]}' | jq .




#	- - - - - - - - - - - - - - - - - - -
#	HTTP PUT: Use this verb to update a resource.
#	- - - - - - - - - - - - - - - - - - -
curl -s -X PUT "http://localhost:8080/api/v1" -H "accept: application/json" -H "Content-Type: application/json" -d '{"id":1001,"name":"It is a beatiful world", "phone":"11321987456","dateOfBirth":"2008-01-01T12:00:00"}' | jq




#	- - - - - - - - - - - - - - - - - - -
#	HTTP DELETE: Use this verb to delete a created resource.
#	- - - - - - - - - - - - - - - - - - -
curl -i -X DELETE "http://localhost:8080/api/v1/1001" -H "accept: */*" 

```