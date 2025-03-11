
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends Entity
{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp,KeyHandler keyH) {
        this.gp=gp;
        this.keyH=keyH;

        screenX=gp.ScreenWidth/2 -(gp.tileSize/2);
        screenY=gp.ScreenHeight/2 - (gp.tileSize/2);

        setDefaultValues();
        getPlayerImage();
        direction="down";

    }

    public void setDefaultValues(){
        worldX=gp.tileSize*23;
        worldY=gp.tileSize*21;
        speed=4;
    }
    public void getPlayerImage()
    {
        try {
            
            up1 = ImageIO.read(getClass().getResourceAsStream("/src/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/src/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/src/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/src/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/src/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/src/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/src/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/src/player/boy_right_2.png"));

            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(keyH.upPressed==true||keyH.downPressed==true||keyH.leftPressed==true||keyH.rightPressed==true)
        {
        
       
            if(keyH.upPressed==true)
            {
                direction="up";
                //if (worldY - speed >= 0) { 
                    worldY -= speed;
                //}
            }
            if(keyH.downPressed==true)
            {
                direction="down";
                //if (worldY + speed <= gp.ScreenHeight - gp.tileSize) { 
                    worldY += speed;
                //}
            }
             if(keyH.leftPressed==true)
            {
                direction="left";
                //if (worldX - speed >= 0) { 
                    worldX -= speed;
                //}
            }
            if(keyH.rightPressed==true)
            {
                direction="right";
                //if (worldX + speed <= gp.ScreenWidth - gp.tileSize) {
                    worldX += speed;
                //}
            }
            spriteCounter++;
            if(spriteCounter>12)
            {
            
                if(spriteNum==1)
                {
                    spriteNum=2;
                }
                else if(spriteNum==2)
                {
                    spriteNum=1;
                }
                spriteCounter=0;
            }
        }
    }
    public void draw(Graphics2D g2)
    {
        // g2.setColor(Color.WHITE);
        // g2.fillRect(x,y,gp.tileSize,gp.tileSize);

        BufferedImage image=null;

        switch(direction)
        {
            case "up": 
                if(spriteNum==1)
                {
                    image=up1;
                }
                if(spriteNum==2)
                {
                    image=up2;
                }
                 
                break;
            case "down": 
                if(spriteNum==1)
                {
                    image=down1;
                }
                if(spriteNum==2)
                {
                    image=down2;
                }
                break;
            case "left": 
                if(spriteNum==1)
                {
                    image=left1;
                }
                if(spriteNum==2)
                {
                    image=left2;
                }
                break;
            case "right": 
                if(spriteNum==1)
                {
                    image=right1;
                }
                if(spriteNum==2)
                {
                    image=right2;
                }
                break;

        }
        g2.drawImage(image, screenX, screenY,gp.tileSize,gp.tileSize,null);


    }

    
}