package main;

import javax.swing.*;
import java.awt.*;

public class TelaInicial extends JFrame {

    public TelaInicial() {

        setTitle("KillSwap");
        setSize(768, 576);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel("KILLSWAP");

        titulo.setFont(new Font("Arial", Font.BOLD, 50));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        add(titulo);
    }
}