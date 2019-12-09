package Ex1;
import Ex1.Polynom;
import Ex1.Monom;
public class ComplexFunction implements complex_function{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	function right;
	function left;
	Operation op;

	@Override
	public function initFromString(String s) {
		// TODO Auto-generated method stub
		ComplexFunction cf1=new ComplexFunction();
		return FromString(s,cf1);
	}
	public function FromString(String s,ComplexFunction cf1) {
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
			cf1.left=cf1.left.initFromString(leftside);
			if(rightside.contains(",")) 
				cf1.right=new ComplexFunction();
			else
				cf1.right=new Polynom();
			cf1.right=cf1.right.initFromString(rightside);
		}
		if(!s.contains("(")&&!s.contains(")"))
			cf1.left=new Polynom(s);
		return cf1;
	}
	
	private ComplexFunction() {
		this.right=this.left=null;
		this.op=Operation.None;
	}
	public ComplexFunction(function func) {
		// TODO Auto-generated constructor stub
		if(func instanceof Monom) func= new Polynom((Monom)func);
		this.left=func;
		this.right=null;
		this.op= Operation.None;
	}
	public ComplexFunction(String string, function left, function right) {
		// TODO Auto-generated constructor stub
		if(left instanceof Monom) left= new Polynom((Monom)left);
		if(right instanceof Monom) right= new Polynom((Monom)right);
		this.op= getop(string);
		this.left = left;
		this.right=right; 
	}
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
	public ComplexFunction(String string, String left, String right) {
		// TODO Auto-generated constructor stub
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
		this.op= getop(string);
	}

	@Override
	public double f(double x) {
		// TODO Auto-generated method stub
		if(this.left==null) return 0;
		if(this.op==Operation.None) return this.left.f(x);
		double sum=0;
		if(this.op==Operation.Plus)
		 sum = this.left.f(x)+this.right.f(x);
		if(this.op==Operation.Divid)
			 sum = this.left.f(x)/this.right.f(x);
		if(this.op==Operation.Max)
			 sum = Math.max(this.left.f(x),this.right.f(x));
		if(this.op==Operation.Min)
			 sum = Math.min(this.left.f(x),this.right.f(x));
		if(this.op==Operation.Times)
			 sum = this.left.f(x)*this.right.f(x);
		if(this.op==Operation.Comp)
			 sum = this.left.f(this.right.f(x));
		return sum;
	}
	
/**
 *  * Partial JUnit + main test for the GUI_Functions class, expected output from the main:
 * 0) java.awt.Color[r=0,g=0,b=255]  f(x)= plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0)
1) java.awt.Color[r=0,g=255,b=255]  f(x)= plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)
2) java.awt.Color[rf=255,g=0,b=255]  f(x)= div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)
3) java.awt.Color[r=255,g=200,b=0]  f(x)= -1.0x^4 +2.4x^2 +3.1
4) java.awt.Color[r=255,g=0,b=0]  f(x)= +0.1x^5 -1.2999999999999998x +5.0
5) java.awt.Color[r=0,g=255,b=0]  f(x)= max(max(max(max(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0)
6) java.awt.Color[r=255,g=175,b=175]  f(x)= min(min(min(min(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),plus(div(+1.0x +1.0,mul(mul(+1.0x +3.0,+1.0x -2.0),+1.0x -4.0)),2.0)),div(plus(-1.0x^4 +2.4x^2 +3.1,+0.1x^5 -1.2999999999999998x +5.0),-1.0x^4 +2.4x^2 +3.1)),-1.0x^4 +2.4x^2 +3.1),+0.1x^5 -1.2999999999999998x +5.0
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
	public static Operation getop(String oper) {
	// TODO Auto-generated method stub
		oper=oper.toLowerCase();
		if(oper.equalsIgnoreCase( "plus") )
			return Operation.Plus;
		else if(oper.equalsIgnoreCase("comp") )
			return Operation.Comp;
		else if(oper.equalsIgnoreCase( "times")) 
			return Operation.Times;
		else if(oper.equalsIgnoreCase("divid")||oper.equalsIgnoreCase("div"))
			return Operation.Divid;
		else if(oper.equalsIgnoreCase("max"))
			return Operation.Max;
		else if(oper.equalsIgnoreCase("min"))
			return Operation.Min;
		else 
			return Operation.Error;
}
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

	@Override
	public void plus(function f1) {
		// TODO Auto-generated method stub
		if(this.op==Operation.None) {
			this.op=Operation.Plus;
			this.right=f1;
			return;
		}
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Plus;
		
		
	}

	@Override
	public void mul(function f1) {
		if(this.op==Operation.None) {
			this.op=Operation.Times;
			this.right=f1;
			return;
		}
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Times;	
	}

	@Override
	public void div(function f1) {
		if(this.op==Operation.None) {
			this.op=Operation.Divid;
			this.right=f1;
			return;
		}
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Divid;
	}

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

	@Override
	public void min(function f1) {
		if(this.op==Operation.None) {
			this.op=Operation.Min;
			this.right=f1;
			return;
		}
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Min;
	}

	@Override
	public void comp(function f1) {
		if(this.op==Operation.None) {
			this.op=Operation.Comp;
			this.right=f1;
			return;
		}
		this.left=this.copy();
		this.right=f1;
		this.op=Operation.Comp;
	}

	@Override
	public function left() {
		// TODO Auto-generated method stub
		return this.left;
	}

	@Override
	public function right() {
		// TODO Auto-generated method stub
		return this.right();
	}

	@Override
	public Operation getOp() {
		// TODO Auto-generated method stub
		return this.op;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toString(sb);
		
		return sb.toString();
		
	}
	private void toString(StringBuilder sb) {
		// TODO Auto-generated method stub
		 sb.append(""+this.op+"("+this.left+","+this.right+")");
		return ;
	}


}
