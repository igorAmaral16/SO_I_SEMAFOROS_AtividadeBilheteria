package controller;

import java.util.concurrent.Semaphore;
import java.util.Random;

	@SuppressWarnings("unused")
	public class ComprarIngresso extends Thread {
	
	
	
	private Semaphore semaforo;
	private int tempLogin, tempMax;
	private int comprar;
	private int qtdIngressos = 100;
	private int qtdIngressosVen;
	
		public ComprarIngresso(int qtdIngressosVen, Semaphore semaforo) {
			this.qtdIngressosVen = qtdIngressosVen;
			this.semaforo = semaforo;
			
		}
		
		public void run() {
			fazerLogin();
			processoDeCompra();
			validacaoCompra(this.qtdIngressosVen);
		
		}
		
		public void fazerLogin() {
			tempMax = 1000;
			tempLogin = (int)(Math.random()*1951)+50;
			
			try {
			sleep(tempLogin);
				System.out.println("Fazendo login, aguarde...");
					if(tempLogin>tempMax) {
						
						System.err.println("Erro de Login.");
					}
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		}
		
		public void processoDeCompra() {
		
			int tempLimite = 2500;
			comprar = (int)(Math.random()*2000)+1000;
			
			try {
				sleep(comprar);
				if (comprar>tempLimite) {
					System.err.println("Erro de tempo de compra.");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (comprar>2500) {
			System.out.println("Final de tempo de sessao, não poder fazer a compra.");
		}
		
			qtdIngressosVen = (int)(Math.random()*3)+1;
		
		
		}
		
		public int validacaoCompra(int qtdIngressosVen) {
			try {
				semaforo.acquire();
				
				if(qtdIngressos >= qtdIngressosVen) {
					this.qtdIngressos = qtdIngressos - qtdIngressosVen;
					System.out.println("A quantidade de ingressos vendidos foi de: " + qtdIngressosVen + " e estao disponiveis ainda: " + qtdIngressos);

				}else {					
					System.out.println("Nao ha ingressos suficientes");
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			finally {
				semaforo.release();
			}
			
		
				System.out.println("A quantidade de ingressos vendidos foi de: " + qtdIngressosVen + " e estao disponiveis ainda: " + qtdIngressos);
				
				return this.qtdIngressosVen;
		}
	
	}