import java.util.ArrayList;

public class Crawler {
	
	private int aktuellePositionZeile;
	private int aktuellePositionSpalte;
	private int letztePositionZeile;
	private int letztePositionSpalte;
	private int knotenwert;
	private int knotenwertLetztePosition;
	private int knotenwertZielposition;
	private int schritte;
	private ArrayList<Integer> aktuelleNachbarn = new ArrayList<Integer>();
	 ArrayList<Integer> geringsterAbstand = new ArrayList<Integer>();
	
	//Konstruktor
	public Crawler(int startpositionZeile, int startpositionSpalte, int knotenwertZielPosition) {
		this.letztePositionZeile = startpositionZeile;
		this.letztePositionSpalte = startpositionSpalte;
		this.aktuellePositionZeile = startpositionZeile;
		this.aktuellePositionSpalte = startpositionSpalte;
		this.knotenwertZielposition = knotenwertZielPosition;
		
		this.schritte = 0;
	}
	
	//Setter
	public void setKnotenwert(int knotenwertAktuellePosition){
		this.knotenwert = knotenwertAktuellePosition;
	}
	public void setKnotenwertLetztePosition(int knotenwertletztePosition){
		this.knotenwertLetztePosition = knotenwertletztePosition;
	}
	public void move(int zielPositionZeile, int zielPositionSpalte, int neuerKnotenwert) {
		
		this.letztePositionZeile = this.aktuellePositionZeile;
		this.letztePositionSpalte = this.aktuellePositionSpalte;
		this.aktuellePositionZeile = zielPositionZeile;
		this.aktuellePositionSpalte = zielPositionSpalte;
		this.knotenwertLetztePosition = this.knotenwert;
		this.knotenwert = neuerKnotenwert;
	
		
		this.schritte ++;
		this.geringsterAbstand.clear();
		ausgabeSchritt();
	}
	
	public void setAlleNachbarn(ArrayList<Integer> alleNachbarn){
		this.aktuelleNachbarn = alleNachbarn;
	}
	public void setGeringsterAbstand(int position, int neuerWert){
		this.geringsterAbstand.add(position, neuerWert);
	}
	
	
	
	
	//Getter
	public int getSchrittZähler(){
		return this.schritte;
	}
	public int getKnotenwert(){
		return this.knotenwert;
	}
	public int getKnotenwertZielposition(){
		return this.knotenwertZielposition;
	}
	
	public int getZeile(){
		return this.aktuellePositionZeile;
	}
	public int getSpalte(){
		return this.aktuellePositionSpalte;
	}
	public int getLetztePositionZeile(){
		return this.letztePositionZeile;
	}
	public int getLetztePositionSpalte(){
		return this.letztePositionSpalte;
	}
	public ArrayList<Integer> getAlleNachbarn(){
		return this.aktuelleNachbarn;
	}
	public ArrayList<Integer> getGeringsterAbstand(){
		return this.geringsterAbstand;
	}
	
	
	
	
	
	
	
	private void ausgabeSchritt() {
		System.out.println("Crawler: Ich bin vom Knotenwert " +this.knotenwertLetztePosition +" (Zeile " +getLetztePositionZeile() +", Spalte " + +getLetztePositionSpalte() +")" +" zum Knotenwert " +this.knotenwert +" (Zeile " +getZeile() +", Spalte " + +getSpalte() +")" +(" gewandert und habe insgesamt " +this.schritte +" Schritt(e) zurückgelegt.") );
	}
	public void amZiel() {
		System.out.println("Crawler: Ich bin am Ziel und habe insgesamt " +this.schritte +" Schritt(e) gebraucht." );
	}
	
	
}//Ende Klasse Crawler 
