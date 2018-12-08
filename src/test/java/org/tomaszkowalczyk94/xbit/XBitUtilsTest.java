package org.tomaszkowalczyk94.xbit;

import junit.framework.TestCase;

public class XBitUtilsTest extends TestCase {

    public void testIncrementBy() {
        XBit8 value = XBit8.valueOfUnsigned(100);
        XBit8 incrementedValue = XBitUtils.incrementBy(value, 1);
        assertEquals(101, incrementedValue.getUnsignedValue());
    }

    public void testIncrementByWithOverload() {
        XBit8 value = XBit8.valueOfUnsigned(255);
        XBit8 incrementedValue = XBitUtils.incrementBy(value, 1);
        assertEquals(0, incrementedValue.getUnsignedValue());
    }

    public void testIncrementByNegativeValue() {
        XBit8 value = XBit8.valueOfUnsigned(0);
        XBit8 incrementedValue = XBitUtils.incrementBy(value, -1);
        assertEquals(255, incrementedValue.getUnsignedValue());
    }
}