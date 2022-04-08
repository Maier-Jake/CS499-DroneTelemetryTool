package dronetelemetrytool.gaugeselection;

import java.util.ArrayList;
import java.util.List;
import java.lang.Boolean;

public class BoolField extends Field{
    List<Boolean> myBools = new ArrayList<>();
    int nullCounter = 0;

    public BoolField(Field myField) {
        super(myField.myName);
        super.rawData = myField.rawData;
        parseBoolField();
    }
    private void parseBoolField() {
        for (String b : super.getRawData()) {
            if (b.toLowerCase() == "true" ) {
                myBools.add(Boolean.valueOf(true));
            } else if (b.toLowerCase() == "false") {
                myBools.add(Boolean.valueOf(false));
            } else {
                nullCounter++;
                myBools.add(null);
            }
        }
    }

    public void printDataAt(int index) { System.out.println(myBools.get(index)); }
}
