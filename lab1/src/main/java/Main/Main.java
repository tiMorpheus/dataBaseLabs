package Main;


import MAP.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Map map = new Map("map_db","localhost",3306);
        map.showCountries();
        Thread.sleep(100);
        map.addCountry(1,"Russia");
        map.addCountry(5,"JAPAN");
        map.addCountry(6,"Ukraine");
        map.deleteCountry(3);
        map.deleteCountry(1);
        map.showCountries();
        map.stop();
    }
}
