package me.ifmo.common.data;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class to represent the DragonCave field.
 */

public class DragonCave implements Serializable {
    private Long numberOfTreasures; //Поле не может быть null, Значение поля должно быть больше 0

    /**
     * Constructor for the DragonCave class.
     * @param numberOfTreasures - The value for the numberOfTreasures field.
     */

    public DragonCave(Long numberOfTreasures){
        this.numberOfTreasures = numberOfTreasures;
    }

    /**
     * Method for setting the new value of field numberOfTreasures.
     * @param numberOfTreasures - The new numberOfTreasures value.
     */

    public void setNumberOfTreasures(Long numberOfTreasures){
        this.numberOfTreasures = numberOfTreasures;
    }

    /**
     * Method for getting the value of the numberOfTreasures field.
     * @return The value of the numberOfTreasures field.
     */

    public Long getNumberOfTreasures(){
        return this.numberOfTreasures;
    }

    /**
     * Overridden toString method for the DragonCave class.
     * @return Information about an object of type DragonCave.
     */

    @Override
    public String toString(){
        return "DragonCave: numberOfTreasures - " + getNumberOfTreasures();
    }

    /**
     * Overridden hashCode method for the DragonCave class.
     * @return Returns the hashcode of an object of type DragonCave.
     */

    @Override
    public int hashCode() {
        return this.numberOfTreasures.hashCode();
    }

    /**
     * Overridden equals method for the DragonCave class.
     * @param obj - The object to be compared to.
     * @return Returns the result of comparing two objects of type DragonCave.
     */

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj instanceof DragonCave dragonCaveObj){
            return (Objects.equals(this.numberOfTreasures, dragonCaveObj.getNumberOfTreasures()));
        }
        return false;
    }
}