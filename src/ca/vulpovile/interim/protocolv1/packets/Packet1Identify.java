package ca.vulpovile.interim.protocolv1.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import ca.vulpovile.interim.protocolv1.NetHandler;
//This packet cannot be replaced in any later version
public class Packet1Identify extends Packet{
	public byte[] login;
	public byte[] password;
	public boolean extraData;
	public int version;
	public int minVersion;
	

	public Packet1Identify(){}
	public Packet1Identify(String login, byte[] password, boolean extraData){
		this.login = login.getBytes();
		this.password = password;
		this.extraData = extraData;
	}
	
	
	@Override
	public void sendPacket(DataOutputStream stream) throws IOException {
		stream.writeInt(VERSION);
		stream.writeInt(MINVERSION);
		stream.writeInt(login.length);
		stream.write(login);
		stream.writeInt(password.length);
		stream.write(password);
		stream.writeBoolean(extraData);
	}
	@Override
	public void getPacket(DataInputStream stream) throws IOException {
		version = stream.readInt();
		minVersion = stream.readInt();
		login = new byte[stream.readInt()];
		stream.readFully(login);
		password = new byte[stream.readInt()];
		stream.readFully(password);
		extraData = stream.readBoolean();
	}	
	@Override
	public void handlePacket(NetHandler handler) {
		handler.handlePacket(this);
	}
}
