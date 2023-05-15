package by.milansky.translate.google;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author milansky
 */
class GoogleTranslateTest {

    private final GoogleTranslate translate = new GoogleTranslate();

    @Test
    void translateTest() {
        val request = GoogleTranslateRequest
                .builder()
                .text("Hello, it's test! How are you?")
                .to(GoogleTranslateLanguage.RUSSIAN)
                .build();

        val text = translate.translate(request).join().translate();

        assertEquals(text, "Привет, это тест! Как вы?");
    }

}