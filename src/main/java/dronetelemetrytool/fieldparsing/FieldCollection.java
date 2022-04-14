package dronetelemetrytool.fieldparsing;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;

public class FieldCollection {
    // After a subset of Fields is selected and typed, the typedFields array
    // holds an array for each of the Field subtypes with their data as the
    // appropriate type. Each type is indexed by its type code.
    List<ArrayList<Field>> typedFields = new ArrayList<>();
    // fields attribute stores each column of the CSV as untyped
    // Field() object with an array of String data and a title
    private List<Field> fields;
    String[] fieldNames;
    int rowCount;
    TypeChecker myTypeChecker = new TypeChecker();

    public FieldCollection() {
        typedFields.add(new ArrayList<Field>()); // Number type
        typedFields.add(new ArrayList<Field>()); // Time type
        typedFields.add(new ArrayList<Field>()); // Boolean type
        typedFields.add(new ArrayList<Field>()); // String type
        typedFields.add(new ArrayList<Field>()); // Null type
    }

    public List<Field> getFields()
    {
        return fields;
    }

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
                    fields.get(index).addRawDatum(row[index]);
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

        ArrayList<String> selectedFields = new ArrayList<String>();
        selectedFields.add(fields.get(0).myName);
        selectedFields.add(fields.get(3).myName);
        selectedFields.add(fields.get(22).myName);
        selectedFields.add(fields.get(26).myName);

//        System.out.println(selectedFields);
        // Get the type of each selected field and cast it to the appropriate type Field.
        int type;
        for (Field tmpField : fields ) {
            if (!selectedFields.contains(tmpField.myName)) { continue; }
            type = tmpField.getType();
            switch(type) {
                case 0:
                    typedFields.get(0).add(new NumberField(tmpField));
                    break;
                case 1:
                    typedFields.get(1).add(new TimeField(tmpField));
                    break;
                case 2:
                    typedFields.get(2).add(new BoolField(tmpField));
                    break;
                case 3:
                    typedFields.get(3).add(new StringField(tmpField));
                    break;
                case 4:
                    typedFields.get(4).add(new NullField(tmpField));
                    break;
            }
//            System.out.println("Loaded "+tmpField.myName+" as type "+type);
            /*
            if (type == 1) {
                TimeField tf = new TimeField(tmpField);
                System.out.println(tmpField.myName);
                for (int i=0 ; i<20 ; i++) {
                    tf.printAt(i);
                }
            }
            */
        }
    }

    public ArrayList<String> getHeaders()
    {
        ArrayList<String> temp = new ArrayList<>();
        for (String s: fieldNames) {
            temp.add(s);
        }
        return temp;
    }

    public ArrayList<NumberField> getNumberFields() {
        ArrayList<NumberField> nfs = new ArrayList<>();
        for ( Field nf : typedFields.get(0)) {
            nfs.add((NumberField) nf);
        }
        return nfs;
    }

    public ArrayList<TimeField> getTimeFields() {
        ArrayList<TimeField> tfs = new ArrayList<>();
        for(Field f : fields)
        {
            if (f.type == 1)
            {
                tfs.add((TimeField) f);
            }
        }
        return tfs;
    }

    public ArrayList<BoolField> getBoolFields() {
        ArrayList<BoolField> bfs = new ArrayList<>();
        for ( Field bf : typedFields.get(2)) {
            bfs.add((BoolField) bf);
        }
        return bfs;
    }

    public ArrayList<StringField> getStringFields() {
        ArrayList<StringField> sfs = new ArrayList<>();
        for ( Field sf : typedFields.get(3)) {
            sfs.add((StringField) sf);
        }
        return sfs;
    }

    public ArrayList<NullField> getNullFields() {
        ArrayList<NullField> nfs = new ArrayList<>();
        for ( Field nf : typedFields.get(4)) {
            nfs.add((NullField) nf);
        }
        return nfs;
    }

    public void loadFieldTypes() {
        int tmpType;
        for (Field tmpField : fields ) {
            tmpType = myTypeChecker.getType(tmpField);
            tmpField.setType(tmpType);
        }
    }

    public void getDataSample(int start, int depth, int length) {
//        System.out.println("Got "+rowCount+" rows.");
        for (int i = start ; i<depth ; i++) {
            fields.get(i).printData(length);
        }
    }
}
