package com.traviswalsh.employee;

public class HourlyEmployee extends Employee {
    public HourlyEmployee(String firstName, String lastName, int id) {
        super(firstName, lastName, id, "Hourly");
    }

    @Override
    public void work(int daysWorked) throws IllegalArgumentException {
        if (daysWorked >= 0 && daysWorked <= maxWorkedDays) {
            float newVacationDays = super.round(daysWorked * 10.0f / maxWorkedDays);
            super.setWorkedDays(daysWorked);
            super.accrueVacation(-newVacationDays);
        } else {
            throw new IllegalArgumentException(super.overdays);
        }
    }
}