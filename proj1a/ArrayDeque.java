public class ArrayDeque<T> {
    private T[] arraylist;
    private int size;
    private int nextFirst;
    private int nextLast;
    private float usage;

    public ArrayDeque() {
        arraylist = (T[]) new Object[8];
        size = 0;
        nextFirst = 1;
        nextLast = nextFirst + size + 1;
        usage = 0;
    }

    public void addFirst(T item) {
        arraylist[nextFirst] = item;
        nextFirst = minusOne(nextFirst, arraylist.length);
        size++;
        usage = (float)size / (float)arraylist.length;
        if (usage >= 0.75) enlargeArr();
    }

    public void addLast(T item) {
        arraylist[nextLast] = item;
        nextLast = plusOne(nextLast, arraylist.length);
        size++;
        usage = (float)size / (float)arraylist.length;
        if (usage >= 0.75) enlargeArr();
    }

    private void enlargeArr() {
        T[] newArr = (T[]) new Object[arraylist.length*2];
        if (nextFirst < nextLast || nextLast == 0 || nextFirst == arraylist.length-1) {
            int srcPos = plusOne(nextFirst, arraylist.length);
            System.arraycopy(arraylist, srcPos, newArr, 1, size);
        } else {
            int srcFirstHalfStart = plusOne(nextFirst, arraylist.length);
            int srcFitsHalfLength = arraylist.length - srcFirstHalfStart;
            System.arraycopy(arraylist, srcFirstHalfStart, newArr, 1, srcFitsHalfLength);

            int newArrSecondHalfStart = 1 + srcFitsHalfLength;
            int srcSecondHalfStart = 0;
            int srcSecondHalfLength = size - srcFitsHalfLength;
            System.arraycopy(arraylist, srcSecondHalfStart, newArr, newArrSecondHalfStart ,srcSecondHalfLength);
        }
        arraylist = newArr;
        usage = (float)size / (float)arraylist.length;
        nextFirst = 0;
        nextLast = nextFirst + size + 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void shrinkArr() {
        if (arraylist.length<= 16) return;
        T[] newArr = (T[]) new Object[arraylist.length/2];
        if (nextFirst < nextLast || nextLast == 0 || nextFirst == arraylist.length-1) {
            int srcPos = plusOne(nextFirst, arraylist.length);
            System.arraycopy(arraylist, srcPos, newArr, 1, size);
        } else {
            int srcFirstHalfStart = plusOne(nextFirst, arraylist.length);
            int srcFitsHalfLength = arraylist.length - srcFirstHalfStart;
            System.arraycopy(arraylist, srcFirstHalfStart, newArr, 1, srcFitsHalfLength);

            int newArrSecondHalfStart = 1 + srcFitsHalfLength;
            int srcSecondHalfStart = 0;
            int srcSecondHalfLength = size - srcFitsHalfLength;
            System.arraycopy(arraylist, srcSecondHalfStart, newArr, newArrSecondHalfStart, srcSecondHalfLength);
        }
        arraylist = newArr;
        usage = size / arraylist.length;
        nextFirst = 0;
        nextLast = nextFirst + size + 1;
    }

    private int plusOne(int num, int len) {
        if (num != len-1) return num+1;
        return 0;
    }

    private int minusOne(int num, int len) {
        if (num == 0) return len-1;
        return num-1;
    }

    public T removeFirst() {
        if (size == 0) return null;
        nextFirst = plusOne(nextFirst, arraylist.length);
        T res = arraylist[nextFirst];
        arraylist[nextFirst] = null;
        size--;
        usage = (float) size / (float) arraylist.length;
        if(usage <= 0.25) shrinkArr();
        return res;
    }

    public T removeLast() {
        if (size == 0) return null;
        nextLast = minusOne(nextLast, arraylist.length);
        T res = arraylist[nextLast];
        arraylist[nextLast] = null;
        size--;
        usage = (float)size / (float)arraylist.length;
        if (usage <= 0.25) shrinkArr();
        return res;
    }

    public T get(int index) {
        if (index >= size) return null;
        int dequeIdx = nextFirst + 1 + index;
        if (dequeIdx >= arraylist.length) return arraylist[dequeIdx - arraylist.length];
        return arraylist[dequeIdx];
    }

//    public void printDeque() {
//
//    }

    public void printDeque() {
        for (T p : arraylist) {
            System.out.print(p + " ");
        }
        System.out.println();
    }
//
    public static void main(String[] args) {
        ArrayDeque<Integer> testAList = new ArrayDeque<>();
        testAList.addLast(0);
        testAList.addLast(1);
        testAList.addLast(2);
        testAList.addLast(3);
        testAList.addLast(4);
        testAList.addLast(5);
        testAList.addLast(6);
        testAList.addLast(7);
        testAList.addLast(8);
        testAList.addLast(9);
        testAList.addLast(10);
        testAList.addLast(11);
        testAList.addLast(12);
        testAList.addLast(13);
        testAList.addLast(14);
        testAList.addLast(15);
        testAList.addLast(16);
        testAList.addLast(17);
        testAList.printArray();
        testAList.removeLast();
        testAList.removeLast();
        testAList.removeLast();
        testAList.removeLast();
        testAList.removeLast();
        testAList.removeFirst();
        testAList.removeFirst();
        testAList.removeFirst();
        testAList.removeFirst();
        testAList.removeFirst();
        testAList.removeFirst();
        testAList.removeFirst();
        testAList.removeFirst();
        testAList.addFirst(0);
        testAList.addFirst(1);
        testAList.addFirst(2);
        testAList.addFirst(0);
        testAList.addFirst(3);
        System.out.println("------------");
        System.out.println(testAList.size());

        testAList.printArray();
        System.out.println(testAList.get(0));
        System.out.println(testAList.get(1));
        System.out.println(testAList.get(2));
        System.out.println(testAList.get(3));
        System.out.println(testAList.get(4));
        System.out.println(testAList.get(5));
        System.out.println(testAList.get(6));
        System.out.println(testAList.get(7));
    }
}
