package jukebox.dao;

import jukebox.exception.GenreNotFoundException;
import jukebox.util.MusicPlayer;
import jukebox.bean.Songs;
import jukebox.util.UtilDb;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GenreDAO {

    public static ArrayList<Songs> searchByGenre(String genreNm) {
        Connection connection = UtilDb.getConnection();
        ArrayList<Songs> genre = new ArrayList<>();
        MusicPlayer musicPlayer = new MusicPlayer();
        Songs song = null;
        try {

//            Statement statement = connection.createStatement();
//            String sql1 = "select genrename from songs where genre = '"+genreNm+"';";
//            ResultSet resultSet = statement.executeQuery(sql1);
//            if (!resultSet.next())
//                System.out.println("Invalid Genre Name!");
//            String genreName = resultSet.getString(1);

            Statement statement1 = connection.createStatement();
            String sql2 = "select genre from songs where genre = '"+genreNm+"';";
            ResultSet resultSet1 = statement1.executeQuery(sql2);
            if (!resultSet1.next())
                throw new GenreNotFoundException("Please enter correct genre");
            String genreName = resultSet1.getString(1);


            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("select * from songs where genre='" + genreName + "';");
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.format("%-9s\t%-25s\t%-16s\t%-20s\t%-8s\n", "SongId", "Song Name", "Album", "Duration", "Genre" );
            System.out.println("-------------------------------------------------------------------------------------------");
            while (rs.next()) {
                Songs song1 = new Songs();
                song1.setSongId(rs.getInt(1));
                song1.setSongName(rs.getString(2));
                song1.setAlbum(rs.getString(3));
                song1.setDuration(rs.getString(4));
                System.out.format("%-9d\t%-25s\t%-16s\t%-20s\t%-8s\n", rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), genreName);
                System.out.println("-------------------------------------------------------------------------------------------");
            }
        } catch (SQLException | GenreNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }
}
