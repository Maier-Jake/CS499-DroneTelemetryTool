package dronetelemetrytool.fieldparsing;

import java.util.regex.Pattern;
import java.util.Arrays;

public class TypeChecker {
    Pattern timePattern = Pattern.compile(
            "(\\d{4})/(\\d{2})/(\\d{2}) (\\d{2}):(\\d{2}):(\\d{2})");
    Pattern floatPattern = Pattern.compile("[+-]?\\d*\\.?\\d*");
    Pattern intPattern = Pattern.compile("[+-]?\\d");
    Pattern boolPattern = Pattern.compile("True|False", Pattern.CASE_INSENSITIVE);
    Pattern nullPattern = Pattern.compile("");

    public TypeChecker() {}

    public int getType(Field tmpField) {
        /**
         * Detects and returns the type of the provided field, encoded as an integer.
         * 0 : Number type
         * 1 : Time type
         * 2 : Boolean type
         * 3 : String type
         * 4 : Null type
         */
        // Set up an array to keep track of the counts detected for each type.
        int[] counts = {0, 0, 0, 0, 0};
        String[] typeMappings = {"Num", "Time", "Bool", "Str", "Null"};
        int type = 0;
        float tolerance = .05f;

        // Count the occurrences of each type in the Field.
        for ( String datum : tmpField.getRawData() ) {
            if (nullPattern.matcher(datum).matches()) { counts[4]++; }
            else if (floatPattern.matcher(datum).matches() || intPattern.matcher(datum).matches()) {
                counts[0]++;
            }
            else if (boolPattern.matcher(datum).matches()) { counts[2]++; }
            else if (timePattern.matcher(datum).find()) { counts[1]++; }
            else { counts[3]++; }
        }

        // The type code is the index of the maximum value in the counts array.
        for (int i = 0 ; i < counts.length ; i++) {
            type = counts[i] > counts[type] ? i : type;
        }

        System.out.print(" ("+typeMappings[type]+")\t");
        for (int j = 0 ; j < counts.length ; j++) {
            System.out.print(counts[j]+",");
        }
        System.out.println("\t"+tmpField.myName);

        // If this is a null type, check if there's a nonzero second-best. If so, set the type to
        // the second-best type; the data is just sparse. Otherwise, this is truly a null type.
        if (type==4) {
            int[] tmpCounts = Arrays.copyOf(counts, 4);
            int second = 0;
            for (int j=0; j<tmpCounts.length ; j++ ) {
                second = tmpCounts[j] > tmpCounts[second] ? j : second;
            }
            if (tmpCounts[second]!=0) {
                type = second;
                System.out.println("\t\tSparse data; defaulting to type "+typeMappings[second]);
            }
        }

        // Report Null type count and any significant
        for (int k = 0 ; k < counts.length ; k++) {
            if (k == 4 && counts[k]!=0) {
                //System.out.println("\t\tNull types: "+counts[k]);
            }
            else if (type != k && counts[k]/counts[type] > tolerance ) {
                System.out.println("\t\tAlt type " + k + " has " + counts[k] + " members.");
            }
        }

        return type;
    }

}
