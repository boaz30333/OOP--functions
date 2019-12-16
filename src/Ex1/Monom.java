
package Ex1;

import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz sharabi
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 *
	 * @return  this method return the coefficient monom of this.
	 */
	public double get_coefficient() {
		return this._coefficient;
	}
	/**
	 * 
	 * @return this method return the power monom of this.
	 */
	public int get_power() {
		return this._power;
	}
	/** 
	 * 
	 * @return this method returns the derivative monom of this.
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}

	/**
	 * 
	 * @param x The variable you want to substitute in this monom .
	 *
	 * 
	 * @return this method returns the monom value dependent on x.
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	} 
	
	/**
	 * 
	 * @return return if this monom is a zero monom ("0").
	 */
	public boolean isZero() {return Math.abs(this.get_coefficient()) <EPSILON;}
	// ***************** add your code below **********************

	/**
	 * this constructor build monom from input String s.
	 * @param s the string represent the monom
	 */
	public Monom(String s) {
		s=s.toLowerCase();
		s = s.replaceAll("\\s","");
		if (s=="") {
			this._coefficient=0;
			this._power=0;
			return;
		}
		if(!(s.contains("x"))){                      // the monom is a number
			try {
				double coefficient = Double.parseDouble(s);
				this._coefficient=coefficient;             
				this._power=0;
				return;
			}
			catch(Exception e) {
				throw new RuntimeException("invalid String for monom constructor:"+s+" - if thre are no 'x' the string must be just num");
			}
		}
		int indx= s.indexOf("x");                    // the monom contain x parameter

		int x=0;
		if(s.contains("*")) x++;                    //  there is a char between the 'x' to the coefficient 
		if(s.charAt(0)=='-'&& s.charAt(1)=='x' ) 
			this._coefficient=-1; 
		else if(s.charAt(0)=='x')
			this._coefficient=1; 
		else {
			try {
				this._coefficient=Double.parseDouble(s.substring(0, indx-x));
			}
			catch(Exception r) { 
				throw new RuntimeException("invalid String for monom constructor:"+s+"the coefficient invalid");

			}}

		if(!(s.contains("^"))){                     // there isn't char between the 'x' to the power

			if(s.indexOf('x')==s.length()-1)
				this._power=1;
			else 
				throw new RuntimeException("input error:"+s+" if there are power so after the x must be '^' and than Integer");
		}
		else {
			try {
				this._power=Integer.parseInt(s.substring((indx+2)));
			}
			catch(Exception p) {
				throw new RuntimeException("invalid String for monom constructor:"+s+" if there are power so after the x must be '^' and than Integer");
			}
		}


	}
	public boolean equalsMonom(Monom obj) {
		Monom p2 = (Monom) obj.copy();
if(Math.abs(p2.get_coefficient()-0)<0.0001 && Math.abs(this.get_coefficient()-0)<0.0001) 		return true;
else if(Math.abs(p2.get_coefficient()-this.get_coefficient())<0.0001 &&this.get_power()==p2.get_power())return true;
return false;
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Polynom_able) {
			return equalsMonom( new Monom(obj.toString())) ;

		}
		else if (obj instanceof Monom) {
			return equalsMonom((Monom)obj);
		}
		else if(obj instanceof ComplexFunction) {
			ComplexFunction cf1=(ComplexFunction)obj;
			double x;
			for(double i=-1;i<1;i=i+0.1) {
				x=this.f(i)-cf1.f(i);
				if(Math.abs(x)>0.001) return false;
			}
			return true;
		}
		else
			return false;
	}	
	
	/**
	 * 
	 * @param m this method add monom 'm' to this monom 
	 */
	public   void add(Monom m) {
		if(this._power==m._power)
			this._coefficient+=m._coefficient;
		else
			throw new RuntimeException("can\"t add monom with diffrent power: this monom="+this+"the addition monom="+m); 
	}
	
	/**
	 * 
	 * @param d this method multiply this monom by monom 'd' 
	 */
	public void multipy(Monom d) {
		this._coefficient= this._coefficient*d._coefficient;
		this._power+=d._power;
	}
	/**
	 * 
	 * @return  this method return String that represent this monom
	 */
	@Override
	public String toString() {
		String ans ;
		if(this._coefficient==0) return "+0";
		if(this._power==0) ans= ""+this._coefficient;
		else if(this._power==1)
			ans = ""+this._coefficient+"x";
			else
			ans = ""+this._coefficient+"x^"+this._power;
		if(this._coefficient>0) ans= "+"+ans;
		return ans;
	}

	//******************  *****************
	/**
	 * 
	 * @param a is a diffrent monom
	 *
	 * @return   this method return if this monom is the same like monom a;
	 */
//	public boolean equals(Monom a) {
//		double num =Math.abs(a._coefficient-this._coefficient);
//		if(num<EPSILON && this.get_power() == a.get_power()) return true;
//		else
//			return false;
//	}
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;
	@Override
	public function initFromString(String s) {
		Monom m = new Monom(s);
		return m;
	}
	@Override
	public function copy() {
		// TODO Auto-generated method stub
		return new Monom(this.get_coefficient(),this.get_power());
	}
	
	
}
