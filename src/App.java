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
        // String url = "https://imdb-api.com/en/API/MostPopularMovies/k_2yn959kg";
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-01&end_date=2022-06-03";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // Extrair só os dados que interessam (titulo,poster,classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeConteudos = parser.parse(body);

        // Exibir e manipular os dados
        GeradoraDeFigurinhas geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Map<String, String> conteudo = listaDeConteudos.get(i);

            String urlImage = 
            //conteudo.get("image")
            conteudo.get("url")
            .replaceAll("(@+)(.*).jpg$", "$1.jpg");

            String titulo = conteudo.get("title");
            
            InputStream inputStream = new URL(urlImage).openStream();
            String nomeArquivo = "saida/" + titulo  + ".png";
            geradora.cria(inputStream, nomeArquivo);

           // System.out.println("\u001b[3mTítulo: \u001b[m\u001b[1m" + titulo + "\u001b[m");
    //      System.out.println("\u001b[3mPoster: \u001b[m\u001b[1m" + filme.get("image") + "\u001b[m");
           //  System.out.println("\u001b[45m\u001b[3mClassificação: \u001b[m\u001b[45m\u001b[1m" + filme.get("imDbRating")
           //         + " \u001b[m");
           //  int classificacao = (int) Float.parseFloat(filme.get("imDbRating"));
           //  String stars = "";
           // for (int c = 0; c < classificacao; c++) {
        //    stars = stars + "\u2B50";
         //   }
          //  System.out.println(stars);
            System.out.println(titulo);
            System.out.println();
        }

    }
}
