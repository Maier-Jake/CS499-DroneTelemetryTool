package dronetelemetrytool.fieldparsing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.lang.Long;
import java.util.List;

public class TimeField extends Field {
    SimpleDateFormat timeTemplate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    SimpleDateFormat decimalTimeTemplate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
    List<Long> myTimes = new ArrayList<>();
    int nullCounter = 0;

    public TimeField(Field myField) {
        super(myField.myName);
        super.rawData = myField.rawData;
        parseTimeField();
    }

    private void parseTimeField() {
        for (String time : super.getRawData()) {
            try {
                myTimes.add(decimalTimeTemplate.parse(time).getTime());
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
    }

    public void printDataAt(int index) {
        System.out.println(myTimes.get(index));
    }
}
