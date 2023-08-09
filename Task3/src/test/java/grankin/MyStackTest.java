package grankin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MyStackTest {

    @Test
    @DisplayName("Поместить три элемента")
    public void testPushThreeElements() {

        MyStack stack1 = new MyStack(3);
        stack1.push(0);
        stack1.push(1);
        stack1.push(2);
        Object[] integers = new Object[3];
        integers[0] = 0;
        integers[1] = 1;
        integers[2] = 2;
        Assertions.assertArrayEquals(integers, stack1.getArray());
    }

    @Test
    @DisplayName("Ошибка обоавления элемента")
    public void testCantPushElement() {

        MyStack stack1 = new MyStack(3);
        stack1.push(0);
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);     // Элемент, который не должен добавится!
        Object[] integers = new Object[3];
        integers[0] = 0;
        integers[1] = 1;
        integers[2] = 2;
        Assertions.assertArrayEquals(integers, stack1.getArray());
    }

    @Test
    @DisplayName("Забрать верхний элемент")
    public void testPopElement() {
        MyStack stack1 = new MyStack(3);
        stack1.push(0);
        stack1.push(1);
        Assertions.assertEquals(1, stack1.pop());
    }

    @Test
    @DisplayName("Ошибка заброра верхнего элемента")
    public void testCantPopElement() {
        MyStack stack1 = new MyStack(3);
        Assertions.assertEquals(null, stack1.pop());
    }

    @Test
    @DisplayName("Получить верхний элемент")
    public void testTopElement() {
        MyStack stack1 = new MyStack(3);
        stack1.push(0);
        stack1.push(1);
        Assertions.assertEquals(1, stack1.top());
    }

    @Test
    @DisplayName("Ошибка получения верхнего элемента")
    public void testCantTopElement() {
        MyStack stack1 = new MyStack(3);
        Assertions.assertEquals(null, stack1.top());
    }

    @Test
    @DisplayName("Стек пустой")
    public void testIsEmpty() {
        MyStack stack1 = new MyStack(3);
        Assertions.assertEquals(true, stack1.isEmpty());
    }

    @Test
    @DisplayName("Стек не пустой")
    public void testIsNotEmpty() {
        MyStack stack1 = new MyStack(3);
        stack1.push(0);
        Assertions.assertEquals(false, stack1.isEmpty());
    }

    @Test
    @DisplayName("Стек полный")
    public void testIsFull() {
        MyStack stack1 = new MyStack(1);
        stack1.push(0);
        Assertions.assertEquals(true, stack1.isFull());
    }

    @Test
    @DisplayName("Стек не полный")
    public void testIsNotFull() {
        MyStack stack1 = new MyStack(3);
        stack1.push(0);
        Assertions.assertEquals(false, stack1.isFull());
    }
}
