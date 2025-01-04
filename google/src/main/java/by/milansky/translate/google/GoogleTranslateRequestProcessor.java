package by.milansky.translate.google;

import by.milansky.translate.api.ImmutableTranslateResponse;
import by.milansky.translate.api.TranslateRequest;
import by.milansky.translate.api.TranslateRequestProcessor;
import by.milansky.translate.api.TranslateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NoArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

/**
 * @author milansky
 */
@NoArgsConstructor(staticName = "create")
public final class GoogleTranslateRequestProcessor implements TranslateRequestProcessor {
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final String URL_FORMAT = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=%s&tl=%s&dt=t&q=%s";

    @Override
    public @NotNull CompletableFuture<TranslateResponse> processRequest(final @NotNull TranslateRequest request) {
        val urlEncodedText = URLEncoder.encode(request.text(), StandardCharsets.UTF_8);
        val httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URL_FORMAT.formatted(request.from().identifier(), request.to().identifier(), urlEncodedText)))
                .build();

        return HTTP_CLIENT.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString()).thenApply(response -> {
            try {
                val rootNode = OBJECT_MAPPER.readTree(response.body());
                val translationArray = rootNode.get(0);
                val translatedText = new StringBuilder();

                for (val node : translationArray) {
                    translatedText.append(node.get(0).asText());
                }

                return ImmutableTranslateResponse.builder()
                        .downstreamRequest(request)
                        .result(translatedText.toString())
                        .build();
            } catch (final Throwable throwable) {
                throw new RuntimeException("Failed to parse translation response", throwable);
            }
        });
    }
}
