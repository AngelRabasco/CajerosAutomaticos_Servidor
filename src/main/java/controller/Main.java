package controller;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerController server = new ServerController();
		Thread threadServer = new Thread(server);
		threadServer.start();
	}

}
