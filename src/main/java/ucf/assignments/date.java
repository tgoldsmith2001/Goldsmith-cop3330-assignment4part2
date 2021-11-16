/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Tyler Goldsmith
 */
package ucf.assignments;

public class date {
    private int year;
    private int month;
    private int day;
    public date(int month, int day, int year){
        this.month=month;
        this.day=day;
        this.year=year;
    }

    public String getDateinString(){
        return this.year+"-"+this.month+"-"+this.day;
    }
}
