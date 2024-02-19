package jukebox.dao;
import jukebox.exception.JukeboxException;
import jukebox.util.MusicPlayer;
import jukebox.bean.Songs;
import jukebox.bean.SongsInPlaylist;
import jukebox.util.UtilDb;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class PlaylistSongDAO {

    public static Connection connection = UtilDb.getConnection();
    SongsInPlaylist songsInPlaylist = new SongsInPlaylist();
    MusicPlayer musicPlayer = new MusicPlayer();
    Scanner sc = new Scanner(System.in);
    static SongDAO songDAO = new SongDAO();

    public void playSongOfPlaylist(int songId) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
       List<Songs> list =songDAO.playBySongId(songId);
       musicPlayer.playSong(list);
    }

    public void songDetailsOfSpecificPlaylist(int playlistId) throws SQLException, JukeboxException {
        Statement statement = connection.createStatement();

        String sql1 = "select playlistid from playlist where playlistid = "+playlistId+";";
        ResultSet resultSet = statement.executeQuery(sql1);

        if (!resultSet.next()){
            throw new JukeboxException("Invalid Playlist Id");
        }
        int playId = resultSet.getInt(1);

        String sql2 = "select songid from songsinplaylist where playlistid = "+playId+";";
        ResultSet resultSet1 = statement.executeQuery(sql2);

        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.format("%-9s\t%-25s\t%-16s\t%-20s\t%-15s\n", "SongId", "Song Name", "Album", "Duration", "Playlist Id");
        System.out.println("--------------------------------------------------------------------------------------------------");

        while(resultSet1.next()){

            int songId = resultSet1.getInt(1);
            Statement statement1 = connection.createStatement();
            String sql3 = "select songId, songName, album, duration, filePath from songs where songId = "+songId+";";
            ResultSet resultSet2 = statement1.executeQuery(sql3);
            while(resultSet2.next()){
                int sId = resultSet2.getInt(1);
                String sName = resultSet2.getString(2);
                String album = resultSet2.getString(3);
                String duration = resultSet2.getString(4);
                String filePath = resultSet2.getString(5);
                System.out.format("%-9d\t%-25s\t%-16s\t%-20s\t%-15d\n", sId, sName, album,duration,playId );

            }
            System.out.println("--------------------------------------------------------------------------------------------------");

            resultSet2.close();
        }
    }
    public void addSongsToPlayList(int playlistId, int songId) throws SQLException, JukeboxException {

             Statement statement = connection.createStatement();
        String sql1 = "select playlistid from playlist where playlistid = "+playlistId+";";
        ResultSet resultSet = statement.executeQuery(sql1);
        if (!resultSet.next()){
            throw new JukeboxException("Invalid Playlist Id");
        }

        int playId = resultSet.getInt(1);

        String sql2 = "select songId from songs where songId = "+songId+";";
        ResultSet resultSet1 = statement.executeQuery(sql2);
        if (!resultSet1.next())
            throw new JukeboxException("Invalid Song Id");
        int sngId = resultSet1.getInt(1);

            String sql = "insert into songsinplaylist values(?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, playId);
            stmt.setInt(2, sngId);
            boolean status = stmt.execute();
            if(!status) {
                System.out.println("Added successfully ");
            }
            else {
                System.out.println("Song not Added");
            }
    }


//    public static void executingPlaylistSongQuery(String query) throws SQLException {
//        Connection connection = UtilDb.getConnection();
//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery(query);
//        System.out.println("--------------------------------------------");
//        System.out.format("%-18s\t%-1s\n","SongId","PlaylistSong Name");
//        System.out.println("--------------------------------------------");
//        while (resultSet.next())
//        {
//            int playlistId = resultSet.getInt(1);
//            String playlistName = resultSet.getString(2);
//            System.out.format("%-18d\t%-1s\n",playlistId,playlistName);
//        }
//        System.out.println("--------------------------------------------");
//    }



}
