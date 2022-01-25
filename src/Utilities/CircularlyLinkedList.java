package Utilities;

/**
 * @author modified from
 * https://www.javatpoint.com/java-program-to-create-and-display-a-circular-linked-list
 * <p>Michael Kramer, author of below distance() function</p>
 * <p>
 * CS622 Spring 1, 2022 Advanced Programming Techniques
 * <p>
 * The purpose of this class is:
 * <p>Use Case: 1. Put all letters of musical alphabet into a Circularly Linked List
 *  </p>
 *  <p>Use Case: 2. Use list to determine pure INTERVAL NUMBER between any two LETTER NAMES
 *  i.e. any version of a note with prefix "c" ("c#", "c-" etc.) as bottom and
 *  any version of note with prefic "e" as top ("e-", "e#") are a 3rd apart
 */
public class CircularlyLinkedList {

    /**
     * The purpose of this embedded class is to create the Node data structure
     * for each element in the CircularlyLinkedList class
     */
    public class Node{
        Object data;
        Node next;
        public Node(Object E) {
            this.data = E;
        }
    }

    //Declaring head and tail pointer as null.
    public Node head = null;
    public Node tail = null;

    //This function will add the new node at the end of the list.

    /**
     * The purpose of this method is to add a Node element to the end of the
     * CircularlyLinkedList
     * <p>Precondition: A CircularlyLinkedList has been instantiated</p>
     * <p>Postcondition: A new Node with the Object pass as a parameter is
     * at the end of the CircularlyLinkedList</p>
     *
     * @param data in the context of HarmonyMuse is a String of each letter in
     *             the musical alphabet (range "a to g")
     */
    public void add(Object data){
        //Create new node
        Node newNode = new Node(data);
        //Checks if the list is empty.
        if(head == null) {
            //If list is empty, both head and tail would point to new node.
            head = newNode;
            tail = newNode;
            newNode.next = head;
        }
        else {
            //tail will point to new node.
            tail.next = newNode;
            //New node will become new tail.
            tail = newNode;
            //Since, it is circular linked list tail will point to head.
            tail.next = head;
        }
    }

    //Displays all the nodes in the list

    /**
     * The purpose of this method is to print out the current contents of this
     * CircularlyLinkedList
     * <p>Precondition: a CircularlyLinkedList is instantiated</p>
     * <p>Postcondition: the contents of the list are on the console </p>
     */
    public void display() {
        Node current = head;
        if (head == null) {
            System.out.println("List is empty");
        } else {
            System.out.println("Nodes of the circular linked list: ");
            do {
                //Prints each node by incrementing pointer.
                System.out.print(" " + current.data);
                current = current.next;
            } while (current != head);
            System.out.println();
        }
    }


    /**
     * The purpose of this method is to get the number of elements in the
     * CircularlyLinkedList. Code modified from:
     * https://stackoverflow.com/questions/30970211/size-for-doubly-circular-linked-list
     * <p>Precondition: A CircularlyLinkedList has been instantiated</p>
     * <p>Postcondition: The number of elements in the list is returned as
     * an int</p>
     *
     * @return the number of elements in the list as an int
     */
    public int getSize() {
        int count = 0;
        if (head == null)
            return count;
        else {
            Node temp = head;
            do {
                temp = temp.next;
                count++;
            } while (temp != head);
        }
        return count;
    }


    /**
     * The purpose of this method is to get the integer representation of distance
     * in circularly linked list (i.e. "a, b, c, d, e, f, g" distance of
     * c = bottom and g = top is 5 distance of g = bottom and b = top is 3)
     * <p>Precondition: A CircularlyLinkedList has been instantiated and the
     * musical alphabet ("a" to "g") has been added to the list as String objects</p>
     * <p>Postcondition: an integer representing the distance from a bottom
     * letter to the top letter (counting the bottom letter as 1) </p>
     *
     * @param bottom a String Object letter representing a Note to begin searching
     *               from and counting up from 1
     *
     * @param top a String Object letter representing a target Note (the count
     *            will never exceed 7)
     * @return an int representing the distance from a bottom
     * letter to the top letter (counting the bottom letter as 1)
     *
     * @throws Exception when the list is empty or if one of the @params is
     * not in the list (i.e. not in the range of the musical alphabet)
     */
    public int distance(Object bottom, Object top) throws Exception{
        boolean foundBottom = false;
        boolean foundTop = false;
        int count = 1; // initialize to 1 because the measure of no distance is 1
        Node current = head;
        if (head == null){
            throw new Exception("List is empty");
        }
        else {
            // search for bottom
            while(!foundBottom && count <= this.getSize() + 2){ // catch bottom not in list
                if (count == this.getSize() + 2) {
                    throw new Exception("Bottom note " + bottom + " does not exist");
                }
                else if (!current.data.equals(bottom)) {
                    current = current.next;
                    count++;
                }
                else {
                    foundBottom = true;
                }
            }
        }
        count = 1; // reset count
        while (!foundTop && count <= this.getSize() + 2) { // catch bottom not in list
            if (count == this.getSize() + 2) {
                throw new Exception("Top note " + top + " does not exist");
            }
            else if (!current.data.equals(top)) {
                current = current.next;
                count++;
            }
            else{
                foundTop = true;
            }

        }
        return count;
    }

    public static void main(String[] args) {
        CircularlyLinkedList cll = new CircularlyLinkedList();
        //Adds data to the list
        cll.add("a");
        cll.add("b");
        cll.add("c");
        cll.add("d");
        cll.add("e");
        cll.add("f");
        cll.add("g");
        //Displays all the nodes present in the list
        cll.display();
        try {
            int distance = cll.distance(new String("a"), new String("g"));
            System.out.println(distance); // expect 7
            int distance5 = cll.distance(new String("g"), new String("a"));
            System.out.println(distance5); // expect 2
            int distance6 = cll.distance(new String("c"), new String("c"));
            System.out.println(distance6); // expect 1
            int distance1 = cll.distance(new String("g"), new String("b"));
            System.out.println(distance1); // expect 3, wraps around list
            int distance2 = cll.distance(new String("c"), new String("e"));
            System.out.println(distance2); // expect 3
            int distance3 = cll.distance(new String("h"), new String("e"));
            System.out.println(distance3); // java.lang.Exception: Bottom note does not exist
            int distance4 = cll.distance(new String("e"), new String("h"));
            System.out.println(distance4); // java.lang.Exception: Top note does not exist




        } catch (Exception exception){
            System.out.println(exception);
        }
    }
}
