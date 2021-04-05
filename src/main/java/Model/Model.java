package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Model {

    public Polynomial p1 = null;
    public Polynomial p2 = null;

    public Polynomial parsePolynomial(String polString) {

        ArrayList<Monomial> monomials = new ArrayList<>();
        polString = polString.replace(" ", "");

        // Split after + and -
        Pattern pattern = Pattern.compile("(?=-)|(?=\\+)");
        ArrayList<String> terms = new ArrayList<>(Arrays.asList(pattern.split(polString)));

        for(String currentString : terms) {

            // Split the current term after "*"
            ArrayList<String> currentTerm = new ArrayList<>(Arrays.asList(currentString.split("\\*")));
            Monomial monomial;
            String firstTerm = currentTerm.get(0);
            int sign = 1;

            //determine the sign
            if(firstTerm.indexOf("+") == 0) {
                firstTerm = firstTerm.replace("+", "");
                sign = 1;
            }
            else if(firstTerm.indexOf("-") == 0) {
                firstTerm = firstTerm.replace("-", "");
                sign = -1;
            }

            // if there is no "*"
            if(currentTerm.size() == 1) {

                // if on first pos there is x => coefficient is 1
                if(firstTerm.indexOf('x') == 0) {

                    // check for power
                    if(!firstTerm.contains("^"))
                        monomial = new Monomial(1, sign);
                    else
                        monomial = new Monomial(Integer.parseInt(firstTerm.split("\\^")[1]), sign);
                } else {

                    // if there is no x => x^0
                    if(!firstTerm.contains("x")) {
                        monomial = new Monomial(0, sign * Double.parseDouble(firstTerm));
                    } else {

                        // there is no "*", but it has a coefficient: 15x, -5x^4

                        // check for power
                        if(firstTerm.contains("^")) {

                            monomial = new Monomial(Integer.parseInt(firstTerm.split("\\^")[1]), sign*Double.parseDouble(firstTerm.split("x")[0]));
                        } else {

                            monomial = new Monomial(1, sign*Double.parseDouble(firstTerm.split("x")[0]));
                        }
                    }
                }
            } else {

                // there is "*"

                // first term is the coefficient, second term is x^power
                String secondTerm = currentTerm.get(1);
                double coefficient = sign*Double.parseDouble(firstTerm);

                //check for power
                if(!secondTerm.contains("^")) {
                    monomial = new Monomial(1, coefficient);
                } else {
                    monomial = new Monomial(Integer.parseInt(secondTerm.split("\\^")[1]), coefficient);
                }
            }
            monomial.setRepresentation();
            monomials.add(monomial);
        }

        monomials.sort(new Polynomial.Sort());
        Polynomial p = new Polynomial(monomials);
        Polynomial.removeRedundancy(p);

        return p;
    }

    public void readPol(String firstPol, String secondPol) {

        p1 = parsePolynomial(firstPol);
        if(!secondPol.equals(""))
            p2 = parsePolynomial(secondPol);
    }
}