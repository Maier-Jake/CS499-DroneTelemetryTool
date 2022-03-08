package dronetelemetrytool.gaugeselection;

import java.util.ArrayList;
import java.util.List;


public class Field {
    String myName;
    Float[] times;
    List<String> myData;

    public Field(String name) {
        myName = name;
        myData = new ArrayList<String>();
    }

    public void addDatum(String datum) {
        myData.add(datum);
    }

    public int getLength() {
        return myData.size();
    }

    public void printData() { printData(myData.size()); }

    public void printData(int len) {
        for (int j=0 ; j<len ; j++) { System.out.print(myData.get(j)+","); }
        System.out.println();
    }
}
