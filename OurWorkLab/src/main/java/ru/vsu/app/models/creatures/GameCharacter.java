package ru.vsu.app.models.creatures;

public class GameCharacter extends Creatures{
    private String name = "A";

    public GameCharacter(int hp, int speed, int damage, String name) {
        super(hp, speed, damage);
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
