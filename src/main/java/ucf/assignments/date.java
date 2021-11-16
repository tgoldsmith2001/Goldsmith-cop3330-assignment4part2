/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Tyler Goldsmith
 */
package ucf.assignments;

public class date {
    private String year;
    private String month;
    private String day;

    public date(String month, String day, String year){
        this.month=month;
        this.day=day;
        this.year=year;
    }

    public String getDateinString(){
        return this.year+"-"+this.month+"-"+this.day;
    }
}
