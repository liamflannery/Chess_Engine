import java.awt.Graphics;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public abstract class Piece{
    Square loc;
    BufferedImage image;
    Boolean isWhite;
    public Piece(){
      //  if(isWhite){
          
        }
     /*   else{
            try{
                image = ImageIO.read(new File(".\\img\\black" + this.getClass().getName() + ".png"));
                }
            catch(IOException e){
                    System.out.println("Failed to load: .\\img\\black" + this.getClass().getName() + ".png");
                }
        }
    }*/
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
        g.drawImage(image, loc.x + 2, loc.y + 2, 85, 85, null);
    }
}