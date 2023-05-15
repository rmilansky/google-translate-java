package by.milansky.translate.google;

import lombok.NonNull;

/**
 * @author milansky
 */
public record GoogleTranslateResponse(
        @NonNull String original,
        @NonNull String translate
) {
}
