package dronetelemetrytool.gaugeselection

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.util.List;
import java.util.Dictionary;
import java.util.Hashtable;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;


public class FieldCollection {
    List<Field> fields;
    String[] fieldNames;
    int rowCount;

    public void loadCSV(FileReader csvFileReader) {
        CSVReader reader = new CSVReaderBuilder(csvFileReader).build();
        fields = new ArrayList<Field>();
        try {
            List<String[]> csvData = reader.readAll();
            rowCount = 0;
            int index;
            fieldNames = csvData.get(0);
            for (String name : fieldNames) {
                System.out.println("Loading field name "+name);
                fields.add(new Field(name));
            }
            for (String[] row : csvData) {
                // Skip the header line
                if (rowCount == 0) {
                    rowCount++;
                    continue;
                }

                index = 0;
                while (index < row.length) {
                    fields.get(index).addDatum(row[index]);
                    index++;
                }
                rowCount++;
            }
        } catch (Exception csvException) {
            System.out.println("CSV reader exception");
            csvException.printStackTrace();
        }
        getDataSample(0, 10, 10);
    }

    public void getDataSample(int start, int depth, int length) {
        System.out.println("Got "+rowCount+" rows.");
        for (int i = start ; i<depth ; i++) {
            fields.get(i).printData(length);
        }
    }
}
