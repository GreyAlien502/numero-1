package numerouno.gameobjects;
import java.util.*;

public class Location {
	private byte attack; //how easy is it to attack from here
	private byte happiness; //how happy does this envoronment make people
	private byte food; //how much food can be produced
	private byte defence; //how easy to defend is this place
	private boolean city; //is this a city
	private List<Mineral> ores; //all the ores in the ground that can be mined
	public List<Human> occupants; //list of all the people in the city/town
	public List<Technology> research; //list of all the ongoing research in the city
	public List<Mineral> materials; //list of all the materials in the city that noone is using
	
	public Location(byte attack, byte happiness, byte food, byte defence, List<Mineral> ores) {
		this.attack = attack;
		this.happiness = happiness;
		this.food = food;
		this.defence = defence;
		this.ores = ores;
		this.occupants = new ArrayList<Human>(0);
		this.research = new ArrayList<Technology>(0);
		this.materials = new ArrayList<Mineral>(0);
		for (int i=0;i!=ores.size();i++) {
			ores.get(i).setLocation(this);
		}

	}
	public byte getAttack() {
		return attack;
	}
	public byte getHapppiness() {
		return happiness;
	}
	public byte getFood() {
		return food;
	}
	public byte getDefence() {
		return defence;
	}
	public boolean isCity() {
		return city;
	}
	public void makeCity() {
		if (this.humansWithJob((byte)2).size() == 0) { city = true; }
	}
	public Mineral getCurrentOre() { //returns the top ore (the one currently disponible for mining)
					 // returns null if there are no ores left
		if (ores.size() == 0) {return null;}
		return ores.get(0);
	}
	public void removeOre() {
		if ( ores.get(0).getProgress() == 16 && this == ores.get(0).getLocation() ){ ores.remove(0); }
	}
	public void addOre(Mineral ore) {
		if (ore.getLocation()==this && ore.getUser()==null) {
			ores.add(ore);
		}
	}
	protected void addHuman(Human human) {
		if (human.getLocation() == this) {occupants.add(human);}
	}
	public List<Human> humansWithJob(byte job) { //returns list of all people with a specific job in this place
		List<Human> output = new ArrayList<Human>(0);
		int size = occupants.size();
		if (size == 0) { return output; }
		for (int i=0; i<size; i++) {
			if (occupants.get(i).getJob() == job) { output.add(occupants.get(i));}
		}
		return output;
	}
	
	public String toString() {
		return
			"landvalues:	"+attack+",	"+happiness+",	"+food+",	"+defence+
			"\npeople:		"+this.humansWithJob((byte)0).size()+",	"+this.humansWithJob((byte)1).size()+",	"+(this.humansWithJob((byte)2).size()+this.humansWithJob((byte)4).size())+",	"+this.humansWithJob((byte)3).size();
	}
}
