package org.tomaszkowalczyk94.xbit;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class XBit16 extends XBit {
    public static final short MIN_SIGNED_VALUE = -32768;
    public static final short MAX_SIGNED_VALUE = 32767;

    public static final int MIN_UNSIGNED_VALUE = 0;
    public static final int MAX_UNSIGNED_VALUE = 65535;

    public static final int SIZE = 16;

    @Override
    public short getSize() {
        return SIZE;
    }

    protected XBit16(short valueContainer) {
        this.valueContainer = valueContainer;
    }



    public static XBit16 valueOfUnsigned(int value) {
        if(value <MIN_UNSIGNED_VALUE || value > MAX_UNSIGNED_VALUE) {
            throw new NumberFormatException();
        }

        return new XBit16((short)value);
    }

    public static XBit16 valueOfSigned(short value) {
        return new XBit16(value);
    }

    public static XBit16 valueOfSigned(int value) {
        if(value <MIN_SIGNED_VALUE || value > MAX_SIGNED_VALUE) {
            throw new NumberFormatException();
        }

        return new XBit16((short)value);
    }

    public static XBit16 valueOfHighAndLow(XBit8 high, XBit8 low) {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.put(high.getSignedValue());
        bb.put(low.getSignedValue());
       return new XBit16(bb.getShort(0));
    }

    public short getSignedValue() {
        return (short)valueContainer;
    }

    public int getUnsignedValue() {
        return (valueContainer & 0xFFFF);
    }

    public XBit8 getHighByte() {
        return XBit8.valueOfBooleanArray(new boolean[]{
                getBit(15),
                getBit(14),
                getBit(13),
                getBit(12),
                getBit(11),
                getBit(10),
                getBit(9),
                getBit(8),
        });
    }

    public XBit8 getLowByte() {
        return XBit8.valueOfBooleanArray(new boolean[]{
                getBit(7),
                getBit(6),
                getBit(5),
                getBit(4),
                getBit(3),
                getBit(2),
                getBit(1),
                getBit(0),
        });
    }
}
