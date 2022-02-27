package dronetelemetrytool.gaugeselection

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TypeChecker {
    public TypeChecker() {
        Pattern timePattern = Pattern.compile(
                "(\\d{2})/(\\d{2})/(\\d{4}) (\\d{2}):(\\d{2}):(\\d{3})");
        Pattern floatPattern = Pattern.compile("[+-]?\\d*\\.?\\d*");
        Pattern intPattern = Pattern.compile("[+-]?\\d");
    }
    private boolean tryDate(String datum) {

    }
    private boolean tryNumber(String datum) {

    }
}
