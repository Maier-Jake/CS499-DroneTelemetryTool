package dronetelemetrytool.fieldparsing;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class StringField extends Field{
    transient List<String> myStrings = new ArrayList<>();
    transient int nullCounter = 0;
    transient private int i;
    transient private int j;

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

    // Set the iteration index of the StringField; returns True if successful.
    // The index parameter refers to the next data point that will be returned
    // if the update is successful.
    public boolean setIndex(int index) {
        if (0<=index && index<myStrings.size()) {
            this.i = index;
            return true;
        } else { return false; }
    }

    // Returns the next j
    public String getNext() {
        this.j = this.i;
        this.i = this.i+1;
        if (!(this.j >= this.myStrings.size())) {
            return this.myStrings.get(this.j);
        } else {
            return null;
        }
    }

    public void printDataAt(int index) {
        System.out.println(myStrings.get(index));

    }
}
