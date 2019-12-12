package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

public class Functions_GUI implements functions{
	LinkedList<function> funcs = new LinkedList<function>();

	public void drawFunctions(int w, int h, Range rx, Range ry, int res) {
		StdDraw.setCanvasSize(h,w);

		double maxY = ry.get_max(), minY =ry.get_min();
double m=rx.get_max()-rx.get_min();
double n=ry.get_max()-ry.get_min();

		// the function y = sin(4x), sampled at n+1 points
		// between x = 0 and x = pi 
		double[][] x = new double[funcs.size()][res+1];
		double[][] y = new double[funcs.size()][res+1];
		 for(int i = 0; i < funcs.size(); i++)
		 {
		for (int j = 0; j <= res; j++) {
			x[i][j] =rx.get_min() +(m*j)/res;
			try {
				y[i][j]=funcs.get(i).f(x[i][j]);
			}
catch(Exception e) {
	y[i][j]=Double.MAX_VALUE;
}

		}
		 }
		// rescale the coordinate system
		StdDraw.setXscale(rx.get_min(),rx.get_max());
		StdDraw.setYscale(minY, maxY);
		
		//////// vertical lines
		StdDraw.setPenColor(Color.LIGHT_GRAY);
		for (double j = rx.get_min(); j <= rx.get_max(); j=j+(m)/20) {
			StdDraw.line(j, minY, j, maxY);
		}
		//////// horizontal  lines
		for (double i = minY; i <= maxY; i=i+n/20) {
			StdDraw.line(rx.get_min(), i, rx.get_max(), i);
		}
		//////// x axis
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setPenRadius(0.005);
		StdDraw.line(rx.get_min(), 0, rx.get_max(),0);
		StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
		for (double i = rx.get_min(); i <= rx.get_max(); i=i+(m)/10) {
			StdDraw.text(i-0.07, -0.07, Integer.toString((int) (i)));
		}
		//////// y axis
		StdDraw.line(0, minY, 0, maxY);
		for (double i = minY; i <= maxY; i=i+n/10) {
			StdDraw.text(0, i+0.07, Double.toString(i));
		}

		// plot the approximation to the function
		 for(int i = 0; i < funcs.size(); i++)
		 {
				StdDraw.setPenColor(Color.getHSBColor((float) Math.random(), .8f, .8f));
				StdDraw.setPenRadius(0.005);
		for (int j = 0; j< res; j++) {
			if(y[i][j]<Double.MAX_VALUE && y[i][j+1]<Double.MAX_VALUE )
			StdDraw.line(x[i][j], y[i][j], x[i][j+1], y[i][j+1]);
		}
		StdDraw.setPenColor(Color.getHSBColor((float) Math.random(), .8f, .8f));
		StdDraw.setPenRadius(0.005);
	}
	}





	public function get(int i) {

		return funcs.get(i);
	}

	@Override
	public boolean add(function arg0) {

		try {
			this.funcs.add(arg0);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean addAll(Collection<? extends function> arg0) {

		Iterator<? extends function> iter= arg0.iterator();
		function b;
		boolean flag=true;
		while(iter.hasNext()) {
			 b=  iter.next().copy();
			flag=this.add(b);
			if(flag==false)return false;
		}
		
		return true;
	}

	@Override
	public void clear() {

		this.funcs.clear();
		
	}

	@Override
	public boolean contains(Object arg0) {

		
		return this.funcs.contains(arg0);
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {

		return this.funcs.containsAll(arg0);
	}

	@Override
	public boolean isEmpty() {

		return this.funcs.isEmpty();
	}

	@Override
	public Iterator<function> iterator() {

		return this.funcs.iterator();
	}

	@Override
	public boolean remove(Object arg0) {

		return this.funcs.remove(arg0);
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {

		return this.funcs.removeAll(arg0);
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {

		return this.funcs.retainAll(arg0);
	}

	@Override
	public int size() {

		return this.funcs.size();
	}

	@Override
	public Object[] toArray() {
		
		return this.funcs.toArray();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		
		return this.funcs.toArray(arg0);
	}

	@Override
	public void initFromFile(String file) throws IOException {

		
	}

	@Override
	public void saveToFile(String file) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFunctions(String json_file) {
		Gson gson = new Gson();
		Gui_params params=new Gui_params();
		
		try 
		{
			FileReader reader = new FileReader(json_file);
			 params = gson.fromJson(reader,Gui_params.class);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Range rx=new Range(params.Range_X[0],params.Range_X[1]);
		Range ry=new Range(params.Range_Y[0],params.Range_Y[1]);
		drawFunctions(params.Width, params.Height,rx,ry, params.Resolution);
		
	}

}
