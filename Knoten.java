import java.util.ArrayList; // import the ArrayList class


public class Knoten {
	
	private int knotenWert;
	private int rechterNachbar;
	private int linkerNachbar;
	private int obererNachbar;
	private int untererNachbar;
	private int zeile;
	private int spalte;
	
	ArrayList<Integer> alleNachbarn = new ArrayList<Integer>();

	//Konstruktor
	public Knoten(int knotenWert, int zeile, int spalte) {
		this.knotenWert = knotenWert;
		this.zeile = zeile;
		this.spalte = spalte;
	}
	
	
	//Setter
	public void setRechterNachbar(int rechterNachbar){
		this.rechterNachbar = rechterNachbar;
		this.alleNachbarn.add(rechterNachbar);
	}
	public void setLinkerNachbar(int linkerNachbar){
		this.linkerNachbar = linkerNachbar;
		this.alleNachbarn.add(linkerNachbar);
	}
	public void setObererNachbar(int obererNachbar){
		this.obererNachbar = obererNachbar;
		this.alleNachbarn.add(obererNachbar);
	}
	public void setUntererNachbar(int untererNachbar){
		this.untererNachbar = untererNachbar;
		this.alleNachbarn.add(untererNachbar);
	}
	public void setEntfernterNachbar(int entfernterNachbar){
		this.alleNachbarn.add(entfernterNachbar);
	}
	
	
	
	
	//Getter
	public int getKnotenwert(){
		return this.knotenWert;
	}
	public int getRechterNachbar(){
		return this.rechterNachbar;
	}
	public int getLinkerNachbar(){
		return this.linkerNachbar;
	}
	public int getObererNachbar(){
		return this.obererNachbar;
	}
	public int getUntererNachbar(){
		return this.untererNachbar;
	}
	public ArrayList<Integer> getAlleNachbarn(){
			return  this.alleNachbarn;
	}
	public int getZeile() {
		return this.zeile;
	}
	public int getSpalte() {
		return this.spalte;
	}
}//Ende Klasse knoten
