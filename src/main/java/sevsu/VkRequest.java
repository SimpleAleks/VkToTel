package sevsu;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.messages.MessageNewEvent;
import api.longpoll.bots.model.objects.basic.Message;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class VkRequest extends LongPollBot {

    @Override
    public void onMessageNew(MessageNewEvent messageNewEvent) {//Метод запускается при получении сообщения ботом или при его упоминании.
        try {
            Message message = messageNewEvent.getMessage();//Создания объекта Message с содержащим полученного сообщения
            if (message.hasText()) {//Если в сообщении есть текст
                String response = "Test message with resend: " + message.getText();//Промежуточный код для проверки работы кода
                vkBotsApi.messages().send()
                        .setPeerId(message.getPeerId())
                        .setMessage(response)
                        .execute();
            }
        } catch (VkApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAccessToken() {//Получение токена доступа
        return getProperties("access_token");//Загрузка токена из конфигурационного файла
    }

    @Override
    public int getGroupId() {//Получение id группы
        return Integer.parseInt(getProperties("group_id"), 10);//Загрузка id группы из конфигурационного файла и перевод String -> Int
    }

    public String getProperties(String propertieName) { //Метод для получения настрйоки из конфиг файла
        File file = new File("src\\main\\resources\\data.properties");//Создание объекта File с путём к конфиг файлу
        Properties properties = new Properties(); //Создание объекта Properties для чтения конфиг файла
        try {
            properties.load(new FileReader(file)); //Загрузка конфиг файла
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(propertieName);
    }
}
