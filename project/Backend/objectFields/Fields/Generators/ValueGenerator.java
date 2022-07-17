package Backend.objectFields.Fields.Generators;

public abstract class ValueGenerator {
    private static ValueGenerator valueGenerator;
    public static boolean debug = false;
    public static ValueGenerator getInstance() {
        if (valueGenerator == null) {
            if (debug)
                valueGenerator = new DeterministicGenerator();
            else
                valueGenerator = new RandomGenerator();
        }
        return valueGenerator;
    }
    public abstract int getValue(int amount);
}
