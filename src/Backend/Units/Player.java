package Backend.Units;

import Backend.Tiles.Empty;
import Backend.Tiles.Unit;
import Backend.Tiles.Wall;
import Backend.objectFields.Fields.Experience;

import java.util.List;

public abstract class Player extends Unit {
    public static final char CHAR = '@';
    public static final char DEAD_CHAR = 'X';
    public static char specialAbility;
    private final int ATTACK_ADDITION = 4;
    private final int DEFENCE_ADDITION = 1;
    private final int HEALTH_ADDITION = 10;


    protected Experience experience;
    protected int level;

    public Player(String name, int healthAmount, int attackPoints, int defencePoints){
        super(CHAR,name,healthAmount,attackPoints,defencePoints);
        experience = new Experience();
        level = 1;
    }

    public void levelUp(){
        experience.levelUp(level);
        level++;
        health.updateLevel(HEALTH_ADDITION,level);
        attackPoints = attackPoints + (ATTACK_ADDITION * level);
        defencePoints = defencePoints + (DEFENCE_ADDITION * level);
    }

    public abstract void cast(List<Enemy> enemies);
    public void deadChar(){
        represent = DEAD_CHAR;
    }

    public void cast(Enemy enemy,int abilityAttack){
        int enemyDefence = enemy.rollDefence();
        int defenceDamage = Math.min(abilityAttack, enemyDefence);
        int healthDamage = abilityAttack - defenceDamage;
        enemy.dealDamage(healthDamage);
        messageCall(String.format("%s rolled %d defence points\n%s hit %s for %d ability damage\n",enemy.getName(),enemyDefence,getName(),enemy.getName(),healthDamage));
        if(!enemy.alive()) {
            experience.addExperience(enemy.getExperienceValue());
            while (experience.isFull())
                levelUp();
            enemy.deathCall(this);
        }
    }

    public void accept(Unit u){
        u.visit(this);
    }
    public void visit(Empty empty){
        tick();
        super.visit(empty);
    }

    @Override
    public void visit(Wall w) {
        tick();
    }


    public void visit(Enemy enemy){
        tick();
        super.battle(enemy);
        if(!enemy.alive()){
            experience.addExperience(enemy.getExperienceValue());
            while (experience.isFull())
                levelUp();
        }
    }

    public void deathCall(Unit u){
        deathCallBack.call(u);
    }

    public void visit(Player player){ tick();}
    public String toString(){
        return String.format("name: %s,  Health: %s,  Attack: %d,  Defence: %d,  Experience: %s,  level: %d  ",getName(),health.toString(),attackPoints,defencePoints,experience.toString(),level);
    }
    public abstract int getRange();
    public abstract void tick();
    @Override
    public String toCharString(){
        if(alive())
            return super.toCharString();
        else
            return DEAD_CHAR + "";
    }
}
