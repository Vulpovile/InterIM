package ca.vulpovile.interim.protocolv1.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import ca.vulpovile.interim.protocolv1.NetHandler;

public class Packet4JoinGroup extends Packet{
	public int groupId;
	public Packet4JoinGroup(){}
	public Packet4JoinGroup(int groupId)
	{
		this.groupId = groupId;
	}
	
	@Override
	public void sendPacket(DataOutputStream stream) throws IOException {
		stream.writeInt(groupId);
	}
	@Override
	public void getPacket(DataInputStream stream) throws IOException {
		groupId = stream.readInt();
	}
	@Override
	public void handlePacket(NetHandler handler) {
		handler.handlePacket(this);
	}
}
