package view;

import java.util.concurrent.Semaphore;
import controller.ComprarIngresso;


public class Main{

	public static void main(String [] args) {
		int compradores = 300;
		Semaphore semaforo = new Semaphore(1);
		for(int i = 0; i < compradores; i++) {
			int qtdIngressosVen = (int)((Math.random()*4)+1);
				ComprarIngresso sistem = new ComprarIngresso(qtdIngressosVen, semaforo);
				sistem.run();
		}

	}

}