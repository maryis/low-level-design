//package connectFour;
//
//
//import onlinechess.Color;
//
//import java.util.Arrays;
//
//public class Game {
//    int w;
//    int h;
//    CellType[][] board;
//    Color currentCollor;
//
//    public Game(int w, int h) {
//        this.w = w;
//        this.h = h;
//        board = new CellType[h][w];
//        init();
//    }
//
//    private void init(){
//        for (int i=0; i<h; i++)
//            Arrays.fill(board[i], CellType.Empty);
//        currentCollor = Color.Red;
//    }
//
//    public void play(int i) {
//        //valid move
//        //
//        board[i][j]
//
//    }
//
//    enum CellType{
//        Empty,
//        Red,
//        White
//    }
//
//    enum Color {
//        Red,
//        White;
//
//        CellType getCellType() {
//            if (this.equals(Red)) return CellType.Red;
//            return CellType.White;
//        }
//    }
//}
