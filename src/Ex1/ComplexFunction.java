package Ex1;
import Ex1.Polynom;
import Ex1.Monom;

public class ComplexFunction implements complex_function{
	
	private static final long serialVersionUID = 1L;
	function right;
	function left;
	Operation op;

	/**
	 *initial the tree from string
	 *the string can be monom or polymom or complexFunc
	 */
	@Override
	public function initFromString(String s) {
		
		// TODO Auto-generated method stub
		ComplexFunction cf1=new ComplexFunction();
		return FromString(s,cf1);
	}
	/**
	 * @param s 
	 * @param cf1
	 * @return
	 */
	private function FromString(String s,ComplexFunction cf1) {
		//  
		String leftside="";
		String rightside="";
		if(s.contains("(")&&s.contains(")")&&s.indexOf("(")<s.lastIndexOf(")")) {
			cf1.op=getop(s.substring(0, s.indexOf("(")));
			int indexmed= getmed(s,s.indexOf("(")+1);
			leftside=s.substring(s.indexOf("(")+1, indexmed);
			rightside=s.substring(indexmed+1, s.length()-1);
			if(leftside.contains(",")) 
				cf1.left=new ComplexFunction();
			else
				cf1.left=new Polynom();
			try {
			cf1.left=cf1.left.initFromString(leftside);
			}
			catch(Exception e) {
				throw new RuntimeException("cant build sub function:"+rightside);
			}
			if(rightside.contains(",")) 
				cf1.right=new ComplexFunction();
			else
				cf1.right=new Polynom();
			if(!rightside.equalsIgnoreCase("null")) {
				
				try {
				cf1.right=cf1.right.initFromString(rightside);
				}
				catch(Exception e) {
					throw new RuntimeException("cant build sub function:"+rightside);
				}
				
			}
		}
		if(!s.contains("(")&&!s.contains(")"))
			cf1.left=new Polynom(s);
		return cf1;
	}
	
	/**
	 * constructor none as a default
	 */
	private ComplexFunction() {
		
		this.right=this.left=null;
		this.op=Operation.None;
	}
	/**
	 * @param func can be monom or polymom or complexFunc
	 */
	public ComplexFunction(function func) {
		
		// TODO Auto-generated constructor stub
		if(func instanceof Monom) func= new Polynom((Monom)func);
		this.left=func.copy();
		this.right=null;
		this.op= Operation.None;
	}
	/**
	 * @param op one of this sing :Plus, Times, Divid, Max, Min, Comp , None, Error
	 * @param left can be monom or polymom or complexFunc
	 * @param right can be monom or polymom or complexFunc
	 */
	public ComplexFunction(Operation op, function left, function right) {
		
		// TODO Auto-generated constructor stub
		if(left instanceof Monom) left= new Polynom((Monom)left);
		if(right instanceof Monom) right= new Polynom((Monom)right);
		this.op= op;
		this.left = left.copy();
		this.right=right.copy(); 
	}
	/**
	 * @param op one of this sing :Plus, Times, Divid, Max, Min, Comp , None, Error
	 * @param left can be monom or polymom or complexFunc
	 * @param right can be monom or polymom or complexFunc
	 */
	public ComplexFunction(String op, function left, function right) {
		
		 this(getop(op),left,right);
	}
	/**
	 * 
	 * @param left can be monom or polymom or complexFunc
	 * the other are null
	 */
	public ComplexFunction(String left) {
		
		// TODO Auto-generated constructor stub
		if(!left.contains(",")) {
			this.left= new Polynom(left);
		}
		else {
			this.left=new ComplexFunction();
			this.left=this.left.initFromString(left);
			
		}
		this.right=null;
		this.op= Operation.None;
	}
	/**
	 * @param op one of this sing :Plus, Times, Divid, Max, Min, Comp , None, Error
	 * @param left can be monom or polymom or complexFunc
	 * @param right can be monom or polymom or complexFunc
	 */
	public ComplexFunction(String op, String left, String right) {
		
		
		if(!left.contains(",")) {
			this.left= new Polynom(left);
		}
		else {
			this.left=new ComplexFunction();
			this.left=this.left.initFromString(left);
		}
		if(!right.contains(",")) {
			this.right= new Polynom(right) ;
		}
		else {
			this.right=new ComplexFunction();
			this.right=this.right.initFromString(right);
		}
		this.op= getop(op);
	}

	
	/**
	 *calculate the func at point 'x'
	 */
	@Override
	public double f(double x) {
		
		// TODO Auto-generated method stub
		if(this.left==null) return 0;
		else if(this.op==Operation.None) return this.left.f(x);
		double sum=0;
		if(this.op==Operation.Plus)
		 sum = this.left.f(x)+this.right.f(x);
		else if(this.op==Operation.Divid) {
			if(this.right.f(x)==0) {
				throw new ArithmeticException("cant divide by zero");
			}
				 sum = this.left.f(x)/this.right.f(x);
		}
	
		else if(this.op==Operation.Max)
			 sum = Math.max(this.left.f(x),this.right.f(x));
		else if(this.op==Operation.Min)
			 sum = Math.min(this.left.f(x),this.right.f(x));
		else if(this.op==Operation.Times)
			 sum = this.left.f(x)*this.right.f(x);
		else if(this.op==Operation.Comp)
			 sum = this.left.f(this.right.f(x));
		return sum;
	}
	
/**
 */


	/**
	 * @param s the func you want to check
	 * @param indexOf index of charater at the string 
	 * @return
	 */
	private int getmed(String s, int indexOf) {
		
	// TODO Auto-generated method stub
		int i =s.length()-2;
		int count=0;
		while(i>indexOf) {
			if(s.charAt(i)==','&&count==0) return i;
			if(s.charAt(i)=='(') count--;
			if(s.charAt(i)==')') count++;
			if(count<0||s.charAt(s.length()-1)!=')') return 0;
			i--;
		}
	return 0;
}
	
	/**
	 * @param oper :Plus, Times, Divid, Max, Min, Comp , None, Error
	 * @return operator
	 */
	public static Operation getop(String oper) {
		
	// TODO Auto-generated method stub
		oper=oper.toLowerCase();
		if(oper.equalsIgnoreCase( "plus") )
			return Operation.Plus;
		else if(oper.equalsIgnoreCase("comp") )
			return Operation.Comp;
		else if(oper.equalsIgnoreCase( "times")||oper.equalsIgnoreCase("mul")) 
			return Operation.Times;
		else if(oper.equalsIgnoreCase("divid")||oper.equalsIgnoreCase("div"))
			return Operation.Divid;
		else if(oper.equalsIgnoreCase("max"))
			return Operation.Max;
		else if(oper.equalsIgnoreCase("min"))
			return Operation.Min;
		else if(oper.equalsIgnoreCase("none"))
			return Operation.None;
		else 
			return Operation.Error;
}
	/**
	 * deep copy
	 */
	@Override
	public function copy() {
		
		// TODO Auto-generated method stub
		ComplexFunction cf= new ComplexFunction();
		cf.left= this.left.copy();
		cf.op= this.op;
if(this.right!= null)
		cf.right=this.right.copy();
return cf;
	}

	/**
	 * f1 can be monom or polymom or complexFunc 
	 * f1 push to right node
	 * and plus to operator
	 */
	@Override
	public void plus(function f1) {
		
		if(this.op==Operation.None) {
			this.op=Operation.Plus;
			this.right=f1;
			return;
		}
		this.left=this.copy();
		this.right=f1.copy();
		this.op=Operation.Plus;
		
		
	}
	/**
	 * f1 can be monom or polymom or complexFunc 
	 * f1 push to right node
	 * and mul to operator
	 */
	@Override
	public void mul(function f1) {
		if(this.op==Operation.None) {
			this.op=Operation.Times;
			this.right=f1.copy();
			return;
		}
		this.left=this.copy();
		this.right=f1.copy();
		this.op=Operation.Times;	
	}
	/**
	 * f1 can be monom or polymom or complexFunc 
	 * f1 push to right node
	 * and div to operator
	 */
	@Override
	public void div(function f1) {
		if(this.op==Operation.None) {
			this.op=Operation.Divid;
			this.right=f1.copy();
			return;
		}
		this.left=this.copy();
		this.right=f1.copy();
		this.op=Operation.Divid;
	}
	/**
	 * f1 can be monom or polymom or complexFunc 
	 * f1 push to right node
	 * and max to operator
	 */
	@Override
	public void max(function f1) {
		if(this.op==Operation.None) {
			this.op=Operation.Max;
			this.right=f1;
			return;
		}
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Max;
	}
	/**
	 * f1 can be monom or polymom or complexFunc 
	 * f1 push to right node
	 * and min to operator
	 */
	@Override
	public void min(function f1) {
		if(this.op==Operation.None) {
			this.op=Operation.Min;
			this.right=f1.copy();
			return;
		}
		this.left=this.copy();
		this.right=f1.copy();
		this.op=Operation.Min;
	}
	/**
	 * f1 can be monom or polymom or complexFunc 
	 * f1 push to right node
	 * and comp to operator
	 */
	@Override
	public void comp(function f1) {
		if(this.op==Operation.None) {
			this.op=Operation.Comp;
			this.right=f1.copy();
			return;
		}
		this.left=this.copy();
		this.right=f1.copy();
		this.op=Operation.Comp;
	}

	/**
	 *return left Node
	 */
	@Override
	public function left() {
		
		// TODO Auto-generated method stub
		return this.left;
	}
	/**
	 *return right Node
	 */
	@Override
	public function right() {
		// TODO Auto-generated method stub
		if (this.right==null) return null;
		else
		return this.right();
	}
	/**
	 *return operator
	 */
	@Override
	public Operation getOp() {
		// TODO Auto-generated method stub
		return this.op;
	}
	/**
	 *convert the object to string
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		toString(sb);
		
		return sb.toString();
		
	}
	/**
	 * @param sb string builder from build string efficiently
	 */
	private void toString(StringBuilder sb) {
		
		// TODO Auto-generated method stub
		 sb.append(""+this.op+"("+this.left+","+this.right+")");
		return ;
	}
	
	/**
	 * return true if the object are logicly equals ti f1
	 */
	public boolean equals (Object f1) {
		
		boolean definedA=true;
		boolean definedB=true;	
		if(f1 instanceof function) {
			double a=0;
			double b=0;
			double x;
			for(double i=0;i<50;i++) {
				try {
					a=this.f(i);
				}
					catch(ArithmeticException e){
						definedA=false;
					}
				try {
					b=((function)f1).f(i);
				}
					catch(ArithmeticException e){
						definedB=false;
					}
				if (definedA!=definedB)	
					return false;
				if(definedA==true&&definedB==true) {
				x=a-b;
					if(Math.abs(x)>0.001) 
						return false;
				}
			}
			return true;
		}
		return false;
	}


}
