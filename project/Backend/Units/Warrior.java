package Backend.Units;

import Backend.objectFields.Abilities.AvengersShield;

import java.util.List;

public class Warrior extends Player {
    private final int HEALTH_ADDITION = 5;
    private final int ATTACK_ADDITION = 2;
    private final int DEFENCE_ADDITION = 1;
    private final int ABILITY_HEALTH_ADDITION = 10;
    private final int ABILITY_RANGE = 3;

    private AvengersShield Avengers;

    public Warrior(String name, int healthAmount, int attackPoints, int defencePoints,int coolDown){
        super(name,healthAmount,attackPoints,defencePoints);
        Avengers = new AvengersShield(coolDown, ABILITY_RANGE);
    }
    public String toString(){
        String output = super.toString();
        return String.format(output + "Cooldown: %s",Avengers.CollDownString());
    }

    @Override
    public void levelUp(){
        int attackBefore = attackPoints;
        int healthBefore = health.getHealthPool();
        int defenceBefore = defencePoints;
        super.levelUp();
        health.updateLevel(HEALTH_ADDITION,level);
        attackPoints = attackPoints + (ATTACK_ADDITION * level);
        defencePoints = defencePoints + (DEFENCE_ADDITION * level);
        messageCall(String.format("you just leveled up to level - %d: Attack:%d (+%d),  Health:%d (+%d),  Defence:%d (+%d)",level,attackPoints,(attackPoints - attackBefore),health.getHealthPool(),(health.getHealthPool() - healthBefore),defencePoints,(defencePoints - defenceBefore)));
    }

    public void cast(List<Enemy> enemies) {
        if(Avengers.cast()) {
            int healthUpgrade = ABILITY_HEALTH_ADDITION * defencePoints;
            messageCall(String.format("%s used Avenger's shield, healing for %d\n",getName(),healthUpgrade));
            if(!enemies.isEmpty()) {
                int random = (valueGenerator.getValue(enemies.size() - 1));
                Enemy enemy = enemies.get(random);
                int abilityAttack = (int) (health.getHealthPool() * 0.1);
                super.cast(enemy, abilityAttack);
                health.healthUpgrade(healthUpgrade);
            }
        }
        else{
            messageCall(String.format("%s tried to use Avenger's shield but its in coolDown\n",getName()));
            tick();
        }
    }

    public void tick(){
        Avengers.tick();
    }
    public int getRange(){
        return Avengers.getRange();
    }
}
