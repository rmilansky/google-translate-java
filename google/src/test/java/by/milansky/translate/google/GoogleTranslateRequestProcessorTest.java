package by.milansky.translate.google;

import by.milansky.translate.api.ImmutableTranslateRequest;
import by.milansky.translate.api.TranslateRequest;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author milansky
 */
class GoogleTranslateRequestProcessorTest {
    @Test
    void requestProcessor_shouldReturnValidWordTranslation() {
        val requestProcessor = GoogleTranslateRequestProcessor.create();

        val request = ImmutableTranslateRequest.builder()
                .text("Hello, it's test! How are you?")
                .to(GoogleTranslateLanguage.RUSSIAN)
                .from(GoogleTranslateLanguage.AUTO)
                .build();

        val text = requestProcessor.processRequest(request).join().result();
        assertEquals(text, "Привет, это тест! Как вы?");
    }
}