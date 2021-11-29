package com.example.daysduk;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.widget.CalendarView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;
import java.util.Date;

public class SelectedDayDecorator implements DayViewDecorator {
    private CalendarDay date;

    public SelectedDayDecorator(){
        date=CalendarDay.today();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return date!=null && day.equals(date);
    }

    @Override
    public void decorate(DayViewFacade dayViewFacade) {
        dayViewFacade.addSpan(new StyleSpan(Typeface.BOLD));
        dayViewFacade.addSpan(new RelativeSizeSpan(1.3f));
        dayViewFacade.addSpan(new ForegroundColorSpan(Color.YELLOW));




    }

    public void setDate(Date date){
        this.date = CalendarDay.from(date);
    }

}
