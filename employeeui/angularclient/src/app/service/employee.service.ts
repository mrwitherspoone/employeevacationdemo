import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, map } from 'rxjs';
import { Employee } from '../model/employee'
@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private baseUrl = "http://localhost:8080/";
  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  private options = { headers: this.headers };

  constructor(private http: HttpClient) { }


  getEmployees(): Observable<Employee[]> {
    console.log("getting employee list")
    return this.http.get<Employee[]>(`${this.baseUrl + "employees"}`);
  }

  getEmployee(id: Number): Observable<Employee | undefined> {
    console.log("attempting to get user id " + id);
    return this.getEmployees().pipe(
      map(ems => ems.find(ems => ems.id === id))
    );

  }

  work(id: Number, days: Number): Observable<Number> {
    return this.http.post<Number>(this.baseUrl + id + "/work", days, this.options);
  }

  takeVacation(id: Number, days: Number): Observable<Number> {
    return this.http.post<Number>(this.baseUrl + id + "/take-vacation", days);
  }

}
