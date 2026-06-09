package main;

import Tiles.ConfigTile;
import entidade.Jogador;

import javax.swing.JPanel;
import java.awt.*;

public class TelaJogo extends JPanel implements Runnable {

    final int tamanhoGrid = 16;
    final int escala = 3;

    public final int tamanhoTile = tamanhoGrid * escala;

   public final int maxTelaCol = 16;
   public final int maxTelaFil = 12;

   public final int telaComp = tamanhoTile * maxTelaCol;
   public final int telaAlt = tamanhoTile * maxTelaFil;

    int FPS = 60;

    public ConfigTile configTile = new ConfigTile(this);
    ComandoTeclas comandoteclas = new ComandoTeclas();
    Jogador jogador = new Jogador(this, comandoteclas);
    Thread threadjogo;

    public TelaJogo() {

        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(telaComp, telaAlt));
        this.addKeyListener(comandoteclas);
        this.setFocusable(true);

    }

    public void começaJogoThread(){

        threadjogo = new Thread(this);
        threadjogo.start();

    }

    public void run() {

        double intervalo = 1000000000.0 / FPS;

        double delta = 0;

        long ultimoTempo = System.nanoTime();
        long tempoAtual;

        while(threadjogo != null){

            tempoAtual = System.nanoTime();

            delta += (tempoAtual - ultimoTempo) / intervalo;

            ultimoTempo = tempoAtual;

            if (delta >= 1){

                update();
                repaint();

                delta--;
            }
        }
    }

    public void update(){

        jogador.update();

    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        configTile.draw(g2);

        jogador.draw(g2);

        g2.dispose();

    }
}