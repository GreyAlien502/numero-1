package numerouno.gameobjects;
import java.util.*;

//Minerals are mined and then used to increase certain skills.
public class Mineral {
	private byte type; /*0-4: what the mineral is used for
					0: attack
					1: happiness
					2: farming
					3: defence*/
	private byte value; //0-11 how good it is at doing what it does, uranium is always 0
	private byte progress; //0-16 how much of it is mined
	private Location location;
	private Human user;
	
	public Mineral(byte type, byte value) {
		this.type = type;
		this.value = value;
		this.progress = 0;
	}

	public byte getType() { return type; }
	public byte getValue() { return value; }
	public byte getProgress() { return progress; }
	public Location getLocation() { return location; }
	public void setLocation(Location location) { //this sets the location, but can only be done once, before it already has a location
		if (this.location == null) { this.location = location; }
	}
	public Human getUser() { return user; }
	public void setUser(Human user) {
		if (this.user == null) {
			user.equipment.add(this);
			this.location.materials.remove(this);
			this.user = user;
		}
	}
	public void removeUser() {
		this.user.equipment.remove(this);
		this.location.materials.add(this);
		this.user = null;
	}

	public void mine() { //makes the ore be a little more mined. if already mined, nothing happens
		progress = (byte)(progress + 1);
		if (progress == 16) {
			location.materials.add(this);
			location.removeOre();
		} else if (progress == 17) {
			progress = 16;
		}
	}
	public String typeAsString() {
		switch (type) {
			case 0: return "steel";
			case 1: return "gold";
			case 2: return "phosphate";
			case 3: return "iron";
		}
		return "BROKEN";
	}

	public String toString() {
		return "grade "+value+" "+this.typeAsString()+ ( (progress==16) ? ((user!=null)?(" in use"):(" not in use")):(" "+progress*6.25+"% mined"));
	}
}
