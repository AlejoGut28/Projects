package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
      
	public static void main(String[] args) {
		ServerSocket server;
		Socket connection;
		
		DataOutputStream output;
		BufferedInputStream bis;
		BufferedOutputStream bos;
		
		byte[] receivedData;
		int in;
		String file;
		
		try {
			//Servidor 
			server = new ServerSocket (5000);
			while (true) {
			   //Conexiones 
				connection = server.accept();
				//Buffer 1024 bytes
				receivedData = new byte[1024];
				bis = new BufferedInputStream (connection.getInputStream());
				DataInputStream dis = new DataInputStream(connection.getInputStream());
				//Recibimos el fichero 
				file = dis.readUTF();
				file = file.substring(file.indexOf('\\')+1, file.length());
				//Guardar el fichero
			    bos = new BufferedOutputStream(new FileOutputStream(file));
			    while((in = bis.read(receivedData)) != -1) {
			    	 bos.write(receivedData, 0 , in);
			    }
			    bos.close();
			    dis.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.print(e);
		}
		
	}
}
