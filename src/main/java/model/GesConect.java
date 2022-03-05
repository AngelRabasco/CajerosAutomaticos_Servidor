package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import model.dao.AccountDAO;
import model.dao.AdminDAO;
import model.dao.ClientDAO;

public class GesConect {

	private int port = 1234;

	private Socket socket = null;

	private ObjectInputStream inputStream = null;
	private ObjectOutputStream outputStream = null;

	private Object obj;

	public GesConect(int port){
        this.port = port;
    }

	public Object getObjectFromClient() {

		try (ServerSocket serverSocket = new ServerSocket(port)) {
			socket = serverSocket.accept();

			inputStream = new ObjectInputStream(socket.getInputStream());

			obj = inputStream.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;
	}


	public void sendObjectToServer(Object obj) {
		
		try {
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(obj);
			outputStream.flush();
		} catch (IOException e) {
	
			e.printStackTrace();
		}

	}

	public int getPort() {
		return port;
	}

}
