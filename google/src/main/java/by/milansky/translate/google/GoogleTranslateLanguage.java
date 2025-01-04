package by.milansky.translate.google;

import by.milansky.translate.api.TranslateLanguage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author milansky
 */
@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum GoogleTranslateLanguage implements TranslateLanguage {
    AUTO("auto"),
    ENGLISH("en"),
    RUSSIAN("ru"),
    FRENCH("fr"),
    CHINESE_SIMPLIFIED("zh-cn"),
    CHINESE_TRADITIONAL("zh-tw"),
    POLISH("pl"),
    BELARUSIAN("be");

    String identifier;
}
