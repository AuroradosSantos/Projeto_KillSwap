package main;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

    public static void main(String[] args) {

        JFrame janela = new JFrame();

        janela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        janela.setResizable(false);
        janela.setTitle("KillSwap");

        TelaJogo telaJogo = new TelaJogo();

        janela.add(telaJogo);

        janela.pack();

        janela.setLocationRelativeTo(null);
        janela.setVisible(true);

        telaJogo.começaJogoThread();

    }
}