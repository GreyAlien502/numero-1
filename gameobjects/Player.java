package numerouno.gameobjects;
import java.util.*;

public class Player {
	public String name; //name of country (not person)
	public String demonym; //adjective to describe something from country
	private List<Human> citizens; //list of all humans in the country
	private byte[] technologies; /*array of the highest technologies achieved
					 in each of the 5 categories:
						0: attack
						1: happiness
						2: food
						3: defence
						4: general*/
	
	public Player(String name, String demonym) {
		this.name = name;
		this.demonym = demonym;
		this.citizens = new ArrayList<Human>(0);
		this.technologies = new byte[5];
	}
	public Player(String name) { //use automatic demonym generation
		this(name, "");
		int length = name.length();
		if (name.endsWith("istan")) {
			this.demonym = name+"i";
		} else if (name.endsWith("n")) {
			this.demonym = name+"ese";
		} else if (name.endsWith("na") || name.endsWith("ma")) {
			this.demonym = name.substring(0,length-1)+"ese";
		} else if (name.endsWith("u") || name.endsWith("o")) {
			this.demonym = name+"vian";
		} else if (name.endsWith("land")) {
			this.demonym = name.substring(0,length-4)+"ish";
		} else if (name.endsWith("ay")) {
			this.demonym = name.substring(0,length-2)+"egian";
		} else if (name.endsWith("a")) {
			this.demonym = name+"n";
		} else if (name.endsWith("y")) {
			this.demonym = name.substring(0,length-1)+"ian";
		} else if (name.endsWith("e")) {
			this.demonym = name+"r";
		} else {
			this.demonym = name+"ian";
		}
	}
	public List<Human> getCitizens() {return citizens;}
	public void addCitizen(Human human) {
		if (human.getCountry() == this) {this.citizens.add(human);}
	}
	public byte[] getTechnologies() {return this.technologies;}
}
