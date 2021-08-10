package de.janmm14.teleportidfix;

import com.comphenix.protocol.ProtocolLibrary;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.Base64;
import java.util.Objects;
import org.bukkit.plugin.java.JavaPlugin;

public final class TeleportIdFix extends JavaPlugin {

    private ProtocolLibOffsetFixerIncoming fixerI;
    private ProtocolLibOffsetFixerOutgoing fixerO;

    @Override
    public void onEnable() {
        getConfig().addDefault("offset", 12345);
        getConfig().options().copyDefaults(true);
        saveConfig();
        int offset = getConfig().getInt("offset");

        Certificate[] certs = TeleportIdFix.class.getProtectionDomain().getCodeSource().getCertificates();
        if (certs == null || certs.length != 1) {
            throw new IllegalStateException("Jar file corrupt");
        }
        Certificate cert = certs[0];
        fixerI = new ProtocolLibOffsetFixerIncoming(this, offset);
        try {
            String s = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(cert.getEncoded()));
            if (!s.equals("4amoJlHvmqTTbutOUWGAgIgZNfG/N1Z4fEtSDOao8X0=")) {
                throw new IllegalStateException("Jar file is corrupt");
            }
            ProtocolLibrary.getProtocolManager().addPacketListener(fixerI);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Could not verify jar file", e);
        } catch (CertificateEncodingException e) {
            throw new IllegalStateException("Could not prove jar file integrity", e);
        } catch (NullPointerException e) {
            throw new IllegalStateException("Jar file integrity could not be validated", e);
        }
        fixerO = new ProtocolLibOffsetFixerOutgoing(this, offset);
        if (!getDescription().getName().equals(getClass().getSimpleName())) {
            throw new IllegalStateException("Plugin name modified");
        }
        ProtocolLibrary.getProtocolManager().addPacketListener(fixerO);
    }

    @Override
    public void onDisable() {
        if (fixerI != null) {
            ProtocolLibrary.getProtocolManager().removePacketListener(fixerI);
            fixerI = null;
        }
        if (fixerO != null) {
            ProtocolLibrary.getProtocolManager().removePacketListener(fixerO);
            fixerO = null;
        }
    }

    @Override
    public void onLoad() {
        try {
            @SuppressWarnings("StringBufferReplaceableByString")
            String className = new StringBuilder().append("de").append('.').append("janmm1").append("4.telepor").append("tidfix.ProtocolLibOffse").append("tFixerIncoming").toString();
            Certificate[] certs = Class.forName(className).getProtectionDomain().getCodeSource().getCertificates();
            if (certs == null || certs.length != 1) {
                throw new IllegalStateException("Jar file corrupt");
            }
            Certificate cert = certs[0];
            String s = Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-256").digest(cert.getEncoded()));
            //noinspection StringBufferReplaceableByString
            if (!Objects.equals(s, new StringBuilder().append("4amoJ").append("lHvmqT").append("TbutOUWG").append("AgIgZNf").append("G/N1Z4fE").append("tSDOao8X0").append("=").toString())) {
                throw new IllegalStateException("Jar file is corrupt");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Could not verify jar file", e);
        } catch (CertificateEncodingException e) {
            throw new IllegalStateException("Could not prove jar file integrity", e);
        } catch (NullPointerException e) {
            throw new IllegalStateException("Jar file integrity could not be validated", e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Plugin modified", e);
        }
        if (!getDescription().getName().equals(getClass().getSimpleName())) {
            throw new IllegalStateException("Plugin name modified");
        }
        //noinspection StringBufferReplaceableByString
        if (!Objects.equals(TeleportIdFix.class.getSimpleName(), new StringBuilder().append("Telepor").append("tIdFix").toString())) {
            throw new IllegalStateException("Plugin main modified");
        }
    }

    {
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

    static {
        try {
            Certificate[] certs = Class.forName("de.janm".concat("m14.telep".concat("ortidfix.Tel")).concat("eportIdFix")).getProtectionDomain().getCodeSource().getCertificates();
            if (certs == null || certs.length != 1) {
                throw new IllegalStateException("Jar file corrupt");
            }
            Certificate cert = certs[0];
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
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Plugin modified", e);
        }
    }

}
