package Backend.Tiles;

import Backend.Messages.*;
import Backend.Units.Enemy;
import Backend.Units.Player;
import Backend.objectFields.Fields.Health;
import Backend.objectFields.Fields.Position;
import Backend.objectFields.Fields.Generators.ValueGenerator;

public abstract class Unit extends Tile {
    protected String name;
    protected Health health;
    protected int attackPoints;
    protected int defencePoints;
    protected MessageCallBack messageCallBack;
    protected DeathCallBack deathCallBack;
    protected ValueGenerator valueGenerator;

    public Unit(char represent,Position position,String name, int healthAmount, int attackPoints, int defencePoints){
        super(represent,position);
        this.name = name;
        health = new Health(healthAmount);
        this.attackPoints = attackPoints;
        this.defencePoints = defencePoints;
        valueGenerator = ValueGenerator.getInstance();
    }
    public Unit(char represent,String name, int healthAmount, int attackPoints, int defencePoints){
        super(represent);
        this.name = name;
        health = new Health(healthAmount);
        this.attackPoints = attackPoints;
        this.defencePoints = defencePoints;
        valueGenerator = ValueGenerator.getInstance();
    }
    public int getHealthAmount(){
        return health.getHealthAmount();
    }
    public String getName(){
        return name;
    }

    public void interact(Tile tile){
        tile.accept(this);
    }

    public void visit(Wall w){}
    public void visit(Empty empty){
        swapPositions(empty);
    }
    public abstract void visit(Enemy enemy);
    public abstract void visit(Player player);

    private void swapPositions(Tile tile){
        int x = position.getXPosition();
        int y = position.getYPosition();
        position.set(tile.position.getXPosition(),tile.position.getYPosition());
        tile.position.set(x,y);
    }

    protected void battle(Unit unit){
        messageCall(String.format("%s engaged into a battle with %s\n",getName(),unit.getName()));
        int attack = rollAttack();
        int enemyDefence = unit.rollDefence();
        int defenceDamage = Math.min(attack, enemyDefence);
        int healthDamage = attack - defenceDamage;
        unit.dealDamage(healthDamage);
        messageCall(String.format("%s rolled %d attack points\n%s rolled %d defence points\n%s dealt %d damage to %s\n",getName(),attack,unit.getName(),enemyDefence,getName(),healthDamage,unit.getName()));
        if(!unit.alive()) {
            unit.deathCall(this);
        }
    }

    public int rollAttack(){
        return valueGenerator.getValue(attackPoints);
    }

    public int rollDefence(){
        return valueGenerator.getValue(defencePoints);
    }

    public void dealDamage(int healthDamage){
        health.dealDamage(healthDamage);
    }

    public boolean alive(){
        return health.alive();
    }
    public void setPosition(Position p){
        position = new Position(p);
    }
    public void setMessageCallBack(MessageCallBack messageCallBack){
        this.messageCallBack = messageCallBack;
    }
    public void setDeathCallBack(DeathCallBack deathCallBack){
        this.deathCallBack = deathCallBack;
    }

    protected void deathCall(Unit u){
        deathCallBack.call(u);
    }
    protected void messageCall(String msg){
        messageCallBack.send(msg);
    }

    //for testing
    public int getAttackPoints(){
        return attackPoints;
    }
    public int getDefencePoints(){
        return defencePoints;
    }
    public int getHealthPool(){
        return health.getHealthPool();
    }
}
