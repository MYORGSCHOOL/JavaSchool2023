package alexenko;

/**
 * Миска, в которой хранится еда.
 */
public class Bowl {

    /**
     * Время, затрачиваемое на то, чтобы съесть одну штуку еды.
     */
    private static final long COUNT_TIME_FOR_EAT_ONE_AMOUNT_FOOD = 5000L;

    /**
     * Количество еды в миске
     */
    private int foodCount;

    /**
     * Конструктор, инициализирующий миску едой.
     *
     * @param foodCount количество еды
     */
    public Bowl(int foodCount) {
        if (foodCount < 0) {
            throw new IllegalArgumentException("Bowl can't have negative food count.");
        }
        this.foodCount = foodCount;
    }

    /**
     * Если миска пуста, то кот ждёт, пока она пополниться.
     * Каждый отрезок времени съедает по одному количеству еду, пока в миске есть еда.
     *
     * @throws InterruptedException если кто-то прервёт кота во время поедания или ожидания еды.
     */
    public synchronized void getFood() throws InterruptedException {
        if (foodCount < 1) {
            wait();
        }
        while (foodCount > 1) {
            Thread.sleep(COUNT_TIME_FOR_EAT_ONE_AMOUNT_FOOD);
            foodCount--;
        }
    }
}
