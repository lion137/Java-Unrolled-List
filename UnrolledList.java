package com.unrolledlist;



/**
 * Copyleft by lion137.logspot.ie

 */
public class UnrolledList<E> {

    private Node first;
    private Node last;
    private int length = 0;
    private int max;


    public UnrolledList (int maxElements) {//maxElements must  be greater than 1
        if (maxElements < 2){
            throw new ValueException("Unrolled List: max elements must be greater than 1");
        }
        max = maxElements;
    }
     private class Node {
        byte side;
        int numCnt = 0;
        int localInd;
        Node next;
        Node prev;
        Object [] in_array;
        public Node() {
            localInd = max - 1;
            in_array = new Object[max];
        }
    }
    public int size(){
        return length;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public void push(E elem){//adds at the beginning
        if (first == null) {
            first = new Node();
            first.side = -1;
            first.in_array[first.localInd] = elem;
            first.numCnt += 1;
            length += 1;
            last = first;
            first.next = null;
            last.prev = null;
        }
        else {
            if (first.numCnt < max) {
                if (first.side == -1) {
                    first.localInd--;
                    first.in_array[first.localInd] = elem;
                    first.numCnt += 1;
                    length += 1;
                }
                else if (first.side == 1) {
                   Node old = first;
                   first = new Node();
                   first.in_array[first.localInd] = elem;
                   first.numCnt += 1;
                   first.side = -1;
                   length += 1;
                   first.next = old;
                   //last = old;
                   last.prev = first;
                }
          }
            else  {
                Node old = first;
                first = new Node();
                first.in_array[first.localInd] = elem;
                first.numCnt += 1;
                first.side = -1;
                length += 1;
                first.next = old;
                first.next.prev = first;
            }
        }
    }
    public void add(E elem){// add an element at the end of the list
        if (last == null){
            last = new Node();
            last.side = 1;
            last.localInd = 0;
            last.in_array[last.localInd] = elem;
            last.numCnt += 1;
            length += 1;
            first = last;
            first.next = null;
            last.prev = null;
        }
        else {
            if (last.numCnt < max) {
                if (last.side == 1){
                    last.localInd++;
                    last.in_array[last.localInd] = elem;
                    length++;
                    last.numCnt++;
                }
                else {
                    Node old = last;
                    last = new Node();
                    last.localInd = 0;
                    last.in_array[last.localInd] = elem;
                    last.numCnt++;
                    length++;
                    last.side = 1;
                    last.prev = old;
                    last.prev.next = last;
                }
            }
            else {

                Node old = last;
                last = new Node();
                last.localInd = 0;
                last.in_array[0] = elem;

                last.numCnt++;
                length++;
                last.side = 1;
                last.prev = old;
                last.prev.next = last;
            }

        }
    }
    public E get(int ind){ // get, not remove item w ith index ind, returns the last if ind =  -1
        if (ind >= length || ind < -1) {throw new IndexOutOfBoundsException("" +
                "Unrolled List: Index Out of Bound");}
        if (ind == -1 ) {ind = length - 1;}
        if (ind <= length / 2) {
            int free_in_first = max - first.numCnt;
            ind = ind + free_in_first; // to start count from the beginning of the first array
            int to_go = ind / max;
            int to_go_in_array = ind % max;
            Node tmp = this.first;
            int i = 0;
            while (i < to_go) { // go through the nodes
                tmp = tmp.next;
                i++;
            }
            E temp = (E) tmp.in_array[to_go_in_array];
            return temp;
        }
        else {
            int free_in_last = max - last.numCnt;
            ind = ind + free_in_last;
            int to_go = ind / max;
            int to_go_in_array = ind % max;
            Node tmp = this.last;
            int i = 0;
            while (i < to_go) {// go through the nodes in reverse order
                tmp = tmp.prev;
                i++;
            }
            E temp = (E) tmp.in_array[to_go_in_array];
            return temp;
        }
    }
    public void replace(E elem, int ind){// replaces element with index int with elem (-1 updates the last)
        if (ind >= this.length || ind < -1) {throw new IndexOutOfBoundsException("" +
                "Unrolled List: Index Out of Bound");}
        if (ind == -1 ) {ind = this.length - 1;}
        if (ind <= length / 2) {
            int free_in_first = max - first.numCnt;
            ind = ind + free_in_first; // to start count from the beginning of the first array
            int to_go = ind / max;
            int to_go_in_array = ind % max;
            Node tmp = this.first;
            int i = 0;
            while (i < to_go) { // go through the nodes
                tmp = tmp.next;
                i++;
            }
            tmp.in_array[to_go_in_array] = elem;
        }
        else {
            int free_in_last = max - last.numCnt;
            ind = ind + free_in_last;
            int to_go = ind / max;
            int to_go_in_array = ind % max;
            Node tmp = this.last;
            int i = 0;
            while (i < to_go) {// go through the nodes in reverse order
                tmp = tmp.prev;
                i++;
            }
            tmp.in_array[to_go_in_array] = elem;
        }
    }
    public E pop() {//removes and returns the first item, throws exception if empty
        if (first == null) throw new IndexOutOfBoundsException(""+
            "Unrolled List: Pop from empty list!");
        if (first.side == -1) {
            if (first.in_array[max - 2] == null) {
                E temp = (E) first.in_array[max - 1];
                first = first.next;
                length--;
                return temp;
            }
            else {
                E temp = (E) first.in_array[first.localInd];
                length--;
                first.numCnt--;
                first.in_array[first.localInd] = null;
                first.localInd++;
                return  temp;
            }
        }
        else {
            if (length == 1) {
                E temp = (E) first.in_array[first.localInd];
                first = null;
                last = null;
                length--;
                return temp;
            }
            else {
                E temp = (E) first.in_array[first.localInd];
                int i = 1;
                while (i < first.numCnt) {
                    first.in_array[i - 1] = first.in_array[i];
                    i++;
                }
                first.in_array[i] = null;
                length--;
                first.numCnt--;
                first.localInd--;
                return temp;
            }
        }

    }
    public E popLast(){//removes and returns the last item in the list, throws exception if empty
        if (first == null) throw new IndexOutOfBoundsException(""+
                "Unrolled List: Pop from empty list!");
        if (last.side == 1) {
            if (last.localInd == 0) {
                if (length == 1) {
                    E temp = (E) last.in_array[first.localInd];
                    last = null;
                    first = null;
                    length--;
                    return temp;
                }
                E temp = (E) last.in_array[last.localInd];
                length--;
                last.prev.next = null;
                last = last.prev;
                return temp;
            }
            else {
                E temp = (E) last.in_array[last.localInd];
                last.in_array[last.localInd] = null;
                length--;
                last.localInd--;
                last.numCnt--;
                return temp;
            }
        }
        else {
            if (length == 1) {
                E temp = (E) last.in_array[last.localInd];
                last = null;
                first = null;
                length--;
                return temp;
            }
            else {
                E temp = (E) last.in_array[max - 1];
                int i = max - 1;
                while ( i > last.localInd) {
                    last.in_array[i] = last.in_array[i - 1];
                    i--;
                }
                last.in_array[i] = null;
                length--;
                last.localInd++;
                last.numCnt--;
                return temp;
            }
        }
    }
    public void printList(){
        System.out.print("[");
        int i = 0;
        while ( i < length) {
            System.out.print(this.get(i));
            if (i != length - 1)
            System.out.print(", ");
            i++;
        }
        System.out.print("]");
    }
}

