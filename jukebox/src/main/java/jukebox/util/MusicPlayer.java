package jukebox.util;

import jukebox.bean.Songs;
import jukebox.dao.SongDAO;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MusicPlayer extends UtilDb {

    static Scanner sc = new Scanner(System.in);

    public MusicPlayer() {
    }

    public static void playSong(List<Songs> songList) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        boolean flag = false;
        do {
            String choice = " ";
            String path = " ";
            SongDAO.getSonglist();
            for (Songs s : songList) {
                path = s.getFilePath();
            }
            Clip clip = AudioSystem.getClip();
            File file = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip.open(audioStream);

            while (!choice.equalsIgnoreCase("Q")) {
                System.out.println(" \t\tP : Play | S : Stop |  N : Next | Q : Quit \n");
                System.out.println("Enter you choice");
                choice = sc.next().toUpperCase();
                switch (choice) {
                    case "P":
                        System.out.println("Play");
                        clip.start();
                        break;
                    case "S":
                        System.out.println("Stop");
                        clip.stop();
                        break;

                    case "Q":
                        System.out.println("Quit");
                        clip.stop();
                        break;

                    case "N":
                        System.out.println("Next");
                        clip.close();
                        break;

                    default:
                        System.out.println("Invalid Input");
                }
            }
        } while (flag);
    }

//    public static  void   playSongs(List<Songs> allSongList) {
//        try {
//            allSongList = SongDAO.songlist;
//            SongDAO.getSonglist();
//
//            Iterator<Songs> itr = allSongList.iterator();
//
//            while (itr.hasNext()) {
//                String filepath = itr.next().getFilePath();
//                try {
//                    File path = new File(filepath);
//                    if (path.exists()) {
//                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(path);
//                        Clip clip = AudioSystem.getClip();
//                        clip.open(audioStream);
//                        String response = " ";
//
//                        while (!response.equals("Q")) {
//                            System.out.println(" \t\t\t\t\t\tP : play  |  Q : Quit ");
//                            System.out.print("Press the Key : ");
//                            response = sc.next();
//                            response = response.toUpperCase();
//
//                            switch (response) {
//                                case ("P"):
//                                    clip.start();
//                                    break;
//
////                            case ("S"):
////                                System.out.println("hi");
////                                long currentFrame = 0L;
////                                clip.stop();
////                                clip.close();
////                                break;
////
////                            case ("R"):
////                                System.out.println("hello");
////                                clip.stop();
////                                clip.close();
////                                clip.loop(Clip.LOOP_CONTINUOUSLY);
////                                currentFrame = 0L;
////                                clip.setMicrosecondPosition(0);
////                                clip.start();
////                                break;
//
//                                case ("Q"):
//                                    clip.close();
//                                    break;
//
////                            case ("N"):
////                                clip.stop();
////                                filepath = itr.next().getFilePath();
////                                if(filepath != null){
////                                path = new File(filepath);
////                                    audioStream = AudioSystem.getAudioInputStream(path);
////                                    clip = AudioSystem.getClip();
////                                    clip.open(audioStream);
////                                    clip.start();
////                                }else{
////                                    System.out.println("No more song exist !");
////                                }
//
//                                default:
//                                    System.out.println("Please press correct key");
//
//                            }
//
//                            if (response.equalsIgnoreCase("Q") || response.equalsIgnoreCase("N"))
//                                break;
//
//                        }
//                        if (response.equals("Q") || response.equals("q"))
//                            break;
//
//                    }
//                } catch (FileNotFoundException e) {
//                    System.out.println(e.getMessage());
//                } catch (UnsupportedAudioFileException e) {
//                    throw new RuntimeException(e);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//        } catch (LineUnavailableException exception) {
//            System.out.println(exception.getMessage());
//
//        }
//
//
////    public void resetAudioStream() throws UnsupportedAudioFileException, IOException,
////            LineUnavailableException
////    {
////        audioInputStream = AudioSystem.getAudioInputStream(
////                new File(filePath).getAbsoluteFile());
////        clip.open(audioInputStream);
////        clip.loop(Clip.LOOP_CONTINUOUSLY);
////    }
//    }
}
