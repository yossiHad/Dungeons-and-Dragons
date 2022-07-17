package Backend.objectFields.Fields;

public class Experience {
    private int maxExperience;
    private int points;
    private final int REDUCE_POINTS = 50;

    public Experience(){
        points = 0;
        maxExperience = 50;
    }

    public void levelUp(int level){
        points = points - (REDUCE_POINTS * level);
        maxExperience = maxExperience + REDUCE_POINTS;
    }
    public void addExperience(int experience){
        points = points + experience;
    }

    public String toString(){
        return  points + "/" + maxExperience;
    }
    public boolean isFull(){
        return points >= maxExperience;
    }
    //for testing
    public int getPoints(){
        return points;
    }
}
