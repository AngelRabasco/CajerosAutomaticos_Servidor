package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GesConect {

	private Integer port = null;
	private Socket socket = null;

	private ObjectInputStream inputStream = null;
	private ObjectOutputStream outputStream = null;

	private Object obj;

	public GesConect(Integer port){
        this.port = port;
    }

		public Object getObject() {
			try (ServerSocket serverSocket = new ServerSocket(port)) {
				socket = serverSocket.accept();
				inputStream = new ObjectInputStream(socket.getInputStream());
				obj = inputStream.readObject();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return obj;
		}


	public void sendObject(Object obj) {
		
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
