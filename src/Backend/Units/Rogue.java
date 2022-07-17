package Backend.Units;

import Backend.objectFields.Abilities.FanOfKnives;

import java.util.List;

public class Rogue extends Player {
    private final int ABILITY_RANGE = 2;
    private final int ATTACK_ADDITION = 3;
    private FanOfKnives fanOfKnives;

    public Rogue(String name, int healthAmount, int attackPoints, int defencePoints, int cost){
        super(name,healthAmount,attackPoints,defencePoints);
        fanOfKnives = new FanOfKnives(cost,ABILITY_RANGE);
    }

    public String toString(){
        String output = super.toString();
        return String.format(output + "Energy: %s ",fanOfKnives.EnergyString());
    }

    @Override
    public void levelUp(){
        int attackBefore = attackPoints;
        int healthBefore = health.getHealthPool();
        int defenceBefore = defencePoints;
        super.levelUp();
        attackPoints = attackPoints + (ATTACK_ADDITION * level);
        fanOfKnives.updateLevel();
        messageCall(String.format("you just leveled up to level - %d: Attack:%d (+%d),  Health:%d (+%d),  Defence:%d (+%d)",level,attackPoints,(attackPoints - attackBefore),health.getHealthPool(),(health.getHealthPool() - healthBefore),defencePoints,(defencePoints - defenceBefore)));
    }
    public void tick(){
        fanOfKnives.tick();
    }

    public void cast(List<Enemy> enemies){
        if(fanOfKnives.cast()){
            messageCall(String.format("%s used fan of knives\n",getName()));
            for (Enemy e: enemies) {
                cast(e,attackPoints);
            }
        }
        else {
            messageCall(String.format("%s tried to use fan of knives but didn't have enough energy",getName()));
            tick();
        }
    }
    public int getRange(){
        return fanOfKnives.getRange();
    }
    public int getCurrentEnergy(){
        return fanOfKnives.getCurrentEnergy();
    }


}
