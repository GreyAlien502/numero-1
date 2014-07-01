package numerouno.run;
import java.io.*;
import java.util.*;
import numerouno.gameobjects.*;
public class Run{
	public static void main(String[] args){
		List<Mineral> mineralecian = new ArrayList<Mineral>(0);
		for (byte i= (byte)0;i<11;i++) {
			mineralecian.add(new Mineral((byte)(i%3),(byte)((i*i+5*i+2)%11)));
		}
		Location City = new Location((byte)4, (byte)2, (byte)12, (byte)3, mineralecian);
		Human Bell = new Human((byte)2 ,(byte)3, new Player(args[0]));
		Bell.setLocation(City);
		System.out.println("Bell is "+Bell);
		City.makeCity();
		Bell.setJob((byte)3);
		System.out.println("Bell is "+Bell);
		System.out.println("City is:\n"+City);
		printList(City.occupants);
		for (int i=0; i<100;i++) {
			System.out.println(City.getCurrentOre());
			City.getCurrentOre().mine();
		}
		Bell.use(City.materials.get(1));
		printList(City.materials);
		System.out.println(Bell);
		printList(Bell.equipment);
		printList(City.materials);
		Bell.drop( Bell.equipment.get(0) );
		printList(City.materials);
	}
	private static void printList(List lecian) {
		int i = 0;
		while (i<lecian.size()) {
			System.out.println(lecian.get(i));
			i++;
		}
	}
}
