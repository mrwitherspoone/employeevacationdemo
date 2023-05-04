package com.traviswalsh.employee;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Employee {
    private String firstName;
    private String lastName;
    private int id;
    private String type;
    private float vacationDays;
    private int workedDays;
    protected final int maxWorkedDays = 260;
    protected final String overdays = new String("Cannot work more than " + maxWorkedDays + " days a year.");

    public Employee(String firstName, String lastName, int id, String type) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.type = type;
        this.vacationDays = 0;
        this.workedDays = 0;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getId() { return id; }
    public String getType() { return type; }
    public float getVacationDays() { return vacationDays; }
    public int getWorkedDays() { return workedDays; }

    
    /** 
     * add the number of days an employee worked
     * @param days
     */
    // the decision to impliment this in each of the sunclasses
    // when the logic is the same except for the hours
    // was made to give greater flexibility in the future for
    // more complex accrual models
    public abstract void work(int daysWorked);

    
    /** 
     * sets the total number of days an employee has worked
     * @param days
     */
    protected void setWorkedDays(int days)  throws IllegalArgumentException{
        if (days < 0) {
            throw new IllegalArgumentException("Days worked must be a positive integer.");
        }
        int tot =  days + workedDays;
        if (tot <= maxWorkedDays) {
            this.workedDays = tot;
        } else {
            throw new IllegalArgumentException(this.overdays);
        }
    }

    
    /** 
     * subtract the days (or partial days) from an employyees accrued vacation time
     * @param days
     */
    protected void takeVacation(float days)  throws IllegalArgumentException {
        if (days < 0) {
            throw new IllegalArgumentException("Vacation days taken must be a positive number.");
        }
        this.manageVacation(days);
    }

    protected void acrueVacation(float days) throws IllegalArgumentException {
        this.manageVacation(days);
    }

    private void manageVacation(float days) throws IllegalArgumentException {
        if (days <= vacationDays) {
            vacationDays -= days;
        } else {
            throw new IllegalArgumentException("Not that many vacation hours available.");
        }
    }
    
    //because vacation days acruued aren't exactly divisible by max days

    protected float round(float days) {
        BigDecimal d = new BigDecimal(days);
        d = d.setScale(3, RoundingMode.HALF_UP);
        days = d.floatValue();
        return days;
    }

}
