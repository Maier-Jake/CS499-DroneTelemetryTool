package dronetelemetrytool.fieldparsing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.lang.Long;
import java.util.Date;
import java.util.List;

public class TimeField extends Field {
    SimpleDateFormat timeTemplate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    SimpleDateFormat decimalTimeTemplate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    List<Long> myTimes = new ArrayList<>();
    int nullCounter = 0;
    private int i = 0;   // Index pointing to the next available data point
    private int j = 0;   // Index pointing to the last used data point
    private Long start;  // Start time as an epoch time with nanosecond resolution

    public TimeField(Field myField) {
        super(myField.myName);
        super.rawData = myField.rawData;
        parseTimeField();
    }

    private void parseTimeField() {
        for (String time : super.getRawData()) {
            try {
                myTimes.add(1000000*decimalTimeTemplate.parse(time).getTime());
            } catch (ParseException e) {
                try {
                    myTimes.add(timeTemplate.parse(time).getTime());
                } catch (ParseException ex) {
                    e.printStackTrace();
                    nullCounter++;
                    myTimes.add(null);
                    return;
                }

            }
        }
        start = myTimes.get(0);
    }

    // Set the iteration index of the TimeField; returns True if successful.
    // The index parameter refers to the next data point that will be returned
    // if the update is successful.
    public boolean setIndex(int index) {
        if (0<=index && index<myTimes.size()) {
            this.i = index;
            return true;
        } else { return false; }
    }

    // Returns the elapsed time in nanoseconds since epoch at this
    // data point since the initial time.
    public Long getNextInterval(){
        this.j = this.i;
        this.i = (this.i+1)%myTimes.size();
        return this.myTimes.get(this.j)-this.start;
    }

    // Returns a Long representing nanoseconds since the epoch
    public Long getNext() {
        this.j = this.i;
        this.i = (this.i+1)%myTimes.size();
        return this.myTimes.get(this.j);
    }

    // Returns the next time as a Date object
    public Date getNextDate() {
        this.j = this.i;
        this.i = (this.i+1)%myTimes.size();
        return new Date(this.myTimes.get(this.j)/1000000);
    }

    public void printDataAt(int index) {
        System.out.println(myTimes.get(index));
    }
}
