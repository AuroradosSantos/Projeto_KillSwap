package entidade;

import main.TelaJogo;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

public class PowerUp extends Entidade {

    TelaJogo TL;
    boolean coletado;

    public PowerUp(TelaJogo TL, int coluna, int linha) {
        this.TL = TL;
        WorldX = coluna * TL.tamanhoTile;
        WorldY = linha * TL.tamanhoTile;

        try {
            baixo1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/sprite_objetos/powerup.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(Jogador jogador) {
        int tamanho = TL.tamanhoTile;

        if (!coletado
                && jogador.WorldX < WorldX + tamanho && jogador.WorldX + tamanho > WorldX
                && jogador.WorldY < WorldY + tamanho && jogador.WorldY + tamanho > WorldY) {
            coletado = true;
            jogador.ativarPowerUp();
        }
    }

    public void draw(Graphics2D g2) {
        if (!coletado) {
            int telaX = WorldX - TL.jogador.WorldX + TL.jogador.telaX;
            int telaY = WorldY - TL.jogador.WorldY + TL.jogador.telaY;
            g2.drawImage(baixo1, telaX, telaY, TL.tamanhoTile, TL.tamanhoTile, null);
        }
    }
}
