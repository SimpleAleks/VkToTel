package sevsu;

import api.longpoll.bots.BotsLongPoll;
import api.longpoll.bots.exceptions.VkApiException;

public class Main {

    public static void main(String[] args) throws VkApiException {
        new BotsLongPoll(new VkRequest()).run(); //Запуск VK бота
    }
}
