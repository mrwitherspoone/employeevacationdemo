import { Component } from '@angular/core';
import { Employee } from '../../model/employee';
import { EmployeeService } from '../../service/employee.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-vacation',
  templateUrl: './vacation.component.html',
  styleUrls: ['./vacation.component.sass']
})
export class VacationComponent {
  employee!: Employee;
  days!: Number;
  errorMessage!: String;
  daysAdded!: Number;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private employeeService: EmployeeService) { }


  ngOnInit(): void {
    this.getEmployee();
  }

  getEmployee(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.employeeService.getEmployees().subscribe(employees => {
      this.employee = employees.find(e => e.id === id)!;
    });
  }

  async onSubmit() {

    this.employeeService.takeVacation(this.employee.id, this.days).subscribe({
      next: (v) => console.log(v),
      error: (e) => {
        console.error(e.error);
        this.errorMessage = e.error
      },
      complete: () => {
        this.errorMessage = "";
        this.router.navigate(['/employees'])
      }
    });
  }

}


