# Java-Unrolled-List
Java implementation of Unrolled List: https://en.wikipedia.org/wiki/Unrolled_linked_list    
More: https://lion137.blogspot.co.uk/2017/07/unrolled-list-in-java.html      


Usage:    
UnrolledList x = new UnrolledList(int max)// max - the size of the underlying array.   


Methods:    
x.size()// return length;    
x.isEmpty() // is empty?;    
push(E item), add(E item) - add an item to front/append;    
pop, popLast - pop the first/pop the last;    
get(int ind) - returns (not remove) an item with the index ind;    
replace(E elem, int ind) - replace an element with the index ind with the element elem.    
