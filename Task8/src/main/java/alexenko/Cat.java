package alexenko;

import alexenko.exeptions.CatNotHaveBowlException;

import java.util.Optional;

/**
 * Класс Cat является потоком.
 */
public class Cat implements Runnable {

    /**
     * Опциональное поле bowl, может быть null.
     * Миска с которой будет есть кот.
     */
    private final Optional<Bowl> bowlOptional;

    /**
     * Пустой конструктор, где bowl не передаётся.
     */
    public Cat() {
        bowlOptional = Optional.empty();
    }

    /**
     * Конструктор, инициализирующий bowl.
     *
     * @param bowl миска, с которой будет есть кот.
     */
    public Cat(Bowl bowl) {
        this.bowlOptional = bowl != null ? Optional.of(bowl) : Optional.empty();
    }

    /**
     * Кот ест.
     */
    @Override
    public void run() {
        try {
            eat();
        } catch (InterruptedException | CatNotHaveBowlException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Если миска имеется, то кот ест. Иначе выбрасывается исключение CatNotHaveBowlException.
     *
     * @throws InterruptedException    если кто-то прервёт кота во время еды.
     * @throws CatNotHaveBowlException если нет миски для еды.
     */
    public void eat() throws InterruptedException, CatNotHaveBowlException {
        if (bowlOptional.isPresent()) {
            Bowl bowl = bowlOptional.get();
            bowl.getFood();
        } else {
            throw new CatNotHaveBowlException();
        }
    }
}
