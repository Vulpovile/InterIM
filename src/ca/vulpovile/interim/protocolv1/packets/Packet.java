package ca.vulpovile.interim.protocolv1.packets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;

import ca.vulpovile.interim.protocolv1.NetHandler;

public abstract class Packet {
	public static final int VERSION = 1;
	public static final int MINVERSION = 1;
	public abstract void sendPacket(DataOutputStream stream) throws IOException;
	public abstract void getPacket(DataInputStream stream) throws IOException;
	public static HashMap<Byte, Class<?>> packets = new HashMap<Byte, Class<?>>();
	public static HashMap<Class<?>, Byte> ids = new HashMap<Class<?>, Byte>();
	private static byte currentId = 0;
	public static void registerPacket(Class<?> packet)
	{
		packets.put(currentId, packet);
		ids.put(packet, currentId);
		currentId++;
	}
	static
	{
		registerPacket(Packet0Disconnect.class);
		registerPacket(Packet1Identify.class);
		registerPacket(Packet2RegisterField.class);
		registerPacket(Packet3Alert.class);
		registerPacket(Packet4JoinGroup.class);
		registerPacket(Packet5LeaveGroup.class);
	}
	public final byte getId()
	{
		return ids.get(this.getClass());
	}
	public static Class<?> getPacket(int id)
	{
		return packets.get(id);
	}
	public static byte getId(Class<?> packet)
	{
		return ids.get(packet);
	}
	public abstract void handlePacket(NetHandler handler);
}
