package zuul.entities.items;

import java.util.ArrayList;

/**
 * @author Nicolas Sarroche, Dorian Blanc
 */
public class Item {
    /**
     * Basic class test, has to be improved !
     */
    protected String name;
    protected int energy;

    /**
     * Third Item constructor
     * @param name
     * @param energy
     */
    public Item(String name, int energy){
        this.name = name;
        this.energy = energy;
    }

    /**
     * Second constructor for Item
     * @param name
     */
    public Item(String name){
        this.name = name;
        this.energy = 0;
    }
    
    /**
     * First constructor for Item
     */
    public Item(){
        this.name = "";
        this.energy = 0;
    }

    /**
     * return the name of the item
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * return the energy of the item
     * @return
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * return the use of the item (in a normal item you can't use it)
     * @return
     */
    public String use() {
		return "this item is useless, it requires may be something to be used.";
	}

    @Override
    /**
     * toString()
     */
    public String toString(){
        return getName();
    }

    @Override
    /**
     * Equals..
     */
    public boolean equals(Object object){
        if (object == null) { return false; }
        if (object == this) { return true; }
        if (((Item) object).name.equals(this.name)){ return true; }
        return false;
    }
    

}
