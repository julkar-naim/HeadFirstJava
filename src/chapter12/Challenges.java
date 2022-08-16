package chapter12;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Challenges {
    public void findAllTheRockSongs() {
        List<Song> songList = new Songs().getSongs();
        Stream<Song> songStream = songList.stream();
        List<Song> rockSongs = songStream
                .filter(song -> song.getGenre().contains("Rock")).toList();
        rockSongs.forEach(System.out::println);
    }

    public void listAllTheGenres() {
        List<String> genres = new Songs()
                .getSongs()
                .stream()
                .map(Song::getGenre).toList();

        genres.forEach(System.out::println);
    }
}
