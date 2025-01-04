package by.milansky.translate.api;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * @author milansky
 */
public interface TranslateRequestProcessor {
    @NotNull CompletableFuture<TranslateResponse> processRequest(@NotNull TranslateRequest request);
}
