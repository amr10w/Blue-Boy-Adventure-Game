
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial_40,arial_80B;
    BufferedImage keyImage;
    public boolean messageOn=false;
    public String messagge="";
    int messageConter=0;
    public boolean gameFinished=false;


    double playTime=0;
    DecimalFormat dFormat=new DecimalFormat ("#0.00");
    
     public UI(GamePanel gp)
     {
        this.gp=gp;
        arial_40=new Font("Arial",Font.PLAIN,40);
        arial_80B=new Font("Arial",Font.BOLD,80);
        OBJ_Key key= new OBJ_Key();
        keyImage=key.image;
     }
     public void showMessage(String text)
     {
        messagge=text;
        messageOn=true;
     }
     public void draw(Graphics2D g2)
     {

        if(gameFinished==true)
        {
            g2.setFont(arial_40);
            g2.setColor(Color.WHITE);

            String text;
            int textLength;
            int x;
            int y;

            text="You found the treasure!";
            textLength=(int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();


            x=gp.ScreenWidth/2-textLength/2;
            y=gp.ScreenHeight/2-gp.tileSize*3;
            g2.drawString(text, x, y);


            text="Your Time is :"+dFormat.format(playTime);
            textLength=(int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();


            x=gp.ScreenWidth/2-textLength/2;
            y=gp.ScreenHeight/2+gp.tileSize*4;
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.YELLOW);
            text="Congrautulations";
            textLength=(int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();


            x=gp.ScreenWidth/2-textLength/2;
            y=gp.ScreenHeight/2+gp.tileSize*2;
            g2.drawString(text, x, y);

            gp.gameThread=null;

        }
        else{
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawImage(keyImage,gp.tileSize/2,gp.tileSize/2,gp.tileSize,gp.tileSize,null);
        g2.drawString("x "+gp.player.hasKey, gp.tileSize+gp.tileSize/2, gp.tileSize+gp.tileSize/4);

        playTime+=(double)1/gp.FPS;
        g2.drawString("Time:"+dFormat.format(playTime),gp.tileSize*11+24,65);

        //message if message on
        if(messageOn==true)
        {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(messagge, gp.tileSize/2, gp.tileSize*4);

            messageConter++;

            if(messageConter>2*60)
            {
                messageConter=0;
                messageOn=false;
            }
        }
    }

     }

    
}
