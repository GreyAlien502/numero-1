package numerouno.gameobjects;
import java.util.*;

public class Human {
	private byte type; //1-4:no in game difference, just an appearance
	private byte strength; //1-13:ability for physical tasks such as farming and fighting
	private byte job; /*what is the human doing the state;
					0: soldier
					1: miner
					2: scientist
					3: leader
					4: farmer
					*/
	private Player country; //the country to which the human belongs
	private Location location; //the block the human is on
	public List<Mineral> equipment;

	public Human(byte type, byte strength, Player country) {
		this.type = type;
		this.strength = strength;
		this.job = (byte)0;
		this.country = country;
		this.equipment = new ArrayList<Mineral>(0);
		if (type > 4) {this.type = 4;}
		if (strength > 13) {this.strength = 13;}
		this.country.addCitizen(this);
	}
	
	public byte getType() {return type;}
	public byte getStrength() {return strength;}
	public byte getJob() {return job;}
	public void setJob(byte job) { //attempts to set job, if job is illegal, nothing happens
		switch (job) {
			case 0: this.job = (byte)0; break;
			case 1: this.job = job; break;
			case 2: if (this.location.isCity()) {this.job = (byte)2;} break; //must be in a city
			case 3: if (this.location.isCity()) {this.job = (byte)3;} break;//must be in a city
			case 4: if (!this.location.isCity() && (this.location.humansWithJob((byte)4).size() == 0)) {
					this.job = (byte)4;
				}//must be in a farmerless non city
		}
	}
	public Player getCountry() {return country;}
	public Location getLocation() {return this.location;}
	public void setLocation(Location location) { //this can only be done once, once human is placed, use move() or moveTo()
		if (this.location == null) {
			this.location = location;
			location.addHuman(this);
		}
	}
	public byte getEquipment(byte type) { // returns the value of the equipment with type
		for ( Mineral piece : equipment) {if (piece.getType() == type) {return piece.getValue();}}
		return 0;
	}
	
	public void use(Mineral material) {
		if (material.getUser() == null) {
			if (this.getEquipment(material.getType()) == 0) {
				material.setUser(this);
			}
		}
	}
	public void drop(Mineral material) {
		if (material.getUser() == this) {
			material.removeUser();
			this.equipment.remove(material);
		}
	}







	public String toString() {
		return "type "+this.type+" "+this.country.demonym+" "+this.jobAsString()+" of strength "+this.strength;
	}
	public String jobAsString() { //returns the job as human readable text
		switch (job) {
			case 0: return "soldier";
			case 1: return "miner";
			case 2: return "scientist";
			case 3: return "leader";
			case 4: return "farmer";
		}
		return "BROKEN";
	}
}
 
