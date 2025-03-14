
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

    private final int orginalTileSize=16;
    private final int scale=3;

    public final int tileSize =orginalTileSize*scale;
    public final int MaxScreenCol=16;
    public final int MaxScreenRow=12;
    public final int ScreenWidth=tileSize*MaxScreenCol;
    public final int ScreenHeight=tileSize*MaxScreenRow; 

    //world Setting
    public final int maxWorldCol=60;
    public final int maxWorldRow=60;
    //unless varibales:
    public final int maxWorldWidth=tileSize *maxWorldCol;
    public final int maxWorldHeight=tileSize *maxWorldRow;
    //-------------------------------------------------------



    public int FPS=60;

    //System

    //tile Manager
    TileManager tileM=new TileManager(this);
    SuperObject obj[]=new SuperObject[10];


    Sound music=new Sound();
    Sound soundEffect=new Sound();
    

    public Thread gameThread;
    KeyHandler keyH= new KeyHandler();

    public CollisionChecker cChecker =new CollisionChecker(this);

    public AssetSetter aSetter=new AssetSetter(this);
    public UI ui=new UI(this);  

    //Player entity 
    public Player player =new Player(this,keyH);

    
    public GamePanel()
    {
        super.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
        super.setBackground(new Color(0,0,0));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
       
    }

    public void setupGame()
    {
        aSetter.setObject();   
        playMusic(0); 
    }

    public void startGameThread()
    {
        gameThread=new Thread(this);
        
        gameThread.start();
    }

    @Override
    public void run()
    {
        double drawInterval=1000000000/FPS;
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;

        while(gameThread!=null)
        {
            
            

            currentTime=System.nanoTime();
            delta+=(currentTime-lastTime)/drawInterval; //delta is frames
            lastTime=currentTime;
            if(delta>=1)
            {
                update();
                repaint();
                delta--;
            }


        }


    }

    public void update()
    {
       player.update();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2=(Graphics2D) g;

        tileM.draw(g2);

        for(int i=0;i<obj.length;i++)
        {
            if(obj[i]!=null)
            {
                obj[i].draw(g2, this);

            }
        }

        player.draw(g2);

        ui.draw(g2);

        g2.dispose();
    }
    public void playMusic(int i)
    {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic()
    {
        music.stop();
    }

    public void playSE(int i)
    {
        soundEffect.setFile(i);
        soundEffect.play();
    }

    
}
