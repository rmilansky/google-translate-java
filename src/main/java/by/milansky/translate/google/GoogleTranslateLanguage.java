package by.milansky.translate.google;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author milansky
 */
@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum GoogleTranslateLanguage {

    AUTO("auto"),
    ENGLISH("en"),
    RUSSIAN("ru"),
    FRENCH("fr"),
    CHINESE_SIMPLIFIED("zh-cn"),
    CHINESE_TRADITIONAL("zh-tw"),
    POLISH("pl"),
    BELARUSIAN("be");

    String code;

}
