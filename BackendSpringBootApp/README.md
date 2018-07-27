This is the Spring Boot, JPA and MySQL based backend application which is used to authenticate the login credentials passed from UI.

This app has three methods:
1. test :   This method can be used to test whether the spring boot service has been deployed successfully and can be used by the App.
This is a GET Method call. This method can be called as below:
http://localhost:8080/authenticate/test      

2. login :  This method retrieves the UserName and password sent in the request and validates the same with the database (with the table named LOGIN_INFO).
This is a POST Method call. This method can be called as below:
http://localhost:8080/authenticate/login

Headers:
    Content-Type    application/json

Body :
{
	"username" : "<YourUserName>",
	"password" : "<YourUserPasssword>",
}

3. addNewUser :  This method can be used to add the new user in the LOGIN_INFO table. 
This is a POST Method call. This method can be called as below:
http://localhost:8080/authenticate/addNewUser

Headers:
    Content-Type    application/json

Body :
{
	"id" : "<YourUserId>",
	"username" : "<YourUserName>",
	"password" : "<YourUserPassword>",
	"created_at" : "<YourUserCreatedDate>",
	"updated_at" : "<YourUserUpdatedDate>"
}