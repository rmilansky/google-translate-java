package by.milansky.translate.google;

import lombok.Builder;
import lombok.NonNull;

/**
 * @author milansky
 */
@Builder
public record GoogleTranslateRequest(
        @NonNull String text,
        GoogleTranslateLanguage from,
        @NonNull GoogleTranslateLanguage to
) {
    public GoogleTranslateRequest {
        if (from == null) from = GoogleTranslateLanguage.AUTO;
    }
}
