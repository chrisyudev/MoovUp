package codetestmap.moovup.moovup;

public class Entity {
    public String picture;
    public String _id;
    public String name;
    public String email;
    public  Location location = new Location();

    public class Location {
        public double latitude;
        public double longitude;
    }
}
