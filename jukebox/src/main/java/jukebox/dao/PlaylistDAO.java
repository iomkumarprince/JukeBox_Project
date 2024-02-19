package jukebox.dao;

import jukebox.exception.JukeboxException;
import jukebox.util.UtilDb;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;


public class PlaylistDAO {
    Scanner sc = new Scanner(System.in);
        public void createPlaylist(String name)
                throws SQLException, UnsupportedAudioFileException, LineUnavailableException, IOException, JukeboxException {
            Connection connection = UtilDb.getConnection();
            String sql = "insert into playlist(playlistname) values(?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            boolean status = preparedStatement.execute();
            if (!status)
            {
                System.out.println("Your playlist has been Successfully created \n");
                System.out.println();
            }
            else
            {
                throw new JukeboxException("Your playlist has not been created ");
            }
        }


    public static void executingPlaylistQuery(String query) throws SQLException {
        Connection connection = UtilDb.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("--------------------------------------------");
        System.out.format("%-18s\t%-1s\n","Playlist Id","Playlist Name");
        System.out.println("--------------------------------------------");
        while (resultSet.next())
        {
            int playlistId = resultSet.getInt(1);
            String playlistName = resultSet.getString(2);
            System.out.format("%-18d\t%-1s\n",playlistId,playlistName);
        }
        System.out.println("--------------------------------------------");
    }

        public static void seeExistingPlaylist() throws SQLException {
            String sql = "select * from playlist ;";
            executingPlaylistQuery(sql);
        }
        public void deletePlaylist(int id) throws SQLException, JukeboxException {
            //seeExistingPlaylist();

            String sql1 = "delete from playlistsong where playlistId = "+id+";";
            Connection connection = UtilDb.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql1);

            String sql2 = "delete from playlist where playlistId = "+id+";";
            connection = UtilDb.getConnection();
            statement = connection.createStatement();
            int row = statement.executeUpdate(sql2);
            if (row>=1)
            {
                System.out.println("Playlist Deleted Successfully!");
            }
            else
            {
                throw new JukeboxException();
            }
        }
    }




