import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MapColorer {

    public void colorMap() {
        // You fill this in.
        // Use System.out.println() to output the colored map.
        // See the instructions for the proper format.
        // It is okay to write private helper methods in this class.
        // If you're doing things recursively, you will need one.
        //
        // Remember your job is to systematically to work your way through
        // Region.getAllRegionsAsArray(), calling canColorWith, setColor,
        // removeColor. You will be going forward and backward through
        // the array.

        if(canColorFrom(Region.getAllRegionsAsArray(),0)){
            printColoredMap();
        }
        else{
            System.out.println("IMPOSSIBLE MAP");
        }

    }

    public void printColoredMap(){
        for(Region region : Region.getAllRegionsAsArray()){
            System.out.println(region.getName() + ":" + region.getColor());
            for(Region neighbor : region.getNeighbors()){
                System.out.println(region.getName() + "," + neighbor.getName());
            }
        }
    }

    public void readMapFromStandardInput() {
        new BufferedReader(new InputStreamReader(System.in))
            .lines()
            .forEach(line -> {
                String[] pair = line.trim().split(",");
                Region region0 = Region.forName(pair[0]);
                Region region1 = Region.forName(pair[1]);
                region0.addNeighbor(region1);
            });

        //printMap();    
    }

    private static void printMap() {
        System.out.println("The countries are: ");
        for (Region r : Region.getAllRegionsAsArray()) {
            System.out.println(r + " with Neigbors " + r.getNeighbors());
        }
    }

    public static void main(String[] args) {
        MapColorer mapColorer = new MapColorer();
        mapColorer.readMapFromStandardInput();
        mapColorer.colorMap();
    }

    public boolean canColorFrom(Region[] regions, int index){
        /*for(int i = 0; i <= regions.length - 1; i++){
            for(int color = 0; color < 4; color++){
                if(regions[index].canColorWith(color)){
                    regions[index].setColor(color);
                }
            }
        }*/


        for(int color = 0; color < 4; color++){
            if(regions[index].canColorWith(color)){
                regions[index].setColor(color);
                if(index == regions.length - 1 || canColorFrom(regions, index + 1)){
                    return true;
                } 
            }
        }
        return false;
    }
}

/**
* This class is full of a lot of stuff you don't need to know.
*
* But don't hesitate to ask about such things if you like.
*/
class Region {

    private static Map<String, Region> allRegions = new TreeMap<>();

    public static Region forName(String name) {
        allRegions.putIfAbsent(name, new Region(name));
        return allRegions.get(name);
    }

    public static Region[] getAllRegionsAsArray() {
        return allRegions.values().toArray(new Region[allRegions.size()]);
    }

    private String name;
    private Integer color;
    private Set<Region> neighbors = new HashSet<>();

    private Region(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getColor() {
        return color;
    }

    public boolean canColorWith(int color) {
        return !neighbors.stream().anyMatch(n -> Objects.equals(n.color,color));
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void removeColor() {
        this.color = null;
    }

    public Set<Region> getNeighbors() {
        return neighbors;
    }

    public String toString(){
        return this.name;
    }

    public void addNeighbor(Region region) {
        Objects.requireNonNull(region);
        neighbors.add(region);
        region.neighbors.add(this);
    }
}
