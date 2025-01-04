package by.milansky.translate.api;

import org.jetbrains.annotations.NotNull;

/**
 * @author milansky
 */
public interface TranslateLanguage {
    @NotNull String identifier();

    static @NotNull TranslateLanguage of(final @NotNull String identifier) {
        return new TranslateLanguage() {
            @Override
            public @NotNull String identifier() {
                return identifier;
            }
        };
    }
}
