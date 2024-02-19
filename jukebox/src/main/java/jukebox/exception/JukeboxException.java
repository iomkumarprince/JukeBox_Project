package jukebox.exception;

public class JukeboxException extends  Exception{
    public JukeboxException()
    {
        super("Playlist not Deleted!");
    }
    public JukeboxException(String message)
    {
        super(message);
    }

}
