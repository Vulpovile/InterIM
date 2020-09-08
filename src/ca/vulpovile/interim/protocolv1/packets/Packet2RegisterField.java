package ca.vulpovile.interim.protocolv1.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import ca.vulpovile.interim.protocolv1.NetHandler;

public class Packet2RegisterField extends Packet{
	public byte[] title;
	public byte[] input;
	public static enum Type
	{
		INTROHTML,
		TEXT,
		PASSWORD,
		DROPDOWN,
		RADIO,
		CHECKBOX,
		TIMEOUT
	}
	public Type type;
	public Packet2RegisterField(){}
	public Packet2RegisterField(String title, byte[] values, Type type)
	{
		this.type = type;
		this.input = values;
		this.title = title.getBytes();
	}
	public Packet2RegisterField(String title, String values, Type type)
	{
		this.type = type;
		this.input = values.getBytes();
		this.title = title.getBytes();
	}
	
	public Packet2RegisterField(byte[] title, String values, Type type)
	{
		this.type = type;
		this.input = values.getBytes();
		this.title = title;
	}
	public Packet2RegisterField(byte[] title, byte[] values, Type type) {
		this.type = type;
		this.input = values;
		this.title = title;
	}
	@Override
	public void sendPacket(DataOutputStream stream) throws IOException {
		stream.writeInt(title.length);
		stream.writeInt(input.length);
		stream.write(title);
		stream.write(input);
		stream.writeByte((byte)type.ordinal());
	}
	@Override
	public void getPacket(DataInputStream stream) throws IOException {
		title = new byte[stream.readInt()];
		input = new byte[stream.readInt()];
		stream.readFully(title);
		stream.readFully(input);
		type = Type.values()[stream.readByte()];
	}
	@Override
	public void handlePacket(NetHandler handler) {
		handler.handlePacket(this);
	}
}
