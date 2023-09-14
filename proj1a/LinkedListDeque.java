public class LinkedListDeque<T> {
    private class GenericNode {
        public T item;
        public GenericNode prev;
        public GenericNode next;

        public GenericNode(GenericNode p, T item, GenericNode n) {
            this.prev = p;
            this.item = item;
            this.next = n;
        }
    }

    private int size;
    private GenericNode sentinel;

    public LinkedListDeque() {
        this.size = 0;
        sentinel = new GenericNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void addFirst(T item) {
        GenericNode first = new GenericNode(null, item, null);
        first.next = sentinel.next;
        first.prev = sentinel;
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

    public void addLast(T item) {
        GenericNode last = new GenericNode(null, item, null);
        last.prev = sentinel.prev;
        last.next = sentinel;
        sentinel.prev.next = last;
        sentinel.prev = last;
        size +=1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (isEmpty()) return;
        GenericNode p = sentinel;
        while (p.next != sentinel){
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        GenericNode first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size -= 1;
        return first.item;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        GenericNode last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        size -= 1;
        return last.item;
    }

    public T get(int index) {
        if (isEmpty() || index >= size) return null;
        GenericNode p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    public T getRecursive(int index) {
        if (isEmpty() || index >= size) return null;
        GenericNode p = sentinel.next;
        return getHelper(index, p);
    }

    private T getHelper(int index, GenericNode p) {
        if (index == 0) return p.item;
        index--;
        return getHelper(index, p.next);
    }
}
