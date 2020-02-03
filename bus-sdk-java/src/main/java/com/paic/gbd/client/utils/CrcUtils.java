package com.paic.gbd.client.utils;

import java.io.IOException;
import java.util.zip.Checksum;

public abstract class CrcUtils {
    private static final ThreadLocal<Checksum> checksumThreadLocal = new ThreadLocal<Checksum>() {
        @Override
        protected Checksum initialValue() {
            return new PureJavaCrc32C();
        }
    };

    public static int getCrc32(byte[] payload) throws Exception {
        return getCrc32(payload, 0, payload.length);
    }

    public static int getCrc32(byte[] payload, int offset, int length) throws IOException {
        if (payload == null) {
            throw new IOException("Bytes is null. can't cal crc value");
        }
        Checksum checksum = checksumThreadLocal.get();
        checksum.reset();
        checksum.update(payload, offset, length);
        return (int)checksum.getValue();
    }
}
