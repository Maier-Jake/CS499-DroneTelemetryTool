package dronetelemetrytool.gaugeselection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.lang.Long;
import java.util.List;

public class TimeField {
    SimpleDateFormat timeTemplate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    SimpleDateFormat decimalTimeTemplate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    List<Long> myTimes = new ArrayList<>();
    Field myField;

    public TimeField(Field myField) {
        this.myField = myField;
        parseTimeField();
    }

    private void parseTimeField() {
        for (String time : myField.getDataList()) {
            try {
                myTimes.add(decimalTimeTemplate.parse(time).getTime());
            } catch (ParseException e) {
                try {
                    myTimes.add(timeTemplate.parse(time).getTime());
                } catch (ParseException ex) {
                    e.printStackTrace();
                    System.out.println("Error parsing time "+time+"; adding a null value.");
                    myTimes.add(null);
                    return;
                }

            }
        }
    }

    void printTimeAt(int index) {
        System.out.println(myTimes.get(index));
    }
}
