package Backend.Units;

import Backend.Tiles.Empty;
import Backend.objectFields.Fields.Position;

public class Trap extends Enemy{
    private static final int ATTACK_RANGE = 2;
    private int visibilityTime;
    private int invisibilityTime;
    private int tickCount;
    private boolean visible;

    public Trap(char represent, Position position, String name, int healthAmount, int attackPoints, int defencePoints, int experienceValue, int visibilityTime, int invisibilityTime) {
        super(represent,position,name, healthAmount, attackPoints, defencePoints, experienceValue,ATTACK_RANGE);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        tickCount = 0;
        this.visible = true;
    }

    public String toCharString(){
        if(visible)
            return represent + "";
        return Empty.CHAR + "";
    }
    private void tick(){
        visible = tickCount < visibilityTime;
        if(tickCount == (visibilityTime + invisibilityTime))
            tickCount = 0;
        else
            tickCount++;
    }

    public Position tick(Position position){
        tick();
        return position;
    }

    @Override
    public void visit(Player p) {
        if(inVision(p.getPosition()))
            battle(p);
    }
    public void visit(Empty e){}

}
