package Client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;



public class Client {
	
	public static void main(String[] args) {
		DataInputStream input;
		BufferedInputStream bis;
		BufferedOutputStream bos;
		
		int in;
		
		byte[] byteArray;
		//Fichero que se va transferir 
		
		final String filename = "C:/Users/Alejandro/Documents/Google.docx";
		
		try {
			final File localFile = new File( filename);
			Socket client = new Socket("localhhost", 5000);
			bis = new BufferedInputStream(new FileInputStream(localFile));
			bos = new BufferedOutputStream(client.getOutputStream());
			//Enviamos el archivo 
			DataOutputStream dos = new DataOutputStream(client.getOutputStream());
			dos.writeUTF(localFile.getName());
			//Enviar el fichero 
			byteArray = new byte[8192];
			while ((in = bis.read(byteArray)) != -1) {
				bos.write(byteArray, 0, in);
			}
			bis.close();
			bos.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.print(e);
		}
	}

}
