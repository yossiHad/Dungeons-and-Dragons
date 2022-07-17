package Backend.Units;

import Backend.objectFields.Fields.Movement;
import Backend.objectFields.Fields.Position;

public class Monster extends Enemy{


    public Monster(char represent,Position position,String name, int healthAmount, int attackPoints, int defencePoints,int experienceValue, int visionRange) {
        super(represent,position,name, healthAmount, attackPoints, defencePoints,experienceValue,visionRange);
    }

    public Position tick(Position position) {
        if(inVision(position))
            return chase(position);
        else
            return Movement.chooseRandomMove(this.position);
    }

    public String toString(){
        String output = super.toString();
        return String.format(output + "visionRange: %d",visionRange);
    }


    public Position chase(Position p){
        int dx = position.getXPosition() - p.getXPosition();
        int dy = position.getYPosition() - p.getYPosition();
        if(Math.abs(dx) > Math.abs(dy)){
            if(dx > 0)
                return Movement.left(position);
            else
                return Movement.right(position);
        }
        else {
            if(dy < 0)
                return Movement.down(position);
            else
                return Movement.up(position);
        }
    }
}
