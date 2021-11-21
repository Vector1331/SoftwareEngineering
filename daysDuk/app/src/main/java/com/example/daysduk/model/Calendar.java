package com.example.daysduk.model;
//Calendar 모델 정의 클래스
public class Calendar {
    private String cal_todaysdate;

    private String cal_thismonth;

    private int cal_diaryfrequency;
    //생성자
    public Calendar(){}
    public Calendar(String cal_todaysdate, String cal_thismonth, int cal_diaryfrequency){

    }
    //getter setter
    public String getCal_todaysdate() {
        return cal_todaysdate;
    }

    public String getCal_thismonth() {
        return cal_thismonth;
    }

    public int getCal_diaryfrequency() {
        return cal_diaryfrequency;
    }

    public void setCal_todaysdate(String cal_todaysdate) {
        this.cal_todaysdate = cal_todaysdate;
    }

    public void setCal_thismonth(String cal_thismonth) {
        this.cal_thismonth = cal_thismonth;
    }

    public void setCal_diaryfrequency(int cal_diaryfrequency) {
        this.cal_diaryfrequency = cal_diaryfrequency;
    }
}
