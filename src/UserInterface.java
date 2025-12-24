//Levi Metzger
//Enhanced Binary Search Tree

import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class UserInterface {
    Scanner scnr;
    EnhancedBST tree;
    public UserInterface(){
        scnr = new Scanner(System.in);
        tree = new EnhancedBST();
    }

    public void go(){
        int choice = 1;
        System.out.println("\n\nWelcome to Enhanced BST Tester.");

        do{
            System.out.println("\nMenu -");
            System.out.println("  0) Quit");
            System.out.println("  1) Build a BST from a text file");
            System.out.println("  2) Print the tree");
            System.out.println("  3) Add data");
            System.out.println("  4) Remove data");
            System.out.println("  5) Show tree height");
            System.out.println("  6) Show internal path length");
            System.out.println("  7) Count absent children");
            System.out.println("  8) Find a path sum");
            System.out.println("  9) Export a BST to a text file");
            System.out.print("Enter your choice: ");

            while(true){
                if(scnr.hasNextInt()){
                    choice = scnr.nextInt();
                    scnr.nextLine();
                    if(choice >= 0 && choice <= 9){
                        break;
                    }
                    else{
                        System.out.print("Invalid input. The number must be between 0 and 9: ");
                    }
                }
                else{
                    System.out.print("Invalid input. You must enter an integer between 0 and 9: ");
                    scnr.next();
                }
            }

            switch(choice){
                case 0:
                    break;

                case 1:{
                    System.out.print("Enter a file path: ");
                    String iFilePath = scnr.nextLine();
                    File iFile = new File(iFilePath);

                    try(Scanner fScnr = new Scanner(iFile)){
                        while(fScnr.hasNextInt()){
                            tree.add(fScnr.nextInt());
                        }
                        System.out.println("File successfully added.");
                    }
                    catch(FileNotFoundException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case 2:{
                    tree.print();
                    break;
                }
                case 3:{
                    System.out.print("Enter the value you want to add to the tree: ");
                    while(!scnr.hasNextInt()){
                        System.out.print("You must enter an integer: ");
                        scnr.next();
                    }
                    int num = scnr.nextInt();
                    tree.add(num);
                    scnr.nextLine();
                    System.out.println(num + " added if not already present.");
                    break;
                }
                case 4:{
                    System.out.print("Enter the value you want to remove from the tree: ");
                    while(!scnr.hasNextInt()){
                        System.out.print("You must enter an integer: ");
                        scnr.next();
                    }
                    int num = scnr.nextInt();
                    tree.remove(num);
                    scnr.nextLine();
                    System.out.println(num + " removed if present.");
                    break;
                }
                case 5:{
                    System.out.println("Height: " + tree.getHeight());
                    break;
                }
                case 6:{
                    System.out.println("Internal path length: " + tree.getInternalPathLength());
                    break;
                }
                case 7:{
                    System.out.println("Number of absent children: " + tree.getAbsentChildren());
                    break;
                }
                case 8:{
                    System.out.print("Enter a path sum: ");
                    while(!scnr.hasNextInt()){
                        System.out.print("You must enter an integer: ");
                        scnr.next();
                    }                    
                    int pathSum = scnr.nextInt();
                    scnr.nextLine();
                    System.out.println("The tree " + (tree.isPathSum(pathSum) ? "contains " : "does not contain ") + "a path with a sum of " + pathSum + ".");
                    break;
                }
                case 9:{
                    System.out.print("Enter a file name to which to export the tree: ");
                    String oFilePath = scnr.nextLine();
                    File oFile = new File(oFilePath);
                    Iterator<Integer> treeIterator = tree.getTreeIterator();
                    try(PrintWriter ofWriter = new PrintWriter(oFile)){
                        while(treeIterator.hasNext()){
                            ofWriter.println(treeIterator.next());
                        }
                    }
                    catch(FileNotFoundException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
            }
        }while(choice != 0);
    }

    public static void main(String[] args) throws Exception {
        UserInterface UI = new UserInterface();
        UI.go();        
    }
}
