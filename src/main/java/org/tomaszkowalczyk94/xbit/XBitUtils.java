package org.tomaszkowalczyk94.xbit;

import lombok.Builder;

public class XBitUtils {

    private XBitUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static class Arithmetic8bitResult {
        public XBit8 result;
        boolean carry = false;
        boolean overflow = false;
    }

    /**
     * increment
     * @return
     */
    public static XBit8 incrementBy(XBit8 value, int incrementer) {
        int unsignedValue = value.getUnsignedValue();
        unsignedValue += incrementer;
        unsignedValue = unsignedValue & XBit8.MAX_UNSIGNED_VALUE;
        return XBit8.valueOfUnsigned(unsignedValue);
    }

    public static XBit16 incrementBy(XBit16 value, int incrementer) {
        int unsignedValue = value.getUnsignedValue();
        unsignedValue += incrementer;
        unsignedValue = unsignedValue & XBit16.MAX_UNSIGNED_VALUE;
        return XBit16.valueOfUnsigned(unsignedValue);
    }

    public static XBit8 negativeOf(XBit8 value) {
        int buf = (~value.getUnsignedValue()) & XBit8.MAX_UNSIGNED_VALUE;

        return XBitUtils.incrementBy(
                XBit8.valueOfUnsigned(buf),
                1
        );
    }

    public static Arithmetic8bitResult addTwo8bits(XBit8 value1, XBit8 value2) {
        Arithmetic8bitResult ret = new Arithmetic8bitResult();
        int result = value1.getUnsignedValue() + value2.getUnsignedValue();
        if(result>XBit8.MAX_UNSIGNED_VALUE) {
            ret.carry = true;
        }
        ret.result = XBit8.valueOfUnsigned(result & XBit8.MAX_UNSIGNED_VALUE);

        ret.overflow =((value1.getBit(7) && value2.getBit(7) && !ret.result.getBit(7)) ||
                (!value1.getBit(7) && !value2.getBit(7) && ret.result.getBit(7)));

        return ret;
    }

    public static Arithmetic8bitResult subTwo8bits(XBit8 value1, XBit8 value2) {
        return addTwo8bits(
                value1,
                negativeOf(value2)
        );
    }

}
