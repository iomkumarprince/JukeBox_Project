package jukebox;

import jukebox.exception.JukeboxException;
import jukebox.util.MusicPlayer;
import jukebox.bean.Playlist;
import jukebox.bean.Songs;
import jukebox.dao.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JukeboxImpl {

        public static void choice() throws UnsupportedAudioFileException, LineUnavailableException, IOException, SQLException, JukeboxException {
            Scanner sc = new Scanner(System.in);

            MusicPlayer musicPlayer = new MusicPlayer();
            List<Songs> songlist = new ArrayList<>();
            SongDAO songDAO = new SongDAO();
            //PlaylistDAO playlistDAO = new PlaylistDAO();
           //PlaylistSongDAO playlistSongDAO = new PlaylistSongDAO();
           // Playlist playlist = new Playlist();
            Songs song = new Songs();
            ArtistDAO artistDAO = new ArtistDAO();
            GenreDAO genreDAO = new GenreDAO();

            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("+++++++++++++++++++++++++++++++++++++++|-||--|||---| J |---| U |---| K |---| E |---| B |---| O |---| X |---|||--||-|++++++++++++++++++++++++++++++++++++++");
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n\n");
            System.out.println(" W E L C O M E \n");

            //boolean flag = false;
            boolean superFlag = false;

            do
            {
                System.out.println("Please choose what u want !!!");
                System.out.println("1 : Song Display | 2 : Play Song | 3 : Search | 4 : Playlist   | 5 : Exit\n");
                System.out.print("Enter your choice : \n");
                int option = sc.nextInt();
                switch (option) {

                    case 1:
                        songDAO.displaySonglist();
                        superFlag = true;
                        break;

                    case 2:
                        boolean flag4 = true;
                        do{
                            System.out.println("Choose the option : ");
                            System.out.println("1 : Play All Song  | 2 : Play Song by Choice  | 3. Exit  \n");
                            System.out.print("Enter your choice : ");
                            int option1 = sc.nextInt();
                            sc.nextLine();
                            switch(option1){
                                case 1:
                                    SongDAO.getSonglist();
                                    songDAO.displaySonglist();
                                    System.out.println();
                                    MusicPlayer.playSong(SongDAO.songlist);
                                    flag4=true;
                                    break;
                                case 2:
                                    List<Songs> l2 = songDAO.getSonglist();
                                    songDAO.displaySonglist();
                                    System.out.println();
                                    System.out.print("Enter Song ID : ");
                                    int songId = sc.nextInt();
                                    songDAO.playBySongId(songId);
                                    break;
                                case 3:
                                    flag4 = false;
                                    superFlag = true;
                                    break;
                                default:
                                    System.out.println("Invalid Input");
                                    break;
                            }

                        }while(flag4);
                        break;

                    case 3:
                        boolean flag3 = true;
                        do{
                            System.out.println("Choose the option : ");
                            System.out.println("1 : Search by Song Name  | 2 : Search by artist Name  | 3. Search by genre  | 4. Exit");
                            System.out.println();
                            System.out.print("Enter Search Option : ");
                            int choice3 = sc.nextInt();
                            sc.nextLine();

                            switch (choice3) {

                                case 1:
                                    songDAO.displaySonglist();
                                    System.out.println("Enter Song Name : ");
                                    String name = sc.nextLine();
                                    List<Songs> l1 = SongDAO.searchBySong(name);
                                    musicPlayer.playSong(l1);
                                    break;
                                case 2:
                                    System.out.println("Ariana Grande | Bruno Mars | Billie Elish | Taylor Swift | Ed Sheeran | Justin Bieber | Shawn Mendes | Selena Gomez | Alan Walker");
                                    System.out.println("Enter Artist Name : ");
                                    String artist = sc.nextLine();
                                    List<Songs> l2 = ArtistDAO.searchByArtist(artist);
                                    System.out.println("\n\n");
                                    System.out.print("Enter Song ID : ");
                                    int songId = sc.nextInt();
                                    songDAO.playBySongId(songId);
                                    break;

                                case 3:
                                    System.out.println("Country | EDM | Hip-hop | Indie | Jazz | Love | Party | Rock | Soul");
                                    System.out.println("Enter Genre : ");
                                    String genre = sc.nextLine();
                                    List<Songs> l3 = genreDAO.searchByGenre(genre);
                                    System.out.println("\n\n");
                                    System.out.print("Enter Song ID : ");
                                    songId = sc.nextInt();
                                    songDAO.playBySongId(songId);
                                    break;

                                case 4:
                                    flag3 = false;
                                    superFlag = true;
                                    break;
                                default:
                                    System.out.println("Invalid Input!");
                                    break;
                            }

                        }while(flag3);
                        break;

                    case 4:
                        superFlag = false;
                        break;

                    default:
                        System.out.println("Invalid input");
                        break;
                }
                System.out.println();
                //System.out.println("Press any key to exit");
            }while (superFlag);
        }
        public static void createPlayList() throws SQLException, UnsupportedAudioFileException, LineUnavailableException, JukeboxException, IOException {
            PlaylistSongDAO playlistSongDAO = new PlaylistSongDAO();
            Scanner sc = new Scanner(System.in);
            PlaylistDAO playlistDAO = new PlaylistDAO();
            boolean superFlag=false;
            boolean flag1=false;
            do {
                System.out.format("1 : Existing PlayList  | 2 : Create PlayList | 3 : Delete Playlist  | 4 : Add Song in PlayList | 5 : Play songs of playlist | 7 : Exit\n");
                System.out.println();
                System.out.print("Enter the Option : ");
                int choice1 = sc.nextInt();

                switch (choice1) {
                    case 1:
                        playlistDAO.seeExistingPlaylist();
                        createPlayList();
                        break;

                    case 2:
                        System.out.println("Enter New Playlist Name : ");
                        String name = sc.next();
                        playlistDAO.createPlaylist(name);
                        createPlayList();

                        break;

                    case 3:
                        playlistDAO.seeExistingPlaylist();
                        System.out.println("Enter the playlist id you want to delete : ");
                        int id = sc.nextInt();
                        playlistDAO.deletePlaylist(id);
                        createPlayList();
                        break;

                    case 4:

                        PlaylistDAO.seeExistingPlaylist();
                        List<Songs> li = SongDAO.getSonglist();
                        System.out.println("Enter playlist id : ");
                        int id1 = sc.nextInt();
                        SongDAO.displaySonglist();
                        System.out.println("Enter Song Id : ");
                        int id2 = sc.nextInt();
                        playlistSongDAO.addSongsToPlayList(id1,id2);
                        createPlayList();
                        break;

                    case 5:
                        PlaylistDAO.seeExistingPlaylist();
                        System.out.println("Enter PLaylist Id : ");
                        int playlistId1 = sc.nextInt();
                        playlistSongDAO.songDetailsOfSpecificPlaylist(playlistId1);
                        System.out.println("Enter Song Id : ");
                        int sngid = sc.nextInt();
                        playlistSongDAO.playSongOfPlaylist(sngid);
                        createPlayList();
                        break;

                    default:
                        System.out.println("Invalid Input");
                        createPlayList();
                        break;
                }
            } while (flag1);
        }

    public static void main(String[] args) throws UnsupportedAudioFileException, SQLException, LineUnavailableException, JukeboxException, IOException {
        choice();
        createPlayList();
    }
    }










