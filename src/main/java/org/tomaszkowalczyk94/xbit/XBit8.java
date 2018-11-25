package org.tomaszkowalczyk94.xbit;

public class XBit8 extends XBit {
    public static final byte MIN_SIGNED_VALUE = -128;
    public static final byte MAX_SIGNED_VALUE = 127;

    public static final int MIN_UNSIGNED_VALUE = 0;
    public static final int MAX_UNSIGNED_VALUE = 255;

    public static final int SIZE = 8;

    @Override
    public short getSize() {
        return SIZE;
    }

    protected XBit8(byte valueContainer) {
        this.valueContainer = valueContainer;
    }

    public static XBit8 valueOfUnsigned(int value) {
        if(value <MIN_UNSIGNED_VALUE || value > MAX_UNSIGNED_VALUE) {
            throw new NumberFormatException();
        }

        return new XBit8((byte)value);
    }

    public static XBit8 valueOfSigned(byte value) {
        return new XBit8(value);
    }

    public static XBit8 valueOfSigned(int value) {
        if(value <MIN_SIGNED_VALUE || value > MAX_SIGNED_VALUE) {
            throw new NumberFormatException();
        }

        return new XBit8((byte)value);
    }

    public static XBit8 valueOfBooleanArray(boolean[] values) {
        if(values.length != SIZE) {
            throw new NumberFormatException();
        }
        byte b = 0;
        for(int i=0;i<8;i++) if(values[i]) b |= (128 >> i);
        return new XBit8(b);
    }

    public byte getSignedValue() {
        return (byte)valueContainer;
    }

    public short getUnsignedValue() {
        return (short)(valueContainer & 0xFF);
    }



}
