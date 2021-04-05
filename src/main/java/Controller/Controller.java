package Controller;

import Model.Model;
import Model.Polynomial;
import View.View;
import Model.Monomial;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private final Model model;
    private final View view;

    public Controller(Model model, View view) {

        this.model = model;
        this.view = view;

        view.additionAction(new Addition());
        view.subtractionAction(new Subtraction());
        view.multiplicationAction(new Multiplication());
        view.divisionAction(new Division());
        view.derivativeAction(new Derivative());
        view.integrationAction(new Integration());
        view.delP1Action(new DelP1());
        view.delP2Action(new DelP2());
    }

    public boolean readInput(boolean bothPol) {

        //p1Text.setText(p1Text.getText().replace(" ", ""));
        view.setP1Text(view.getP1Text().replace(" ", ""));
        view.setP2Text(view.getP2Text().replace(" ", ""));
        //p2Text.setText(p2Text.getText().replace(" ", ""));

        if(bothPol) {
            // if one pol is empty
            if (view.getP1Text().equals("") || view.getP2Text().equals("")) {

                String twoEmptyPol = "Please provide two polynomials!";
                JOptionPane.showMessageDialog(new JFrame(), twoEmptyPol, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                return false;

                // if one/both pols has invalid form
            } else if (Polynomial.checkPolString(view.getP1Text()) || Polynomial.checkPolString(view.getP2Text())) {

                String twoInvalidPols = "Please provide two valid polynomials!";
                JOptionPane.showMessageDialog(new JFrame(), twoInvalidPols, "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            model.readPol(view.getP1Text(), view.getP2Text());
        } else {
            if(view.getP1Text().equals("")) {

                String oneEmptyPol = "Please provide one polynomial!";
                JOptionPane.showMessageDialog(new JFrame(), oneEmptyPol, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            } else if(Polynomial.checkPolString(view.getP1Text())) {

                String oneInvalidPols = "Please provide one valid polynomial!";
                JOptionPane.showMessageDialog(new JFrame(), oneInvalidPols, "Error",
                        JOptionPane.ERROR_MESSAGE);
                return false;
            }
            model.readPol(view.getP1Text(), "");
        }
        return true;
    }

    // implement the actions for every button
    class Addition implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            view.setRemVisibility(false);
            view. setReminderLabelVisibility(false);
            view.setPolResult("");

            // check if the pols are valid
            if(!readInput(true))
                return;

            Polynomial p = model.p1.addition(model.p2);

            String result = p.getRepresentation();

            if(result.equals(""))
                result = "0";
            view.setPolResult(result);
        }
    }


    class Subtraction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            view.setRemVisibility(false);
            view. setReminderLabelVisibility(false);
            view.setPolResult("");

            // check if the pols are valid
            if(!readInput(true))
                return;

            Polynomial p = model.p1.subtraction(model.p2);
            String result = p.getRepresentation();

            if(result.equals(""))
                result = "0";
            view.setPolResult(result);
        }
    }

    class Multiplication implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            view.setRemVisibility(false);
            view. setReminderLabelVisibility(false);
            view.setPolResult("");

            // check if the pols are valid
            if(!readInput(true))
                return;

            Polynomial p = model.p1.multiplication(model.p2);

            String result = p.getRepresentation();

            if(result.equals(""))
                result = "0";
            view.setPolResult(result);
        }
    }

    class Division implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            // if the input is not valid
            if(!readInput(true))
                return;

            // if one pol is empty
            if(model.p2.monomials.size() == 0 || model.p1.monomials.size() == 0) {

                String divisionError = "Error, division by 0!";
                JOptionPane.showMessageDialog(new JFrame(), divisionError, "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            view.setRemVisibility(true);
            view.setReminderLabelVisibility(true);

            // invert the polynomials if the first one has the degree smaller than the second
            if (model.p1.monomials.get(0).getDegree() < model.p2.monomials.get(0).getDegree()) {

                Polynomial tempP = model.p1;
                model.p1 = model.p2;
                model.p2 = tempP;
            }

            // the result of the division
            Polynomial result = model.p1.division(model.p2);

            String res = result.getRepresentation();

            if(res.equals(""))
                res = "0";
            view.setPolResult(res);

            // the remainder
            String remainder = model.p1.getRepresentation();

            if(remainder.equals(""))
                remainder = "0";
            view.setReminderText(remainder);
        }
    }

    class Integration implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setRemVisibility(false);
            view. setReminderLabelVisibility(false);
            view.setPolResult("");

            // check if the pol is valid
            if(!readInput(false))
                return;

            Polynomial p = model.p1.integration();

            StringBuilder finalResult = new StringBuilder();
            for(Monomial mon : p.monomials) {

                finalResult.append(mon.getRepresentation());
            }

            if(finalResult.toString().equals(""))
                finalResult = new StringBuilder("0");

            view.setPolResult(finalResult.toString());
        }
    }

    class Derivative implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.setRemVisibility(false);
            view. setReminderLabelVisibility(false);
            view.setPolResult("");

            // check if the pol is valid
            if(!readInput(false))
                return;

            Polynomial p = model.p1.derivative();
            StringBuilder finalResult = new StringBuilder();
            for(Monomial mon : p.monomials) {

                finalResult.append(mon.getRepresentation());
            }

            if(finalResult.toString().equals(""))
                finalResult = new StringBuilder("0");

            view.setPolResult(finalResult.toString());
        }
    }

    class DelP1 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            view.setP1Text("");
            view.setPolResult("");
            view.setReminderText("");
        }
    }

    class DelP2 implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            view.setP2Text("");
            view.setPolResult("");
            view.setReminderText("");
        }
    }
}