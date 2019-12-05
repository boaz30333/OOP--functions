package Ex1;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	public int compare(Monom o1, Monom o2) {
		int dp = o2.get_power() - o1.get_power();
		if(dp==0)
		dp= Double.compare(o2.get_coefficient() ,o1.get_coefficient());
		return dp;
	}

	// ******** add your code below *********

}
