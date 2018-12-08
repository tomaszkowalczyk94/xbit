package org.tomaszkowalczyk94.xbit;

public class XBitUtils {

    private XBitUtils() {
        throw new IllegalStateException("Utility class");
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

}
