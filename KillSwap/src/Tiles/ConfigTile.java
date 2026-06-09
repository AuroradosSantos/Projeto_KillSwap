package Tiles;

import main.TelaJogo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class ConfigTile {
    TelaJogo TL;
   public Tile[]  tile;
   public int mapaTileNum [][];

    public ConfigTile(TelaJogo Tl){

        this.TL = Tl;

        tile = new Tile[3];
        mapaTileNum = new int[TL.maxTelaCol][TL.maxTelaFil];

        imagemTile();
        carregaMapa();
    }

    public void imagemTile(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/sprite_objetos/arvore.png"));
            tile[0].colisao = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/sprite_objetos/chao.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/sprite_objetos/chao2.png"));

        }catch (IOException e){
            e.printStackTrace();}
    }
    public void draw(Graphics2D g2){
        int col = 0;
        int fil = 0;
        int x = 0;
        int y = 0;

        while(col < TL.maxTelaCol && fil < TL.maxTelaFil){

            int numtile = mapaTileNum[col][fil];

            g2.drawImage(tile[numtile].image, x, y, TL.tamanhoTile, TL.tamanhoTile, null);
            col++;
            x += TL.tamanhoTile;

            if(col == TL.maxTelaCol){
                col = 0;
                x = 0;
                fil++;
                y += TL.tamanhoTile;
            }
        }



    }
    public void carregaMapa() {
        try {
            InputStream is = getClass().getResourceAsStream("/mapa/mapa.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int fil = 0;

            while(col < TL.maxTelaCol && fil < TL.maxTelaFil){
                String linha = br.readLine();

                while(col < TL.maxTelaCol){
                    String numeros[] = linha.split(" ");

                    int num = Integer.parseInt(numeros[col]);
                    mapaTileNum[col] [fil] = num;
                    col++;
                }
                if (col == TL.maxTelaCol){
                    col = 0;
                    fil++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }}

