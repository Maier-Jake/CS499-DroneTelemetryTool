package dronetelemetrytool.gaugeselection;

import java.util.ArrayList;
import java.util.List;
import java.util.Dictionary;
import java.util.Hashtable;

class Unit {
    String si;
    String name;
    List<String> subunits = new ArrayList<String>();
    Dictionary<String, Float> factors = new Hashtable<>();
    Dictionary<String, Float> constants = new Hashtable<>();
    public Unit(String name, String standard) {
        this.name = name;
        this.si = standard;
        this.subunits.add(this.si);
        this.factors.put(this.name, 1f);
        this.constants.put(this.name, 0f);
    }

    public String getName() { return this.name; }
    public List<String> getSubunits(){ return this.subunits; }

    // Subunit setter for constant-coefficient conversions;
    // this_subunit = factor*base_unit+constant.
    void addSubunit(String name, float factor) {
        subunits.add(name);
        factors.put(name, factor);
        constants.put(name, 0f);
    }

    // Subunit setter with optional added constant
    // this_subunit = factor*base_unit+constant.
    void addSubunit(String name, float factor, float constant) {
        subunits.add(name);
        factors.put(name, factor);
        constants.put(name, constant);
    }

    // Use this Unit and its defined subunits to convert a
    public Float convert(String from, String to, Float value) {
        // If ( x = s*a+b; y = s*c+d ) such that s is the base unit and x,y are
        // linear subunits and a/c, b/d are the conversion factors and consants
        // respectively, x=((y-d)/c)*a+b
        Float a,b,c,d;
        if (subunits.contains(from) && subunits.contains(to)) {
            a = factors.get(to);
            b = constants.get(to);
            c = factors.get(from);
            d = constants.get(from);
            return ((value-d)/c)*a+b;
        } else { return null; }
    }
}

public class UnitConverter {
    private List<String> unit_names = new ArrayList<>();
    private List<Unit> unit_array = new ArrayList<>();
    private Dictionary<String, List<String>> subunits = new Hashtable<>();
    private Unit speed;
    private Unit power;
    private Unit distance;
    private Unit temperature;

    public UnitConverter() {
        String tmpName;
        this.unit_array.add(new Unit("speed", "m/s"));
        unit_array.get(0).addSubunit("ft/s", 3.28084f);
        unit_array.get(0).addSubunit("mi/h", 3600f/1609.344f);
        unit_array.get(0).addSubunit("km/hr", 3.6f);
        this.unit_array.add(new Unit("power", "mAh"));
        unit_array.get(1).addSubunit("Coulombs", 3.6f);
        unit_array.get(1).addSubunit("Quintillion electrons", 22.4694351f);
        unit_array.get(1).addSubunit("Amp hrs", .001f);
        this.unit_array.add(new Unit("distance", "m"));
        unit_array.get(2).addSubunit("cm", 100);
        unit_array.get(2).addSubunit("ft", 3.28084f);
        unit_array.get(2).addSubunit("mi", .0006213712f);
        unit_array.get(2).addSubunit("km", .0001f);
        this.unit_array.add(new Unit("temperature", "C"));
        unit_array.get(3).addSubunit("Kelvin", 0f, 273.15f);
        unit_array.get(3).addSubunit("Farenheit", 1.8f, 32f);
        this.unit_array.add(new Unit("coordinates", "lat"));
        unit_array.get(4).addSubunit("lon", 1f);

        for (Unit u : unit_array) {
            tmpName = u.getName();
            unit_names.add(tmpName);
            subunits.put(tmpName, u.getSubunits());
            System.out.print("Added unit '"+tmpName);
            System.out.print("' with subunits: ");
            System.out.println(u.getSubunits());
        }
    }


    // Returns the subunits corresponding to a unit name
    // ie the unit name "coordinates" would will return a List <"lat", "lon">
    // Returns null if the provided unit_name is not supported.
    public List<String> getSubunits(String unit_name) {
        if (this.unit_names.contains(unit_name))
            return this.subunits.get(unit_name);
        else
            return null;
    }

    public List<String> getUnitNames() { return this.unit_names; }

}
