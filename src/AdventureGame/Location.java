package AdventureGame;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;



public class Location {
private String name;
private String description;


public Location(String name, String description) {
	this.name = name;
	this.description = description;
}


public String getName() {
	return name;
}

public String getDescription() {
	return description;
}


@Override
public String toString() {
	return "%s -> %s%n".formatted(name,description);
}

}



class GameMap{
	private Map<Character,Location> myMap = new HashMap<>();
	private Location Forest = new Location("Forest","in a large area of trees, plants and animals");
	private Location Valley = new Location("Valley","in a pathway in between two hills");
	private Location Well_House = new Location("Well House", "in a house for a small spring");
	private Location Hill = new Location("Hill","at a naturally raised area of land");
	private Location Road = new Location("Road","on The road to your desired Destination");
	private Location Stream = new Location("Stream", "next to a body of water flowing through a path");
	private Location Lake = new Location("Lake","next to small water body");
	 
	 public GameMap() {
		   myMap.put('R', Road);
		   myMap.put('F', Forest);
		   myMap.put('V', Valley);
		   myMap.put('W', Well_House);
		   myMap.put('H', Hill);
		   myMap.put('S', Stream);
		   myMap.put('L', Lake);
	 }
	 
	 
	 public String getDirections(Location location) {
		 
		 return switch(location.getName().toLowerCase()) {
		 case "forest" -> "%s%n%s%n".formatted("A Lake to the East(E)", "A Road to the South(S)");
		 case "valley" -> "%s%n%s%n%s%n".formatted("A Road to the North(N)", "A Hill to the West(W)", "A Stream to the East(E)");
		 case "well house" -> "%s%n%s%n%s%n".formatted("A Lake to the North(N)", "A Stream to the South(S)", "A Road to the West(W)");
		 case "hill" -> "%s%n%s%n".formatted("A Forest to the North(N)", "A Road to the East(E)");
		 case "road" -> "%s%n%s%n%s%n%s%n".formatted("A Forest to the North(N)", "A Valley to the South(S)", "A Hill to the West(W)", "A Well_House to the East(E)");
		 case "stream" -> "%s%n%s%n".formatted("A Well_House to the North(N)","A Valley to the West(W)");
		 case "lake" -> "%s%n%s%n".formatted("A Forest to the West(W)", "A Well_House to the South(S)");
		 default -> "invalid location";
		 };
	 }
	 
	 
	 public String getDescription(Location locate) {
		 return "You are standing " + locate.getDescription() + "\n" + getDirections(locate) + "\n" + "Select Your desired destination or press Q to Quit";
		 
	
	 }
	 
	 
	 public char inputCheckerForKey(String letter, Location l) {
		    String direction = switch(letter.toUpperCase()) {
		        case "N" -> "North(N)";
		        case "S" -> "South(S)";
		        case "W" -> "West(W)";
		        case "E" -> "East(E)";
		        default -> null;
		    };

		    if (direction == null) {
		        System.out.println("Invalid direction. Try N, S, E, or W.");
		        return '\u0000';
		    }

		    String directionsText = getDirections(l);
		    String[] lines = directionsText.split("\n");

		    for (String line : lines) {
		        if (line.contains(direction)) {
		            // Example line: "A Lake to the East(E)"
		            // Extract the first word after "A "
		            String[] parts = line.split(" ");
		            if (parts.length >= 2) {
		                String locationName = parts[1]; // e.g., "Lake"
		                return locationName.charAt(0);  // e.g., 'L'
		            }
		        }
		    }

		    System.out.println("You can't go that way from here.");
		    return '\u0000';
		}
	 
	 
	 
	 public void playGame() {
		 Scanner input = new Scanner(System.in);
		 
		 boolean flag = true;
		 
		 System.out.println(this.getDescription(Road));
		 
		 Location l = myMap.get('R');
		 char key ='\u0000';
		 
		 while(flag) {
			String letter = input.nextLine();
			if(letter.equalsIgnoreCase("Q")){
				System.out.println("Goodbye for now");
				flag = false;
			}else {
				key = inputCheckerForKey(letter, l);
				if (key != '\u0000') {
				    Location nextLocation = myMap.get(key);
				    if (nextLocation != null) {
				        l = nextLocation;
				        System.out.println(this.getDescription(l));
				    } else {
				        System.out.println("There is no location in that direction. Try another way.");
				    }
				}
			}
		 }
		 
		 input.close();
	 }
	 
	 
	 @Override
	 public String toString() {
		 return myMap.toString();
	 }
}
