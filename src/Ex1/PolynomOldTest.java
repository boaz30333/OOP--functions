package Ex1;

public class PolynomOldTest {
	public static void main(String[] args) {

		test1();
		test2();
		test3();//equals and add and string construactor
		test4(); //build polynom from string and make sub
		test5();// derivative
		test6(); //string constructor
		test7(); //add polynom
		test8(); // multyply
		test9(); // is zero
		test10();//roots
		test11();//area
		test12(); //build copy
		test13(); // f(x)
	}
	public static void test1() {
		Polynom p1 = new Polynom();
		String[] monoms = {"1","x","x^2", "0.5x^2"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
			System.out.println(p1);
		}
		p1.substract(p1);
		System.out.println(p1);

	}
	public static void test2() {
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
		System.out.println("p1: "+p1);
		System.out.println("p2: "+p2);
		p1.add(p2);
		System.out.println("p1+p2: "+p1);
		p1.multiply(p2);
		System.out.println("(p1+p2)*p2: "+p1);
		String s1 = p1.toString();
		Polynom_able pp1 =new Polynom(s1);
		System.out.println("from string: "+pp1);
	}
	public static void test3() {  // equals and add and string construactor
		System.out.println("test 3 \n build polynom from add monoms , equals and add and string construactor");
		Polynom p1 = new Polynom();
		String[] monoms = {"12x","5.2*x","x^2", "0x^2"};
		for(int i=0;i<monoms.length;i++) {
			Monom m = new Monom(monoms[i]);
			p1.add(m);
		}
		System.out.println(p1.equals(new Polynom("x^2+17.2x")));
		System.out.println("---------------------------");

	}
	public static void test4() {  // sub 
		System.out.println("test 4 \n build polynom from string and make sub");
		Polynom x =new Polynom("12x+5*x+x^2+0x^2-2.5x^3");
		System.out.println("p1="+x);
		Polynom y =new Polynom( "12*x+7x-x^2+0+4-2.5*x^3+0");
		System.out.println("p2="+y);
		x.substract(y);
		System.out.println("p1-p2="+x);
		System.out.println(" suppoesed equal to: -2x +2x^2-4");
		System.out.println("---------------------------");
	}
	public static void test5() {  // derivative
		System.out.println("test 5 \n build polynom from string and make derivative");
		Polynom x =new Polynom("12x+5*x+x^2+0x^2-2.5x^3+4");
		System.out.println("p1="+x);
		Polynom y =new Polynom( "-7.5x^2+2x+17");
		System.out.println(" derivative supposed to be"+y);
		Polynom der= (Polynom) x.derivative();
		System.out.println("x after derivative"+der);
		System.out.println(der.equals(y));
		System.out.println("---------------------------");
	}
	public static void test6() {  //  string construactor
		System.out.println("test 6 \n build polynom from string");
		try {
			Polynom invalid = new Polynom("-x^2.0+5");
			System.out.println("the polynom supposed to throw exeption");
		}
		catch(Exception e){
			System.out.println("the program faild : invalid string input");
		}
		Polynom p2 = new Polynom("10x+2.5*x^3");
		Polynom p3 = new Polynom("+x^2+0");
		Polynom p4 = new Polynom("-3");
		System.out.println("its string to build: 10x+2.5*x^3 | +x^2+0 | -3");
		System.out.println("this polynom to string: "+p2+" | "+p3+" | "+p4);

		System.out.println("---------------------------");

	}
	public static void test7() {  // add polynom
		System.out.println("test 5 \n build polynom from string and add to this other polynom");
		Polynom x =new Polynom("12x+5*x+x^2+0x^2-2.5x^3+4");
		System.out.println("p1="+x);
		Polynom y =new Polynom( "-7.5x^2+2x+17");
		System.out.println("p2="+y);
		x.add(y);
		Polynom expected= new Polynom("-2.5x^3+19x-6.5x^2+21");
		System.out.println("actual: p1 after adding p2 is: "+x);
		System.out.println("expected: p1 after adding p2 is: "+expected);
		System.out.println("is equals? "+ x.equals(expected));
		System.out.println("---------------------------");
	}
	public static void test8() {  // multyply
		System.out.println("test 5 \n build polynom from string and multiply this by other polynom");
		Polynom x =new Polynom("x+2");
		System.out.println("p1="+x);
		Polynom y =new Polynom( "x-3");
		System.out.println("p2="+y);
		x.multiply(y);
		Polynom expected= new Polynom("x^2-x-6");
		System.out.println("actual: p1 after multiply by p2 is: "+x);
		System.out.println("expected: p1 after  multiply by p2 is: "+expected);
		System.out.println("is equals? "+ x.equals(expected));
		System.out.println("---------------------------");
	}
	public static void test9() {  // is zero
		System.out.println("test 5 \n  is zero");
		Polynom x =new Polynom("0");
		System.out.println("p1= "+x);
System.out.println("p1 is zero? "+x.isZero());
		System.out.println("---------------------------");
	}
	public static void test10() {  // roots
		System.out.println("test 5 \n roots");
		Polynom x =new Polynom("x+2");
		System.out.println("p1="+x);
		Polynom y =new Polynom( "x-3");
		System.out.println("p2="+y);
		x.multiply(y);
		System.out.println("p1*p2:" +x );
		double root1= x.root(-3, 0, 0.1);
		double root2= x.root(0, 5, 0.1);
		System.out.println("actual:root1: "+root1+"  root2: "+root2);
		System.out.println("expected: root1: -2  root2: 3  eps: 0.1");
		System.out.println("---------------------------");
	}
	public static void test11() {  // area
		System.out.println("test 5 \n area");
		Polynom y =new Polynom( "-7.5x^2+2x+17");
		System.out.println("p1: "+y);
		double area= y.area(-5,5,0.001);
		System.out.println("riemman integral of p1: "+area);
		System.out.println("expected:-455 ");	
		System.out.println("---------------------------");
	}
	public static void test12() {  // copy
		System.out.println("test 5 \n build copy");
		Polynom y =new Polynom( "-7.5x^2+2x+17");
		System.out.println("p1: "+y);
		Polynom copy= (Polynom)y.copy();
		System.out.println("copy of p1 after copy func:"+copy);
		System.out.println("equals? "+copy.equals(y));
		System.out.println("---------------------------");
	}
	public static void test13() {  // f(x)
		System.out.println("test 5 \n f(x)");
		Polynom y =new Polynom( "-7.5x^2+2x+17");
		System.out.println("p: "+y);
		System.out.println("x = -1.378");
		System.out.println(" f(x) supposed to be 0");
		System.out.println("f(x)= "+y.f(-1.3781) +(y.f(-1.3781)<0.001)) ;
		System.out.println("---------------------------");
	}
	
}
