package domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TripStat implements Serializable {
    private String startCell;
    private String endCell;
    private int counter;
    private Date pickupDatetime;
    private Date dropoffDatetime;

    public TripStat(String startCell, String endCell, int count, Date pickupDatetime, Date dropoffDatetime) {
        super();

        this.startCell = startCell;
        this.endCell = endCell;
        this.counter = count;
        this.pickupDatetime = pickupDatetime;
        this.dropoffDatetime = dropoffDatetime;

    }

    public String toString() {
        return "startCell=" + startCell + ", endCell=" + endCell + ", counter=" + counter;
    }

    public Date getPickupDatetime() {
        return pickupDatetime;
    }

    public void setPickupDatetime(Date pickupDatetime) {
        this.pickupDatetime = pickupDatetime;
    }

    public Date getDropoffDatetime() {
        return dropoffDatetime;
    }

    public void setDropoffDatetime(Date dropoffDatetime) {
        this.dropoffDatetime = dropoffDatetime;
    }

    public String getStartCell() {
        return startCell;
    }

    public void setStartCell(String startCell) {
        this.startCell = startCell;
    }

    public String getEndCell() {
        return endCell;
    }

    public void setEndCell(String endCell) {
        this.endCell = endCell;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int count) {
        this.counter = count;
    }


}
