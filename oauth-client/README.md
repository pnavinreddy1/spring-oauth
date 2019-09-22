# To run applicaton
Type "mvn exec:java" to run application.
To package and run the application, type "mvn package"

# Swagger Link 
http://localhost:8080/

# To get Token
curl -X POST "http://localhost:8080/cms/v1/token" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \\"userName\\": \\"string\\", \\"id\\": 0, \\"role\\": \\"string\\"}"

# To create articles
curl -X POST "http://localhost:8080/cms/v1/articles" -H "accept: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9" -H "Content-Type: application/json" -d "[ { \\"docId\\": \\"string\\", \\"title\\": \\"string\\", \\"content\\": \\"string\\" }]"

# To get all articles
$ curl -X GET "http://localhost:8080/cms/v1/articles" -H "accept: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9"

# To update article
curl -X PUT "http://localhost:8080/cms/v1/articles/article" -H "accept: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9" -H "role: ADMIN" -H "Content-Type: application/json" -d "{ \\"docId\\": \\"string\\", \\"title\\": \\"string\\", \\"content\\": \\"string\\"}"

# To get article by Title
curl -X GET "http://localhost:8080/cms/v1/articles/Titles/hi2" -H "accept: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9"

# To detele article by Title
curl -X DELETE "http://localhost:8080/cms/v1/articles/Titles/hi2" -H "accept: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9" -H "role: ADMIN"

Note: Will refactor the code and will try with oAuth2 implementation too.
