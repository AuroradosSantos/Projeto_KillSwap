package entidade;

import main.ComandoTeclas;
import main.TelaJogo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;


public class Jogador extends Entidade {

    TelaJogo TL;
    ComandoTeclas CT;

    public Jogador(TelaJogo TL, ComandoTeclas CT){

        this.TL = TL;
        this.CT = CT;

        valorPadrao();
        imagemJogador();
        direcao = "cima";

    }

    public void valorPadrao(){

        x = 100;
        y = 100;
        velocidade = 4;

    }
    public void imagemJogador(){

        try {

            cima1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/cima1.png")));
            cima2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/cima2.png")));
            baixo1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/baixo1.png")));
            baixo2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/baixo2.png")));
            direita1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/direita1.png")));
            direita2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/direita2.png")));
            esquerda1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/esquerda1.png")));
            esquerda2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/esquerda2.png")));

        } catch(IOException e) {

            e.printStackTrace();

        }
    }
    public boolean colisao(int proxX, int proxY){

        int col = proxX / TL.tamanhoTile;
        int fil = proxY / TL.tamanhoTile;

        int numTile = TL.configTile.mapaTileNum[col][fil];

        return TL.configTile.tile[numTile].colisao;
    }

    public void update(){

        if(CT.cimaPress || CT.baixoPress ||
                CT.esqPress || CT.dirPress){

            if (CT.cimaPress){
                direcao = "cima";
                if(!colisao(x, y - velocidade)){
                    y -= velocidade;
                }
            }

            if (CT.baixoPress){
                direcao = "baixo";
                if(!colisao(x, y + velocidade)){
                    y += velocidade;
                }
            }

            if (CT.dirPress){
                direcao = "direita";
                if(!colisao(x + velocidade, y)){
                    x += velocidade;
                }
            }

            if (CT.esqPress){
                direcao = "esquerda";
                if(!colisao(x - velocidade, y)){
                    x -= velocidade;
                }
            }

            contadorSprite++;
            if(contadorSprite > 15){
                if(numSprite == 1){
                    numSprite = 2;
                }
                else{
                    numSprite = 1;
                }
                contadorSprite = 0;
            }
        }
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch (direcao){
            case "cima":
                if (numSprite ==1){
                    image = cima1;
                }
                if (numSprite ==2){
                    image = cima2;
                }
                break;
            case "baixo":
                if (numSprite ==1){
                    image = baixo1;
                }
                if (numSprite ==2){
                    image = baixo2;
                }
                break;
            case "esquerda":
                if (numSprite ==1){
                    image = esquerda1;
                }
                if (numSprite ==2){
                    image = esquerda2;
                }
                break;
            case "direita":
                if (numSprite ==1){
                    image = direita1;
                }
                if (numSprite ==2){
                    image = direita2;
                }
                break;
        }
        g2.drawImage(image, x, y, TL.tamanhoTile, TL.tamanhoTile, null);

    }
}