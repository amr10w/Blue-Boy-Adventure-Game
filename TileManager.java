    import java.awt.Graphics2D;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import javax.imageio.ImageIO;

    public class TileManager {
        GamePanel gb;
        public Tile [] tile;
    int mapTileNum[][];

        public TileManager(GamePanel gb)
        {
            this.gb=gb;

            tile=new Tile[10];
            //mapTileNum=new int [gb.MaxScreenCol][gb.MaxScreenRow]; this was for map not world
            mapTileNum=new int [gb.maxWorldCol][gb.maxWorldRow];
            
            getTileImage();
            loadMap("/src/maps/world01.txt");
        }

        public void getTileImage()
        {
            try {
                
                tile[0]=new Tile();
                tile[0].image=ImageIO.read(getClass().getResourceAsStream("/src/tiles/grass.png"));
            
                tile[1]=new Tile();
                tile[1].image=ImageIO.read(getClass().getResourceAsStream("/src/tiles/wall.png"));
                tile[1].collision=true;

                tile[2]=new Tile();
                tile[2].image=ImageIO.read(getClass().getResourceAsStream("/src/tiles/water.png"));
                tile[2].collision=true;

                tile[3]=new Tile();
                tile[3].image=ImageIO.read(getClass().getResourceAsStream("/src/tiles/earth.png"));
                
                
                tile[4]=new Tile();
                tile[4].image=ImageIO.read(getClass().getResourceAsStream("/src/tiles/tree.png"));
                tile[4].collision=true;
                
                tile[5]=new Tile();
                tile[5].image=ImageIO.read(getClass().getResourceAsStream("/src/tiles/sand.png"));
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void loadMap(String mapPath)
        {
            try {
                
            InputStream is=getClass().getResourceAsStream(mapPath);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
                
            int col=0;
            int row=0;

            while(col<gb.maxWorldCol&&row<gb.maxWorldRow)
            {
                    String line=br.readLine();

                    while(col<gb.maxWorldCol)
                    {
                        String numbers[]=line.split(" ");
                        int num=Integer.parseInt(numbers[col]);

                        mapTileNum[col][row]=num;
                        col++;
                    }
                    if(col ==gb.maxWorldCol)
                    {
                        col=0;
                        row++;
                    }
            }    
            br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void draw(Graphics2D g2)
        {
            // int col=0;
            // int row=0;
            // int x=0;  this was for map not world
            // int y=0;

            int worldCol=0;
            int worldRow=0;

            while(worldCol<gb.maxWorldCol&&worldRow<gb.maxWorldRow)
            {
                int tileNum=mapTileNum[worldCol][worldRow];

                int worldX=worldCol *gb.tileSize;
                int worldY=worldRow *gb.tileSize;
                int screenX =worldX -gb.player.worldX+gb.player.screenX;
                int screenY =worldY -gb.player.worldY+gb.player.screenY;

                if(worldX +gb.tileSize >gb.player.worldX-gb.player.screenX && worldX -gb.tileSize <gb.player.worldX +gb.player.screenX 
                && worldY +gb.tileSize >gb.player.worldY-gb.player.screenY && worldY -gb.tileSize <gb.player.worldY +gb.player.screenY )
                {
                    g2.drawImage(tile[tileNum].image, screenX, screenY,gb.tileSize,gb.tileSize,null);
                }
            
                worldCol++;

                if(worldCol==gb.maxWorldCol )
                {
                    worldCol=0;
                
                    worldRow++;
                    
                }
            }
        }
    }
