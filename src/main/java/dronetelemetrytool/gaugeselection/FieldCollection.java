package dronetelemetrytool.gaugeselection;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;

public class FieldCollection {
    List<Field> fields;
    String[] fieldNames;
    int rowCount;
    TypeChecker myTypeChecker = new TypeChecker();

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

                // Add the appropriate data points to the Field corresponding to each row.
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

        //getDataSample(0, 10, 10);

        loadFieldTypes();

        for (Field tmpField : fields ) {
            if (tmpField.getType() == 1) {
                TimeField tf = new TimeField(tmpField);
                System.out.println(tmpField.myName);
                /*
                for (int i=0 ; i<20 ; i++) {
                    tf.printTimeAt(i);
                }
                */
            }
        }
    }

    public void loadFieldTypes() {
        int tmpType;
        for (Field tmpField : fields ) {
            tmpType = myTypeChecker.getType(tmpField);
            tmpField.setType(tmpType);
        }
    }

    public void getDataSample(int start, int depth, int length) {
        System.out.println("Got "+rowCount+" rows.");
        for (int i = start ; i<depth ; i++) {
            fields.get(i).printData(length);
        }
    }
}
