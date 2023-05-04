# Employee Vacation App

## Description
This is an app written with a spring-boot backend and an Angular frontend. The simple 
frontend lists the employees as a table and has two functions, add days that an employee 
worked, and subtract days when vacation is used.  On sucess of either the app goes 
back to the list with updated data. On start the service creates 10 instances of each type of employee hourly employees, salaried employees, and managers.

## Requirements
* max workdays per year 260
* Hourly employees accumulate 10 vacation days during the work year.
* Salaried employees accumulate 15 vacation days during the work year.
* Managers accumulate 30 vacation days during the work year.
* An employee cannot work more than the number of workdays in a work year and cannot take more vacation than is available.


## Installation
For simple installation Docker is recommened.  
At the root of the application "employeevacationapp$"

This will start a container that has both the spring-boot service and the angular app behind nginx
```bash
docker build -t employeevacationapp .
docker run -d -p 4200:4200 -p 8080:8080 --name employeevacation employeevacationapp
```

Alternatively these 2 services can be started outside of a container locally.  
To start the spring-boot service 
```bash
cd employeeservice/
./mvnw clean spring-boot:run
```

To start the Angular app

```bash
cd employeeui/
npm intsall
./angularclient/node_modules/.bin/ng serve
```

In wither of the above cases to see the app enter into your browser
```
http://localhost:4200/
```


## Development
Since this is an example app, things that would normally be done in production are missing such as real logging, unit tests, careful css design, monitoring, etc.
