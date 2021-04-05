package View;

import Model.Model;
import Model.Polynomial;
import javax.swing.*;
import java.awt.event.ActionListener;

public class View {

    private final JLabel reminderLabel;
    private final JTextField remResult;
    private final JTextField polResult;
    private final JTextField p1Text;
    private final JTextField p2Text;
    private final JButton addition;
    private final JButton subtraction;
    private final JButton multiplication;
    private final JButton division;
    private final JButton derivative;
    private final JButton integration;
    private final JButton delP1;
    private final JButton delP2;

    public View() {

        int height = 523;
        int width = 600;
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel p1 = new JLabel();
        JLabel p2 = new JLabel();
        JLabel resultLabel = new JLabel();

        panel.setLayout(null);

        p1Text = new JTextField();
        p2Text = new JTextField();
        addition = new JButton();
        subtraction = new JButton();
        multiplication = new JButton();
        division = new JButton();
        derivative = new JButton();
        integration = new JButton();
        polResult = new JTextField();
        reminderLabel = new JLabel();
        remResult = new JTextField();
        delP1 = new JButton();
        delP2 = new JButton();

        frame.setTitle("Polynomial Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(width, height);

        p1.setLocation(150, 50);
        p1.setSize(80, 40);
        p1.setText("First Polynomial:");

        p2.setLocation(150, 150);
        p2.setSize(100, 40);
        p2.setText("Second Polynomial:");

        p1Text.setLocation(150, 100);
        p1Text.setSize(200, 20);
        p1Text.setEditable(true);

        delP1.setLocation(375, 100);
        delP1.setSize(135, 20);
        delP1.setText("Delete polynomial");

        p2Text.setLocation(150, 200);
        p2Text.setSize(200, 20);
        p2Text.setEditable(true);

        delP2.setLocation(375, 200);
        delP2.setSize(135, 20);
        delP2.setText("Delete polynomial");

        resultLabel.setLocation(150, 250);
        resultLabel.setSize(200, 20);
        resultLabel.setText("Result:");

        polResult.setLocation(150, 300);
        polResult.setSize(200, 20);

        reminderLabel.setVisible(false);
        reminderLabel.setLocation(150, 350);
        reminderLabel.setSize(150, 20);
        reminderLabel.setText("Reminder");

        remResult.setVisible(false);
        remResult.setLocation(150, 400);
        remResult.setSize(200,20);

        addition.setLocation(0, 0);
        addition.setSize(110, height /6);
        addition.setText("Addition");

        subtraction.setLocation(0, 80);
        subtraction.setSize(110, height /6);
        subtraction.setText("Subtraction");

        multiplication.setLocation(0, 160);
        multiplication.setSize(110, height /6);
        multiplication.setText("Multiplication");

        division.setLocation(0, 240);
        division.setSize(110, height /6);
        division.setText("Division");

        derivative.setLocation(0, 320);
        derivative.setSize(110, height /6);
        derivative.setText("Derivative");

        integration.setLocation(0, 400);
        integration.setSize(110, height /6);
        integration.setText("Integration");

        panel.add(p1);
        panel.add(p2);
        panel.add(p1Text);
        panel.add(p2Text);
        panel.add(addition);
        panel.add(subtraction);
        panel.add(multiplication);
        panel.add(division);
        panel.add(derivative);
        panel.add(integration);
        panel.add(resultLabel);
        panel.add(polResult);
        panel.add(reminderLabel);
        panel.add(remResult);
        panel.add(delP1);
        panel.add(delP2);

        frame.add(panel);

        frame.setVisible(true);
    }

    public void setRemVisibility(boolean bool) {

        remResult.setVisible(bool);
    }

    public void setReminderLabelVisibility(boolean bool) {

        reminderLabel.setVisible(bool);
    }

    public void setP1Text(String str) {

        p1Text.setText(str);
    }

    public String getP1Text() {

        return p1Text.getText();
    }

    public void setP2Text(String str) {

        p2Text.setText(str);
    }

    public String getP2Text() {

        return p2Text.getText();
    }

    public void setReminderText(String str) {

        remResult.setText(str);
    }

    public void setPolResult(String str) {

        polResult.setText(str);
    }

    public void additionAction(ActionListener l) {

        addition.addActionListener(l);
    }

    public void subtractionAction(ActionListener l) {

        subtraction.addActionListener(l);
    }

    public void multiplicationAction(ActionListener l) {

        multiplication.addActionListener(l);
    }

    public void derivativeAction(ActionListener l) {

        derivative.addActionListener(l);
    }

    public void integrationAction(ActionListener l) {

        integration.addActionListener(l);
    }

    public void divisionAction(ActionListener l) {

        division.addActionListener(l);
    }

    public void delP1Action(ActionListener l) {

        delP1.addActionListener(l);
    }

    public void delP2Action(ActionListener l) {

        delP2.addActionListener(l);
    }
}