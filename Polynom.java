
package Ex1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.function.Predicate;

import javax.management.RuntimeErrorException;

import Ex1.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able{

	HashMap< Integer,Monom> monoms=new HashMap< Integer,Monom>();
	;
	/**
	 * Zero (empty polynom)
	 */
	public Polynom() {
		this.monoms.put(0, Monom.ZERO);


		;
	}
	/**
	 * init a Polynom from a String such as:
	 *  {"-x", "x+x", "x^2", "-1"};
	 * @param s: is a string represents a Polynom
	 */
	public Polynom(String s) {
		HashMap< Integer,Monom> monoms = new HashMap< Integer,Monom>();
		s.toLowerCase();
		Monom x;
		if(s.length()==1) {                     // the polynom is just x or integer num
			try {
				x= new Monom(s);
				monoms.put(x.get_power(),x);
			}
			catch(Exception e) {
				throw new RuntimeException("invalid string if the string is only 1 char it should be num or 'x'");
			}
		}
		else {
			for(int i=1; i<s.length()-1 ; i++) {            
				Monom y;
				String d = new String(s);
				if( s.charAt(i)=='-'|| s.charAt(i)=='+') {
					if(s.charAt(0)!='+')
						try {
							y= new Monom(s.substring(0, i));
						}
					catch(Exception e) {
						throw new RuntimeException("invalid string : the string  contain invalid monom to build (locate between the chars + or -) is: "+s.substring(0, i)+" in String:"+d);
					}
					else
						try {
							y= new Monom(s.substring(1, i));
						}
					catch(Exception e) {
						throw new RuntimeException("invalid string : the string  contain invalid monom to build (locate between the chars + or -) is: "+s.substring(1, i)+" in String:"+d);
					}
					this.add(y);
					s=s.substring(i);
					i=1;
				}





			}

			Monom y= new Monom(s);
			this.add(y);	
		}}
	/**
	 * @param x The variable you want to substitute in this polynom.
	 * @return this method returns the polynom value  dependent on x.
	 */
	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		double sum=0;
		Iterator<Monom> iter= this.iteretor();
		while(iter.hasNext()) {
			Monom b= (Monom) iter.next();
			sum+= b.f(x);

		}
		return sum;
	}
	/**
	 * @param p1 the addition polynom
	 * this method add p1 polynom to this polynom
	 */
	@Override
	public void add(Polynom_able p1) {
		// TODO Auto-generated method stub
		Iterator<Monom> iter= p1.iteretor();
		while(iter.hasNext()) {
			Monom b= (Monom) iter.next();
			this.add(b);
		}
	}
	/**
	 * @param m1 the addition monom
	 * this method add m1 monom to this polynom
	 */
	@Override
	public void add(Monom m1) {
		// TODO Auto-generated method stub
		//try to add to all object if at LAST ITS FALSE ENTER HIM SPECIPIC
		if(m1.get_coefficient()!=0) {
			if(this.monoms.containsKey((m1.get_power())) ){
				Monom x=new Monom(m1.get_coefficient()+this.monoms.get(m1.get_power()).get_coefficient(),m1.get_power());
				this.monoms.replace(m1.get_power(),x );
			}
			else monoms.put(m1.get_power(), m1);
		}
	}

	@Override
	public void substract(Polynom_able p1) {
		// TODO Auto-generated method stub
		Polynom_able B= p1.copy();
		Iterator<Monom> iter= B.iteretor();
		while(iter.hasNext()) {
			Monom c= (Monom) iter.next();
			Monom b = new Monom(c);
			if(this.monoms.containsKey(c.get_power())&&this.monoms.get(c.get_power()).get_coefficient()==c.get_coefficient() )
				this.monoms.remove(c.get_power());
			else {
				b.multipy(new Monom(-1,0));
				this.add(b);
			}
		}
		if(this.monoms.isEmpty()) this.monoms.put(0,Monom.ZERO);

	}

	@Override
	public void multiply(Polynom_able p1) {
		// TODO Auto-generated method stub
		Polynom pAns =new Polynom();	
		Polynom p2 ;
		Iterator<Monom> iter= p1.iteretor();
		while(iter.hasNext()) {
			p2 = (Polynom) this.copy();
			Monom b= (Monom) iter.next();
			p2.multiply(b);
			pAns.add(p2);
		}
		this.monoms=pAns.monoms;
	}
	/**
	 * @param p1 other polynom to compare
	 * @return return true if the other polynom equals to this polynom
	 */
	@Override
	public boolean equals(Object p1) {
		// TODO Auto-generated method stub
if(!(p1 instanceof Polynom_able)) {
	return false;
}
		Polynom_able p2 = ((Polynom_able) p1).copy();
		p2.substract(this);
		if(!p2.isZero())	return false;
		return true;
	}
	/**
	 *  
	 * @return true if this polynom= 0
	 */
	@Override
	public boolean isZero() {
		// TODO Auto-generated method stub
		Iterator<Monom> iter= this.iteretor();
		while(iter.hasNext()) {
			Monom b= (Monom) iter.next();
			if(!b.isZero()) return false;
		}
		return true;
	}
	/**
	 * @param x0 the left x point
	 *  @param x1  the right x point
	 * @param eps  approximation of the answer to 0 
	 * @return this method return the roots(the x value that if we  will substitute in polynomial and get 0) between x0 and x1
	 */
	@Override
	public double root(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		if(f(x0)*f(x1)>0) 
			throw new RuntimeException("f(x0) and f(x1) should be sign difference"); 
		if(x1<x0) 
			throw new RuntimeException("x1 should be sign bigger than x0");


		double x ;
			do{

				
				x = x0 + ((x1-x0)/2);

			
				if(((f(x0) > 0) && (f(x) > 0)) || ((f(x0) < 0) && (f(x) < 0)))
					x0= x;
				else 
					x1= x;
			} while(Math.abs(f(x))>eps);
			
		return x;
	}
	/**
	 * 
	 * @return return new Polynom  like this polynom
	 */
	@Override
	public Polynom_able copy() {
		// TODO Auto-generated method stub
		Polynom p = new Polynom();
		Iterator<Monom> iter= this.iteretor();
		while(iter.hasNext()) {
			Monom b=new Monom( (Monom) iter.next());
			p.add(b);
		}
		return p;
	}
	/**
	 * 
	 * @return this method return String that represent this monom
	 */
	@Override
	public String toString() {
		String s="";
		Iterator<Monom> iter= this.iteretor();
		while(iter.hasNext()) {
			Monom b= (Monom) iter.next();
			if(!b.equals(new Monom(0,0)))
				s=b.toString()+s;
		}
		if(s=="") return "0";
		return s;
	}
	/** 
	 * @return polynom that represent the derivative of this polynom
	 */
	@Override
	public Polynom_able derivative() {
		// TODO Auto-generated method stub
		Polynom p= new Polynom();
		Iterator<Monom> iter= this.iteretor();
		while(iter.hasNext()) {
			Monom b= (Monom) iter.next();
			p.add(b.derivative());
		}
		return p;
	}
	/**
	 * Compute a Riman's integral from x0 to x1 in eps steps. 
	 * @param x0 starting pooint
	 * @param x1 end point
	 * @param eps positive step value
	 * @return the approximated area above X-axis below this function bounded in the range of [x0,x1]
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		// TODO Auto-generated method stub
		double sum=0;
		double a ;
		while(x0<x1) {
			a= f(x0) ;
			sum+= a*eps;
			x0+= eps;
		}
		return sum;
	}
	/**
	 * 
	 * @return iteretor object with which we can go over the values ​​on the map
	 */
	@Override
	public Iterator<Monom> iteretor() {
		// TODO Auto-generated method stub
		Iterator<Monom> pass = this.monoms.values().iterator();
		return pass;
	}
	/**
	 * @param m1 monom
	 * this method multiply this polynom by monom
	 */
	@Override
	public void multiply(Monom m1) {
		// TODO Auto-generated method stub
		Polynom_able tmp = this.copy();
		Polynom ans = new Polynom();
		Iterator<Monom> iter= tmp.iteretor();
		Monom itr;
		while(iter.hasNext()) {
			itr=(Monom)iter.next();
			itr.multipy(m1);
			ans.add(itr);
		}
		this.monoms=ans.monoms;
	}}
