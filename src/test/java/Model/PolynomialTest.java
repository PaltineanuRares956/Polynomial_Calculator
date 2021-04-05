package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialTest {

    Model model = new Model();

    @Test
    void testDerivative() {

        Polynomial p = model.parsePolynomial("3x^7-2x+5-4x^3+5x^7");
        p.derivative();

        assertEquals("+56x^6-12x^2-2", p.getRepresentation());
    }

    @Test
    void testIntegration() {

        Polynomial p = model.parsePolynomial("2x^3+5x-2");
        p.integration();

        assertEquals("+0.5x^4+2.5x^2-2x", p.getRepresentation());
    }

    @Test
    void testAddition() {

        Polynomial p1 = model.parsePolynomial("-3x^4+7x^6-2x^3+15+4x-2x^4-7");
        Polynomial p2 = model.parsePolynomial("3-x^4+3x^2-12x");
        p1.addition(p2);

        assertEquals("+7x^6-6x^4-2x^3+3x^2-8x+11", p1.getRepresentation());

        p1 = model.parsePolynomial("-x+x-x+x-x+x-x+x-x-x-2");
        p2 = model.parsePolynomial("x^2-1");
        p1.addition(p2);
        assertEquals("+x^2-2x-3", p1.getRepresentation());
    }

    @Test
    void testSubtraction() {

        Polynomial p1 = model.parsePolynomial("-3x^4+7x^6-2x^3+15+4x-2x^4-7");
        Polynomial p2 = model.parsePolynomial("3-x^4+3x^2-12x");
        p1.subtraction(p2);

        assertEquals("+7x^6-4x^4-2x^3-3x^2+16x+5", p1.getRepresentation());
    }

    @Test
    void testDivision() {
        Polynomial p1 = model.parsePolynomial("3x^5+4x^3-5x+8");
        Polynomial p2 = model.parsePolynomial("x^2+3");

        Polynomial p3 = new Polynomial(p1.division(p2));

        assertEquals("+3x^3-5x", p3.getRepresentation());
        assertEquals("+10x+8", p1.getRepresentation());
    }

    @Test
    void testMultiplication() {

        Polynomial p1 = model.parsePolynomial("x+1");
        Polynomial p2 = model.parsePolynomial("x-1");

        p1.multiplication(p2);

        assertEquals("+x^2-1", p1.getRepresentation());
    }


    @Test
    void testCheckPolString() {

        assertTrue(Polynomial.checkPolString("3x^2-5x+12-"));
        assertFalse(Polynomial.checkPolString("-7x^3+12x^4-12+x-3x^4"));
    }

}