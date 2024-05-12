package pp_3_1_6.consumer;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pp_3_1_6.model.User;
import pp_3_1_6.util.KataCode;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.logging.Logger;

public class KataApiConsumer {

    private static final Logger consumerLogger = Logger.getLogger(KataApiConsumer.class.getSimpleName());

    public static void main(String[] args) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();
        URI kataURI = new URI("http://94.198.50.185:7081/api/users"); // Url api
        ResponseEntity<String> getResponse = restTemplate.getForEntity(kataURI, String.class); // Запросик чтоб сессию получить из хедера.

        String sessionId = getResponse.getHeaders().get("Set-Cookie").get(0); // Сохраню сессию из хедера.

        User JB = User // Юзверь которого вставлю
                .builder()
                .id(3L)
                .name("James")
                .lastName("Brown")
                .age((byte) 23)
                .build();

        Map<String, Object> jamesBrown = JB.getMap();


        HttpHeaders headers = new HttpHeaders(); // Создаю хедер.
        headers.add("Cookie", sessionId); // Передаю в хедер сохраненный айди сессии.

        KataCode kataCode = new KataCode();

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(jamesBrown, headers); // Формирую запрос с хедером и сущностью.
        kataCode.setHalf
                (
                        restTemplate.postForObject(kataURI, request, String.class) // Сохраняю результат запроса в первую часть "таинственного" кода.
                );

        /*
        Так как редактирую уже полученного пользователя, то нет смысла делать еще 1 запрос на его получение.
        Просто отредактирую его и передам в метод генерирующий мапу.
         */
        Map<String, Object> updatedUser = JB
                .setName("Thomas")
                .setLastName("Shelby")
                .getMap();

        ResponseEntity<String> update = restTemplate.exchange(kataURI, HttpMethod.PUT, new HttpEntity<>(updatedUser, headers), String.class);
        kataCode.setHalf(update.getBody());

        ResponseEntity<String> delete = restTemplate.exchange(kataURI.toString() + "/3", HttpMethod.DELETE, new HttpEntity<>(null, headers), String.class);
        kataCode.setHalf(delete.getBody());

        System.out.println(kataCode);




    }

}
