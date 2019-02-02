package org.tomaszkowalczyk94.xbit;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class XBit16 extends XBit implements Serializable {

    public static final short MIN_SIGNED_VALUE = -32768;
    public static final short MAX_SIGNED_VALUE = 32767;

    public static final int MIN_UNSIGNED_VALUE = 0;
    public static final int MAX_UNSIGNED_VALUE = 65535;

    public static final int SIZE = 16;

    @Override
    public short getSize() {
        return SIZE;
    }

    @Override
    public int getMinSignedValue() {
        return MIN_SIGNED_VALUE;
    }

    @Override
    public int getMaxSignedValue() {
        return MAX_SIGNED_VALUE;
    }

    @Override
    public int getMinUnsignedValue() {
        return MIN_UNSIGNED_VALUE;
    }

    @Override
    public int getMaxUnsignedValue() {
        return MAX_UNSIGNED_VALUE;
    }

    @Override
    protected XBit createNewOfUnsigned(int value) {
        return valueOfUnsigned(value);
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

    public static XBit16 valueOfSigned(int value) {
        if(value <MIN_SIGNED_VALUE || value > MAX_SIGNED_VALUE) {
            throw new NumberFormatException();
        }

        return new XBit16((short)value);
    }

    /**
     * in big endian. alias for valueOfHighAndLowInBigEndian
     */
    public static XBit16 valueOfHighAndLow(XBit8 high, XBit8 low) {
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.put((byte)high.getSignedValue());
        bb.put((byte)low.getSignedValue());
        return new XBit16(bb.getShort(0));
    }

    public static XBit16 valueOfHighAndLowInBigEndian(XBit8 high, XBit8 low) {
        return valueOfHighAndLow(high, low);
    }

    public static XBit16 valueOfHighAndLowInLittleEndian(XBit8 high, XBit8 low) {
        //@todo not implemented yet
        return null;
    }



    public int getSignedValue() {
        return getSignedInBigEndian();
    }

    public int getUnsignedValue() {
        return (valueContainer & 0xFFFF);
    }

    public int getSignedInBigEndian() {
        return valueContainer;
    }

    public int getSignedInLittleEndian() {
        //@todo not implemented yet
        return 0;
    }

    public int getUnsignedValueInLittleEndian() {
        //@todo not implemented yet
        return 0;
    }

    public int getSignedValueInLittleEndian() {
        //@todo not implemented yet
        return 0;
    }

    public XBit8 getFirstByte() {
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

    public XBit8 getSecondByte() {
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
