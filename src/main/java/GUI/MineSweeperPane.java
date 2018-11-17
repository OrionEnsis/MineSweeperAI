package GUI;

import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class MineSweeperPane extends Pane {
    public static boolean bombTrigger;

    //TILE DIMENSIONS
    private static final int X_TILES = 5;
    private static final int Y_TILES = 5;

    //BOARD SIZE
    private static final int BOARD_WIDTH = Tile.SIZE * X_TILES;
    private static final int BOARD_HEIGHT =  Tile.SIZE * Y_TILES;

    //BOARD
    private Tile[][] grid;

    private ArrayList<Tile> mines;

    public MineSweeperPane(){
        super();
        mines = new ArrayList<>();
        grid = new Tile[X_TILES][Y_TILES];

        for(int y = 0; y < Y_TILES; y++){
            for(int x = 0; x < X_TILES; x++){
                Tile tile = new Tile(x,y,Math.random() < .2,this);
                grid[x][y] = tile;
                getChildren().add(tile);
            }
        }

        for(int y = 0; y < Y_TILES; y++){
            for(int x = 0; x < X_TILES; x++){
                //getBombs
                if(!grid[x][y].isMine()) {
                    List<Tile> t = getNeighbors(grid[x][y]);
                    long mine = t.parallelStream().filter(Tile::isMine).count();
                    grid[x][y].setMines((int) mine);
                }else{
                    mines.add(grid[x][y]);
                }
            }
        }
        setOnMouseClicked(e->click());
    }

    public void click(){
        if(bombTrigger){
            System.out.println("GAME OVER");
        }else{
            long total = mines.parallelStream().filter(Tile::isMarked).count();
            if(total == mines.size()){
                System.out.println("Victory!");
            }
        }
    }
    List<Tile> getNeighbors(Tile t){
        List<Tile> tiles = new ArrayList<>();

        //all neighbors
        int[] points = new int[]{
                -1,-1,
                -1,0,
                -1,1,
                0,-1,
                0,0,
                0,1,
                1,-1,
                1,0,
                1,1};

        //iterate
        for (int i = 0; i < points.length; i++) {
            int x = points[i];
            int y = points[++i]; //we want all values, but half the loop iterations

            int neighborX = t.getX() + x;
            int neighborY = t.getY() + y;

            if(neighborX >=0 && neighborX <= X_TILES -1 &&
                    neighborY >= 0 && neighborY <= Y_TILES-1) {
                tiles.add(grid[neighborX][neighborY]);
            }
        }

        return tiles;
    }
}
