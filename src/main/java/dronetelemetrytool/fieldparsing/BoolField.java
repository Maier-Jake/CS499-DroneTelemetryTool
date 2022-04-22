package dronetelemetrytool.fieldparsing;

import java.util.ArrayList;
import java.util.List;
import java.lang.Boolean;

public class BoolField extends Field{
    List<Boolean> myBools = new ArrayList<>();
    int nullCounter = 0;
    private int i;
    private int j;

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

    // Set the iteration index of the BoolField; returns True if successful.
    // The index parameter refers to the next data point that will be returned
    // if the update is successful.
    public boolean setIndex(int index) {
        if (0<=index && index<myBools.size()) {
            this.i = index;
            return true;
        } else { return false; }
    }

    // Get the next Bool value in the Field from the current index.
    public Boolean getNext() {
        this.j = this.i;
        this.i = this.i+1;
        if (!(this.j >= this.myBools.size())) {
            return this.myBools.get(this.j);
        } else {
            return null;
        }
    }

    public void printDataAt(int index) { System.out.println(myBools.get(index)); }
}
