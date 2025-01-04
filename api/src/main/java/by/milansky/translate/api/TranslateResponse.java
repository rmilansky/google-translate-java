package by.milansky.translate.api;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;

/**
 * @author milansky
 */
@Value.Immutable
@Value.Style(nullableAnnotation = "NotNull")
public interface TranslateResponse {
    @NotNull String result();

    @NotNull TranslateRequest downstreamRequest();
}
