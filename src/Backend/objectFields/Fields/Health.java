package Backend.objectFields.Fields;

public class Health {
    private int healthPool;
    private int healthAmount;


    public Health(int healthPool){
        this.healthPool = healthPool;
        this.healthAmount = healthPool;
    }

    public int getHealthPool(){
        return healthPool;
    }
    public int getHealthAmount(){
        return healthAmount;
    }

    public boolean alive(){
        return healthAmount > 0;
    }

    public void updateLevel(int amount, int healthAddition){
        healthPool = healthPool + (amount * healthAddition);
        healthAmount = healthPool;
    }

    public void healthUpgrade(int amount){
        healthAmount = Math.min(healthPool,healthAmount + amount);
    }

    public void dealDamage(int damage){
        healthAmount = healthAmount - damage <= 0 ? 0 : healthAmount - damage;
    }
    public String toString(){
        return healthAmount + "/" + healthPool;
    }

}
