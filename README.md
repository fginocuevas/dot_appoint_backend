# dotAppoint

dotAppoint is a sample doctor's appointment/ scheduler application written in Java using the Spring Framework.

## Setup

**NOTE:** Passwords are not validated at the moment so type in anything for the user's password.

### Option 1 - File-based database

For easier testing purposes, you may comment out the h2 in-memory database and uncomment the file-based storage in the `application.properties`

```
#spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.url=jdbc:h2:file:./data/demo
```
A couple of Scheduler and Doctor App Users have been created for you. 

#### Scheduler Credentials
```
scheduler
scheduler2
```

#### Doctor Credentials
```
doctor
doctor2
```
**NOTE:** Using this option though, you may run into issues with sending email notifications to an invalid email when creating new events. A workaround is to comment out the line. Emails sent are also logged.

```java
EmailServiceImpl

public void sendNotificationEmailForAppointmentCreated(){
...
//emailSender.send(message);
...
}
```

### Option 2 -  H2 In-memory database

This is the default setup, however, currently there's no way to create a user using the frontend so it's recommended to use Postman or any application that can send JSON requests.

#### Create a Scheduler App User
```url
http://localhost:8080/scheduler/create
```
```json
{
    "email" : "abc2@gmail.com",
    "username" : "scheduler",
    "firstname" : "Scheduler",
    "lastname" : "Test",
    "password" : "test",
    "status" : 1
}
```
#### Create a Doctor App User
```url
http://localhost:8080/doctor/create
```
```json
{
    "email" : "doctor@gmail.com",
    "username" : "doctor",
    "firstname" : "Doctor",
    "lastname" : "Test",
    "password" : "test",
    "status" : 1
}
```
**NOTE:** For Doctor App User, make sure to indicate a valid and accessible email address to test email notifications.

## Usage



## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.