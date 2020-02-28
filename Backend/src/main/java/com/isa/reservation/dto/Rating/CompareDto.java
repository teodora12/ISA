package com.isa.reservation.dto.Rating;

import java.util.Date;

public class CompareDto {

    private Date today;
    private Date dropOff;

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public Date getDropOff() {
        return dropOff;
    }

    public void setDropOff(Date dropOff) {
        this.dropOff = dropOff;
    }

    CompareDto(){


    }
}
