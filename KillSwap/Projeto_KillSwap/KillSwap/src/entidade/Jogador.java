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
    BufferedImage powerCima1, powerCima2, powerBaixo1, powerBaixo2;
    BufferedImage powerEsquerda1, powerEsquerda2, powerDireita1, powerDireita2;
    public boolean powerUpAtivo;
    public final int telaX;
    public final int telaY;

    public Jogador(TelaJogo TL, ComandoTeclas CT){

        this.TL = TL;
        this.CT = CT;

        telaX = TL.telaComp/2 - (TL.tamanhoTile/2);
        telaY = TL.telaAlt/2 - (TL.tamanhoTile/2);

        valorPadrao();
        imagemJogador();
        imagemPowerUp();
        direcao = "cima";

    }

    public void valorPadrao(){

        WorldX = TL.tamanhoTile * 3 + 4;
        WorldY = TL.tamanhoTile * 3 + 4;
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

    public void imagemPowerUp(){

        try {
            powerCima1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/powerCima1.png")));
            powerCima2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/powerCima2.png")));
            powerBaixo1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/powerBaixo1.png")));
            powerBaixo2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/powerBaixo2.png")));
            powerDireita1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/powerDireita1.png")));
            powerDireita2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/powerDireita2.png")));
            powerEsquerda1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/powerEsquerda1.png")));
            powerEsquerda2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_jogador/powerEsquerda2.png")));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void ativarPowerUp(){
        powerUpAtivo = true;
    }

    public boolean colisao(int proxX, int proxY){
        int col = proxX / TL.tamanhoTile;
        int fil = proxY / TL.tamanhoTile;
        int colDireita = (proxX + TL.tamanhoTile - 1) / TL.tamanhoTile;
        int filBaixo = (proxY + TL.tamanhoTile - 1) / TL.tamanhoTile;

        if (proxX < 0 || proxY < 0 || colDireita >= TL.ColMundoMax || filBaixo >= TL.FilMundoMax) {
            return true;
        }

        int numTile = TL.configTile.mapaTileNum[col][fil];
        if (TL.configTile.tile[numTile].colisao) {
            return true;
        }

        return TL.configTile.tile[TL.configTile.mapaTileNum[colDireita][fil]].colisao
                || TL.configTile.tile[TL.configTile.mapaTileNum[col][filBaixo]].colisao
                || TL.configTile.tile[TL.configTile.mapaTileNum[colDireita][filBaixo]].colisao;
    }

    public void update(){

        if(CT.cimaPress || CT.baixoPress ||
                CT.esqPress || CT.dirPress){

            if (CT.cimaPress){
                direcao = "cima";
                if(!colisao(WorldX, WorldY - velocidade)){
                    WorldY -= velocidade;
                }
            }

            if (CT.baixoPress){
                direcao = "baixo";
                if(!colisao(WorldX, WorldY + velocidade)){
                    WorldY += velocidade;
                }
            }

            if (CT.dirPress){
                direcao = "direita";
                if(!colisao(WorldX + velocidade, WorldY)){
                    WorldX += velocidade;
                }
            }

            if (CT.esqPress){
                direcao = "esquerda";
                if(!colisao(WorldX - velocidade, WorldY)){
                    WorldX -= velocidade;
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
                    image = powerUpAtivo ? powerCima1 : cima1;
                }
                if (numSprite ==2){
                    image = powerUpAtivo ? powerCima2 : cima2;
                }
                break;
            case "baixo":
                if (numSprite ==1){
                    image = powerUpAtivo ? powerBaixo1 : baixo1;
                }
                if (numSprite ==2){
                    image = powerUpAtivo ? powerBaixo2 : baixo2;
                }
                break;
            case "esquerda":
                if (numSprite ==1){
                    image = powerUpAtivo ? powerEsquerda1 : esquerda1;
                }
                if (numSprite ==2){
                    image = powerUpAtivo ? powerEsquerda2 : esquerda2;
                }
                break;
            case "direita":
                if (numSprite ==1){
                    image = powerUpAtivo ? powerDireita1 : direita1;
                }
                if (numSprite ==2){
                    image = powerUpAtivo ? powerDireita2 : direita2;
                }
                break;
        }
        g2.drawImage(image, telaX, telaY, TL.tamanhoTile, TL.tamanhoTile, null);

    }
}
