package Model;

public class Monomial {

    private int degree;
    private double coefficient;
    private String representation;

    public Monomial(int degree, double coefficient) {

        this.coefficient = coefficient;
        this.degree = degree;
    }

    public void setRepresentation() {

        this.coefficient = Polynomial.round(this.coefficient);
        this.representation = "";
        if(coefficient > 0)
            this.representation += "+";
        else
            if(coefficient == -1.0)
                this.representation += "-";

        if(Math.abs(coefficient) != 1.0) {
            if((int)coefficient == coefficient)
                this.representation += String.valueOf((int)coefficient);
            else
                this.representation += String.valueOf(coefficient);
        } else if(degree == 0)
            this.representation += "1";

        if(degree != 0) {

            this.representation += "x";
            if(degree > 1)
                this.representation += "^" + degree;
        }
    }

    public String getRepresentation() {

        return this.representation;
    }

    public int getDegree() {

        return degree;
    }

    public void setDegree(int degree) {

        this.degree = degree;
    }

    public double getCoefficient() {

        return coefficient;
    }

    public void setCoefficient(double coefficient) {

        this.coefficient = coefficient;
    }
}