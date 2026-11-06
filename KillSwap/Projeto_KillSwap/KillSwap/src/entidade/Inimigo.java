package entidade;

import main.TelaJogo;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class Inimigo extends Entidade {

    TelaJogo TL;
    int tamanho;

    public Inimigo(TelaJogo TL) {
        this.TL = TL;
        tamanho = TL.tamanhoTile;
        WorldX = tamanho * 15;
        WorldY = tamanho * 10;
        velocidade = 1;
        direcao = "baixo";
        imagemInimigo();
    }

    public void imagemInimigo() {
        try {
            cima1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_inimigo/inimigoCima1.png")));
            cima2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_inimigo/inimigoCima2.png")));
            baixo1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_inimigo/inimigoBaixo1.png")));
            baixo2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_inimigo/inimigoBaixo2.png")));
            esquerda1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_inimigo/inimigoEsquerda1.png")));
            esquerda2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_inimigo/inimigoEsquerda2.png")));
            direita1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_inimigo/inimigoDireita1.png")));
            direita2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_inimigo/inimigoDireita2.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Jogador jogador) {
        boolean movendo = false;

        if (jogador.WorldX < WorldX && !colisao(WorldX - velocidade, WorldY)) {
            WorldX -= velocidade;
            direcao = "esquerda";
            movendo = true;
        } else if (jogador.WorldX > WorldX && !colisao(WorldY + velocidade, WorldY)) {
            WorldX += velocidade;
            direcao = "direita";
            movendo = true;
        }

        if (jogador.WorldY < WorldY && !colisao(WorldX, WorldY - velocidade)) {
            WorldY -= velocidade;
            direcao = "cima";
            movendo = true;
        } else if (jogador.WorldY > WorldY && !colisao(WorldX, WorldY + velocidade)) {
            WorldY += velocidade;
            direcao = "baixo";
            movendo = true;
        }

        if (movendo) {
            contadorSprite++;
            if (contadorSprite > 15) {
                numSprite = numSprite == 1 ? 2 : 1;
                contadorSprite = 0;
            }
        }

        if (Math.abs(jogador.WorldX - WorldX) < tamanho && Math.abs(jogador.WorldY - WorldY) < tamanho) {
            if (jogador.powerUpAtivo) {
                vida--;
            } else {
                jogador.vida--;
            }

            if ((vida <= 0 || jogador.vida <= 0) && !TL.gameOver) {
                TL.gameOver = true;
                String mensagem = jogador.vida <= 0 ? "VOCE MORREU" : "INIMIGO DERROTADO";
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showOptionDialog(
                            TL,
                            mensagem,
                            "Game Over",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new Object[]{"Fechar"},
                            "Fechar"
                    );
                    TL.fecharJogo();
                });
            }
        }
    }

    public boolean colisao(int proxX, int proxY) {
        int col = proxX / tamanho;
        int fil = proxY / tamanho;
        int colDireita = (proxX + tamanho - 1) / tamanho;
        int filBaixo = (proxY + tamanho - 1) / tamanho;

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

    public void draw(Graphics2D g2) {
        BufferedImage imagem = null;

        switch (direcao) {
            case "cima": imagem = numSprite == 1 ? cima1 : cima2; break;
            case "baixo": imagem = numSprite == 1 ? baixo1 : baixo2; break;
            case "esquerda": imagem = numSprite == 1 ? esquerda1 : esquerda2; break;
            case "direita": imagem = numSprite == 1 ? direita1 : direita2; break;
        }

        int telaX = WorldX - TL.jogador.WorldX + TL.jogador.telaX;
        int telaY = WorldY - TL.jogador.WorldY + TL.jogador.telaY;
        g2.drawImage(imagem, telaX, telaY, tamanho, tamanho, null);
    }
}
