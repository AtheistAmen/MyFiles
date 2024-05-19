import java.net.ServerSocket;
import java.net.Socket;
//import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.lang.Thread;

interface UsedThings{
	public void out(Object obj); // to instead of "System.out.print()"
}

class UsedMethods implements UsedThings{
	public void out(Object obj){
		String types = new String(obj.getClass().getName());
		switch(types){
			case "Integer":
				System.out.print((int)obj);
				break;
			case "Byte":
				System.out.print((byte)obj);
				break;
			case "String":
				System.out.print((String)obj);
				break;
		}
	}
}

class Hosts extends UsedMethods{
	public Hosts(byte ports) throws Exception{
		ServerSocket sv = new ServerSocket((20000+ports));
		Socket __socket = sv.accept();
		out("<html><body style='text-align:center; color:rgb(0,40,216);'>---THIS HAS BUILT A SOCKET---</body><html>");

		DataOutputStream dos = new DataOutputStream(__socket.getOutputStream());
		//DataInputStream dis = new DataInputStream(__socket.getInputStream());

		dos.writeUTF("Hello");

		sv.close();
	}
}

public class WebDisplay{
	public static void main(String args[]) throws Exception{
		Thread thread1 = new Thread(){
			@SuppressWarnings("unused")
			Hosts h1 = new Hosts((byte)1);
		};
		thread1.start();
	}
}
