package ca.vulpovile.interim.protocolv1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import ca.vulpovile.interim.Server;
import ca.vulpovile.interim.protocolv1.packets.*;

public abstract class NetHandler extends Thread{
	protected DataOutputStream dos;
	protected DataInputStream dis;
	protected Socket socket;
	
	public abstract void run();
	
	public NetHandler(Socket socket) throws IOException
	{
		this.socket = socket;
		/*
		dos = new DataOutputStream(socket.getOutputStream());
		dis = new DataInputStream(socket.getInputStream());*/
	}
	
	public synchronized void sendPacket(Packet packet)
	{
		try {
		byte opcode = packet.getId();
			//Server.logger.severe("Attempted to send invalid packet " + opcode);
			dos.writeByte(opcode);
			packet.sendPacket(dos);
		} catch (IOException e) {
			disconnect();
			e.printStackTrace();
		}
		
	}
	public abstract void disconnect();
	public abstract void disconnect(String message);
	public abstract void handlePacket(Packet0Disconnect packet);
	public abstract void handlePacket(Packet1Identify packet);
	public abstract void handlePacket(Packet2RegisterField packet);
	public abstract void handlePacket(Packet3Alert packet);
	public abstract void handlePacket(Packet4JoinGroup packet);
	public abstract void handlePacket(Packet5LeaveGroup packet);
	public abstract void handlePacket(Packet6Message packet);
	public abstract void handlePacket(Packet7ListGroups packet);
	public abstract void handlePacket(Packet8ListUsers packet);
	
	public void handlePacket(Packet packet) {
		Server.logger.info("Client disconnected due to unusable packet");
		disconnect();
	}

	
}
