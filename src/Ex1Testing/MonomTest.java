package Ex1Testing;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Polynom;
import Ex1.function;

class MonomTest {
	public static final Monom ZERO = new Monom(0,0);
	String[] monoms = {"5", "-x","-3.2x^2","-1.5x^3","2.5x^4","2.5*x^5"};
	Monom[] monomArr= new Monom[monoms.length];


	@BeforeEach
	void setUpBeforeClass() throws Exception {
		for(int i=0;i<monoms.length;i++){
			monomArr[i]=new Monom(monoms[i]);
			//System.out.println("polynom "+i+":"+polynomArr[i]);
		}
	}

	@Test
	void testDerivative() {
		Monom m=new Monom("2x^3");
		Monom mDer=new Monom("6x^2");
		assertEquals(mDer,m.derivative());
	}

	@Test
	void testF() {
		double[] fx0 = {5,0,0,0,0,0};
		double[] fx1 = {5,-2,-12.8,-12,40,80};

		for(int i=0;i<monoms.length;i++) {
			assertEquals(monomArr[i].f(0),fx0[i],0.001);
			assertEquals(monomArr[i].f(2),fx1[i],0.001);
		}
	}

	@Test
	void testIsZero() {
		for (int i = 0; i < monomArr.length; i++) {
			Monom d=new Monom("-1");
			d.multipy(monomArr[i]);
			monomArr[i].add(d);
			assertEquals(monomArr[i], ZERO);
		}
	}

	@Test
	void testEqualsObject() {//------------------------------------------to check
		for(int i=0;i<monomArr.length;i++) {
			monomArr[i]=new  Monom(monoms[i]);
			Monom m=new Monom(monomArr[i]);
			m.multipy(Monom.MINUS1);
			monomArr[i].add(m);
			assertEquals(monomArr[i],Monom.ZERO);//-------coff=0 poewr=real number 

		}
	}

	@Test
	void testAdd() {
		String[] arrS = {"5","0","-1.2x^2","1.5x^3","6.5x^4","7.5*x^5"};
		Monom[] arrM= new Monom[arrS.length];
		for (int i = 0; i < monomArr.length; i++) {
			arrM[i]=new Monom(arrS[i]);
			Monom m=new Monom(i,i);
			monomArr[i].add(m);
			assertEquals(monomArr[i], arrM[i]);
		}
	}

	@Test
	void testMultipy() {
		String[] arrS = {"5x^2", "-2x^4","-9.6x^6","-6x^8","12.5x^10","15*x^12"};
		Monom[] arrM= new Monom[arrS.length];
		for (int i = 0; i < monomArr.length; i++) {
			arrM[i]=new Monom(arrS[i]);
			Monom m=new Monom(i+1,i+2);
			monomArr[i].multipy(m);
			assertEquals(monomArr[i], arrM[i]);
		}
	}

	@Test
	void testToString() {
		for (int i = 0; i < monomArr.length; i++) {
			assertEquals(new Monom(monomArr[i].toString()),monomArr[i]);
		}
	}

	@Test
	void testInitFromString() {
		String[] arrS = {"0", "1","2x","3","4","55"};
		Monom tmp;
		for(int i=0;i<monoms.length;i++) {
			String s="4";
			Monom m=new Monom(0,0);
			try {
			tmp=(Monom) m.initFromString(s);
			}
			catch(Exception e) {
				fail("");
			}
			
		}
	}

	@Test
	void testCopy() {// 
		for(int i=0;i<monoms.length;i++) {
			Monom copyy =(Monom)monomArr[i].copy();
			//			monomArr[i].substract(copied);
			Monom d=new Monom("-1");
			copyy.multipy(d);
			monomArr[i].add(copyy);
			assertEquals(monomArr[i],ZERO);

		} 
	}

}
