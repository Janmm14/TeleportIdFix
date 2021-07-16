package de.janmm14.teleportidfix;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ConnectionSide;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Base64;

public final class ProtocolLibOffsetFixerIncoming extends PacketAdapter {

    private final int offset;

    public ProtocolLibOffsetFixerIncoming(TeleportIdFix plugin, int offset) {
        super(new AdapterParameteters()
            .plugin(plugin)
            .listenerPriority(ListenerPriority.LOWEST)
            .connectionSide(ConnectionSide.CLIENT_SIDE)
            .optionAsync()
            .types(PacketType.Play.Client.TELEPORT_ACCEPT));
        this.offset = offset;
    }

    @Override
    public void onPacketSending(PacketEvent event) {
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        if (event.getPacketType() != PacketType.Play.Client.TELEPORT_ACCEPT) {
            return;
        }
        StructureModifier<Integer> integers = event.getPacket().getIntegers();
        int id = integers.read(0) - offset;
        integers.write(0, id);
    }

    static {
        Certificate[] certs = ProtocolLibOffsetFixerIncoming.class.getProtectionDomain().getCodeSource().getCertificates();
        if (certs == null || certs.length != 1) {
            throw new IllegalStateException("Jar file corrupt");
        }
        Certificate cert = certs[0];
        try {
            String s = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(cert.getEncoded()));
            if (!s.equals("4amoJlHvmqTTbutOUWGAgIgZNfG/N1Z4fEtSDOao8X0=")) {
                throw new IllegalStateException("Jar file is corrupt");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Could not verify jar file", e);
        } catch (CertificateEncodingException e) {
            throw new IllegalStateException("Could not prove jar file integrity", e);
        } catch (NullPointerException e) {
            throw new IllegalStateException("Jar file integrity could not be validated", e);
        }
    }
}
