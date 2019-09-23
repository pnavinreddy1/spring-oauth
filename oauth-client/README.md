When both applications "oauth server" and "oauth client" are up and running,
type one of the endpoint http://localhost:8080/ which will redirect to login page of oauth server. Once you provide correct credentials admin/password, you will redirect back to client application.

# To run applicaton
Type "mvn exec:java" to run application.
To package and run the application, type "mvn package"

# Swagger Link 
http://localhost:8080/

# To call unsecure path
curl -X GET "http://localhost:8080/cms/v1/unsecured" -H "accept: application/json"

# To create articles
curl -X POST "http://localhost:8080/cms/v1/articles" -H "accept: application/json" -H "Content-Type: application/json" -d "[ { \\"docId\\": \\"string\\", \\"title\\": \\"string\\", \\"content\\": \\"string\\" }]"

# To get all articles
$ curl -X GET "http://localhost:8080/cms/v1/articles" -H "accept: application/json" 

# To update article
curl -X PUT "http://localhost:8080/cms/v1/articles/article" -H "accept: application/json" -H "role: ADMIN" -H "Content-Type: application/json" -d "{ \\"docId\\": \\"string\\", \\"title\\": \\"string\\", \\"content\\": \\"string\\"}"

# To get article by Title
curl -X GET "http://localhost:8080/cms/v1/articles/Titles/hi2" -H "accept: application/json" 

# To detele article by Title
curl -X DELETE "http://localhost:8080/cms/v1/articles/Titles/hi2" -H "accept: application/json" -H "role: ADMIN"
