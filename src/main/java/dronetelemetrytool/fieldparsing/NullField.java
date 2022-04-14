package dronetelemetrytool.fieldparsing;

import java.util.List;
import java.util.ArrayList;

public class NullField extends Field{
    int nullCounter = 0;
    public NullField(Field myField) {
        super(myField.myName);
        super.rawData = myField.rawData;
        nullCounter = rawData.size();
    }

    public void printDataAt(int index) {
        System.out.println("null");
    }
}
