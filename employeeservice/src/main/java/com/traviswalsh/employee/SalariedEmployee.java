package com.traviswalsh.employee;

public class SalariedEmployee extends Employee {
    public SalariedEmployee(String firstName, String lastName, int id) {
        super(firstName, lastName, id, "Salaried");
    }

    @Override
    public void work(int daysWorked) {
        if (daysWorked >= 0 && daysWorked <= maxWorkedDays) {
            float newVacationDays = super.round(daysWorked * 15.0f / maxWorkedDays);
            super.setWorkedDays(daysWorked);
            super.accrueVacation(-newVacationDays);
        }  else {
            throw new IllegalArgumentException(super.overdays);
        }
    }
}