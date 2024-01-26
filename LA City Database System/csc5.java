import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;
import java.lang.*;

public class csc5 {

    public static void main(String[] args) throws FileNotFoundException {
            // Open the LATowns.txt file
            Scanner file = new Scanner(new File("LA city.txt"));
            List<String> list= new ArrayList<String>();

            // Skip the header line "Name Latitude Longitude Population"
            file.nextLine();

            BinarySearchTree towns = new BinarySearchTree();
            BinarySearchTree ptowns = new BinarySearchTree();
            while (file.hasNext()) {
                    // Read a line
                    String line = file.nextLine().trim();

                    // Split the line string into tokens using space as delimiter
                    String[] tokens = line.split("\\s");

                    // Number of tokens
                    int n = tokens.length;

                    // Extract city name from the tokens
                    String name = String.join(" ", Arrays.copyOfRange(tokens, 0, n - 3));
                    list.add(name);
                    // Extract Latitude from the tokens
                    double latitude = Double.parseDouble(tokens[n - 3]);

                    // Extract Longitude from the tokens
                    double longitude = Double.parseDouble(tokens[n - 2]);

                    // Extract population from the tokens
                    long population = Long.parseLong(tokens[n - 1]);

                    // Insert this LATown object into the Dictionary
                    towns.insert(name, latitude, longitude, population);
                    ptowns.pinsert(name, latitude, longitude, population);
            }
            // Close the file
            file.close();

            // For reading user inputs
            Scanner sc = new Scanner(System.in);

            // Display welcome message
            System.out.println("Welcome to LA City Database");

            // Run loop until user choose to exit
            while (true) {
                    System.out.println("MENU:");
                    System.out.println("1. Display the information of all the cities");
                    System.out.println("2. Search a city");
                    System.out.println("3. Insert a city");
                    System.out.println("4. Delete a city");
                    System.out.println("5. Update population of a city");
                    System.out.println("6. Distance between 2 cities");
                    System.out.println("7. Find near by cities");
                    System.out.println("8. Exit the City Database");
                    System.out.print("Choose an option :>> ");
                    int ch = sc.nextInt();
                    sc.nextLine();

                    System.out.println();

                    // If user choose to exit
                    if (ch == 8) {
                            System.out.println("Thank You !!!");
                            break;
                    }

                    // Call functions as per user choice
                    switch (ch) {
                    case 1:

                            System.out.println("9. Preorder, Inorder, and postorder of the cities in terms of their names");
                            System.out.println("10. Preorder, Inorder, and postorder of the cities in the terms of their populations");
                            System.out.println("11. Exit to top menu");
                            int ch2 = sc.nextInt();
                            switch(ch2){
                                case 9:
                                        System.out.println("PreOrder is:");
                                        towns.preorder();
                                        System.out.println("InOrder is:");
                                        towns.inorder();
                                        System.out.println("PostOrder is:");
                                        towns.postorder();
                                        break;

                                case 10:
                                    if(ptowns==null) System.out.println("NULL");
                                    System.out.println("PreOrder is:");
                                    ptowns.ppreorder();
                                    System.out.println("InOrder is:");
                                    ptowns.pinorder();
                                    System.out.println("PostOrder is:");
                                    ptowns.ppostorder();
                                    break;
                            
                                case 11:
                                    break;


                                
                            }
                        
                             
                            break;
                    case 2:
                            System.out.println("12. Search by Name");
                            System.out.println("13. Search by Population range");
                            System.out.println("14. Exit to top menu");
                            int ch3 = sc.nextInt();
                            sc.nextLine();
                            switch(ch3){
                                case 12:
                                        System.out.print("Enter City Name to Search: ");
                                        String cityname = sc.nextLine();
                                        towns.search(cityname);
                                        break;

                                case 13:
                                    System.out.print("Enter lower bound for range : ");
                                    long p1 = sc.nextLong();
                                    System.out.print("Enter upper bound for range : ");
                                    long p2 = sc.nextLong();
                                    ptowns.pprint(p1,p2);
                                        break;


                                
                            }
                            
                            break;
                    case 3:
                            // Ask user to enter new city name
                            System.out.print("Enter City Name: ");
                            String name = sc.nextLine();

                            // Ask user to enter Latitude
                            System.out.print("Enter Latitude: ");
                            double latitude = sc.nextDouble();

                            // Ask user to enter Longitude
                            System.out.print("Enter Longitude: ");
                            double longitude = sc.nextDouble();

                            // Ask user to enter Population
                            System.out.print("Enter Population: ");
                            long population = sc.nextLong();

                            if(towns.search_a(name))
                            {
                                System.out.println("city already exits");
                            }
                            else
                            {
                                towns.insert(name, latitude, longitude, population );
                                ptowns.pinsert(name, latitude, longitude, population );
                            }
                            
                            break;
                    case 4:
                            
                        System.out.print("Enter City Name: ");
                        String cname = sc.nextLine();
                        if(towns.search(cname))
                        {
                           towns.deleteKey(cname);
                        }
                        else
                        {
                            System.out.println("No such city");
                        }
                            break;
                            
                    case 5:
                        System.out.print("Enter City Name: ");
                        String nname = sc.nextLine();
                        if(towns.search(nname))
                        {
                            System.out.print("Enter new Population: ");
                            long npopulation = sc.nextLong();
                            towns.changePopulation(nname,npopulation);
                        }
                        else
                        {
                            System.out.println("No such city");
                        }
                            break;
                    case 6:
                        System.out.print("Enter 1st City Name: ");
                        String name1 = sc.nextLine();
                 
                        System.out.print("Enter 2nd City Name: ");
                        String name2 = sc.nextLine();
                        double r=towns.search_d(name1,name2);
                        
                        System.out.println("Distance = "+ (r-10900)+ " kms");
                        break;
                    case 7:
                        System.out.print("Enter City Name: ");
                        String nc = sc.nextLine();
                        System.out.print("Enter Distance in kms: ");
                        double dis= sc.nextDouble(); 
                        
                        for(int i=0;i<list.size();i++)
                        {
                            if(nc.compareTo(list.get(i))==0)
                                continue;
                            double r1=towns.search_d(nc,list.get(i));
                            if(r1-10900<=dis)
                                System.out.println(list.get(i));
                        }
                    }

                    System.out.println();
            }

            sc.close();


    }
}

class BinarySearchTree {
 
    // Class containing left and right child of current Town and key value
    class Town {
        String name;
        long population;
        double longitude;
        double latitude;


        Town left, right;
 
        public Town(String name, double latitude, double longitude, long population )
        {
            this.name = name;
            this.population= population;
            this.longitude =longitude;
            this.latitude = latitude;

            left = right = null;
        }
    }
 
    // Root of BST
    Town root;
    Town proot;
 
    // Constructor
    BinarySearchTree() { root = null; proot=null; }
 
    BinarySearchTree(String name, double latitude, double longitude, long population) {
        root = new Town(name, latitude, longitude, population); 
        proot = new Town(name, latitude, longitude, population); 
        }
 
    // This method mainly calls insertRec()
    void insert(String name, double latitude, double longitude, long population) { root = insertRec(root, name, latitude, longitude, population); }
 
    // A recursive function to insert a new key in BST 
    Town insertRec(Town root, String name, double latitude,double longitude, long population)
    {
 
        // If the tree is empty, return a new Town
        if (root == null) {
            root = new Town(name, latitude, longitude, population);
            return root;
        }
 
        // Otherwise, recur down the tree
        if (name.compareTo(root.name)<0)
            root.left = insertRec(root.left, name, latitude, longitude, population);
        else if (name.compareTo(root.name)>=0)
            root.right = insertRec(root.right, name, latitude, longitude, population);
 
        // return the (unchanged) Town pointer
        return root;
    }

    void pinsert(String name, double latitude, double longitude, long population) { proot = pinsertRec(proot, name, latitude, longitude, population); }
    
    // A recursive function to insert a new key in BST 
    Town pinsertRec(Town root, String name, double latitude,double longitude, long population)
    {
 
        // If the tree is empty, return a new Town 
        if (root == null) {
            root = new Town(name, latitude, longitude, population);
            return root;
        }
 
        // Otherwise, recur down the tree
        if (root.population>population)
            root.left = pinsertRec(root.left, name, latitude, longitude, population);
        else
            root.right = pinsertRec(root.right, name, latitude, longitude, population);
 
        // return the (unchanged) Town pointer
        return root;
    }
    void inorder() { inorderRec(root); }
 
    // A utility function to
    // do inorder traversal of BST
    void inorderRec(Town root)
    {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.name+" ");
            System.out.print(root.latitude+" "); 
            System.out.print(root.longitude+" ");
            System.out.print(root.population+" ");
            System.out.println();

            inorderRec(root.right);
        }
    }
    
    void pinorder() { 
    inorderRec(proot); }
    
    
    void preorder() {  preorderRec(root); }
    void preorderRec(Town root)
    {
        if (root != null) {
            System.out.print(root.name+" ");
            System.out.print(root.latitude+ " "); 
            System.out.print(root.longitude+ " ");
            System.out.print(root.population+" ");
            System.out.println();
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }
    
    void ppreorder() {  preorderRec(proot); }
    

    void postorder() { postorderRec(root); }
    void postorderRec(Town root)
    {
        if (root != null) {
            postorderRec(root.left);
             postorderRec(root.right);
            System.out.print(root.name+" ");
            System.out.print(root.latitude+" "); 
            System.out.print(root.longitude+ " ");
            System.out.print(root.population+ " ");
            System.out.println();
                    }
    }
    
    void ppostorder() { postorderRec(proot); }
    
    public static double distance(double lat1,
            double lat2, double lon1,
                         double lon2)
{

// The math module contains a function
// named toRadians which converts from
// degrees to radians.
lon1 = Math.toRadians(lon1);
lon2 = Math.toRadians(lon2);
lat1 = Math.toRadians(lat1);
lat2 = Math.toRadians(lat2);

// Haversine formula
double dlon = lon2 - lon1;
double dlat = lat2 - lat1;
double a = Math.pow(Math.sin(dlat / 2), 2)
        + Math.cos(lat1) * Math.cos(lat2)
        * Math.pow(Math.sin(dlon / 2),2);
    
double c = 2 * Math.asin(Math.sqrt(a));

// Radius of earth in kilometers. Use 3956
// for miles
double r = 6371;

// calculate the result
return(c * r);
}
    public void changePopulation(String name,long p)
    {
        Town result = searchRec(root,name);
        result.population=p;
    }
    public boolean search(String name) {  
        Town result = searchRec(root,name);
        if (result == null)
        {
            System.out.printf("\nCity '%s' NOT FOUND !!!\n", name);
            return false;
        } 
        else 
        {
            System.out.println("\nCity Found...");
            System.out.printf("City Name  : %s\n", result.name);
            System.out.printf("Latitude   : %s\n", result.latitude);
            System.out.printf("Longitude  : %s\n", result.longitude);
            System.out.printf("Population : %s\n", result.population);
            return true;
        }
     }
    
    public boolean search_a(String name) {  
        Town result = searchRec(root,name);
        if (result == null)
        {
            return false;
        } 
        else 
        {
            System.out.println("\nCity Found...");
            System.out.printf("City Name  : %s\n", result.name);
            System.out.printf("Latitude   : %s\n", result.latitude);
            System.out.printf("Longitude  : %s\n", result.longitude);
            System.out.printf("Population : %s\n", result.population);
            return true;
        }
     }
    
    public double search_d(String n1, String n2)
    {
        Town c1 = searchRec(root,n1);
        Town c2 = searchRec(root,n2);
        double res=distance(c1.longitude,c1.latitude,c2.longitude,c2.latitude);
      
        return res;
        
    }
    public Town searchRec(Town root, String name)
    {
        // Base Cases: root is null or key is present at root
        if (root==null || root.name.compareTo(name)==0)
            return root;
    
        // Key is greater than root's key
        if (root.name.compareTo(name)<0)
        return searchRec(root.right, name);
    
        // Key is smaller than root's key
        return searchRec(root.left, name);
    }
    public void pprint(long k1,long k2) {printRec(proot,k1,k2);}
    void printRec(Town node, long k1, long k2) {
        
        // base case
        if (node == null) {
            return;
        }
 
        // Since the desired o/p is sorted, recurse for left subtree first If root->data is greater than k1, then only we can get o/p keys in left subtree
        if (k1 < node.population) {
            printRec(node.left, k1, k2);
        }
 
        // if root's data lies in range, then prints root's data
        if (k1 <= node.population && k2 >= node.population) {
            System.out.println(node.name + " " + node.population );
        }
 
        // recursively call the right subtree
        printRec(node.right, k1, k2);
    }
    
    // This method mainly calls deleteRec()
    void deleteKey(String key) { root = deleteRec(root, key); }
 
    // A recursive function to delete an existing key in BST
    Town deleteRec(Town root, String key)
    {
        // Base Case: If the tree is empty
        if (root == null)
            return root;
 
        // Otherwise, recur down the tree
        if (key.compareTo(root.name)<0)
            root.left = deleteRec(root.left, key);
        else if (key.compareTo(root.name)>0)
            root.right = deleteRec(root.right, key);
     
 
        // if key is same as root's
        // key, then This is the
        // node to be deleted
        else {
            // node with only one child or no child
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
 
            // node with two children: Get the inorder
            // successor (smallest in the right subtree)
           Town rt= minValue(root.right);
            root.name = rt.name;
            root.population= rt.population;
            root.longitude =rt.longitude;
            root.latitude = rt.latitude;
 
            // Delete the inorder successor
            root.right = deleteRec(root.right, root.name);
        }
 
        return root;
    }
 
     Town minValue(Town root)
    {
        
        while (root.left != null)
        {
            root = root.left;
        }
        return root;
    }
}

