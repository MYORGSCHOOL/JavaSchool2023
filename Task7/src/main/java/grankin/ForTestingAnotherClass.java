package grankin;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * Проверка и запуск тестирования
 */
public class ForTestingAnotherClass {

    /**
     * Работа тестирования
     * @param nameTestingClass      - полное имя класса
     * @return      - Возвращаемое количество исполняемых методов
     */
    public static int start(String nameTestingClass) {                          // Передаем имя класса, который хотим протестировать
        Class class1 = null;
        int result = 0;                                                         // Возвращаемое количество исполняемых методов
        if (nameTestingClass != null) {
            try {
                class1 = Class.forName(nameTestingClass);                           // Создаем класс
                Object object = class1.newInstance();                               // Создаем объект по имени
                InformationOfMethods information = new InformationOfMethods();      // Создаем класс для хранения информации о методах
                Method[] methods = class1.getMethods();                             // Получаем все методы из класса
                for (int i = 0; i < methods.length; i++) {                          // Перебираем все методы
                    readAnnotations(methods[i], information, i);                    // Читаем аннотации над всеми методами
                }

                if (information.getCountAfterAllMethod() > 1 || information.getCountBeforeAllMethod() > 1) {
                    throw new IllegalCountBeforeOrAfterAnnotationsException("Неверное количество методов с аннотациями Before и/или After");
                }

                sortedArrayWithMethodsForPriority(information.getIndexOfMethods(),
                        information.getPrioritetiTestMethod());                     // Сортируем массив методо по приоритету из аннотации @TestMethod

                List<Integer> indexesOfMethods = information.getIndexOfMethods();   // Получаем отсортированный по приоритету массив
                // индексов методотов с аннотацией @TestMethod

                try {                                                               // Вызываем методы по порядку у класса
                    methods[information.getIndexBeforeAllMethod()].invoke(object);  // Метод "до"

                    for (int i = 0; i < indexesOfMethods.size(); i++) {
                        methods[indexesOfMethods.get(i)].invoke(object);            // Основные методы с тестами
                    }

                    methods[information.getIndexAfterAllMethod()].invoke(object);   // Метод "после"

                    result = information.getCountAfterAllMethod() +
                            information.getCountBeforeAllMethod() +
                            information.getIndexOfMethods().size();                 // Количество всех исполняемых методов
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                throw new RuntimeException("Не удалось создать и обработать объект класса " + nameTestingClass, e);
            } catch (IllegalCountBeforeOrAfterAnnotationsException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    /**
     * Чтение аннотаций у класса
     * @param annotatedElement  - аннатируемый элемент
     * @param information       - информация об аннотациях метода
     * @param indexOfMethod     - номер метода
     */
    protected static void readAnnotations(AnnotatedElement annotatedElement,
                                                          InformationOfMethods information,
                                                          int indexOfMethod) {
        Annotation[] annotations = annotatedElement.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof TestMethod) {
                TestMethod testMethod = (TestMethod) annotations[i];
                information.addPrioritet(testMethod.priority());
                information.addIndexTestMethod(indexOfMethod);
            }
            if (annotations[i] instanceof BeforeAllMethod) {
                information.addOneToCountBeforeAllMethod(indexOfMethod);
            }
            if (annotations[i] instanceof AfterAllMethod) {
                information.addOneToCountAfterAllMethod(indexOfMethod);
            }
        }
    }

    /**
     * Сортировка списка индексов с номерами запуска методов в порядке приоритета
     * @param indexesOfMethods      - Номера методов
     * @param arrayPrioriteti       - Приоритеты методов
     */
    public static void sortedArrayWithMethodsForPriority(List<Integer> indexesOfMethods, List<Integer> arrayPrioriteti) {
        // Сортровка сравнением
        for (int j = 1; j < arrayPrioriteti.size(); j++) {
            int key = arrayPrioriteti.get(j);
            int key2 = indexesOfMethods.get(j);
            int i = j - 1;
            while (i >= 0 && arrayPrioriteti.get(i) < key) {
                indexesOfMethods.set(i + 1, indexesOfMethods.get(i));
                arrayPrioriteti.set(i + 1, arrayPrioriteti.get(i));
                i = i - 1;
            }
            arrayPrioriteti.set(i + 1, key);
            indexesOfMethods.set(i + 1, key2);
        }
    }
}
