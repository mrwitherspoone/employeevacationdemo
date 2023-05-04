package com.traviswalsh.employee;

public class Manager extends Employee {
    public Manager(String firstName, String lastName, int id) {
        super(firstName, lastName, id, "Manager");
    }

    @Override
    public void work(int daysWorked) {
        if (daysWorked >= 0 && daysWorked <= maxWorkedDays) {
            float newVacationDays = super.round(daysWorked * 30.0f / maxWorkedDays);
            super.setWorkedDays(daysWorked);
            super.acrueVacation(-newVacationDays);
        } else {
            throw new IllegalArgumentException(super.overdays);
        }
    }
}