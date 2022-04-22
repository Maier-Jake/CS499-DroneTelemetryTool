package dronetelemetrytool.fieldparsing;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Field implements Serializable {
    String myName;
    transient List<String> rawData;
    transient int type;
    private transient int i = 0;
    private transient int j = 0;

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

    public int getType() { return this.type; }

    public String getName() { return this.myName; }

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

    // Set the iteration index of the NumberField; returns True if successful.
    // The index parameter refers to the next data point that will be returned
    // if the update is successful.
    public boolean setIndex(int index) {
        if (0<=index && index<this.rawData.size()) {
            this.i = index;
            return true;
        } else { return false; }
    }

    public Object getNext() { return getNext(1);}
    // Get the next Double value in the Field from the current index.
    public Object getNext(int step) {
        this.j = this.i;
        // Add the step and array size to ensure we get a positive number.
        this.i = (this.i+step);
        if (!(this.j >= this.rawData.size())) {
            return this.rawData.get(this.j);
        } else {
            return null;
        }
    }

    public boolean hasNext()
    {
        return this.i < rawData.size();

    }
    public boolean hasPrev()
    {
        if (this.i > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
