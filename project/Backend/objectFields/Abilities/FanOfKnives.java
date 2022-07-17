package Backend.objectFields.Abilities;

public class FanOfKnives extends Ability {
    private final int MAX_ENERGY_VALUE = 100;
    private final int ENERGY_ADDITION = 10;
    private int cost;
    private int currentEnergy;

    public FanOfKnives(int cost, int abilitRange){
        super(abilitRange);
        this.cost = cost;
        currentEnergy = MAX_ENERGY_VALUE;
    }
    public int getCurrentEnergy(){
        return currentEnergy;
    }

    public String EnergyString(){
        return currentEnergy + "/" + MAX_ENERGY_VALUE;
    }

    public void updateLevel(){
        currentEnergy = MAX_ENERGY_VALUE;
    }
    public void tick(){
        currentEnergy = Math.min(currentEnergy + ENERGY_ADDITION, MAX_ENERGY_VALUE);
    }

    @Override
    public boolean cast() {
        boolean cast = currentEnergy >= cost;
        currentEnergy = cast ? currentEnergy - cost : currentEnergy;
        return cast;
    }
}
