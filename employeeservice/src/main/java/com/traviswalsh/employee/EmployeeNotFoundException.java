package com.traviswalsh.employee;
import org.springframework.http.HttpStatus;



public class EmployeeNotFoundException extends RuntimeException {

  public EmployeeNotFoundException() {
    super("Employee not found");
  }

  public HttpStatus getStatus() {
    return HttpStatus.NOT_FOUND;
  }
}

