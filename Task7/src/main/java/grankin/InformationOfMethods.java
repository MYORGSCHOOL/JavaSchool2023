package grankin;

import java.util.ArrayList;
import java.util.List;

/**
 * Для хранения информации аннотаций внутри класса
 */
public class InformationOfMethods {

    /**
     * Количество аннотаций BeforeAllMethod
     */
    private int countBeforeAllMethod = 0;

    /**
     * Индекс аннотации BeforeAllMethod
     */
    private int indexBeforeAllMethod = 0;

    /**
     * Количество аннотаций AfterAllMethod
     */
    private int countAfterAllMethod = 0;

    /**
     * Индекс аннотации AfterAllMethod
     */
    private int indexAfterAllMethod = 0;

    /**
     * Приоритеты методов
     */
    private List<Integer> prioritetiTestMethod = new ArrayList<>();

    /**
     * Индексы(порядок) методов
     */
    private List<Integer> indexOfMethods = new ArrayList<>();

    /**
     * Конструктор по-умолчанию
     */
    public InformationOfMethods() {
    }

    /**
     * Добавить приоритет
     * @param prioritet     - приоритет
     */
    public void addPrioritet(int prioritet) {
        this.prioritetiTestMethod.add(prioritet);
    }

    /**
     * Добавить индекс тестируемого метода
     * @param index     - индекс
     */
    public void addIndexTestMethod(int index) {
        this.indexOfMethods.add(index);
    }

    /**
     * Прибавить 1 к количеству аннотаций BeforeAllMethod
     * @param index     - индекс аннотации
     */
    public void addOneToCountBeforeAllMethod(int index) {
        this.indexBeforeAllMethod = index;
        this.countBeforeAllMethod++;
    }

    /**
     * Прибавить 1 к количеству аннотаций AfterAllMethod
     * @param index     - индекс аннотации
     */
    public void addOneToCountAfterAllMethod(int index) {
        this.indexAfterAllMethod = index;
        this.countAfterAllMethod++;
    }

    /**
     * Получить количество аннотаций BeforeAllMethod
     * @return  - количество BeforeAllMethod
     */
    public int getCountBeforeAllMethod() {
        return countBeforeAllMethod;
    }

    /**
     * Получить количество аннотаций AfterAllMethod
     * @return  - количество AfterAllMethod
     */
    public int getCountAfterAllMethod() {
        return countAfterAllMethod;
    }

    /**
     * Получить список приоритетов
     * @return      - список приоритетов
     */
    public List<Integer> getPrioritetiTestMethod() {
        return prioritetiTestMethod;
    }

    /**
     * Получить список индексов
     * @return  - список индексов
     */
    public List<Integer> getIndexOfMethods() {
        return indexOfMethods;
    }

    /**
     * Получить номер метода с аннотацией BeforeAllMethod
     * @return      - индекс BeforeAllMethod
     */
    public int getIndexBeforeAllMethod() {
        return indexBeforeAllMethod;
    }

    /**
     * Получить номер метода с аннотацией AfterAllMethod
     * @return      - индекс AfterAllMethod
     */
    public int getIndexAfterAllMethod() {
        return indexAfterAllMethod;
    }
}
