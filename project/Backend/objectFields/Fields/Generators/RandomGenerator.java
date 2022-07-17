package Backend.objectFields.Fields.Generators;

public class RandomGenerator extends ValueGenerator {
    @Override
    public int getValue(int amount) {
        return (int)(Math.random() * amount);
    }
}
