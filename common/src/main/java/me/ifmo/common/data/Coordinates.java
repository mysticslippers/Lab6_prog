package me.ifmo.common.data;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class to represent the Coordinates field.
 */

public class Coordinates implements Serializable {
    private Long x; //Значение поля должно быть больше -489, Поле не может быть null
    private float y; //Значение поля должно быть больше 0, Поле не может быть null

    /**
     * Constructor for the DragonCave class.
     * @param x - The value for the x field.
     * @param y - The value for the y field.
     */

    public Coordinates(Long x, float y){
        this.x = x;
        this.y = y;
    }

    /**
     * Method for setting the new value of field x.
     * @param x - The new x value.
     */

    public void setX(Long x){
        this.x = x;
    }

    /**
     * Method for setting the new value of field y.
     * @param y - The new y value.
     */

    public void setY(float y){
        this.y = y;
    }

    /**
     * Method for getting the value of the x field.
     * @return The value of the x field.
     */

    public Long getX(){
        return this.x;
    }

    /**
     * Method for getting the value of the y field.
     * @return The value of the y field.
     */

    public float getY(){
        return this.y;
    }

    /**
     * Overridden toString method for the Coordinates class.
     * @return Returns information about an object of type Coordinates.
     */

    @Override
    public String toString(){
        return "Coordinates: X - " + getX() + ", Y - " + getY();
    }

    /**
     * Overridden hashCode method for the Coordinates class.
     * @return Returns the hashcode of an object of type Coordinates.
     */

    @Override
    public int hashCode(){
        return this.x.hashCode() + (int) this.y;
    }

    /**
     * Overridden equals method for the Coordinates class.
     * @param obj - The object to be compared to.
     * @return Returns the result of comparing two objects of type Coordinates.
     */

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj instanceof Coordinates coordinatesObj){
            return (Objects.equals(this.x, coordinatesObj.getX()) && this.y == coordinatesObj.getY());
        }
        return false;
    }
}