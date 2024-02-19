package jukebox.dao;

import jukebox.util.MusicPlayer;
import jukebox.bean.Songs;
import jukebox.util.UtilDb;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static jukebox.util.UtilDb.getConnection;

public class SongDAO {

    static MusicPlayer musicPlayer=new MusicPlayer();
   public static List<Songs> songlist = new ArrayList<>();
    public static List<Songs> getSonglist() {
        Connection connection = UtilDb.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from songs");
            while (rs.next()) {
                Songs song = new Songs();
                song.setSongId(rs.getInt(1));
                song.setSongName(rs.getString(2));
                song.setAlbum(rs.getString(3));
                song.setDuration(rs.getString(4));
                song.setFilePath(rs.getString(7));
                songlist.add(song);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return songlist;
    }

    public static ArrayList<Songs> searchBySong(String Song) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Connection con = UtilDb.getConnection();
        ArrayList<Songs> search = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from songs where songName='" + Song + "';");
            System.out.println("--------------------------------------------------------------------------");
            System.out.format("%-9s\t%-25s\t%-16s\t%-20s\n", "SongId", "Song Name", "Album", "Duration");
            System.out.println("--------------------------------------------------------------------------");
            while (rs.next()) {
                Songs song = new Songs();
                song.setSongId(rs.getInt(1));
                song.setSongName(rs.getString(2));
                song.setAlbum(rs.getString(3));
                song.setDuration(rs.getString(4));
                song.setArtistName(rs.getString(5));
                song.setGenre(rs.getString(6));
                song.setFilePath(rs.getString(7));
                System.out.format("%-9d\t%-25s\t%-16s\t%-20s\n", rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4));
                System.out.println("--------------------------------------------------------------------------");

                search.add(song);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        musicPlayer.playSong(search);
        return search;
    }

    public ArrayList<Songs> playBySongId(int Song) throws UnsupportedAudioFileException, LineUnavailableException, IOException {

        Connection con =  getConnection();
        ArrayList<Songs> list = new ArrayList<>();

        System.out.println("--------------------------------------------------------------------------");
        System.out.printf ("%-9s\t%-25s\t%-16s\t%-20s\n","Song Id","Song Name","album","Duration");
        System.out.println("--------------------------------------------------------------------------");
        Songs sng = null;
        try
        {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Songs WHERE songId = '" + Song + "';");
            while (rs.next())
            {
              sng = new Songs(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7));
              list.add(sng);
              System.out.printf("%-9s\t%-25s\t%-16s\t%-20s\n", rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));
              System.out.println("--------------------------------------------------------------------------");

            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            System.err.println(exception.getMessage());
        }
        musicPlayer.playSong(list);
        return list;
    }

    public static void displaySonglist() {
        Connection connection = UtilDb.getConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from songs");
            System.out.println("--------------------------------------------------------------------------");
            System.out.format("%-9s\t%-25s\t%-16s\t%-20s\n", "SongId", "Song Name", "Album", "Duration" );
            System.out.println("--------------------------------------------------------------------------");

            while (rs.next()) {
                int songId = rs.getInt(1);
                String songTile = rs.getString(2);
                String album = rs.getString(3);
                String duration = rs.getString(4);
                String artistName = rs.getString(5);
                String genreName = rs.getString(6);
                System.out.format("%-9d\t%-25s\t%-16s\t%-20s\n", songId, songTile, album,duration );
            }
            System.out.println("--------------------------------------------------------------------------");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}