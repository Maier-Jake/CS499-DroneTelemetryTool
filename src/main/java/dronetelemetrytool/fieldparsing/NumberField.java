package dronetelemetrytool.fieldparsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


public class NumberField extends Field implements java.io.Serializable {
    List<Double> myNumbers = new ArrayList<>(); // All data values in order, including any null values.
    List<Double> mySortedNumbers; // Sorted List of all non-null data values
    List<Double> myConvertedNumbers; // Sorted List of all non-null data values
    public String chosenUnit="";
    public String originalUnit="";
    public String unitType="";

    int nullCounter = 0;
    private int i = 0;
    private int j = 0;
    public UnitConverter uc = new UnitConverter();

    public NumberField(Field myField) {
        super(myField.myName);
        super.rawData = myField.rawData;
        parseNumberField();
        getSortedNumbers();
    }

    public void convert(String type, String from, String to) {
        this.unitType = type;
        this.originalUnit = from;
        this.chosenUnit = to;
        this.myNumbers = this.uc.convert(this.unitType,
                this.chosenUnit, this.originalUnit, this.myNumbers);
        getSortedNumbers();
    }

    // Returns the maximum data value in this NumberField.
    public Double getMaxValue() { return mySortedNumbers.get(mySortedNumbers.size()-1); }

    // Returns the minimum data value in this NumberField.
    public Double getMinValue(){ return mySortedNumbers.get(0); }

    // Returns the mean value of this NumberField's data.
    public Double getMean() { return getSum()/mySortedNumbers.size(); }

    // Returns the sum of this NumberField's data.
    public Double getSum() {
        Double sum = 0d;
        for (Double f : mySortedNumbers) {
            sum += f;
        }
        return sum;
    }

    // Returns the standard deviation of the dataset.
    public Double getStandardDeviation() {
        int size = mySortedNumbers.size();
        Double mean = getMean();
        Double squares = 0d;
        for (Double f : mySortedNumbers) {
            squares += Math.pow(f - mean, 2);
        }
        return Math.pow(squares/size, .5f);
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

    // Get the next Double value in the Field from the current index.
    public Double getNext() {
        this.j = this.i;
        this.i = this.i+1;
        if (!(this.j >= this.myNumbers.size())) {
            return this.myNumbers.get(this.j);
        } else {
            return null;
        }

    }

    // Print the data point at the provided index.
    public void printDataAt(int index) {
        System.out.println(myNumbers.get(index));
    }

    // Parse the string values of the provided Field into Doubles, adding null values instead of ""
    private void parseNumberField() {
        for (String number : super.getRawData()) {
            try {
                Double tmpDouble = Double.parseDouble(number);
                myNumbers.add(tmpDouble);
            } catch (NumberFormatException e) {
                nullCounter++;
                myNumbers.add(null);
            }
        }
    }

    // Update this NumberField with a new data array. This will probably only
    // be called if there is a unit conversion.
    public void setNumberData(List<Double> newData) {
        this.myNumbers = newData;
        this.getSortedNumbers();
        this.setIndex(0);
    }

    // Fill in the mySortedNumbers attribute with a new arraylist including
    // all non-null data values in ascending order.
    private void getSortedNumbers(){
        this.mySortedNumbers = new ArrayList<>();
        for (int i=0 ; i<this.myNumbers.size() ; i++) {
            if (this.myNumbers.get(i)==null) { continue; }
            this.mySortedNumbers.add(this.myNumbers.get(i));
        }
        Collections.sort(this.mySortedNumbers);
    }
    public List<Double> getNumberData() { return this.myNumbers; }
}
