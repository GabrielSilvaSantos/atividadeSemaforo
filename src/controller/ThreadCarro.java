package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread{
	private int idCarro;
	private static int posicaoChegada;
	private static int posicaoSaida;
	private Semaphore semaforo;

	public ThreadCarro(int idCarro, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}

	public void run() {
		try {
			// P (Acquire)
			semaforo.acquire();
			carroParado();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// V (Release);
			semaforo.release();
			carroSaiu();
		}
	}


	private void carroParado() {
		System.out.println("#" + idCarro + " parou no sinal.");
		int tempo = (int) ((Math.random() * 401) + 100);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void carroSaiu() {
		posicaoSaida++;
		System.out.println("#" + idCarro + " foi o " + posicaoSaida + "o. a sair");
	}
}
