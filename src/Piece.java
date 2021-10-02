import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Point;
public abstract class Piece{
    public Square loc;
    public int x;
    public int y;
    public boolean moved;
    public int posOnBoard;
    BufferedImage image;
    Boolean isWhite;

    public Piece(){
        moved = false;
    }
    public void paint(Graphics g) {
        if(isWhite){
            try{
                image = ImageIO.read(new File(".\\img\\white_" + this.getClass().getName() + ".png"));
                }
            catch(IOException e){
                    System.out.println("Failed to load: .\\img\\white_" + this.getClass().getName() + ".png");
                }
        }
        else{
            try{
                image = ImageIO.read(new File(".\\img\\black_" + this.getClass().getName() + ".png"));
                }
            catch(IOException e){
                    System.out.println("Failed to load: .\\img\\black_" + this.getClass().getName() + ".png");
                }  
        }
        draw(g);
       
    }
    public void draw(Graphics g){
        g.drawImage(image, x + 4, y + 4, 75, 75, null);
    }
    public void setPos(Point p){
        x = p.x;
        y = p.y;
    }
    public void setLoc(Square s){
        loc.piece = null;
        loc = s;
        loc.piece = this;
        x = loc.x;
        y = loc.y;
    }
}