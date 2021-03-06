package com.example.daysduk;


import android.graphics.Color;
import android.text.style.ForegroundColorSpan;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

public class SundayDecorator implements DayViewDecorator {
    private final Calendar calendar = Calendar.getInstance();
    public SundayDecorator() {

    }

    @Override
    public boolean shouldDecorate(CalendarDay calendarDay) {
        calendarDay.copyTo(calendar);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);

        return weekDay == Calendar.SUNDAY;
    }

    @Override
    public void decorate(DayViewFacade dayViewFacade) {
        dayViewFacade.addSpan(new ForegroundColorSpan(Color.RED));
    }
}
