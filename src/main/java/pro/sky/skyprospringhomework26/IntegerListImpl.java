package pro.sky.skyprospringhomework26;

import java.util.Objects;

public class IntegerListImpl implements IntegerList {

    public static final int INITIAL_SIZE = 15;

    private Integer[] date;
    private int capacity;

    public IntegerListImpl() {
        date = new Integer[INITIAL_SIZE];
        capacity = 0;
    }

    public IntegerListImpl(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Размер списка должен быть положительныЙ!");
        }
        date = new Integer[n];
        capacity = 0;
    }

     void grow() {
        Integer[] newData = new Integer[(int)(1.5 * date.length)];
        System.arraycopy(date,0, newData,0, capacity);
        date = newData;
    }



    @Override
    public Integer add(Integer item) {
        return add(capacity, item);
    }

    @Override
    public Integer add(int index, Integer item) {
        if (capacity >= date.length) {
           grow();
        }
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в список!");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть неотрицательный!");
        }
        if (index > capacity) {
            throw new IllegalArgumentException("Индекс: " + index + ", Размер: " + capacity);
        }
        System.arraycopy(date, index, date, index + 1, capacity - index);
        date[index] = item;
        capacity++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя устанавливать null в список!");
        }
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть неотрицательный!");
        }
        if (index >= capacity) {
            throw new IllegalArgumentException("Индекс: " + index + ", Размер: " + capacity);
        }

        date[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        int indexForRemoving = indexOf(item);
        if (indexForRemoving == -1) {
            throw new IllegalArgumentException("Элемент не найден!");
        }
        return remove(indexForRemoving);
    }

    @Override
    public Integer remove(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть неотрицательный!");
        }
        if (index >= capacity) {
            throw new IllegalArgumentException("Индекс: " + index + ", Размер: " + capacity);
        }
        Integer item = date[index];
        System.arraycopy(date, index + 1, date, index, capacity - 1 - index);
        date[--capacity] = null;
        return item;
    }


    @Override
    public int indexOf(Integer item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добвавлять null в список!");
        }
        int index = -1;
        for (int i = 0; i < capacity; i++) {
            if (date[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Integer item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в список!");
        }
        int index = -1;
        for (int i = capacity - 1; i >= 0; i--) {
            if (date[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }


    @Override
    public Integer get(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("Индекс должен быть неотрицательный!");
        }
        if (index >= capacity) {
            throw new IllegalArgumentException("Индекс: " + index + ", Размер: " + capacity);
        }
        return date[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (size() != otherList.size()) {
            return false;
        }
        for (int i = 0; i < capacity; i++) {
            if (!date[i].equals(otherList.get(i)))
                return false;
        }
        return false;
    }


    @Override
    public int size() {
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            date[i] = null;
        }
        capacity = 0;
    }

    @Override
    public Integer[] toArray() {
        Integer[] result = new Integer[capacity];
        System.arraycopy(date, 0, result, 0, capacity);
        return result;
    }

    @Override
    public boolean contains(Integer item) {
        if (Objects.isNull(item)) {
            throw new IllegalArgumentException("Нельзя добавлять null в список!");
        }
        Integer[] arrayForSearch = toArray();
        mergeSort(arrayForSearch);
        int min = 0;
        int max = arrayForSearch.length - 1;

        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(arrayForSearch[mid])) {
                return true;
            }
            if (item < arrayForSearch[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
    public static void mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    public static void merge(Integer[] arr, Integer[] left, Integer[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }

}

