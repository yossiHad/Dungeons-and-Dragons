package Backend.objectFields.Fields.Generators;

public class DeterministicGenerator extends ValueGenerator {
    @Override
    public int getValue(int amount) {
        return (int)(0.25 * amount);
    }
}
