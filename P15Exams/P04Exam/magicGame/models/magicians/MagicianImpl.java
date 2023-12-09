package P15Exams.P04Exam.magicGame.models.magicians;

import P15Exams.P04Exam.magicGame.common.ExceptionMessages;
import P15Exams.P04Exam.magicGame.models.magics.Magic;

public abstract class MagicianImpl implements Magician{
    private String username;
    private int health;
    private int protection;
    private boolean isAlive;
    private Magic magic;

    public MagicianImpl(String username, int health, int protection, Magic magic) {
        this.setUsername(username);
        this.setHealth(health);
        this.setProtection(protection);
        this.isAlive = true;
        this.setMagic(magic);
    }

    protected void setUsername(String username) {
        if (username == null || username.trim().isEmpty()){
            throw new NullPointerException(ExceptionMessages.INVALID_MAGICIAN_NAME);
        }
        this.username = username;
    }

    protected void setHealth(int health) {
        if (health < 0 ){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_HEALTH);
        }
        this.health = health;
    }

    protected void setProtection(int protection) {
        if (protection < 0 ){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MAGICIAN_PROTECTION);
        }
        this.protection = protection;
    }


    protected void setMagic(Magic magic) {
        if (magic == null){
            throw new NullPointerException(ExceptionMessages.INVALID_MAGIC);
        }
        this.magic = magic;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public int getProtection() {
        return this.protection;
    }

    @Override
    public Magic getMagic() {
        return this.magic;
    }

    @Override
    public boolean isAlive() {
        return this.isAlive;
    }

    @Override
    public void takeDamage(int points) {
        this.protection -= points;
        if (this.protection < 0){
            health += this.protection;
        }
        if (health <= 0){
            isAlive = false;
        }
    }


    @Override
    public String toString() {
        int health = this.getHealth();
        int protection = this.getProtection();

        if (health < 0){
            health = 0;
        }

        if (protection < 0){
            protection = 0;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s: %s",this.getClass().getSimpleName(),this.username)).append(System.lineSeparator());
        sb.append(String.format("Health: %d",health)).append(System.lineSeparator());
        sb.append(String.format("Protection: %d",protection)).append(System.lineSeparator());
        sb.append(String.format("Magic: %s",this.getMagic().getName()));
        return sb.toString().trim();
    }
}
