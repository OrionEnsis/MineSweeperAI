package GUI;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


public class Tile extends StackPane {
    static final int SIZE = 40;

    private int x,y;
    private boolean mine;
    private boolean isOpen;
    private int mines;
    private boolean marked;
    private MineSweeperPane parent;

    private Rectangle border;
    private Text text;

    public Tile(int x, int y, boolean mine, MineSweeperPane parent){
        this.x = x;
        this.y = y;
        this.mine = mine;
        this.parent = parent;
        mines = 0;
        isOpen = false;
        marked = false;
        border = new Rectangle(SIZE-2,SIZE-2);
        border.setStroke(Color.LIGHTGRAY);

        //TODO replace with graphic
        text = new Text();
        text.setVisible(false);
        if(mine){
            text.setText("X");
        }
        //border.setFill(Color.WHITE);
        getChildren().addAll(border,text);

        setTranslateX(x * SIZE);
        setTranslateY(y * SIZE);

        setOnMouseClicked(event -> {
            MouseButton m = event.getButton();
            if(m==MouseButton.PRIMARY){
                open();
            }else{
                markMine();
            }
        });
    }
    public void markMine(){
        if(!isOpen) {
            marked = !marked;
            if (marked) {
                text.setText("F");
                text.setFill(Color.WHITE);
                text.setVisible(true);
            }
            else{
                text.setText(""+mines);
                text.setFill(Color.BLACK);
                text.setVisible(false);
            }
        }
    }

    public void open(){
        if(!isOpen && !marked){
            isOpen = true;
            text.setVisible(true);
            border.setFill(null);

            if(text.getText().equals("0")){
                parent.getNeighbors(this).forEach(Tile::open);
            }else if(isMine()){
                //gameover
                System.out.println("GAME OVER");
            }
        }
    }

    public boolean isMine() {
        return mine;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMines(int mines) {
        if (!isMine()) {
            this.mines = mines;
            text.setText(""+this.mines);

        }
    }

    public boolean isMarked() {
        return marked;
    }
}
