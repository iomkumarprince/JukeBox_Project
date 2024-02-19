import jukebox.bean.Songs;
import jukebox.dao.SongDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SongDAOTest {
    SongDAO songDAO;
    @BeforeEach
    void setup(){
        SongDAO songDAO = new SongDAO();
    }

    void tearDown(){
        songDAO = null;
    }
    @Test
    public void Songs(){
        List<Songs> list = songDAO.getSonglist();
        assertEquals(19,(list.size()));
    }
}
