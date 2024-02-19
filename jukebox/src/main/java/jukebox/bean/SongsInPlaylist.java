package jukebox.bean;

public class SongsInPlaylist {


    private int songId;
    private int playlistId;

    public SongsInPlaylist() {
    }

    public SongsInPlaylist(int songId, int playlistId) {
        this.songId = songId;
        this.playlistId = playlistId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    @Override
    public String toString() {
        return "SongsInPlaylist{" +
                "songId=" + songId +
                ", playlistId=" + playlistId +
                '}';
    }
}
