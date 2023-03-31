package org.assignment2;

//import java.io.ObjectInputStream.GetField;
import java.util.Scanner;
import java.util.Stack;

public class FormulaCalcCopy2 {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Tests?");

        // int tests = input.nextInt(); // Number of test to be performed

        for (int i = 0; i < 4; i++) {
            System.out.println("Total: " + Algorithm(formulas[i]));
        }
    }

    static String[] atomicNum = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33",
            "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51",
            "52", "53", "54" };

    static String[] element = { "H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne", "Na", "Mg", "Al", "Si", "P",
            "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn", "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As",
            "Se", "Br", "Kr", "Rb", "Sr", "Y", "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb",
            "Te", "I", "Xe" };

    static int newElement[] = new int[54]; // parsed atomic# to int
    static Stack<Integer> elements = new Stack<Integer>();
    static String[] formulas = { "H", "KBr", "H2O", "C6H12O6" };;

    /**
     * Algorithm
     * 
     * @param a Molecular formula
     * @return
     * @return Total number of protons
     */

    public static Integer Algorithm(String formula) {

        int totalProton = 0;
        String elem = null;
        int i = 0;
        char c = formula.charAt(i);
        char d = formula.charAt(i);
        char e;

        while (i < formula.length()
                && ((Character.isUpperCase(c) || Character.isLowerCase(c) || Character.isDigit(c)))) {

            if ((i < formula.length() - 1 && Character.isDigit(formula.charAt(i + 1)))) {
                i++;
                c = formula.charAt(i);
            } else if (i == formula.length() - 1 && Character.isUpperCase(c)) {
                System.out.println("formula length: " + formula.length());
                elem = Character.toString(c);
                elements.push(getProton(elem));
                System.out.println("stack: " + elements.peek());
                i++;
            }

            else if (i < formula.length()) {
                System.out.println("formula length: " + formula.length());
                elem = Character.toString(c);
                elements.push(getProton(elem));
                System.out.println("stack: " + elements.peek());
                i++;
                c = formula.charAt(i);

            }

            if (i < formula.length() && Character.isUpperCase(c) && i != formula.length() - 1) {
                e = formula.charAt(i + 1);
                c = formula.charAt(i);
                if (Character.isLowerCase(e)) {
                    elem = Character.toString(c) + Character.toString(e);
                    elements.push(getProton(elem));
                    i++;
                    break;
                }
            } else if (Character.isDigit(c) && (i < formula.length())) {
                if (i == formula.length() - 1) {
                    c = formula.charAt(i);
                    d = formula.charAt(i - 1);
                    elem = Character.toString(d);
                    int atomNum = Integer.parseInt(Character.toString(c));
                    System.out.println("atomNum: " + atomNum);
                    System.out.println(elements.push(getProton(elem)));
                    i++;
                } else if (i < formula.length()) {
                    e = formula.charAt(i + 1);
                    c = formula.charAt(i);
                    d = formula.charAt(i - 1);
                    if (Character.isDigit(e) && Character.isLetter(d)) {
                        String atomicNum = Character.toString(c) + Character.toString(e);

                        int atomNumber = Integer.parseInt(atomicNum);

                        elem = Character.toString(d);
                        int protons = getProton(elem);
                        elements.push((protons + 1) * atomNumber);
                        System.out.println("stack: " + elements.peek());
                        i++;
                        c = formula.charAt(i);
                    } else if (Character.isUpperCase(e)) {
                        elem = Character.toString(d);
                        int atomNum = Integer.parseInt(Character.toString(c));
                        elements.push(getProton(elem) * atomNum);
                        i++;
                        c = formula.charAt(i);

                    } else {
                        i++;
                        d++;
                        break;
                    }
                }
            }

        }
        while (!elements.empty()) {
            totalProton += elements.pop();

        }
        return totalProton;
    }

    public static int getProton(String elem) {
        int proton = 0;
        for (int f = 0; f < element.length; f++) { // find elem in array
            if (element[f].equals(elem)) {
                element[f] = element[f].replaceAll(element[f], atomicNum[f]);
                proton = Integer.parseInt(element[f]);
            }
        }
        return proton;

    }
}