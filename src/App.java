import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Fazer uma conexão HTTP e buscar os top 250 filmes
        // String url = "https://imdb-api.com/en/API/MostPopularMovies/k_2yn959kg";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-01&end_date=2022-06-03";

        var http = new ClienteHttp();
        String json = http.buscaDados(url);


        // Exibir e manipular os dados
        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Map<String, String> conteudo = listaDeConteudos.get(i);

            String urlImage =
                    // conteudo.get("image")
                    conteudo.get("url")
                            .replaceAll("(@+)(.*).jpg$", "$1.jpg");

            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = "saida/" + titulo + ".png";
            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();
        }

    }
}
