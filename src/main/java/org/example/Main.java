package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        List<String> universe = getUniverse(scan);
        Set set = getSet(universe, scan);

        while (true) {
            System.out.print("\nChoose Operation:\n1) Union of two sets\n" +
                    "2) Intersection of two sets\n" +
                    "3) Complement of a set\n" +
                    "4) Difference between two sets\n" +
                    "5) Cardinality of a set\n" +
                    "6) Print a set\n" +
                    "7) Exit\n\n");

            int operation = getOperation(scan);

            switch (operation) {
                case 1:
                    System.out.println();
                    System.out.println("Union: " + getUnion(set, scan).toString());
                    break;
                case 2:
                    System.out.println();
                    System.out.println("Intersection: " + getIntersection(set, scan).toString());
                    break;
                case 3:
                    System.out.println();
                    System.out.println("Complement: " + set.complement().toString());
                    break;
                case 4:
                    System.out.println();
                    System.out.println("Difference: " + getDifference(set, scan));
                    break;
                case 5:
                    System.out.println();
                    System.out.println("Set Cardinality: " + set.cardinality());
                    break;
                case 6:
                    System.out.println();
                    System.out.println("Set: " + set.toString());
                    break;
                case 7:
                    System.out.println();
                    System.out.println("Done");
                    return;
            }
        }
    }

    public static List<String> parseInput(String input) {
        Pattern pattern = Pattern.compile(" *([\\w\\s\"-']+)");
        Matcher matcher = pattern.matcher(input);
        List<String> universe = new ArrayList<>();

        while(matcher.find()) {
            String match = matcher.group(1).strip();
            if (!universe.contains(match)) {
                universe.add(match);
            }
        }
        return universe;
    }

    public static List<String> getUniverse(Scanner scan) {
        System.out.print("Enter Universe (U): ");
        String input = scan.nextLine();
        List<String> universe = parseInput(input);

        return universe;
    }

    public static Set getSet(List<String> universe, Scanner scan) {
        String input;
        Set set;

        while(true) {
            System.out.print("Enter Set (S): ");
            input = scan.nextLine();
            List<String> inputSet = parseInput(input);

            try {
                set = new Set(universe);
                set.initializeSetFromList(inputSet);
                break;
            } catch (InvalidSetException e) {
                System.out.println(e.getMessage());
            }
        }
        return set;
    }

    public static int getOperation(Scanner scan) {
        int operation;
        while (true) {
            try {
                System.out.print("Operation Number: ");
                String input = scan.nextLine();
                operation = Integer.parseInt(input);
                if (operation > 7 || operation < 1) throw new Exception("");
                return operation;
            } catch (Exception e) {
                System.out.println("Invalid operation");
            }
        }
    }

    public static List<String> getUnion(Set set, Scanner scan) {
        Set newSet = getSet(set.getUniverse(), scan);
        Set unionSet = set.union(newSet);

        return unionSet.getElements();
    }

    public static List<String> getIntersection(Set set, Scanner scan) {
        Set newSet = getSet(set.getUniverse(), scan);
        Set intersectionSet = set.intersection(newSet);

        return intersectionSet.getElements();
    }

    public static List<String> getDifference(Set set, Scanner scan) {
        Set newSet = getSet(set.getUniverse(), scan);
        Set differeneceSet = set.difference(newSet);

        return differeneceSet.getElements();
    }
}