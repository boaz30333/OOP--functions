package Ex1;

public class Gui_params {
	public int Width=800;
	public int Height=800;
	public double[] Range_X= {-10,10};
	public double[] Range_Y= {-20,20};
	public int Resolution=1000;
	public Gui_params(int w, int h,double[] rx,double[] ry, int res) {
		this.Height= h;
				this.Resolution=res;
				this.Width=w;
				this.Range_X[0]= rx[0];
				this.Range_X[1]= rx[1];
				this.Range_Y[0]= ry[0];
				this.Range_Y[0]= ry[0];
	}
	public Gui_params() {
		
	}
}
