package com.example.assignment42_final;

public class date {
    private int year;
    private int month;
    private int day;
    public date(int month, int day, int year){
        this.month=month;
        this.day=day;
        this.year=year;
    }
    public boolean dateLessThan(date compareDate){
        if
        (this.year< compareDate.year||
                (this.year==compareDate.year&&this.month< compareDate.month)||
                (this.year==compareDate.year&&this.month==compareDate.month&&this.day<compareDate.day)) {
            return true;
        }else
            return false;
    }
    public String getDateinString(){
        return this.year+"-"+this.month+"-"+this.day;
    }
}
