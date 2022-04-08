package dronetelemetrytool.gaugeselection;

import java.util.ArrayList;
import java.util.List;
import java.util.Dictionary;
import java.util.Hashtable;

class Unit {
    String si;
    String name;
    Dictionary<String, Float> conversions = new Hashtable<>();
    public Unit(String name, String standard) {
    }

    void addSubunit(String name, float conversion) { conversions.put(name, conversion); }

    String getName() { return this.name; }
}

public class UnitConverter {
    public List<String> types = new ArrayList<>();
    private Unit velocity;
    private Unit power;
    private Unit distance;
    private Unit time;
    private Unit temperature;


    public UnitConverter() {
        velocity = new Unit("speed", "m/s");
        velocity.addSubunit("ft/s", 3.28084f);
        velocity.addSubunit("mi/h", 3600f/1609.344f);
        velocity.addSubunit("km/hr", 3.6f);
        power = new Unit("power", "mAh");
    }

    public List<String> getPossibleTypes() { return this.types; }

}
