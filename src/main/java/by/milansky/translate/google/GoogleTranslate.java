package by.milansky.translate.google;

import com.google.gson.JsonParser;
import lombok.NonNull;
import lombok.val;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author milansky
 */
public final class GoogleTranslate {

    private static final Function<GoogleTranslateRequest, String> URL_FORMAT = request ->
            "https://translate.googleapis.com/translate_a/single?client=gtx&sl=" +
                    request.from().getCode() + "&tl=" + request.to().getCode() + "&dt=t&q=" +
                    URLEncoder.encode(request.text(), StandardCharsets.UTF_8);

    public CompletableFuture<GoogleTranslateResponse> translate(
            @NonNull GoogleTranslateRequest googleRequest
    ) {
        val client = HttpClient.newHttpClient();
        val request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL_FORMAT.apply(googleRequest)))
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    val translate = JsonParser.parseString(response.body())
                            .getAsJsonArray()
                            .get(0)
                            .getAsJsonArray()
                            .asList()
                            .stream()
                            .map(jsonElement -> jsonElement.getAsJsonArray().get(0).getAsString())
                            .collect(Collectors.joining(""));

                    return new GoogleTranslateResponse(googleRequest.text(), translate);
                });
    }

}
