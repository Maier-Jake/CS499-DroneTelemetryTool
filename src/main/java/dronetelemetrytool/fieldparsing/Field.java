package dronetelemetrytool.fieldparsing;

import java.util.ArrayList;
import java.util.List;


public class Field {
    public String myName;
    public List<String> rawData;
    public int currentIndex;
    public int type;

    public Field(String name) {
        myName = name;
        rawData = new ArrayList<String>();
        currentIndex = 0;
    }

    void addRawDatum(String datum) { rawData.add(datum); }

    public int getLength() {
        return rawData.size();
    }

    List<String> getRawData() { return rawData; }

    public void printDataAt(int index) {
//        System.out.println(rawData.get(index));
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

    public void printData(int len) {
        for (int j=0 ; j<len ; j++) {
            String tmp = rawData.get(j);
            if (tmp=="") {
//                System.out.print("NULL,");
            }
            else {
//                System.out.print(rawData.get(j)+",");
            }
        }
//        System.out.println();
    }

    public boolean hasNext()
    {
        if (currentIndex < rawData.size())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean hasPrev()
    {
        if (currentIndex > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
