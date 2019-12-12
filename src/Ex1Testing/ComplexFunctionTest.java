package Ex1Testing;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Function;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Ex1.ComplexFunction;
import Ex1.Monom;
import Ex1.Operation;
import Ex1.Polynom;
import Ex1.function;

class ComplexFunctionTest {
	function cf;
	function cf1;
	function cf2;
	function cf3;
	function cf4;
	function cf5 ;
	function p1 ;
	function p2 ;
	function m1;
	ComplexFunction cf6;
	@BeforeEach
	void setUpBeforeEach() {
		cf=cf1=cf2=cf3=cf4=cf5=null;
		try {
			p1=new Polynom("2x^3+1.5x^2+5");
			p2= new Polynom("5x-5");
		}
		catch(Exception e){
			throw new RuntimeException("cant build the Polynoms p1,p2 for tests please check this polynom or polynom class");
		}
		try {
			m1=new Monom("-3x^5");

		}
		catch(Exception e){
			throw new RuntimeException("cant build the Monom m1 for tests please check this polynom or polynom class");
		}
	}

	@Test
	void testComplexFunction_From_Function() {
		try {
			cf1=new ComplexFunction(p1);
		}
		catch(Exception E) {
			fail("cant build ComplexFunction from legal Polynom");
		}
		try {
			cf2=new ComplexFunction(m1);
		}
		catch(Exception E) {
			fail("cant build ComplexFunction from legal Monom");
		}
		try {
			cf3=new ComplexFunction(cf1);
		}
		catch(Exception E) {
			fail("cant build ComplexFunction from legal ComplexFunction");
		}

	}

	@Test
	void testComplexFunctionFrom_String_Function_Function() {
		testComplexFunction_From_Function();
		try {
			cf3=new ComplexFunction("plus",cf1,cf2);
		}
		catch(Exception E) {
			fail("cant build ComplexFunction from legal phrse check ComplexFunction_From_Function() first or this constructor o");
		}
	}


	@Test
	void testComplexFunctionFrom_String_String_String() {
		String op="plus";
		String left="x+5";
		String right="-x";
		try {
			cf4=  new ComplexFunction(op,left,right) ;
			System.out.println(cf4);
		}
		catch(Exception e) {
			fail("cant build  new ComplexFunction ("+op+","+left+","+right+")");
		}
	}

	@Test
	void testF() {
		double x=0;
		double[] value = {1,2,3,4,5,0};
		for(int i =0; i<value.length;i++) {
		cf6= new ComplexFunction(p1);
		assertEquals(cf6.f(value[i]), p1.f(value[i]));                 
		x=cf6.f(value[i]);
		cf6.plus(m1);
		x= x+m1.f(value[i]);                                  //plus
		assertEquals( x,cf6.f(value[i]),"program faild in f(x) function- plus");
		ComplexFunction cf7= (ComplexFunction) cf6.copy();
		cf6.comp(p1);                                    //comp
		assertEquals(cf7.f(p1.f(value[i])),cf6.f(value[i]),"program faild in f(x) function- comp");
		x=cf6.f(value[i]);                                     //mul
		cf6.mul(m1);
		x=x*m1.f(value[i]);
		assertEquals(x,cf6.f(value[i]),"program faild in f(x) function- mul");
		cf6.div(p1);                                    //div
		try {
		x=x/p1.f(value[i]);                      
		}
		catch (ArithmeticException e) { //A failed
			try {                             
				x= cf6.f(value[i]);
				fail("program faild in f(x) function- divid by zero"); // A failed B success =  failed
			}
			catch(ArithmeticException d) {//  A failed B failed =success
			}
		}
		try {
			assertEquals(x,cf6.f(value[i]),"program faild in f(x) function- div"); //A success  => A==b?
		}
		catch(ArithmeticException d) {                                 // B failed so A!=B
			fail("program faild in f(x) function- divid by zero");
		}

		cf6.max(m1);
		x= Math.max(x, m1.f(value[i]));                         // max
		assertEquals(x, cf6.f(value[i]),"program faild in f(x) function- max");
		cf6.min(p1);
		x= Math.min(x,p1.f(value[i]));                         // min
		assertEquals(x, cf6.f(value[i]),"program faild in f(x) function- min");
	}
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
	}



	@Test
	void testCopy() {
		cf= new ComplexFunction(p1);
		cf1= cf.copy();
		assertEquals(cf, cf1);
		((Polynom)p1).add(new Monom("x"));  // cause deep copy done cf and cf1 not depand on p1 changes
		assertEquals(cf, cf1);


	}


	@Test
	void testPlus() {
		cf1= new ComplexFunction(p1) ;
		((ComplexFunction)cf1).plus(m1);
		assertEquals(new ComplexFunction("plus",p1,m1),cf1);
	}

	@Test
	void testMul() {
		cf1= new ComplexFunction(p1) ;
		((ComplexFunction)cf1).mul(m1);
		assertEquals(new ComplexFunction("times",p1,m1),cf1);
	}

	@Test
	void testDiv() {
		cf1= new ComplexFunction(p1) ;
		((ComplexFunction)cf1).div(m1);
		cf2=new ComplexFunction("divid",p1,m1);
		boolean b= cf2.equals(cf1);
		System.out.println(b);
		assertEquals(cf2,cf1);

	}

	@Test
	void testMax() {
		cf1= new ComplexFunction(p1) ;
		((ComplexFunction)cf1).max(m1);
		assertEquals(new ComplexFunction("max",p1,m1),cf1); 
	}

	@Test
	void testMin() {
		cf1= new ComplexFunction(p1) ;
		((ComplexFunction)cf1).min(m1);
		assertEquals(new ComplexFunction("min",p1,m1),cf1); 
	}

	@Test
	void testComp() {
		cf1= new ComplexFunction(p1) ;
		((ComplexFunction)cf1).comp(m1);
		assertEquals(new ComplexFunction("comp",p1,m1),cf1);
	}

	@Test
	void testLeft() {
		cf1= new ComplexFunction(p1);
		assertEquals(p1,((ComplexFunction)cf1).left());
	}

	@Test
	void testRight() {

		cf1= new ComplexFunction(p1);
		System.out.println(cf1);
		if(((ComplexFunction)cf1).right()!= null)
			fail("");
	}

	@Test
	void testGetOp() {
		String[] ops= { "plus","Plus","PLUS","Times","mul", "comp", "Comp"};
		for(int i =0;i<ops.length; i++) {
			Operation op= ComplexFunction.getop(ops[i]);
			assertNotEquals(Operation.Error, op);
		}
	}




	@Test
	void testToString() {
		cf6=new ComplexFunction(p1);
		cf1= cf6.initFromString("max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)");
		System.out.println(cf1);
		cf2=cf6.initFromString(cf1.toString());
		assertEquals(cf2,cf1);
	}

}
