package Ex1Testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTest {
	ComplexFunction cf;
	ComplexFunction cf1;
	ComplexFunction cf2;
	ComplexFunction cf4;
	ComplexFunction cf5 ;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@Test
	void testComplexFunctionFunction() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testComplexFunctionStringFunctionFunction() {
		fail("Not yet implemented"); // TODO
	}


	@Test
	void testComplexFunctionStringStringString() {
		try {
		cf4= new ComplexFunction("plus","x+5","-x") ;
		System.out.println(cf4);// TODO
		}
		catch(Exception e) {
			fail("cant build new ComplexFunction(\"plus\",\"x+5\",\"-x\")");
		}
	}

	@Test
	void testF() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testInitFromString() {
		function f1;
		function f2;
		String s1 = "3.1 +2.4x^2 -x^4";
		Polynom p1 = new Polynom(s1);
		f1=new ComplexFunction(p1);
		f2=f1.initFromString(s1);
		assertEquals(f1, f2);  // f1=f2=p1 cause all function from same string
		assertEquals(f1, p1);
//		String[] s3 = {"x +3","x -2", "x -4"};
//		Polynom p1 = new Polynom(s1);
//		Polynom p2 = new Polynom(s2);
//		Polynom p3 = new Polynom(s3[0]);
//		ComplexFunction cf3 = new ComplexFunction(p3);
//		System.out.println(cf3);
//		for(int i=1;i<s3.length;i++) {
//			cf3.mul(new Polynom(s3[i]));
//		}
//		System.out.println(cf3);
		
//		ComplexFunction cf = new ComplexFunction("plus", p1,p2);
//		System.out.println(cf);
//		ComplexFunction cf4 = new ComplexFunction("div", new Polynom("x +1"),cf3);
//		System.out.println(cf4);
//		cf4.plus(new Monom("2"));
//		System.out.println(cf4);
//	//	ans.add(cf.copy());
//	//	ans.add(cf4.copy());
//		//cf.div(p1);
//	//	ans.add(cf.copy());
//		String s = cf.toString();
//		function cf5 = cf4.initFromString(s1);
//		System.out.println(cf5);
//		function cf6 = cf4.initFromString(s2);
//		System.out.println(cf6);
//	//	ans.add(cf5.copy());
//	//	ans.add(cf6.copy()); // TODO
	}

	@Test
	void testGetop() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testCopy() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testPlus() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testMul() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testDiv() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testMax() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testMin() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testComp() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testLeft() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testRight() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	void testGetOp() {
		String[] ops= { "plus","Plus","PLUS","Times", "comp", "Comp"};
		for(int i =0;i<ops.length; i++) {
		Operation op= ComplexFunction.getop(ops[i]);
		assertNotEquals(Operation.Error, op);
	}// TODO
	}




	@Test
	void testToString() {
		fail("Not yet implemented"); // TODO
	}

}
