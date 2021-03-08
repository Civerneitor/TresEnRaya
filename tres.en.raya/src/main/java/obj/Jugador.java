package obj;

import javax.swing.JButton;

public class Jugador {
	
	private String nombre;
	private boolean npc;
	private char pieza;
	
	public Jugador(String nombre, boolean npc, boolean turno) {
		this.nombre = nombre;
		this.npc = npc;
		validarTurno(turno);
		
	}

	private void validarTurno(boolean t) {
		if(t) {
			this.pieza = 'X';
		}else {
			this.pieza = 'O';
		}
		
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * @return the es_npc
	 */
	public boolean isNpc() {
		return npc;
	}

	

	/**
	 * @return the pieza
	 */
	public char getPieza() {
		return pieza;
	}


	public int[] juega(Tablero t, int i, int j) {
		int[] res = new int[2];
		if(npc) {
			
			res = movimiento(t);
			
		}else {
			t.setTablero(this.pieza,i,j);
		}
		return res;
	
	}
	public int[] movimiento(Tablero t) {
		int fi=0,fj=0;
		char tablero[][]=t.getTablero();
		for(int n = 0;n<3;n++) {//inicializamos las coordenadas al primer elemento vacío que encontrmos
			for(int m=0;m<3;m++) {
				if(tablero[n][m]!=this.pieza && tablero[n][m]!=getReverse()) {
					fi=n;
					fj=m;
				}
			}
		}
		if(tablero[1][1]==0) {//si el centro del tablero está vacío colocamos
			fi=1;
			fj=1;
		}else {//miramos si ganamos
			for (int i=0;i<3;i++) {//recorrido horizontal comprueba columnas
				if(tablero[i][0]==this.pieza) {
					if(nextMov(t,i,1)) {
						if (tablero[i][2]!=getReverse()) {
							fi=i;
							fj=2;
						}
					}
					else if(nextMov(t,i,2)) {
						if (tablero[i][1]!=getReverse()) {
							fi=i;
							fj=1;
						}
					}
				}else if(tablero[i][1]==this.pieza) {
					if(nextMov(t,i,2)) {
						if (tablero[i][0]!=getReverse()) {
							fi=i;
							fj=0;
						}
					}
				}
			}
			for (int j=0;j<3;j++) {//recorrido vertical comprueba filas
				if(tablero[0][j]==this.pieza) {
					if(nextMov(t,1,j)) {
						if (tablero[2][j]!=getReverse()) {
							fi=2;
							fj=j;
						}
					}
					else if(nextMov(t,2,j)) {
						if (tablero[1][j]!=getReverse()) {
							fi=1;
							fj=j;
						}
					}
				}else if(tablero[1][j]==this.pieza) {
					if(nextMov(t,2,j)) {
						if (tablero[0][j]!=getReverse()) {
							fi=0;
							fj=j;
						}
					}
				}
			}
			//comprobamos diagonales
			if(tablero[0][0]==this.pieza) {// diagonal en \
				if(nextMov(t,1,1) && tablero[2][2]!=getReverse()){
					fi=2;
					fj=2;
				}else if(nextMov(t,2,2) && tablero[1][1]!=getReverse()){
					fi=1;
					fj=1;
				}
			}else {
				if(tablero[1][1]==this.pieza) {
					if(nextMov(t,2,2) && tablero[0][0]!=getReverse()){
						fi=0;
						fj=0;
					}
				}
				if(tablero[2][0]==this.pieza) {// diagonal en /
					if(nextMov(t,1,1) && tablero[0][2]!=getReverse()){
						fi=0;
						fj=2;
					}else if(nextMov(t,0,2) && tablero[1][1]!=getReverse()){
						fi=1;
						fj=1;
					}
				}else {
					if(tablero[1][1]==this.pieza && nextMov(t,0,2) && tablero[2][0]!=getReverse()){
						fi=2;
						fj=0;
					}
				}
			}
		}
		t.setTablero(this.pieza,fi,fj);
		int[] res = new int[2];
		res[0] = fi;
		res[1] = fj;
		return res;
	}
	public boolean nextMov(Tablero t,int i, int j) {
		if(t.getTablero()[i][j]==this.pieza) return true;
		else return false;
	}
	private char getReverse() {
		if(this.pieza=='X') return 'O';
		else return 'X';
	}


}
