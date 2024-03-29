package Ex1;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import com.google.gson.Gson;


public class Functions_GUI implements functions{
	LinkedList<function> funcs = new LinkedList<function>();

	/**
	 * Draws all the functions in the collection in a GUI window using the
	 * given parameters for the GUI windo and the range & resolution
	 * @param width - the width of the window - in pixels
	 * @param height - the height of the window - in pixels
	 * @param rx - the range of the horizontal axis
	 * @param ry - the range of the vertical axis
	 * @param resolution - the number of samples with in rx: the X_step = rx/resulution
	 */
	public void drawFunctions(int w, int h, Range rx, Range ry, int res) {
		
		StdDraw.setCanvasSize(w,h);

		double maxY = ry.get_max(), minY =ry.get_min();
		double m=rx.get_max()-rx.get_min();
		double n=ry.get_max()-ry.get_min();

		// the function y = sin(4x), sampled at n+1 points
		// between x = 0 and x = pi 
		double[][] x = new double[funcs.size()][res+1];
		double[][] y = new double[funcs.size()][res+1];
		boolean[][] b=  new boolean[funcs.size()][res+1];
		double asmp;
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
				if(y[i][j]<Double.MAX_VALUE-1 && y[i][j+1]<Double.MAX_VALUE-1 )
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

	
	/**
	 *
	 */
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

	/**
	 * Initial a new collection of functions from a file
	 * @param file - the file name
	 * @throws IOException if the file does not exists of unreadable (wrong format)
	 */
	@Override
	public void initFromFile(String fileName) throws IOException {
		
        String line = "";
        Functions_GUI ans= new Functions_GUI();
        ComplexFunction tmp= new ComplexFunction("x");
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader(fileName));
        	
            while ((line = br.readLine()) != null) 
            {
               ans.add(tmp.initFromString(line));

                }
            
br.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            System.out.println("could not read file");
        }

	}
	/**
	 * save the in the file Name
	 * @param file - the file name
	 * @throws IOException if the file is not writable
	 */
	@Override
	public void saveToFile(String fileName) throws IOException {

		
		try 
		{
			PrintWriter pw = new PrintWriter(new File(fileName));
			
			StringBuilder sb = new StringBuilder();
			Iterator<function> iter= this.iterator();
			function b;
			while(iter.hasNext()) {
				b=  iter.next().copy();
				sb.append(b.toString());
				sb.append("\n");
			}
			pw.write(sb.toString());
			pw.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}

	}

	/**
	 * Draws all the functions in the collection in a GUI window using the given JSON file
	 * @param json_file - the file with all the parameters for the GUI window. 
	 * Note: is the file id not readable or in wrong format should use default values. 
	 */
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
