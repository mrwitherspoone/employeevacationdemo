import { Component } from '@angular/core';
import { Employee } from '../../model/employee';
import { EmployeeService } from '../../service/employee.service';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-workform',
  templateUrl: './workform.component.html',
  styleUrls: ['./workform.component.sass']
})
export class WorkformComponent {
  employee!: Employee;
  days!: Number;
  errorMessage!: String;
  daysAdded!: Number;
  form: FormGroup = new FormGroup({});
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private employeeService: EmployeeService,
    private fb: FormBuilder) { 
      this.form = fb.group({
        number: ['', [Validators.required, Validators.pattern("^[0-9]*$")]]
      })
    }


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

    this.employeeService.work(this.employee.id, this.days).subscribe({
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


