package dronetelemetrytool.fieldparsing;

import java.util.List;
import java.util.ArrayList;

public class StringField extends Field{
    List<String> myStrings = new ArrayList<>();
    int nullCounter = 0;

    public StringField(Field myField) {
        super(myField.myName);
        super.rawData = myField.rawData;
        parseStringField();
    }

    private void parseStringField() {
        for (String s : super.getRawData()) {
            if (s!="") {
                myStrings.add(s);
            } else {
                nullCounter++;
                myStrings.add(null);
            }
        }
    }

    public void printDataAt(int index) {
        System.out.println(myStrings.get(index));
    }
}
