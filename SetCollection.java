import java.util.Arrays;

/**
 * Класс SetCollection реализует множество целых чисел без повторов.
 * Элементы хранятся в отсортированном виде.
 */

public class SetCollection {

    private int[] data; // массив для хранения элементов
    private int size; // количество элементов во множестве

    /**
     * Создаёт пустое множество.
     */

    public SetCollection(){
        this.data = new int[0];
        this.size = 0;
    }

    /**
     * Создаёт множество из массива чисел.
     * Повторяющиеся числа удаляются, элементы сортируются.
     *
     * @param array входной массив
     */
    public SetCollection(int[] array){
        this.data = Arrays.stream(array).distinct().sorted().toArray();
        this.size = this.data.length;
    }

    /**
     * Копирует другое множество.
     * ВНИМАНИЕ: используется та же ссылка на массив.
     *
     * @param other другое множество
     */
    public SetCollection(SetCollection other){
        this.size = other.size;
        this.data = other.data;
    }

    /**
     * Проверяет, есть ли значение во множестве.
     *
     * @param value искомое число
     * @return true, если найдено, иначе false
     */
    public boolean contains(int value) {
        return Arrays.binarySearch(data, 0, size,value) >= 0;
    }

    /**
     * Добавляет новое число в множество.
     * Если оно уже есть — ничего не делает.
     *
     * @param value число для добавления
     */
    public void addElement(int value){
        if(contains(value)) return;
        int[] newData = new int[size + 1];
        int i = 0;
        while (i < size && data[i] < value){
            newData[i] = data[i];
            i++;
        }
        newData[i] = value;
        for(int j = i; j < size;j++){
            newData[j + 1] = data[j];
        }
        data = newData;
        size++;
    }

    /**
     * Удаляет число из множества.
     * Если его нет — ничего не делает.
     *
     * @param value число для удаления
     */
    public void removeElement(int value){
        if(!contains(value)) return;
        int[] newData = new int[size - 1];
        int i = 0, g = 0;
        while (i < size){
            if(data[i] != value){
                newData[g++] = data[i];
            }
            i++;
        }
        data = newData;
        size--;
    }

    /**
     * Возвращает наибольшее число во множестве.
     *
     * @return максимальное число
     * @throws IllegalStateException если множество пусто
     */
    public int findMax(){
        if(size == 0) throw new IllegalStateException("Set is empty");
        return data[size - 1];
    }

    /**
     * Возвращает наименьшее число во множестве.
     *
     * @return минимальное число
     * @throws IllegalStateException если множество пусто
     */
    public int findMin(){
        if(size == 0) throw new IllegalStateException("Set is empty");
        return data[0];
    }

    /**
     * Выводит все элементы множества в консоль.
     */
    public void iterate(){
        for (int i = 0; i < size;i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    /**
     * Возвращает массив, в котором хранятся элементы.
     * ВНИМАНИЕ: это тот же массив, что используется внутри.
     *
     * @return массив с элементами
     */
    public int getAddress(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Element: " + index);
        }
        return data[index];
    }

    /**
     * Объединяет текущее множество с другим.
     *
     * @param other второе множество
     * @return новое множество, содержащее все уникальные элементы
     */
    public SetCollection union(SetCollection other){
        int[] combo = Arrays.copyOf(data, size+ other.size);
        System.arraycopy(other.data,0,combo, size, other.size);
        return new SetCollection(combo);
    }

    /**
     * Проверяет, равны ли два множества.
     * Элементы сравниваются по значениям и порядку.
     *
     * @param other другое множество
     * @return true, если множества равны
     */
    public boolean eqal(SetCollection other){
        return Arrays.equals(this.data, other.data);
    }

    public static void main(String[] args){
        // Создаём два множества
        SetCollection a = new SetCollection(new int[]{1,2,3});
        SetCollection b = new SetCollection(new int[]{3,4,5});

        // Выводим элементы множества a
        System.out.print("Element a: ");
        a.iterate();

        // Объединение множеств
        SetCollection union = a.union(b);

        // Проверка наличия элементов
        assert a.contains(2); // должен быть
        assert !a.contains(5); // не должен быть

        // Проверка минимума и максимума
        assert a.findMin() == 1;
        assert b.findMax() == 5;

        // Проверка объединения
        assert union.eqal(new SetCollection(new int[]{1,2,3,4,5}));

        // Добавление и удаление элементов
        a.addElement(6);
        assert a.contains(6);
        a.removeElement(1);
        assert !a.contains(1);


        System.out.println("All test completed");
    }
}

