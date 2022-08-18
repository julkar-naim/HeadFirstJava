import chapter12.Challenges;
import chapter12.Songs;

import chapter11.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.findLadders("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }

    public static void chapter12() {
        var test = new Challenges();
        System.out.println("\n------------------------------All the Songs from Rock genre--------------------------\n");
        test.findAllTheRockSongs();
        System.out.println("\n------------------------------Listing all the genre--------------------------------\n");
        test.listAllTheGenres();
        System.out.println("\n------------------------------Listing all the distinct genre--------------------------\n");
        test.listAllTheGenresDistinct();
    }
}
