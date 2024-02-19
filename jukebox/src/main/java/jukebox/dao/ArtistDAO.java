package jukebox.dao;

import jukebox.bean.Songs;
import jukebox.util.UtilDb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ArtistDAO {

    public static ArrayList<Songs> searchByArtist(String artistNm) {

        Connection con = UtilDb.getConnection();
        ArrayList<Songs> search = new ArrayList<>();

        try {
            Statement statement = con.createStatement();
//            String sql1 = "select artistname from artist where artistname = '" + artistNm + "';";
//            ResultSet resultSet = statement.executeQuery(sql1);
//            if (!resultSet.next())
//                System.out.println("Invalid Artist Name!");
//            String artistName = resultSet.getString(1);

            Statement statement1 = con.createStatement();
            String sql2 = "select artistName from songs where artistname = '" + artistNm + "';";
            ResultSet resultSet1 = statement1.executeQuery(sql2);
            if (!resultSet1.next())
                System.out.println("Invalid Artist Id!");
            String artistName = resultSet1.getString(1);


            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from songs where artistName ='" + artistName + "';");

            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.format("%-9s\t%-25s\t%-16s\t%-20s\t%-8s\n", "SongId", "Song Name", "Album", "Duration", "Artist Name");
            System.out.println("-----------------------------------------------------------------------------------------------");
            Songs song = null;
            while (rs.next()) {
                song = new Songs();
                song.setSongId(rs.getInt(1));
                song.setSongName(rs.getString(2));
                song.setAlbum(rs.getString(3));
                song.setDuration(rs.getString(4));

                System.out.format("%-9d\t%-25s\t%-16s\t%-20s\t%-8s\n", rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), artistName);
                System.out.println("-----------------------------------------------------------------------------------------------");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return search;
    }
}
