package tsimmer.Server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.LinkedList;

/**
 * класс хранящий в ссылочном приватном
 * списке информацию о последних 10 (или меньше) сообщениях
 */
public class Story {
    /**
     * список сообщений
     */
    private final LinkedList<String> story = new LinkedList<>();

    /**
     * Метод для добавления сообщения в список
     * Если сообщений больше 10, удаляем первое и добавляем новое, иначе просто добавить
     *
     * @param msg сообщение
     */

    public void addStoryMsg(String msg) {
        if (story.size() >= 10) {
            story.removeFirst();
            story.add(msg);
        } else {
            story.add(msg);
        }
    }

    /**
     * Метод для отправки последовательно каждого сообщение из списка
     * в поток вывода данному пользователю (новому подключению)
     *
     * @param writer запись в поток
     */

    public void printStory(BufferedWriter writer) {
        if (!story.isEmpty()) {
            try {
                writer.write("History messages" + "\n");
                for (String vr : story) {
                    writer.write(vr + "\n");
                }
                writer.write("/...." + "\n");
                writer.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
