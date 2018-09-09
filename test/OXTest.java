import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OXTest {
    @Test
    void getTableString(){
        OX ox = new OX();
        // " 012\n0---\n1---\n2---\n"
        assertEquals(" 012\n" +
                "0---\n" +
                "1---\n" +
                "2---\n",ox.getTableString());
        assertEquals("X",ox.getCurrentPlayer());
    }

    @Test
    public void switchPlayer(){
        OX ox = new OX();
        ox.switchPlayer();
        assertEquals("O", ox.getCurrentPlayer());
        ox.switchPlayer();
        assertEquals("X", ox.getCurrentPlayer());
    }

    @Test
    void getCurrentPlayer(){
        OX ox = new OX();
        assertEquals("X", ox.getCurrentPlayer());
    }

    @Test
    void put(){
        OX ox = new OX();

        ox.put(0,0);
        assertEquals(" 012\n" +
                "0X--\n" +
                "1---\n" +
                "2---\n",ox.getTableString());

    }

    @Test
    void get(){
        OX ox = new OX();

        ox.put(0,0);
        assertEquals(" 012\n" +
                "0X--\n" +
                "1---\n" +
                "2---\n",ox.getTableString());
        assertEquals("X",ox.get(0,0));

    }

    @Test
    void putTwice(){
        OX ox = new OX();
        assertTrue(ox.put(0, 0));
        assertFalse(ox.put(0, 0));
    }

    @Test
    void putOverTable(){
        OX ox = new OX();
        assertFalse(ox.put(0, -1));
        assertFalse(ox.put(0, -3));
        assertFalse(ox.put(3, -1));
        assertFalse(ox.put(-1, 3));
    }

    @Test
    void getOverTable(){
        OX ox = new OX();
        assertNull(ox.get(0, -1));
        assertNull(ox.get(0, -3));
        assertNull(ox.get(3, -1));
        assertNull(ox.get(-1, 3));
    }

    @Test
    void checkWinCol0(){
        OX ox = new OX();
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertTrue(ox.checkWin(0,0));
        assertTrue(ox.checkWin(0,1));
        assertTrue(ox.checkWin(0,2));
        assertFalse(ox.checkWin(1,0));
        assertFalse(ox.checkWin(1,1));
        assertFalse(ox.checkWin(1,2));
    }

    @Test
    void checkWinCol2(){
        OX ox = new OX();
        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertTrue(ox.checkWin(2,0));
        assertTrue(ox.checkWin(2,1));
        assertTrue(ox.checkWin(2,2));
        assertFalse(ox.checkWin(1,0));
        assertFalse(ox.checkWin(1,1));
        assertFalse(ox.checkWin(1,2));
    }

    @Test
    void checkWinRow2(){
        OX ox = new OX();
        ox.put(0,2);
        ox.put(1,2);
        ox.put(2,2);
        assertTrue(ox.checkWin(0,2));
        assertTrue(ox.checkWin(1,2));
        assertTrue(ox.checkWin(2,2));
        assertFalse(ox.checkWin(1,0));
        assertFalse(ox.checkWin(1,1));
        assertFalse(ox.checkWin(2,1));
    }

    @Test
    void checkWinES(){
        OX ox = new OX();
        ox.put(0,0);
        ox.put(1,1);
        ox.put(2,2);
        assertTrue(ox.checkWin(0,0));
        assertTrue(ox.checkWin(1,1));
        assertTrue(ox.checkWin(2,2));

    }

    @Test
    void checkWinSS(){
        OX ox = new OX();
        ox.put(2,0);
        ox.put(1,1);
        ox.put(0,2);
        assertTrue(ox.checkWin(2,0));
        assertTrue(ox.checkWin(1,1));
        assertTrue(ox.checkWin(0,2));

    }

    @Test
    void reset(){
        OX ox = new OX();
        ox.put(2,0);
        ox.put(1,1);
        ox.put(0,2);
        ox.reset();
        assertEquals(" 012\n" +
                "0---\n" +
                "1---\n" +
                "2---\n",ox.getTableString());
        assertEquals("X", ox.getCurrentPlayer());
        assertEquals(0, ox.getTurnCount());
    }

    @Test
    void getTurnCount(){
        OX ox = new OX();
        assertEquals(0 , ox.getTurnCount());
        ox.put(0,0);
        assertEquals(1 ,ox.getTurnCount());
    }

    @Test
    void isDraw(){
        OX ox = new OX();
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertFalse(ox.isDraw());
        ox.put(1,0);
        ox.put(1,1);
        ox.put(1,2);
        assertFalse(ox.isDraw());
        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertTrue(ox.isDraw());
    }

    @Test
    void getCountX(){
        OX Ox = new OX();
        assertEquals(0 ,Ox.getCountX());
        Ox.put(0,0);
        Ox.put(0,1);
        Ox.put(0,2);
        assertEquals(1,Ox.getCountX());

    }

    @Test
    void getCountO(){
        OX ox = new OX();
        ox.switchPlayer();
        assertEquals(0 ,ox.getCountO());
        ox.put(0,0);
        ox.put(1,1);
        ox.put(2,2);
        assertEquals(1,ox.getCountO());

    }

    @Test
    void getCountDraw(){
        OX ox = new OX();
        assertEquals(0 ,ox.getCountDraw());
        ox.put(0,0);
        ox.put(0,1);
        ox.put(0,2);
        assertFalse(ox.isDraw());
        ox.put(1,0);
        ox.put(1,1);
        ox.put(1,2);
        assertFalse(ox.isDraw());
        ox.put(2,0);
        ox.put(2,1);
        ox.put(2,2);
        assertTrue(ox.isDraw());
        assertEquals(1,ox.getCountDraw());

    }
}