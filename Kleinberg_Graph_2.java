import java.util.ArrayList;
import java.util.Collections;

public class Kleinberg_Graph_2 {

	private int knotenMatrixHorizontal;
	private int knotenMatrixVertikal;
	Knoten [][] knotenMatrix;//Unsere Matrix

	
	//Konstruktor
	public Kleinberg_Graph_2(int knotenMatrixHorizontal, int knotenMatrixVertikal) {
		this.knotenMatrixHorizontal = knotenMatrixHorizontal;
		this.knotenMatrixVertikal = knotenMatrixVertikal;
		knotenMatrix = new Knoten[knotenMatrixVertikal][knotenMatrixHorizontal];//Unsere Matrix
		
	}
	
	
	//Erzeuge Knoten
	public void knotenErzeugen() {
		//Knotenwert
		int knotenwert = 1;
		//Doppelschleife um jede Position der Matrix zu erreichen
		for (int j = 0; j < knotenMatrixVertikal; j++) {
				for (int i = 0; i < knotenMatrixHorizontal; i++) {
					//Knoten mit Knotenwert erzeugen
					Knoten knoten = new Knoten(knotenwert, j, i);
					//Knoten an Matrix übergeben
					
					knotenMatrix[j][i] = knoten;
					knotenwert++;
			}
		}
		kantenErzeugen();
	}
	
	
	//Erzeuge Nachbarn / Kanten
		public void kantenErzeugen() {
			//Doppelschleife um jede Position der Matrix zu erreichen
			for (int j = 0; j < knotenMatrixVertikal; j++) {
					for (int i = 0; i < knotenMatrixHorizontal; i++) {
						
						//Linker Nachbarn für Knoten in der ersten Spalte ist das letzte Element der Zeile
						if (i == 0)
						{
							knotenMatrix[j][i].setLinkerNachbar(knotenMatrix[j][i - 1  + knotenMatrixHorizontal].getKnotenwert());
						}else 
							knotenMatrix[j][i].setLinkerNachbar(knotenMatrix[j][i - 1].getKnotenwert());
						
						//Rechter Nachbarn für Knoten in der letzten Spalte ist erstes Element der Zeile
						if (i + 1 == knotenMatrixHorizontal)
						{
							knotenMatrix[j][i].setRechterNachbar(knotenMatrix[j][i + 1  - knotenMatrixHorizontal].getKnotenwert());
						}else 
							knotenMatrix[j][i].setRechterNachbar(knotenMatrix[j][i + 1].getKnotenwert());
						
						//Oberer Nachbarn für Knoten in der ersten Zeile ist Element in der gleichen Spalte in der letzten Zeile
						if (j == 0)
						{
							knotenMatrix[j][i].setObererNachbar(knotenMatrix[knotenMatrixVertikal - 1][i].getKnotenwert());
						}else 
							knotenMatrix[j][i].setObererNachbar(knotenMatrix[j - 1][i].getKnotenwert());
						
						//Unterer Nachbarn für Knoten in der letzten Zeile ist Element in der gleichen Spalte in der ersten Zeile
						if (j + 1 == knotenMatrixVertikal)
						{
							knotenMatrix[j][i].setUntererNachbar(knotenMatrix[0][i].getKnotenwert());
						}else 
							knotenMatrix[j][i].setUntererNachbar(knotenMatrix[j + 1][i].getKnotenwert());
						
						
						
				}
			}
		}
		
	
	//Knotenwerte abfragen
	public void knotenWerteAbfragen() {
		for (int j = 0; j < knotenMatrixVertikal; j++) {
			for (int i = 0; i < knotenMatrixHorizontal; i++) {	
				System.out.println("Knoten: " +j +"." +i +" Knotenwert: " +knotenMatrix[j][i].getKnotenwert()
						+ " Linker Nachbar: "  + knotenMatrix[j][i].getLinkerNachbar()
						+ " Rechter Nachbar: "  + knotenMatrix[j][i].getRechterNachbar()
						+ "   Oberer Nachbar: " + knotenMatrix[j][i].getObererNachbar()
						+ "   Unterer Nachbar: " + knotenMatrix[j][i].getUntererNachbar()
						+ "   Alle Nachbarn: " + knotenMatrix[j][i].getAlleNachbarn());
				
			}
		}
	}

	
	
	
	
	
	//Suche starten
	public void sucheStarten(int startZeile, int startSpalte, int zielKnotenwert){
		//Erschaffe neuen Crawler an den angegebenen Koordinaten und übergebe den zu suchenden Knotenwert
		Crawler crawler = new Crawler(startZeile, startSpalte, zielKnotenwert);
		//Fülle die Liste alleNachbarn mit den Nachbarn des Startknotens auf
		crawler.setAlleNachbarn(knotenMatrix[crawler.getZeile()][crawler.getSpalte()].getAlleNachbarn());
		//Lokale Variable zur Bestimmung des aktuellen Knotenwerts;
		int aktuellerKnotenwert = knotenMatrix[startZeile][startSpalte].getKnotenwert();
		crawler.setKnotenwert(aktuellerKnotenwert);
		
		if(aktuellerKnotenwert == zielKnotenwert) {
			crawler.amZiel();
		}else
			sucheNächstenKnoten(crawler);
	
	}
	
	
	
	
	//Suche den Nachbarn, der am Nächsten am Zielwert ist
	public void sucheNächstenKnoten(Crawler crawler) {
		int nächsteZeile;
		int nächsteSpalte;
		int aktuellerKnotenwert = crawler.getKnotenwert();
		int zielKnotenwert = crawler.getKnotenwertZielposition();
		
		//Liste mit den aktuellen Nachbarknoten
		crawler.setAlleNachbarn(knotenMatrix[crawler.getZeile()][crawler.getSpalte()].getAlleNachbarn());
		ArrayList<Integer> aktuelleNachbarn = crawler.getAlleNachbarn();
		
	
		
		
		//Suche den Nachbarn, der am Nächsten am Zielwert ist
			for(int i = 0; i < aktuelleNachbarn.size(); i++) {
				if(aktuelleNachbarn.get(i) == zielKnotenwert) {
					//Wenn gesuchter Wert in Nachbarschaftsliste - hole die Koordinaten und gehe dort hin
					nächsteZeile = this.getZeile(zielKnotenwert);
					nächsteSpalte = this.getSpalte(zielKnotenwert);
					crawler.move(nächsteZeile, nächsteSpalte, zielKnotenwert);
					aktuellerKnotenwert = crawler.getKnotenwert();
					crawler.amZiel();
					break;
				}
				else {
					//Ziehe gefundenen Wert vom Zielwert ab und packe ihn in eine neue Liste (geringsterAbstand)
					crawler.setGeringsterAbstand(i, Math.abs(zielKnotenwert - aktuelleNachbarn.get(i)));
				}	
			}
			
			if(aktuellerKnotenwert != zielKnotenwert) {
				sucheNächstenKnotenWeiter(crawler);
			}
			
	}
		
			
	public void sucheNächstenKnotenWeiter(Crawler crawler) {
				ArrayList<Integer> aktuelleNachbarn = crawler.getAlleNachbarn();
				ArrayList<Integer> geringsterAbstand = crawler.getGeringsterAbstand();
				int nächsteZeile;
				int nächsteSpalte;
				System.out.println("Knotenliste Nachbarn: " +crawler.getAlleNachbarn());
				System.out.println("Knotenliste Geringster Abstand: " +crawler.getGeringsterAbstand());
				
				
				
				
				//Finde den Index des Knotens mit dem geringsten Abstand zum Zielwert in der Liste geringsterAbstand
				int minimum = Collections.min(geringsterAbstand);
				int index = geringsterAbstand.indexOf(Collections.min(geringsterAbstand));
				
				//Nehme den gefundenen Index und ermittle damit den Knotenwert mit dem geringsten Abstand aus der Liste der Nachbarn
				int nächsterKnoten = aktuelleNachbarn.get(index);
				
				nächsteZeile = this.getZeile(nächsterKnoten);
				nächsteSpalte = this.getSpalte(nächsterKnoten);
				
				
				/*
				System.out.println("Knotenliste Nachbarn: " +crawler.getAlleNachbarn());
				System.out.println("Knotenliste Geringster Abstand: " +crawler.getGeringsterAbstand());
				System.out.println("Nächster Knoten: " +nächsterKnoten);
				System.out.println("Nächste Zeile: " +nächsteZeile);
				System.out.println("Nächste Spalte: " +nächsteSpalte);
				*/
				crawler.move(nächsteZeile, nächsteSpalte, nächsterKnoten);
			
				sucheNächstenKnoten(crawler);
		
		
		
	}
	
	
	
	
	//Ausgabe der Koordinaten basierend auf dem Knotewert
	public int getZeile(int nächsteZeile) {
		int wertNächsteZeile = 0;
		//Doppelschleife um jede Position der Matrix zu erreichen
		for (int j = 0; j < knotenMatrixVertikal; j++) {
				for (int i = 0; i < knotenMatrixHorizontal; i++) {
					if (knotenMatrix[j][i].getKnotenwert() == nächsteZeile) {
						wertNächsteZeile = knotenMatrix[j][i].getZeile();
					}
				}
		}
		return wertNächsteZeile;
	}
	
	public int getSpalte(int nächsteSpalte) {
		int wertNächsteZeile = 0;
		//Doppelschleife um jede Position der Matrix zu erreichen
		for (int j = 0; j < knotenMatrixVertikal; j++) {
				for (int i = 0; i < knotenMatrixHorizontal; i++) {
					if (knotenMatrix[j][i].getKnotenwert() == nächsteSpalte) {
						wertNächsteZeile = knotenMatrix[j][i].getSpalte();
					}
				}
		}
		return wertNächsteZeile;
	}
	
	
	
	
	
	public static void main(String[] args) {
		
		Kleinberg_Graph_2 graph = new Kleinberg_Graph_2(100,200);
				
		graph.knotenErzeugen();
		//graph.knotenWerteAbfragen();
		
		graph.sucheStarten(0,0,1457);
		
	}//Ende Main
	
}//Ende Klasse
