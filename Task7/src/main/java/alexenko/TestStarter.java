package alexenko;

import alexenko.annotations.AfterAllMethods;
import alexenko.annotations.BeforeAllMethods;
import alexenko.annotations.TestMethod;
import alexenko.exceptions.ObjectTypeClassIsNullValue;
import alexenko.exceptions.ClassTestNotHaveAnnotationsTestMethodException;
import alexenko.exceptions.IllegalArgumentInAnnotationTestMethodException;
import alexenko.exceptions.IllegalCountAnnotationsAfterAllMethodsException;
import alexenko.exceptions.IllegalCountAnnotationsBeforeAllMethodsException;
import alexenko.exceptions.IllegalCountAnnotationsOnMethod;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Optional;


/**
 * Класс стартер запускает переданный ему класс тест.
 */
public class TestStarter {

    /**
     * Минимальное значение номера в очереди на запуск.
     */
    private static final int MIN_ORDER_TEST = 1;

    /**
     * Максимальное количество аннотаций @BeforeAllMethods, @TestMethod, @AfterAllMethods на методе.
     */
    private static final int MAX_COUNT_ANNOTATIONS_ON_METHOD = 1;

    /**
     * В начале, по имени класса создаётся экземпляр класса.
     * Получается массива всех методов в классе.
     * Создаются ссылки на методы, которые помечены аннотациями @BeforeAllMethods, @TestMethod, @AfterAllMethods.
     * Начинается итерация по списку методов.
     * Получается список аннотаций у текущего метода.
     * Создаётся счётчик аннотаций @BeforeAllMethods, @TestMethod, @AfterAllMethods на методе.
     * Контракт, что метод может иметь только одну из этих трёх аннотаций.
     * Определяется тип аннотации.
     * Контракт, что в классе тесте может быть только одна аннотация @BeforeAllMethods и одна аннотация @AfterAllMethods.
     * Контракт, что аннотация @TestMethod не принимает значения меньше 1.
     * Контракт, что в классе тесте должен быть хотя бы один метод с аннотацией @TestMethod.
     * После итерации по списку методов и проверки выполнения контрактов выполняется
     * сортировка методов с аннотацией @TestMethod в порядке увеличения указанного значения очереди.
     * Если имеется метод с аннотацией @BeforeAllMethods то, он запускается первым.
     * Запускаются методы с аннотацией @TestMethod.
     * Если имеется метод с аннотацией @AfterAllMethods то, он запускается после всех методов.
     *
     * @param anyClass имя класса теста, который надо запустить
     * @return созданный экземпляр объекта класса теста
     */
    public static Object start(Class<?> anyClass) {
        try {
            if (anyClass == null) {
                throw new ObjectTypeClassIsNullValue();
            }
            Object objectAnyClass = anyClass.getConstructor().newInstance();
            Method[] methods = anyClass.getMethods();
            Optional<Method> beforeAllMethodsOptional = Optional.empty();
            ArrayList<Method> listTestMethods = new ArrayList<>();
            Optional<Method> afterAllMethodsOptional = Optional.empty();
            for (var method : methods) {
                Annotation[] annotations = method.getAnnotations();
                int countAnnotationsOnMethod = 0;
                for (var annotation : annotations) {
                    if (countAnnotationsOnMethod > MAX_COUNT_ANNOTATIONS_ON_METHOD) {
                        throw new IllegalCountAnnotationsOnMethod();
                    }
                    if (annotation instanceof BeforeAllMethods) {
                        countAnnotationsOnMethod++;
                        if (beforeAllMethodsOptional.isEmpty()) {
                            beforeAllMethodsOptional = Optional.of(method);
                        } else {
                            throw new IllegalCountAnnotationsBeforeAllMethodsException();
                        }
                    }
                    if (annotation instanceof TestMethod) {
                        countAnnotationsOnMethod++;
                        var testMethod = (TestMethod) annotation;
                        if (testMethod.value() < MIN_ORDER_TEST) {
                            throw new IllegalArgumentInAnnotationTestMethodException();
                        }
                        listTestMethods.add(method);
                    }
                    if (annotation instanceof AfterAllMethods) {
                        countAnnotationsOnMethod++;
                        if (afterAllMethodsOptional.isEmpty()) {
                            afterAllMethodsOptional = Optional.of(method);
                        } else {
                            throw new IllegalCountAnnotationsAfterAllMethodsException();
                        }
                    }
                }
            }
            if (listTestMethods.isEmpty()) {
                throw new ClassTestNotHaveAnnotationsTestMethodException();
            }
            listTestMethods.sort((method1, method2) -> {
                int order1 = method1.getAnnotation(TestMethod.class).value();
                int order2 = method2.getAnnotation(TestMethod.class).value();
                return Integer.compare(order1, order2);
            });
            if (beforeAllMethodsOptional.isPresent()) {
                Method beforeAllMethods = beforeAllMethodsOptional.get();
                beforeAllMethods.invoke(objectAnyClass);
            }
            for (var method : listTestMethods) {
                method.invoke(objectAnyClass);
            }
            if (afterAllMethodsOptional.isPresent()) {
                Method method = afterAllMethodsOptional.get();
                method.invoke(objectAnyClass);
            }
            return objectAnyClass;
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}