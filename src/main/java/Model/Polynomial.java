package Model;

import java.text.DecimalFormat;
import java.util.*;

public class Polynomial {

    public ArrayList<Monomial> monomials;
    private static final String validChars = " +-^*.1234567890x";

    public Polynomial(ArrayList<Monomial> monomials) {

        this.monomials = monomials;

        for(Monomial mon : this.monomials)
            mon.setRepresentation();
    }

    public Polynomial() {

        monomials = new ArrayList<>();
    }

    public Polynomial derivative() {

        // removing the element with the power 0
        Polynomial p = new Polynomial(this.monomials);
        if(p.monomials.get(p.monomials.size() - 1).getDegree() == 0)
            p.monomials.remove(p.monomials.size() - 1);

        for(Monomial mon : p.monomials) {

            mon.setCoefficient(mon.getCoefficient() * mon.getDegree());
            mon.setDegree(mon.getDegree() - 1);
            mon.setRepresentation();
        }

        return p;
    }

    public Polynomial integration() {

        Polynomial p = new Polynomial(this.monomials);
        for(Monomial mon : p.monomials) {

            mon.setDegree(mon.getDegree() + 1);
            mon.setCoefficient(mon.getCoefficient() / mon.getDegree());
            mon.setCoefficient(round(mon.getCoefficient()));
            mon.setRepresentation();
        }

        return p;
    }

    public Polynomial addition(Polynomial p) {

        ArrayList<Monomial> newMonomials = new ArrayList<>();
        int i = 0, j = 0;
        int index1 = this.monomials.size(), index2 = p.monomials.size();

        // something like merging linked lists
        while(i < index1 && j < index2) {

            if(this.monomials.get(i).getDegree() > p.monomials.get(j).getDegree()) {

                // if the degree is greater, then add to the list, because the monomials are in descending order in terms of the degree
                newMonomials.add(this.monomials.get(i++));
            } else if(this.monomials.get(i).getDegree() < p.monomials.get(j).getDegree()) {

                newMonomials.add(p.monomials.get(j++));
            } else {

                int degree = this.monomials.get(i).getDegree();
                double coefficient = this.monomials.get(i++).getCoefficient() + p.monomials.get(j++).getCoefficient();
                if(coefficient != 0) {
                    Monomial mon = new Monomial(degree, coefficient);
                    mon.setRepresentation();
                    newMonomials.add(mon);
                }
            }
        }

        while(i < index1)
            newMonomials.add(this.monomials.get(i++));

        while(j < index2)
            newMonomials.add(p.monomials.get(j++));

        return new Polynomial(newMonomials);
    }

    public Polynomial subtraction(Polynomial p) {

        ArrayList<Monomial> newMonomials = new ArrayList<>();
        int i = 0, j = 0;
        int index1 = this.monomials.size(), index2 = p.monomials.size();

        // something like merging linked lists
        while(i < index1 && j < index2) {

            if(this.monomials.get(i).getDegree() > p.monomials.get(j).getDegree()) {

                // if the degree is greater, then add to the list, because the monomials are in descending order in terms of the degree
                newMonomials.add(this.monomials.get(i++));
            } else if(this.monomials.get(i).getDegree() < p.monomials.get(j).getDegree()) {

                int degree = p.monomials.get(j).getDegree();
                double coefficient = (-1)*p.monomials.get(j++).getCoefficient();
                Monomial mon = new Monomial(degree, coefficient);
                mon.setRepresentation();
                newMonomials.add(mon);
            } else {

                int degree = this.monomials.get(i).getDegree();
                double coefficient = this.monomials.get(i++).getCoefficient() - p.monomials.get(j++).getCoefficient();
                if(coefficient != 0) {

                    Monomial mon = new Monomial(degree, coefficient);
                    mon.setRepresentation();
                    newMonomials.add(mon);
                }
            }
        }

        while(i < index1)
            newMonomials.add(this.monomials.get(i++));

        while(j < index2) {

            int degree = p.monomials.get(j).getDegree();
            double coefficient = (-1)*p.monomials.get(j++).getCoefficient();
            Monomial mon = new Monomial(degree, coefficient);
            mon.setRepresentation();
            newMonomials.add(mon);
        }

        return new Polynomial(newMonomials);
    }

    public Polynomial division(Polynomial p) {

        ArrayList<Monomial> result = new ArrayList<>();

        //while the first polynomial has terms and the degree >= than the second polynomial
        while(this.monomials.size() > 0 && this.monomials.get(0).getDegree() >= p.monomials.get(0).getDegree()) {

            double coefficient = this.monomials.get(0).getCoefficient()/p.monomials.get(0).getCoefficient();
            int degree = this.monomials.get(0).getDegree() - p.monomials.get(0).getDegree();
            Monomial currentTerm = new Monomial(degree, coefficient);

            // add the monomial to the result
            result.add(currentTerm);

            // create an instance of Polynomial to multiply with the first polynomial and then to subtract from the second
            Polynomial pol = new Polynomial();
            pol.monomials.add(currentTerm);
            pol.monomials = pol.multiplication(p).monomials;
            this.monomials = this.subtraction(pol).monomials;
        }

        return new Polynomial(result);
    }

    public Polynomial multiplication(Polynomial p) {

        ArrayList<Monomial> newMonomials = new ArrayList<>();

        // multiply each term with each term
        for(Monomial mon1 : this.monomials) {
            for(Monomial mon2 : p.monomials) {

                int degree = mon1.getDegree() + mon2.getDegree();
                double coefficient = mon1.getCoefficient()*mon2.getCoefficient();
                Monomial mon = new Monomial(degree, coefficient);
                mon.setRepresentation();
                newMonomials.add(mon);
            }
        }

        newMonomials.sort(new Sort());
        this.monomials = newMonomials;
        removeRedundancy(this);

        return new Polynomial(newMonomials);
    }

    //checking if the string has a valid form
    public static boolean checkPolString(String poLString) {

        if(poLString.length() == 1 && !Character.isDigit(poLString.toCharArray()[0]) && poLString.charAt(0) != 'x')
            return true;

        for(char ch : poLString.toCharArray()) {
            if(!validChars.contains(String.valueOf(ch)))
                return true;
        }

        char previous = ' ';
        for(char ch : poLString.toCharArray()) {

            if(previous == ' ') {
                previous = ch;
                continue;
            }

            if(previous == 'x' && ch != '^' && ch != '+' && ch != '-')
                return true;

            if(previous == '*' && ch != 'x')
                return true;

            if(previous == '^' && !Character.isDigit(ch))
                return true;

            if((previous == '+' || previous == '-') && (!Character.isDigit(ch) && ch != 'x'))
                return true;

            if(previous == '.' && !Character.isDigit(ch))
                return true;

            previous = ch;
        }
        
        // if the last character is not a digit or x, return false
        return !Character.isDigit(previous) && previous != 'x';
    }

    // remove redundant terms like
    // 2x^3+5x^4-x^3 => 5x^4+x^3
    //2x^4+x-x => 2x^4
    public static void removeRedundancy(Polynomial p) {

        ArrayList<Monomial> toRemove = new ArrayList<>();
        Monomial currentMonomial = p.monomials.get(0);
        for(Monomial mon : p.monomials) {
            if(mon == currentMonomial)
                continue;

            if(mon.getDegree() != currentMonomial.getDegree()) {
                currentMonomial = mon;
            } else {
                double coefficient1 = currentMonomial.getCoefficient();
                double coefficient2 = mon.getCoefficient();
                currentMonomial.setCoefficient(coefficient1 + coefficient2);
                currentMonomial.setRepresentation();
                toRemove.add(mon);
            }
        }

        for(Monomial mon : p.monomials)
            if(mon.getCoefficient() == 0)
                toRemove.add(mon);

        for(Monomial removeMon : toRemove) {
            p.monomials.remove(removeMon);
        }
    }

    public static class Sort implements Comparator<Monomial> {
        @Override
        public int compare(Monomial m1, Monomial m2) {
            return m2.getDegree() - m1.getDegree();
        }
    }

    public String getRepresentation() {

        StringBuilder str = new StringBuilder();

        for(Monomial mon : monomials) {
            str.append(mon.getRepresentation());
        }

        return str.toString();
    }

    //rounding the number with 3 decimals
    public static double round(double value) {

        DecimalFormat df = new DecimalFormat("#.###");
        value = Double.parseDouble(df.format(value));
        return value;
    }
}