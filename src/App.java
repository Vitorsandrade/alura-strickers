import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP e buscar os top 250 filmes
        String url = "https://imdb-api.com/en/API/MostPopularMovies/k_2yn959kg";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair só os dados que interessam (titulo,poster,classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // Exibir e manipular os dados
        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();

        for (Map<String, String> filme : listaDeFilmes) {

            String urlImage = filme.get("image");
            InputStream inputStream = new URL(urlImage).openStream();

            String titulo = filme.get("title");
            String nomeArquivo = titulo  + ".pnj";
            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[3mTítulo: \u001b[m\u001b[1m" + titulo + "\u001b[m");
    //      System.out.println("\u001b[3mPoster: \u001b[m\u001b[1m" + filme.get("image") + "\u001b[m");
            System.out.println("\u001b[45m\u001b[3mClassificação: \u001b[m\u001b[45m\u001b[1m" + filme.get("imDbRating")
                    + " \u001b[m");
            int classificacao = (int) Float.parseFloat(filme.get("imDbRating"));
            String stars = "";
            for (int i = 0; i < classificacao; i++) {
                stars = stars + "\u2B50";
            }
            System.out.println(stars);
            System.out.println();
        }

    }
}
