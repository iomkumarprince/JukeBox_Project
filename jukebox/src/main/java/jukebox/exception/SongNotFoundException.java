package jukebox.exception;

public class SongNotFoundException extends Exception{
    public SongNotFoundException(String str){
        super(str);
    }
}
