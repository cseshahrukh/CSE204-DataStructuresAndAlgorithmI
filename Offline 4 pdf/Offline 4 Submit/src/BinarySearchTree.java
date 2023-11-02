
import java.util.Scanner;

public class BinarySearchTree<T> {

    private int totalNode=0;

    private Node root=null;


    public boolean isEmpty()
    {
        return totalNode==0;
    }


    public int getSize()
    {
        return totalNode;
    }


    public void insertItem(int value)
    {
        if (contains(root, value))
            return;
        //karon already ache bole ami add korbo na
        //ensuring just unique value
        root=insertItem(root, value);
        totalNode++;
    }


    public Node insertItem(Node node, int value)
    {
        //got leaf node where we will add this value
        if (node==null)
        {
            node=new Node(null, null, value);
        }
        else
        {
            if (value<(int)node.data)
            {
                node.left=insertItem(node.left, value);
            }
            else
                node.right=insertItem(node.right, value);
        }
        return node;
        //just added a value at last
        //now i want to indicate if the free node is right or left child of other
        //to assign
    }


    public boolean deleteItem(int value)
    {
        if (contains(root, value))
        {
            root=deleteItem(root, value);
            totalNode--;
            return true;
        }
        return false;
    }


    public Node deleteItem(Node node, int value)
    {
        if (node == null) return null;

        int cmp = value-(int)node.data;
        //cmp=value.compareTo(node.data) this will work if we do the below part
        //then <T extends Comparable<T>>

        //going left
        if (cmp < 0) {
            node.left = deleteItem(node.left, value);
        }

        //going right
        else if (cmp > 0) {
            node.right = deleteItem(node.right, value);

        }

        //Found it !!
        else {
            // only a right subtree or no subtree
            // swap the node we wish to remove with its right child.
            if (node.left == null) {

                Node rightChild = node.right;
                //if there is no right then rightChild is null
                //if there is right child then the right child will come to this position
                node.data = null; //if we work with  objects rather than int then this is good practice
                node = null; //I will assign after return
                //we can also write node=rightChild here !!!

                return rightChild; //if there is no right child then this will return null after ruturning
                //node=rightchild will be done
            }

            // only a left subtree
            // swap the node we wish to remove with its left child
            else if (node.right == null) {
                Node leftChild = node.left;

                node.data = null;
                node = null;
                return leftChild;
            }


            //both right and left exists !!
            //max of left or min of right

            // According to question min of right
            // traversing as far left as possible in the right subtree.
            else {
                // Find the leftmost node in the right subtree
                Node tmp = getMinItem(node.right);

                // Swap the data
                node.data = tmp.data;

                // Go into the right subtree and remove the leftmost node we
                // found and swapped data with.
                node.right = deleteItem(node.right, (int) tmp.data);

                // largest node in the left korte chaile
                // Node tmp = findMax(node.left);
                // node.data = tmp.data;
                // node.left = remove(node.left, tmp.data);

            }
        }

        return node;
    }


    public Node getMinItem(Node node) {
        //helper method for delete
        //helper method for succesor
        while (node.left != null) node = node.left;
        return node;
    }

    public int getMinItem(){
        Node node=root;
        while (node.left != null) node = node.left;
        return (int)node.data;
    }



    private Node getMaxItem(Node node) {
        //helper method for delete
        //helper method for prece
        while (node.right != null) node = node.right;
        return node;
    }

    public int getMaxItem(){
        Node node=root;
        while (node.right != null) node = node.left;
        return (int)node.data;
    }



    public boolean searchItem(int value) {
        if (contains(root, value))
        {
            System.out.println(value+" has been found");
            return true;
        }
        else {
            System.out.println(value + " has not been found");
            return false;
        }
        //search from the root
    }

    // private recursive method to find an element in the tree
    public boolean contains(Node node, int value) {

        // Base case: reached bottom, value not found
        if (node == null) {

            return false;
        }

        int cmp = value-(int)node.data;

        //search in left sub tree
        if (cmp < 0)
            return contains(node.left, value);

            //search in right sub tree
        else if (cmp > 0)
            return contains(node.right, value);


        else
        {
            //got it else cmp==0
            return true;
        }
    }




    // Recursive helper method to compute the height of the tree
    public int height(Node node) {
        if (node == null) return 0;
        //height ta ulta vabe count korlam
        //mane ekdom nicher node er height 1
        return Math.max(height(node.left), height(node.right)) + 1;
        //count starts from 1
        //1 based
    }

    public int getHeight() {
        return height(root);
        //O(n)
        //one node is visited only once
    }


    public int getItemDepth(int value)
    {
        if (!searchItem(value) )
            return -1;
        else return getItemDepth(root, value,0);


    }
    public int getItemDepth(Node root, int value, int currentDepth)
    {
        if ((int)root.data==value)
        {
            return currentDepth;

        }
        else if (value<(int)root.data)
            return getItemDepth(root.left, value, currentDepth+1);
        else
            return getItemDepth(root.right, value, currentDepth+1);
    }


    public Node nodeOfItem(int value) {
        return nodeOfItem(root,value);
    }

    public Node nodeOfItem(Node node, int value) {

        //we ensured using contain function that value exists

        int cmp = value-(int)node.data;

        if (cmp==0) return node;
        //search in left sub tree
        if (cmp < 0) return nodeOfItem(node.left, value);

            //search in right sub tree
        else return nodeOfItem(node.right, value);

            //got it

    }


    public int getInOrderSuccessor(int value) {
        if (!contains(root,value)){
            System.out.println("Not found");
            return -1;
        }
        Node anode=nodeOfItem(value);
        if (getMinItem(anode)!=null)
        {
            return (int) getMinItem(anode.right).data;
        }
        else
        {
            System.out.println("No right subtree");
            return -1;
        }



    }
    public int getInOrderPredecessor(int value)
    {
        if (!contains(root,value)){
            System.out.println("Your input item not found");
            return -1;
        }
        else {
            Node aNode = nodeOfItem(value);
            if (getMaxItem(aNode) != null)
                return (int) getMaxItem(aNode.left).data;
            else {
                System.out.println("Your input does not have left subtree");
                return -1;
            }
        }
    }

    public void printInOrder()
    {
        printInOrder(root);
        System.out.println();
    }

    public void printInOrder(Node node)
    {
        if (node==null)
            return ;
        printInOrder(node.left);
        System.out.print(node.data+" ");
        printInOrder(node.right);

    }

    public void printPreOrder()
    {
        printPreOrder(root);
        System.out.println();
    }


    public void printPreOrder(Node node)
    {
        if (node==null)
            return ;
        System.out.print(node.data+" ");
        printPreOrder(node.left);
        printPreOrder(node.right);

    }


    public void printPostOrder()
    {
        printPostOrder(root);
        System.out.println();
    }

    public void printPostOrder(Node node)
    {
        if (node==null)
            return ;
        printPostOrder(node.left);
        printPostOrder(node.right);
        System.out.print(node.data+" ");
    }

    public static void main(String[] args) {
        Scanner scn=new Scanner(System.in);
        String str; int data, answer;
        BinarySearchTree bst=new BinarySearchTree();
        while(true)
        {
            System.out.println("1. Insert Item,             2. Search Item");
            System.out.println("3. Get In Order Successor,  4. Get In Oder Predecessor");
            System.out.println("5. Delete Item,             6. Get Item Depth");
            System.out.println("7. Get Max Item             8. Get Min Item");
            System.out.println("9. Get Height               10. Print In Order");
            System.out.println("11. Print Pre Order         12. Print Post Order");
            System.out.println("13. Get size                14. End the program ");

            str=scn.next();

            if (str.compareTo("1")==0)
            {
                data=scn.nextInt();
                bst.insertItem(data);

            }
            else  if (str.compareTo("2")==0)
            {

                data=scn.nextInt();
                bst.searchItem(data);
            }
            else  if (str.compareTo("3")==0)
            {

                data=scn.nextInt();
                answer=bst.getInOrderSuccessor(data);
                if (answer!=444)
                {
                    System.out.println("Successor is "+answer);
                }
                else
                    System.out.println("No successor");
            }
            else  if (str.compareTo("4")==0)
            {

                data=scn.nextInt();
                answer=bst.getInOrderPredecessor(data);
                if (answer!=444)
                {
                    System.out.println("Predecessor is "+answer);
                }
                else
                    System.out.println("No predecessor");
            }
            else  if (str.compareTo("5")==0)
            {

                data=scn.nextInt();
                bst.deleteItem(data);
            }
            else  if (str.compareTo("6")==0)
            {

                data=scn.nextInt();
                answer=bst.getItemDepth(data);
                System.out.println("Item depth is "+answer);
            }
            else  if (str.compareTo("7")==0)
            {

                answer=bst.getMaxItem();
                System.out.println("Max item is "+answer);
            }
            else  if (str.compareTo("8")==0)
            {

                answer=bst.getMinItem();
                System.out.println("Min item is "+answer);
            }
            else  if (str.compareTo("9")==0)
            {

                answer=bst.getHeight();
                System.out.println("Height is "+answer);
            }
            else  if (str.compareTo("10")==0)
            {

                bst.printInOrder();
            }
            else  if (str.compareTo("11")==0)
            {

                bst.printPreOrder();
            }
            else  if (str.compareTo("12")==0)
            {

               bst.printPostOrder();
            }
            else  if (str.compareTo("13")==0)
            {

                answer=bst.getSize();
                System.out.println("size is "+answer);
            }
            else  if (str.compareTo("14")==0)
            {

                break;
            }

        }
    }
}
