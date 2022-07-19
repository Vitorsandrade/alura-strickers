import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    

    public void cria() throws Exception {

        // Leitura da imagem
        InputStream inputStream = new FileInputStream(new File("entrada/filme-maior.jpg");
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // Cria nova imagem com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // Copiar a imagem original para nova imagem(em mémoria)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);

        // Configurar a fonte
        Font fonte = new Font(Font.SANS_SERIF, Font.BOLD, 80);
        graphics.setFont(fonte);
        graphics.setColor(Color.GREEN);

        // Escrever uma frase na nova imagem
        graphics.drawString("TOPZERA", largura/4 -20 , novaAltura - 80 );


        // Escrever a nova imagem em um arquivo
        ImageIO.write(novaImagem, "png", new File("saida/figurinha.png"));

    }

    public static void main(String[] args) throws Exception {
        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();
        geradora.cria();
    }

}