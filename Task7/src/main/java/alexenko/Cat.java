package alexenko;

/**
 * Класс Кот.
 */
public class Cat {

    /**
     * Класс enum отражающий действия кота.
     * Каждое действие кота имеет соответствующее сообщение.
     */
    public enum Action {
        SLEEP("I'm sleeping"),
        WAKE_UP("I'm wake up"),
        EAT("I'm eating"),
        RUN("I'm runnig"),
        VOICE("Meow");

        private String message;

        Action(String message) {
            this.message = message;
        }

        public String getMessage() {
            return this.message;
        }
    }

    /**
     * Действие, которое кот выполняет в текущий момент времени.
     */
    private Action action;

    /**
     * Конструктор создаёт кота, который спит.
     */
    public Cat() {
        this.action = Action.SLEEP;
    }

    /**
     * Метод реализует пробуждение кота, если он спит.
     * Если кот не спит, то метод возвращает текущее действие кота.
     *
     * @return текущее действие кота
     */
    public Action wakeUp() {
        if (this.action == Action.SLEEP) {
            return this.action = Action.WAKE_UP;
        }
        return this.action;
    }

    /**
     * Метод реализует усыпление кота, если он не спит.
     * Если кот спит, то метод возвращает текущее действие кота.
     *
     * @return текущее действие кота
     */
    public Action sleep() {
        if (this.action != Action.SLEEP) {
            return this.action = Action.SLEEP;
        }
        return this.action;
    }

    /**
     * Метод реализует голос кота, если он не спит.
     * Если кот спит, то метод возвращает текущее действие кота.
     *
     * @return текущее действие кота
     */
    public Action voice() {
        if (this.action != Action.SLEEP) {
            return this.action = Action.VOICE;
        }
        return this.action;
    }

    /**
     * Метод реализует действие покушать у кота, если он не спит.
     * Если кот спит, то метод возвращает текущее действие кота.
     *
     * @return текущее действие кота
     */
    public Action eat() {
        if (this.action != Action.SLEEP) {
            return this.action = Action.EAT;
        }
        return this.action;
    }

    /**
     * Метод реализует бег кота, если он не спит.
     * Если кот спит, то метод возвращает текущее действие кота.
     *
     * @return текущее действие кота
     */
    public Action run() {
        if (this.action != Action.SLEEP) {
            return this.action = Action.RUN;
        }
        return this.action;
    }

    /**
     * Метод возвращает то действие, которым занимается кот в настоящий момент времени.
     *
     * @return текущее действие кота
     */
    public Action getAction() {
        return this.action;
    }
}
