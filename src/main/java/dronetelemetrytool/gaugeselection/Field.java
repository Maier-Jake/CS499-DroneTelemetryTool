package dronetelemetrytool.gaugeselection;

import java.util.ArrayList;
import java.util.List;


public class Field {
    String myName;
    List<String> rawData;
    int type;

    public Field(String name) {
        myName = name;
        rawData = new ArrayList<String>();
    }

    void addRawDatum(String datum) { rawData.add(datum); }

    public int getLength() {
        return rawData.size();
    }

    List<String> getRawData() { return rawData; }

    public void printDataAt(int index) {
        System.out.println(rawData.get(index));
    }
    public void printDataLength() { printData(rawData.size()); }

    void setType(int type) {
        /**
         * Set the coded type of this Field object.
         *
         * Type codes:
         * 0 : Number type
         * 1 : Time type
         * 2 : Boolean type
         * 3 : Null type
         * 4 : String type
         */
        this.type = type;
    }

    int getType() { return this.type; }

    // Print the first n data points of the array.
    public void printData(int n) {
        for (int j=0 ; j<n ; j++) {
            String tmp = rawData.get(j);
            if (tmp=="") {
                System.out.print("NULL,");
            }
            else {
                System.out.print(rawData.get(j)+",");
            }
        }
        System.out.println();
    }

    // Shell function for Field subclasses that don't need to implement an index.
    public boolean setIndex(int index) { return true; }

    // Shell functions for Null Fields.
    public Object getNext() { return null; }
}
