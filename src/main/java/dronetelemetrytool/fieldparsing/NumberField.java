package dronetelemetrytool.fieldparsing;

import java.util.ArrayList;
import java.util.List;


public class NumberField extends Field {
    List<Float> myNumbers = new ArrayList<>();
    int nullCounter = 0;

    public NumberField(Field myField) {
        super(myField.myName);
        super.rawData = myField.rawData;
        parseNumberField();
    }

    private void parseNumberField() {
        for (String number : super.getRawData()) {
            try {
                Float tmpFloat = Float.parseFloat(number);
                myNumbers.add(tmpFloat);
            } catch (NumberFormatException e) {
                    System.out.println("Error parsing number "+number+"; adding a null value.");
                    nullCounter++;
                    myNumbers.add(null);
                return;
            }

        }
    }

    public void printDataAt(int index) {
        System.out.println(myNumbers.get(index));
    }
}
