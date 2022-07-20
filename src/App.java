import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar os top 250 filmes
        // String url = "https://imdb-api.com/en/API/MostPopularMovies/k_2yn959kg";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-01&end_date=2022-06-03";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        // Exibir e manipular os dados
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();
        }

    }
}
