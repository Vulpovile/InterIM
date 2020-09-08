package ca.vulpovile.interim.protocolv1.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import ca.vulpovile.interim.protocolv1.NetHandler;
//This packet cannot be replaced in any later version
public class Packet0Disconnect extends Packet{
	public byte[] message;
	public Packet0Disconnect(){}
	public Packet0Disconnect(String message)
	{
		this.message = message.getBytes();
	}
	
	@Override
	public void sendPacket(DataOutputStream stream) throws IOException {
		stream.writeInt(message.length);
		stream.write(message);
	}
	@Override
	public void getPacket(DataInputStream stream) throws IOException {
		message = new byte[stream.readInt()];
		stream.readFully(message);
	}
	@Override
	public void handlePacket(NetHandler handler) {
		handler.handlePacket(this);
	}
}
