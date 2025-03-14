

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision=false;
    public int worldX,worldY;

    public void draw(Graphics2D g2,GamePanel gb)
    {
        int screenX =worldX -gb.player.worldX+gb.player.screenX;
                int screenY =worldY -gb.player.worldY+gb.player.screenY;

                if(worldX +gb.tileSize >gb.player.worldX-gb.player.screenX && worldX -gb.tileSize <gb.player.worldX +gb.player.screenX 
                && worldY +gb.tileSize >gb.player.worldY-gb.player.screenY && worldY -gb.tileSize <gb.player.worldY +gb.player.screenY )
                {
                    g2.drawImage(image, screenX, screenY,gb.tileSize,gb.tileSize,null);
                }
    }

}
