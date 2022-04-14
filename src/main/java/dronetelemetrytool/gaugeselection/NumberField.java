package dronetelemetrytool.gaugeselection;

import java.util.ArrayList;
import java.util.List;


public class NumberField extends Field {
    List<Float> myNumbers = new ArrayList<>();
    int nullCounter = 0;
    private int i = 0;
    private int j = 0;

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

    // Set the iteration index of the NumberField; returns True if successful.
    // The index parameter refers to the next data point that will be returned
    // if the update is successful.
    public boolean setIndex(int index) {
        if (0<=index && index<myNumbers.size()) {
            this.i = index;
            return true;
        } else { return false; }
    }

    // Get the next Float value in the Field from the current index.
    public Float getNext() {
        this.j = this.i;
        this.i = (this.i+1)%myNumbers.size();
        return this.myNumbers.get(this.j);
    }

    public void printDataAt(int index) {
        System.out.println(myNumbers.get(index));
    }
}
