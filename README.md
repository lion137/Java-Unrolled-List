# Java-Unrolled-List
Java implementation of Unrolled List: https://en.wikipedia.org/wiki/Unrolled_linked_list    
Usage:    
UnrolledList x = new UnrolledList(int max)// max - the size of the underlying array.    
Methods:
x.size()// return length;    
x.isEmpty() // is empty?;    
push, add - add to front/append;    
pop, popLast - pop the first/pop the last;    
get(int i) - returns (not remove) an item with the index i;    
replace(E elem, int ind) - replace an elemnt with the index i with the element elem.    
