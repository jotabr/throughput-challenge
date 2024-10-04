
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KafkaConsumerService {

    private final RestTemplate restTemplate;

    public KafkaConsumerService() {
        this.restTemplate = new RestTemplate();
    }

    @KafkaListener(topics = "my-topic", groupId = "my-group")
    public void consumeMessage(String message) {
        String response = processMessageSync(message);
        
        System.out.println("Resposta da API: " + response);
    }

    private String processMessageSync(String message) {
        return restTemplate.postForObject("https://api.remota.com/process", message, String.class);
    }
}
