package sevostyanov;

public class Stack {
    /**
     * ������ ��� �������� ��������� c����
     */
    private Object[] stackArray;
    /**
     * ������� �������� �����
     */
    private int top;
    /**
     * ������������ �������� ������� �����
     */
    private int maxSize;

    public Stack(int s) {
        maxSize = s;
        stackArray = new Object[maxSize];
        top = -1;
    }

    /**
     * ����� ������� ��������� ������� � ���� ������, ����  ���� ��������
     * ������� � ������������ ���� ���������.
     *
     * @param item ������� ������� ����� �������� � ����
     */
    public void push(Object item) {
        if (top == maxSize - 1) {
            System.out.println("���� �����. ���������� �������� �������.");
        } else {
            top++;
            stackArray[top] = item;
        }
    }

    /**
     * ����� ������� ������� ������� ������� �� �����, ���� ���� ���� � ������ �������
     * ����� � ����������� ����� ��������� � ��� ��� ���� ����
     *
     * @return ��������� �������
     */
    public Object pop() {
        if (top == -1) {
            System.out.println("���� ����. ��� ��������� ��� ��������.");
            return null;
        } else {
            Object item = stackArray[top];
            top--;
            return item;
        }
    }

    /**
     * ����� ��������� ������������� �����
     *
     * @return ���������� ��� ���� ���� �����
     */
    public boolean isEmpty() {
        return (top == -1);
    }

    /**
     * ����� ������� ���������� ������� ������� �����, ���� ���� ����
     * ������� � ������������ ����� ��������� � ��� ��� ���� ����
     *
     * @return ���������� ������� ������ �����
     */
    public Object top() {
        if (top == -1) {
            System.out.println("���� ����. ��� �������� ��������.");
            return null;
        } else {
            return stackArray[top];
        }
    }

    /**
     * � ������ ��������� ����� ������ copyarray �������� top + 1, ��� top - ��� ������ �������� �������� � �����.
     * �����, � ������� ������������ ������ arraycopy �� ������ System, ���������� ��� �������� �� stackarray (��������� ������� �����) � copyarray
     *
     * @return ����� ���������� ���������� ����� �������
     */
    public Object[] getAll() {
        Object[] copyArray = new Object[top + 1];
        System.arraycopy(stackArray, 0, copyArray, 0, top + 1);
        return copyArray;
    }
}
