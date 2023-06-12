package main;

import javax.swing.Timer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame implements ActionListener{

	Timer timer;
	int windowSize;
	//Board board;
	final int boardSize=50;
	Rectangles[][] recArr,recArrCache;
	int scale;
	Mesh mesh;
	JPanel panel = new JPanel();
	RecDraw recDraw;
	
	
	Window(int windowSize){
		recArrCache=new Rectangles[boardSize][boardSize];
		recArr=new Rectangles[boardSize][boardSize];
		scale=windowSize/boardSize;

		

		initializeRectangles(50);
		
		
		
		
		this.windowSize=windowSize;
		windowInitialize(windowSize);
		
		recArr[20][20].setState(1);			//GLIDER
		//recArr[7][6].setState(1);
		//recArr[7][7].setState(1);
		//recArr[6][7].setState(1);
		//recArr[5][6].setState(1);
		

		
		//System.out.println(checkProximity(6,6));
		timerInitialize(124);
		//showRecArr();
		
		mesh=new Mesh(scale);
		this.add(mesh);
		
		recDraw=new RecDraw(boardSize,recArr);
		this.add(recDraw);
		
		
		
		//ystem.out.println("scale "+scale);
	}
	
	private void windowInitialize(int size) {
		
		
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(size,size);
		
		
		
	}
	
	private void timerInitialize(int delay) {
		timer=new Timer(delay,this);
    	timer.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//checkBoardState();
		
		for(int i=0;i<boardSize;i++) {
			for(int j=0;j<boardSize;j++) {

				if(recArr[i][j].getState()==1)checkNeighbours(i,j);

			}
		}
		
		implementCache();
		recDraw.repaint();
		
	}

	public void checkNeighbours(int x,int y){


		int chance=-1;
		LinkedList<Rectangles> recArrNeighbours=new LinkedList<>();




		if((x > 0 && y > 0) 					&& 	recArr[x-1][y-1].getState()!=1) recArrNeighbours.add(recArr[x-1][y-1]);					//LEWA STRONA
		if((x > 0 )								&& 	recArr[x-1][y].getState()!=1)	recArrNeighbours.add(recArr[x-1][y]);
		if((x > 0 && y < boardSize-1 ) 			&& 	recArr[x-1][y+1].getState()!=1)	recArrNeighbours.add(recArr[x-1][y+1]);

		if((y > 0) 								&& 	recArr[x][y-1].getState()!=1)	recArrNeighbours.add(recArr[x][y-1]);						// NAD
		if((y < boardSize-1) 					&& 	recArr[x][y+1].getState()!=1)	recArrNeighbours.add(recArr[x][y+1]); 							// POD

		if((x < boardSize-1 && y > 0) 			&&	recArr[x+1][y-1].getState()!=1)	recArrNeighbours.add(recArr[x+1][y-1]); 						//PRAWA STRONA
		if((x < boardSize-1) 					&&	recArr[x+1][y].getState()!=1)	recArrNeighbours.add(recArr[x+1][y]);
		if((x < boardSize-1 && y < boardSize-1) &&	recArr[x+1][y+1].getState()!=1)	recArrNeighbours.add(recArr[x+1][y+1]);


		while(!recArrNeighbours.isEmpty()){
			Rectangles n=recArrNeighbours.poll();
			int state=n.getState();
			switch(state){
				case 0: chance=getRandom(10);
				break;
				case 1: break;
				case 2: chance=getRandom(5);
				break;
				case 3:	chance=getRandom(2);
				break;
			}
			if(chance==0){
				recArrCache[n.getI()][n.getJ()].setState(1);
			}
		}

	}
	public int getRandom(int range){
		Random r=new Random();
		return r.nextInt(range);
	}


	private void initializeRectangles(int size) {
		int h=0;
		for(int i=0;i<boardSize;i++) {
			int k=0;
			for(int j=0;j<boardSize;j++) {
				k+=scale;
				recArr[i][j]=new Rectangles(size,i,j);
				recArr[i][j].setBounds(k, h, scale, scale);
				
				//System.out.println("setting bounds i="+i+" j="+j+" k="+k+" h="+h+" scale="+scale);
				
				recArrCache[i][j]=new Rectangles(size,i,j);														//CREATING CACHE
				
			}
			h+=scale;
		}
	}
	public void showRecArr() {
		for(int i=0;i<recArr.length;i++) {
			for(int j=0;j<recArr.length;j++) {
				System.out.print(recArr[i][j].getState()+" ");
			}
			System.out.println();
		}
	
	}	
	
	public void implementCache() {
		for(int i=0;i<boardSize;i++) {
			for(int j=0;j<boardSize;j++) {
				recArr[i][j].setState(recArrCache[i][j].getState());
			}
		}
		//recArr=recArrCache;
	}
}
