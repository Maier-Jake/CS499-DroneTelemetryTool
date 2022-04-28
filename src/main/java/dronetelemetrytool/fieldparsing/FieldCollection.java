package dronetelemetrytool.fieldparsing;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.util.List;
import java.io.*;
import java.util.ArrayList;

public class FieldCollection {
    // After a subset of Fields is selected and typed, the typedFields array
    // holds an array for each of the Field subtypes with their data as the
    // appropriate type. Each type is indexed by its type code.
    List<ArrayList<Field>> typedFields = new ArrayList<>();
    // fields attribute stores each column of the CSV as untyped
    // Field() object with an array of String data and a title
    List<Field> fields;
    String[] fieldNames;
    int rowCount;
    TypeChecker myTypeChecker = new TypeChecker();

    public FieldCollection() {
        this.typedFields.add(new ArrayList<Field>()); // Number type
        this.typedFields.add(new ArrayList<Field>()); // Time type
        this.typedFields.add(new ArrayList<Field>()); // Boolean type
        this.typedFields.add(new ArrayList<Field>()); // String type
        this.typedFields.add(new ArrayList<Field>()); // Null type
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

        // Set all the Fields' types using the TypeChecker.
        loadFieldTypes();

        // Get the type of each selected field and cast it to the appropriate type Field.
        int type;
        for (Field tmpField : fields ) {
            type = tmpField.getType();
            switch(type) {
                case 0:
                    this.typedFields.get(0).add(new NumberField(tmpField));
                    break;
                case 1:
                    this.typedFields.get(1).add(new TimeField(tmpField));
                    break;
                case 2:
                    this.typedFields.get(2).add(new BoolField(tmpField));
                    break;
                case 3:
                    this.typedFields.get(3).add(new StringField(tmpField));
                    break;
                case 4:
                    this.typedFields.get(4).add(new NullField(tmpField));
                    break;
            }
        }
    }

    // Returns the typed Field corresponding to the provided title. Returns null if the Field isn't found.
    public Field getField(String name) {
        for (ArrayList<Field> fa : this.typedFields ) {
            for (Field f : fa) {
                if (f.getName().equals(name)) {
                    return f;
                }
            }
        }
        System.out.println("Couldn't find Field "+name+" "+this.typedFields.size()+" "+this.typedFields.get(0).size());
        return null;
    }

    public List<ArrayList<Field>> getTypedFields() { return this.typedFields; }

    public ArrayList<NumberField> getNumberFields() {
        ArrayList<NumberField> nfs = new ArrayList<>();
        for ( Field nf : this.typedFields.get(0)) {
            nfs.add((NumberField) nf);
        }
        return nfs;
    }

    public ArrayList<TimeField> getTimeFields() {
        ArrayList<TimeField> tfs = new ArrayList<>();
        for ( Field tf : this.typedFields.get(1)) {
            tfs.add((TimeField) tf);
        }
        return tfs;
    }

    public ArrayList<BoolField> getBoolFields() {
        ArrayList<BoolField> bfs = new ArrayList<>();
        for ( Field bf : this.typedFields.get(2)) {
            bfs.add((BoolField) bf);
        }
        return bfs;
    }

    public ArrayList<StringField> getStringFields() {
        ArrayList<StringField> sfs = new ArrayList<>();
        for ( Field sf : this.typedFields.get(3)) {
            sfs.add((StringField) sf);
        }
        return sfs;
    }

    public ArrayList<NullField> getNullFields() {
        ArrayList<NullField> nfs = new ArrayList<>();
        for ( Field nf : this.typedFields.get(4)) {
            nfs.add((NullField) nf);
        }
        return nfs;
    }

    public List<Field> getFields() { return this.fields; }

    public ArrayList<String> getHeaders() {
        ArrayList<String> temp = new ArrayList<>();
        for (String s : fieldNames)
            temp.add((s));
        return temp;
    }

    public void loadFieldTypes() {
        int tmpType;
        for (int i=0 ; i<fields.size() ; i++) {
            tmpType = myTypeChecker.getType(fields.get(i));
            fields.get(i).setType(tmpType);
        }
    }

    public void getDataSample(int start, int depth, int length) {
        System.out.println("Got "+rowCount+" rows.");
        for (int i = start ; i<depth ; i++) {
            fields.get(i).printData(length);
        }
    }

    public int fieldLength() {
        return fields.get(0).getLength();
    }
}