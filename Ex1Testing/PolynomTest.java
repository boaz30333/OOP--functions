package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.Monom;
import Ex1.Polynom;

class PolynomTest {
	String[] polynoms = {"5", "-x","-3.2x^2","-1.5x^2","12x+5*x+x^2+0x^2-2.5x^3+4","10x+2.5*x^3"};
	Polynom[] polynomArr= new Polynom[polynoms.length] ;
	@BeforeEach
void setUpBeforeTest() throws Exception {
		for(int i=0;i<polynoms.length;i++) {
			polynomArr[i]=new  Polynom(polynoms[i]);
			System.out.println("polynom "+i+":"+polynomArr[i]);
		}
	}

	@Test
	void testPolynom() {
		Polynom x=new Polynom(); // TODO
		assertEquals(x.toString(),"0");
	}

	@Test
	void testPolynomString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testF() {
		double[] fx0 = {5,0,0,0,4,0};	//f(0)
		double[] fx1 = {5,-1,-3.2,-1.5,19.5,12.5};	//f(1)
		for(int i=0;i<polynoms.length;i++) {
			polynomArr[i]=new  Polynom(polynoms[i]);
			assertEquals(polynomArr[i].f(0),fx0[i],0.001);
			assertEquals(polynomArr[i].f(1),fx1[i],0.001);
		}
	}

	@Test
	void testAddPolynom_able() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testAddMonom() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testSubstract() {
		for(int i=0;i<polynoms.length;i++) {
			polynomArr[i]=new  Polynom(polynoms[i]);
			polynomArr[i].substract(polynomArr[i]);
			assertEquals(polynomArr[i].toString(),"0");
		}
		 // TODO
	}

	@Test
	void testMultiplyPolynom_able() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testEqualsObject() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testEqualsPolynom() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testIsZero() {
		for(int i=0;i<polynoms.length;i++) {
			polynomArr[i]=new  Polynom(polynoms[i]);
			polynomArr[i].substract(polynomArr[i]);
			assertEquals(true,polynomArr[i].isZero());

		} 
	}

	@Test
	void testRoot() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testCopy() {
		for(int i=0;i<polynoms.length;i++) {
			polynomArr[i]=new  Polynom(polynoms[i]);
			Polynom copied =(Polynom)polynomArr[i].copy();
			polynomArr[i].substract(copied);
			assertEquals(true,polynomArr[i].isZero());

		} // TODO
	}

	@Test
	void testToString() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testDerivative() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testArea() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testIteretor() {
		for(int i=0;i<polynoms.length;i++) {
			polynomArr[i]=new  Polynom(polynoms[i]);
			Polynom sub= (Polynom) polynomArr[i].copy();
			Iterator<Monom> iter = sub.iteretor();
			Monom itr;
			while(iter.hasNext()) {
				itr=(Monom)iter.next();
				itr.multipy(Monom.MINUS1);
				polynomArr[i].add(itr);
			}
			assertEquals(true,polynomArr[i].isZero());	
		} 
	}

	@Test
	void testMultiplyMonom() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testInitFromString() {
		fail("Not yet implemented"); // TODO
	}

}
