package main;

import java.awt.Rectangle;

public class Rectangles extends Rectangle{

	int size,i,j;
	int state;			// 0-PUSTE(ogien moze sie rozprzestrzeniac, ZWYKLY LAS, SZANSA 10%); 1-PALI SIE!!!!!; 2-LAS GĘSTY SZANSA 20%; 3-LAS B GĘSTY SZANSA 50%; 4-WODA- SZANSA 0% //TODO - ABY WODA DAWAŁA SZANSE NA UGASZENIE


	Rectangles(int size,int i,int j){
		this.i=i;
		this.j=j;
		this.size=size;
		this.state=0;
		
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	Rectangles(int size, int state){
		
		this.size=size;
		this.state=state;
	}
	
	public int getState() {
		return this.state;
	}
	public void setState(int state) {
		this.state=state;
	}
	
}
	
	
	
	

