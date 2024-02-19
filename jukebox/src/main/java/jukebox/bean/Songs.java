package jukebox.bean;

public class Songs {
    private int songId;
    private String songName;
    private String album;
    private String duration;

    private String artistName;
    private String genre;
    private String filePath;


    public Songs() {
    }

    public Songs(int songId, String songName, String album, String duration, String artistName, String genre, String filePath) {
        this.songId = songId;
        this.songName = songName;
        this.album = album;
        this.duration = duration;
        this.artistName = artistName;
        this.genre = genre;
        this.filePath = filePath;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Songs{" +
                "songId=" + songId +
                ", songName='" + songName + '\'' +
                ", album='" + album + '\'' +
                ", duration='" + duration + '\'' +
                ", artistName='" + artistName + '\'' +
                ", genre='" + genre + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}

