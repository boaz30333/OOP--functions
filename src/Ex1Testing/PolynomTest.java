package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;

class PolynomTest {
	String[] polynoms = {"5", "-x","-3.2x^2","-1.5x^2","12x+5*x+x^2+0x^2-2.5x^3+4","10x+2.5*x^3"};
	Polynom[] polynomArr= new Polynom[polynoms.length] ;
	
	@BeforeEach
void setUpBeforeTest() throws Exception {
		for(int i=0;i<polynoms.length;i++) {
			polynomArr[i]=new  Polynom(polynoms[i]);
			//System.out.println("polynom "+i+":"+polynomArr[i]);
		}
	}

	@Test
	void testPolynom() {
		Polynom x=new Polynom(); 
		assertEquals(x.toString(),"0");
	}

	@Test
	void testPolynomString() {
		Polynom p1 = new Polynom(), p2 =  new Polynom();
		String[] monoms1 = {"2", "-x","-3.2x^2","4","-1.5x^2"};
		String[] monoms2 = {"5", "1.7x","3.2x^2","-3","-1.5x^2"};
		for(int i=0;i<monoms1.length;i++) {
			Monom m = new Monom(monoms1[i]);
			p1.add(m);
		}
		for(int i=0;i<monoms2.length;i++) {
			Monom m = new Monom(monoms2[i]);
			p2.add(m);
		}

		assertEquals(p1,new Polynom("-4.7x^2-1.0x+6.0")) ;
		assertEquals(p2,new Polynom("1.7x^2+1.7x+2"));
	}
	@Test
	void testToString() {
		for(int i=0;i<polynoms.length;i++) {
			polynomArr[i]=new  Polynom(polynoms[i]);
			assertEquals(polynomArr[i],new Polynom(polynomArr[i].toString()));

		}
	}
	@Test
	void testAddPolynom_able() {
		Polynom p1 =new Polynom("12x+5*x+x^2+0x^2-2.5x^3+4");
		Polynom p2 =new Polynom( "-7.5x^2+2x+17");
		p1.add(p2);
		Polynom expected= new Polynom("-2.5x^3+19x-6.5x^2+21");
		assertEquals(expected, p1);		
	}
	@Test
	void testMultiplyMonom() {
		Polynom p1 =new Polynom("x+2");
		Polynom p2 =new Polynom( "x-3");
		p1.multiply(p2);
		Polynom expected=new Polynom("x^2-x-6");
		assertEquals(expected, p1);
	}
	@Test
	void testSubstract() {
		for(int i=0;i<polynoms.length;i++) {
			polynomArr[i]=new  Polynom(polynoms[i]);
			polynomArr[i].substract(polynomArr[i]);
			assertEquals(polynomArr[i].toString(),"0");
		}	
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
	void testCopy() {
		for(int i=0;i<polynoms.length;i++) {
			polynomArr[i]=new  Polynom(polynoms[i]);
			Polynom copied =(Polynom)polynomArr[i].copy();
			polynomArr[i].substract(copied);
			assertEquals("0",polynomArr[i].toString());

		} 
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
	void testMultiplyPolynom_able() {
		Polynom excepted=new Polynom("x^2+5x+6");
		Polynom p1=new Polynom("x+2");
		Polynom p2 =new Polynom("x+3");
		p1.multiply(p2);
		assertEquals(excepted,p1);

	}
	

	@Test
	void testEqualsObject() {
		ComplexFunction cf1=new ComplexFunction("times","x+2","x+3");
		Polynom p1=new Polynom();
		p1.add(new Monom("x^2"));
		p1.add(new Polynom("5x+6"));
		function x =cf1;
		boolean b= p1.equals(x);
		assertEquals(true,b);
		
	}

	@Test
	void testEqualsPolynom() {
		for(int i=0;i<polynomArr.length;i++) {
			polynomArr[i].substract(polynomArr[i]);
			assertEquals("0",polynomArr[i].toString());

		}
	}



	@Test
	void testRoot() {
		Polynom[] polynoms = {new Polynom("3x^2-6x^3+9x-2"), new Polynom("x+5x+0-5"),new Polynom("4x^6-5x^5+1")};
		double[] res = {0.2135,0.83334,0.9999}; 
		for(int i=0;i<3;i++) {
		assertEquals(res[i] ,polynoms[i].root(0 ,1,0.0001),0.01) ;
		}
	}





	@Test
	void testDerivative() {
		Polynom p =new Polynom("12x+5*x+x^2+0x^2-2.5x^3+4");
		Polynom pDer =new Polynom("-7.5x^2+2x+17");
		assertEquals(pDer,p.derivative());
	}

	@Test
	void testArea() {
		
		Polynom[] polynoms = {new Polynom("3x^2-6x^3+9x-2"), new Polynom("x+5x+0-5"),new Polynom("4x^6-5x^5+1")};
		double[][] res = {{0,0.2135},{0,0.83334},{2.404,0.9999}}; 
		for(int i=0;i<3;i++) {
			double a = polynoms[i].area(-1 , 0 ,0.0001);
			assertEquals(res[i][0] ,a,0.01) ;
		}
	}




	@Test
	void testInitFromString() {
		Polynom tmp= new Polynom();
		for(int i=0;i<polynoms.length;i++) {
			tmp =new  Polynom(polynoms[i]);
		Polynom p=	(Polynom) tmp.initFromString(tmp.toString());
			assertEquals(p,tmp);

		}
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
}
