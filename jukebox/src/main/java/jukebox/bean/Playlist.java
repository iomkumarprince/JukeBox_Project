package jukebox.bean;

public class Playlist {
    private int playlistId;
    private String playlistName;


    public Playlist() {
    }

    public Playlist(int playlistId, String playlistName) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    @Override
    public String toString() {
        return "PlaylistDAO{" + "playlistId=" + playlistId + ", playlistName='" + playlistName + '\'' + '}';
    }
}
