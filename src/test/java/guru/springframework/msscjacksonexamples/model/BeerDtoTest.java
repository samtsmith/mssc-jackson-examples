package guru.springframework.msscjacksonexamples.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@ActiveProfiles("kebab")
@JsonTest
class BeerDtoTest {

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testSerialization() throws JsonProcessingException {
        BeerDto beerDto = BeerDto.builder()
                .beerName("Blonde Fatale")
                .beerStyle("Ale")
                .price(new BigDecimal("12.99"))
                .id(UUID.randomUUID())
                .upc(123123131L)
                .createdDate(OffsetDateTime.now())
                .lastUpdatedDate(OffsetDateTime.now())
                .build();
        String json = objectMapper.writeValueAsString(beerDto);
        System.err.println(json);
    }

    @Test
    void testDeserialization() throws IOException {
        String json = "{\"id\":\"2210e718-dd71-4fb4-9be5-b8c0ca67c43e\",\"beer-name\":\"Blonde Fatale\",\"beer-style\":\"Ale\",\"upc\":123123131,\"price\":12.99,\"created-date\":\"2022-02-07T12:13:56.4372724-06:00\",\"last-updated-date\":\"2022-02-07T12:13:56.4392672-06:00\"}";
        BeerDto beerDto = objectMapper.readValue(json, BeerDto.class);
        System.err.println(beerDto);
    }
}