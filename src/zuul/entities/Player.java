package zuul.entities;

import zuul.entities.items.Item;
import zuul.rooms.Room;

import java.util.ArrayList;

/**
 * Created by user on 13/11/14.
 */
public class Player {

    private final String name;
    private ArrayList<Item> inventory;
    private int energy;
    private int currentPOOLevel;

    public Player(String name, Item item){
        this.name = name;
        this.inventory = new ArrayList<>(100);
        this.inventory.add(item);
        this.currentPOOLevel = 0;
    }

    /* Basic getters / setters */
    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public int getCurrentPOOLevel() {
        return currentPOOLevel;
    }

    public void setCurrentPOOLevel(int currentPOOLevel) {
        this.currentPOOLevel = currentPOOLevel;
    }
    /* Basic getters / setters */


    /**
     * Method allowing the player to pickup an item on the ground
     * and to put it in is own inventory
     * @param item Item being pick up
     * @return If the item has successfully been added or not
     */
    public boolean pickUp(Room room,Item item){
        if(room != null && item != null){
            room.getItemsList().remove(item);
            return this.inventory.add(item);
        }
        return false;
    }
    
    public boolean pickUp(Room room, String itemString){
        if(room != null && itemString != null){
        	for (Item item : room.getItemsList()) {
				if(item.getName().equals(itemString)){
					room.getItemsList().remove(item);
					return this.inventory.add(item);
				}
			}  
        }
        return false;
    }

    /**
     * Drop one player's Item from its inventory in the current room
     * @param room The room the player is currently settled
     * @param item The item the player want to drop
     * @return If the player has successfully dropped his item
     */
    public boolean dropItem(Room room, Item item){
        if(room != null && item != null &&  this.inventory.contains(item)){
            room.addItem(item);
            return this.inventory.remove(item);
        }
        return false;
    }
    
    public boolean dropItem(Room room, String itemString){
        if(room != null && itemString != null){
        	for (Item item : inventory) {
				if(item.getName().equals(itemString)){
					 room.addItem(item);
					 return this.inventory.remove(item);
				}
			}
        }
        return false;
    }

    /**
     * show player's inventory
     * @return String of the inventory
     */
    public String getInventoryContent() {
        String res = "";
        for(Item item : inventory){
            res += item.getName() + " - ";
        }
        return (res.length()>3)? res.substring(0, res.length()-3): res;
    }


    /**
     * Set an Amount of energy for the player
     * @param energy new amount of energy
     */
    private void setEnergy(int energy) {
        if(energy > 5){
            energy=5;
        }else if(energy < 0){
            energy =0;
        }
        this.energy = energy;
    }

    /**
     * Gain a specific amount of energy
     * @param i amount of energy
     */
    public void gainAmountEnergy(int i){
        setEnergy(energy+i);
    }

    /**
     * Gain one point of energy
     */
    public void gainEnergy(){
        setEnergy(energy+1);
    }

    /**
     * Loose one point of energy
     */
    public void looseEnergy(){
        setEnergy(energy-1);
    }

    /**
     * loose a specifig amount of energy
     * @param i amount of energy
     */
    public void looseAmountEnergy(int i){
        setEnergy(energy-i);
    }

    public String use(String itemString){
        for(Item item : inventory){
            if(item.getName().equals(itemString)){
            	String effect = inventory.get(inventory.indexOf(item)).use();
            	inventory.remove(inventory.indexOf(item));
                return effect;
            }
        }
		return "Player don't have this item.";
    }


}
