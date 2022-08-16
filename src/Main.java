import chapter12.Challenges;
import chapter12.Songs;

public class Main {
    public static void main(String[] args) {
        var test = new Challenges();
        System.out.println("\n------------------------------All the Songs from Rock genre--------------------------\n");
        test.findAllTheRockSongs();
        System.out.println("\n------------------------------Listing all the genre--------------------------------\n");
        test.listAllTheGenres();
        System.out.println("\n------------------------------Listing all the distinct genre--------------------------\n");
        test.listAllTheGenresDistinct();
    }
}
