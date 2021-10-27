package sevsu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TelegramSend {
    public static String sendTG(String textMessage) throws IOException {
        //Формируем URL для отправки запроса в Telegram API
        URL url = new URL(String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=From VK chat: %s",
                VkRequest.getProperties("tg_token"),//Загружаем TG бот токен
                VkRequest.getProperties("tg_chat_id"),//Загружаем TG чат в который отправляется reply
                textMessage));//Текст сообщения
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");//Устанавливаем метод запроса как "GET"
        connection.setRequestProperty("Content-Type", "application/json");
        //Отправляем запрос в API и читаем ответ
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            final StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            return content.toString();
        } catch (final Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
}
