package ca.vulpovile.interim.protocolv1.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import ca.vulpovile.interim.protocolv1.NetHandler;

public class Packet5LeaveGroup extends Packet{
	public int groupId;
	public byte[] reason;
	public Packet5LeaveGroup(){}
	public Packet5LeaveGroup(int groupId, String reason)
	{
		this.groupId = groupId;
		this.reason = reason.getBytes();
	}
	
	@Override
	public void sendPacket(DataOutputStream stream) throws IOException {
		stream.writeInt(groupId);
		stream.writeInt(reason.length);
		stream.write(reason);
	}
	@Override
	public void getPacket(DataInputStream stream) throws IOException {
		groupId = stream.readInt();
		reason = new byte[stream.readInt()];
		stream.readFully(reason);
	}
	@Override
	public void handlePacket(NetHandler handler) {
		handler.handlePacket(this);
	}
}
