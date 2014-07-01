package numerouno.gameobjects;
import java.util.*;

public class Technology {
	private byte type;
	private byte value;
	private byte progress;
	private Location location;
	private List<Human> researchers;
	
	public Technology(byte type, byte value, Location location) {
		this.type = type;
		this.value = value;
		this.progress = (byte)0;
		this.researchers = new ArrayList<Human>(0);
	}
	public Location getLocation() {
		return this.location;
	}
}
