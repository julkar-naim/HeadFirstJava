package chapter12;

public class TestChapter12 {
    public void run() {
        var test = new Challenges();
        System.out.println("\n------------------------------All the Songs from Rock genre--------------------------\n");
        test.findAllTheRockSongs();
        System.out.println("\n------------------------------Listing all the genre--------------------------------\n");
        test.listAllTheGenres();
        System.out.println("\n------------------------------Listing all the distinct genre--------------------------\n");
        test.listAllTheGenresDistinct();
    }
}
