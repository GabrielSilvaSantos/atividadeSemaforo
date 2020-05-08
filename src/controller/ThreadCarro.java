package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {
	private int idCarro;
	static int sentido;
	private Semaphore semaforo;

	public ThreadCarro(int idCarro, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}

	public void run() {
		carroChegando();
		try {
			// P (Acquire)
			semaforo.acquire();
			carroFarol();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// V (Release);
			semaforo.release();
			carroPassou();
		}
	}

	private void carroChegando() {
		try {
			sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("O CARRO" + "#" + idCarro + " chegou no farol ");
	}

	private void carroFarol() {

		try {
			sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		switch (sentido) {
		case 0:
			System.out.println("\nO CARRO" + "#" + idCarro + " parou no SUL e saiu NORTE");
			break;
		case 1:
			System.out.println("\nO CARRO" + "#" + idCarro + " parou no NORTE e saiu SUL");
			break;
		case 2:
			System.out.println("\nO CARRO" + "#" + idCarro + " parou no LESTE e saiu OESTE");
			break;
		case 3:
			System.out.println("\nO CARRO" + "#" + idCarro + " parou no OESTE e saiu LESTE");
			break;
		}
		sentido++;
	}

	private void carroPassou() {
		System.out.println("proximo carro liberado");
	}
}
