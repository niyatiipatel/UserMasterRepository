# UserMasterRepository

Problem Statement :
As user should be able to register into a system with basic personal details. User can add multiple addresses. User should be able to update user information or save if user does not exist in the system. User should be able to get list of all users, to search a user by email, to edit address of the user and to delete user.

Summary of the application:
“User Master” is a spring-boot web application, which is used to manage the details of users along with multiple addresses. The application creates the required User entity along with the relationship with Address entity. There are several REST API calls to fulfil the problem statement. All the API(s) are well constructed and tested using JUnit API. The application is docker containerized for Spring Boot and Database (Instructions to start a docker container and testing APIs through POSTMAN are given below). An architectural diagram to demonstrate the workflow has also been attached.

Tools and Technologies:
●	IDE - Spring Tool Suite 4.12.0 Release
●	Database - PostgreSQL 14.0
●	Technology - JavaSE-11, Spring Boot Framework 2.5.5
●	Data Model - JPA/Hibernate
●	Docker Engine v20.10.8

Instructions to set up connection string
URL = jdbc:postgresql://localhost:5432/backend_test
Username = postgres
Password = test

Instructions to download project through GitHub:
1.	Go to the below URL in GitHub https://github.com/niyatiipatel/ContributeToCommunityRepo/tree/master
2.	Clone the project “UserMasterRepo”.
3.	After downloading/unzipping the project, create docker container images as per the instructions below.

Instructions to start docker container:
1.	Open the path for the “UserMasterRepo” project in the terminal/command prompt.
2.	Run the below command to start the docker container.
    “docker-compose up -d”
3.	Run the below command to check the running container. (It should start with two containers PostgreSQL and API).
    “docker ps”
4.	Once the docker container runs and images have instantiated, test the application through Postman.(Instructions below)


Instructions to call the API(s) from Postman (https://www.postman.com/) : 

-------------------API: To update user if exists or create a new user------------------------
Assumption : User and Address details passed in Request body should be in proper format.

1.	Url endpoint   : localhost:8080/UpsertUser
2.	Request Method : POST
3.	Request Header : application/json
4.	Request Body   : Format shown below
5.	Response Type  : application/json

Example 1 : To save user details

URL - localhost:8080/UpsertUser 

Request Body :

{
    "firstName" : "Cole",
    "lastName" : "Masik",
    "email" : "colem@gmail.com",
    "addressDtls": [
        {
            "addressLine1": "209, 1140 Davie Street",
            "addressLine2": "",
            "city": "Vancouver",
            "state": "BC",
            "zip": "V6E 1R4"
        },
        {
            "addressLine1": "908, 1020 Georgie Street",
            "addressLine2": "Near Eton Center",
            "city": "Torronto",
            "state": "Ontario",
            "zip": "V67UIE"
        }
    ]
}


Example 2 : To update existing user details

URL- localhost:8080/UpsertUser 

Request Body :

{
    "firstName" : "Cole Updated",
    "lastName" : "Masik",
    "email" : "colem@gmail.com"
}

--------------------API: To input email and return appropriate status-------------------
Assumption : Email address should be in proper format

1.	Url endpoint   : localhost:8080/ IsUserExists/{email} 
2.	Request Method : GET
3.	Request Header : application/json
4.	Request Body   : N/A
5.	Response Type  : application/json

Example 1 : To check user by email

URL - localhost:8080/IsUserExists/colem@gmail.com


---------------------API: To return list of all users----------------------------------

1.	Url endpoint   : localhost:8080/ListUsers
2.	Request Method : GET
3.	Request Header : application/json
4.	Request Body   : N/A
5.	Response Type  : application/json

Example 1 : To get list of all users

URL - localhost:8080/ListUsers


-------------------API: To inputs user id and return user details including address------------
Assumption : User id should be of integer type

1.	Url endpoint   : localhost:8080/ GetUserDetails/{userId} 
2.	Request Method : GET
3.	Request Header : application/json
4.	Request Body   : N/A
5.	Response Type  : application/json

Example 1 : To get user details

URL - localhost:8080/GetUserDetails/1


-------------------API: To input user id and mark the user as deleted----------------------
Assumption : User id should be of integer type

1.	Url endpoint   : localhost:8080/DeleteUser/{userId} 
2.	Request Method : DELETE
3.	Request Header : application/json
4.	Request Body   : N/A
5.	Response Type  : application/json

Example 1 : To delete the user

URL - localhost:8080/DeleteUser/1


------------------API: To input user id and update user address such that the old address is archived--------
Assumption : User id should be of integer type. Address details passed in Request body should be in proper format.

1.	Url endpoint   : localhost:8080/UpdateAddress/{userId} 
2.	Request Method : POST
3.	Request Header : application/json
4.	Request Body   : Format shown below
5.	Response Type  : application/json

Example 1 : To delete the user
 
 URL - localhost:8080/UpdateAddress/1

Request Body :
  
  {
    "addressLine1": "908, 1020 Georgie Street",
    "addressLine2": "Near Eton Center",
    "city": "Torronto",
    "state": "Ontario",
    "zip": "V67UIE"
  } 
  

