package Backend.Units;

import Backend.Tiles.Unit;
import Backend.objectFields.Fields.Position;

public abstract class Enemy extends Unit {
    protected int experienceValue;
    protected int visionRange;
    public Enemy(char represent, Position position, String name, int healthAmount, int attackPoints, int defencePoints, int experienceValue, int visionRange) {
        super(represent,position,name, healthAmount, attackPoints, defencePoints);
        this.experienceValue = experienceValue;
        this.visionRange = visionRange;
    }

    public int getExperienceValue(){
        return experienceValue;
    }

    public void accept(Unit u){
        u.visit(this);
    }

    @Override
    public void visit(Enemy enemy) {}

    public void visit(Player p){
        super.battle(p);
    }
    public abstract Position tick(Position player);
    public boolean inVision(Position p) {
        return Position.range(p,position) < visionRange;
    }
    public String toString(){
        return String.format("name: %s,  Health: %s,  Attack: %d,  Defence: %d,  ExperienceValue: %d, ",getName(),health.toString(),attackPoints,defencePoints,experienceValue);
    }

    public void deathCall(Unit u){
        deathCallBack.call(this);
    }
    public boolean equals(Object other){
        if(!(other instanceof Enemy))
            return false;
        Enemy e = (Enemy) other;
        return e.name.equals(name) & e.attackPoints == attackPoints & e.defencePoints == defencePoints & e.health.getHealthPool() == health.getHealthPool() & position.equals(e.position);
    }
}
