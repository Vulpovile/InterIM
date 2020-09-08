package ca.vulpovile.interim.protocolv1.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import ca.vulpovile.interim.protocolv1.NetHandler;

public class Packet3Alert extends Packet{
	public byte[] message;
	public byte[] details;
	public Packet3Alert(){}
	public static enum AlertType
	{
		INFO,
		QUESTION,
		WARN,
		ERROR
	};
	public AlertType type = AlertType.INFO;
	public Packet3Alert(String message, String details, AlertType type)
	{
		this.message = message.getBytes();
		this.details = details.getBytes();
		this.type = type;
	}
	
	@Override
	public void sendPacket(DataOutputStream stream) throws IOException {
		stream.writeInt(message.length);
		stream.write(message);
		stream.writeInt(details.length);
		stream.write(details);
		stream.writeByte((byte)type.ordinal());
	}
	@Override
	public void getPacket(DataInputStream stream) throws IOException {
		message = new byte[stream.readInt()];
		stream.readFully(message);
		details = new byte[stream.readInt()];
		stream.readFully(details);
		type = AlertType.values()[stream.readByte()];
	}
	@Override
	public void handlePacket(NetHandler handler) {
		handler.handlePacket(this);
	}
}
