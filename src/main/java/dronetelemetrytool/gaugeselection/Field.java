package dronetelemetrytool.gaugeselection;

import java.util.ArrayList;
import java.util.List;


public class Field {
    String myName;
    Float[] times;
    List<String> myData;
    int type;

    public Field(String name) {
        myName = name;
        myData = new ArrayList<String>();
    }

    void addDatum(String datum) { myData.add(datum); }

    public int getLength() {
        return myData.size();
    }

    List<String> getDataList() { return myData; }

    public void printData() { printData(myData.size()); }

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

    public void printData(int len) {
        for (int j=0 ; j<len ; j++) {
            String tmp = myData.get(j);
            if (tmp=="") {
                System.out.print("NULL,");
            }
            else {
                System.out.print(myData.get(j)+",");
            }
        }
        System.out.println();
    }
}
