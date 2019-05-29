This is SpringBoot Application

Just run the application as run with spring boot application using com.synch.image.ImagesServiceApplication
configured port number as :9000

For User Registration: all positive and negative scnarios covered 

POST
http://localhost:9000/user

Sample JSON:
{
  "userName": "testName",
  "password": "test@password",
  "firstName":"testFirstName",
  "lastName":"m",
  "emailId":"test@test.com",
  "gender":"m"
}

it will return primary key as response


For Image upload: it will authenticate the username and password before upload
http://localhost:9000/image/upload

Positive Scenarion:
Sample Form Data:
file: file.txt
userName: testName
password: test@password

it will return primary key


Negative scenario:
Sample Form Data:
file: file.txt
userName: testNameinvalid
password: test@password

it will return invalid username and password


For Image View:
http://localhost:9000/image/view

Positvie Scenario:
Sample JSON:
{
  "userName": "testName",
  "password": "test@password"
}

It will return userinfo along with associated images

Negative Scenario:

Sample JSON:
{
  "userName": "testNameInvalid",
  "password": "test@password"
}

it will return invalid username and password



For Delete:
http://localhost:9000/image/delete
Sample JSON
{
  "userName": "testName",
  "password": "test@password",
  "imageId": 2
}

it will return SUCCESS message


TestCases:

Test cases are writted with mockito for only for controller class.
Haven't written for the Service classes hence concept is same as Controller classes.


