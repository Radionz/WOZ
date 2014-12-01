package zuul.entities.items;

import java.util.ArrayList;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class Item {
    /**
     * A basic implementation for the Item class
     */
    protected String name;
    protected int energy;

    /**
     * constructor with item name and amount of energy
     * @param name string of the name
     * @param energy energy amount
     */
    public Item(String name, int energy){
        this.name = name;
        this.energy = energy;
    }

    /**
     * constructor with item name
     * @param name string of item name
     */
    public Item(String name){
        this.name = name;
        this.energy = 0;
    }

    /**
     * constructor without parameter
     */
    public Item(){
        this.name = "";
        this.energy = 0;
    }

    // basic getters / Setters //

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }
    // basic getters / Setters //

    /**
     * method describing how to use an item
     * it has to be overwritten in inherited classes
     * @return String of the behavior of the Item
     */
    public String use() {
		return "this item is useless, it requires may be something to be used.";
	}

    @Override
    public String toString(){
        return getName();
    }

    @Override
    public boolean equals(Object object){
        if (object == null) { return false; }
        if (object == this) { return true; }
        if (((Item) object).name.equals(this.name)){ return true; }
        return false;
    }
    

}
