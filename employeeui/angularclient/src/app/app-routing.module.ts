import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeComponent } from './employee/employee.component';
import { WorkformComponent } from './employee/workform/workform.component';
import { EmployeeDetailsComponent } from './employee/employee-detail.component';
import { VacationComponent } from './employee/vacationform/vacation.component';
const routes: Routes = [
  { path: 'employees', component: EmployeeComponent },
  { path: '', redirectTo: 'employees', pathMatch: 'full' },
  { path: 'workform/:id', component: WorkformComponent},
  { path: 'vacation/:id', component: VacationComponent},
  { path: 'details/:id', component: EmployeeDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }