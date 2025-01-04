package by.milansky.translate.google;

import com.fasterxml.jackson.databind.ObjectMapper;
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

/**
 * @author milansky
 */
public final class GoogleTranslate {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
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

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(response -> {
            try {
                val rootNode = OBJECT_MAPPER.readTree(response.body());
                val translationArray = rootNode.get(0);
                val translatedText = new StringBuilder();

                for (val node : translationArray) {
                    translatedText.append(node.get(0).asText());
                }

                return new GoogleTranslateResponse(googleRequest.text(), translatedText.toString());
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse translation response", e);
            }
        });
    }
}