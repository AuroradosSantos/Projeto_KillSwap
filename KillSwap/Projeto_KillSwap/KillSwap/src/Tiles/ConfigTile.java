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
        mapaTileNum = new int[TL.ColMundoMax][TL.FilMundoMax];

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
        int mundoCol = 0;
        int mundoFil = 0;


        while(mundoCol < TL.ColMundoMax && mundoFil < TL.FilMundoMax){

            int numtile = mapaTileNum[mundoCol][mundoFil];

            int mundoX = mundoCol * TL.tamanhoTile;
            int mundoY = mundoFil * TL.tamanhoTile;
            int telaX = mundoX - TL.jogador.WorldX + TL.jogador.telaX;
            int telaY = mundoY - TL.jogador.WorldY + TL.jogador.telaY;

            g2.drawImage(tile[numtile].image, telaX, telaY, TL.tamanhoTile, TL.tamanhoTile, null);
            mundoCol++;


            if(mundoCol == TL.ColMundoMax){
                mundoCol = 0;
                mundoFil++;
            }
        }



    }
    public void carregaMapa() {
        try {
            InputStream is = getClass().getResourceAsStream("/mapa/mapa.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int fil = 0;

            while(col < TL.ColMundoMax && fil < TL.FilMundoMax){
                String linha = br.readLine();

                while(col < TL.ColMundoMax){
                    String numeros[] = linha.split(" ");

                    int num = Integer.parseInt(numeros[col]);
                    mapaTileNum[col] [fil] = num;
                    col++;
                }
                if (col == TL.ColMundoMax){
                    col = 0;
                    fil++;
                }
            }
            br.close();
        } catch (Exception e) {
        }
    }}

