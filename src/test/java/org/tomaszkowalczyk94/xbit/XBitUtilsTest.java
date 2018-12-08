package org.tomaszkowalczyk94.xbit;

import junit.framework.TestCase;

public class XBitUtilsTest extends TestCase {

    public void testIncrementByFor8bit() {
        XBit8 value = XBit8.valueOfUnsigned(100);
        XBit8 incrementedValue = XBitUtils.incrementBy(value, 1);
        assertEquals(101, incrementedValue.getUnsignedValue());
    }

    public void testIncrementByWithOverloadFor8bit() {
        XBit8 value = XBit8.valueOfUnsigned(255);
        XBit8 incrementedValue = XBitUtils.incrementBy(value, 1);
        assertEquals(0, incrementedValue.getUnsignedValue());
    }

    public void testIncrementByNegativeValueFor8bit() {
        XBit8 value = XBit8.valueOfUnsigned(0);
        XBit8 incrementedValue = XBitUtils.incrementBy(value, -1);
        assertEquals(255, incrementedValue.getUnsignedValue());
    }

    public void testIncrementByFor16bit() {
        XBit16 value = XBit16.valueOfUnsigned(1000);
        XBit16 incrementedValue = XBitUtils.incrementBy(value, 1);
        assertEquals(1001, incrementedValue.getUnsignedValue());
    }

    public void testIncrementByWithOverloadFor16bit() {
        XBit16 value = XBit16.valueOfUnsigned(65535);
        XBit16 incrementedValue = XBitUtils.incrementBy(value, 1);
        assertEquals(0, incrementedValue.getUnsignedValue());
    }

    public void testIncrementByNegativeValueFor16bit() {
        XBit16 value = XBit16.valueOfUnsigned(0);
        XBit16 incrementedValue = XBitUtils.incrementBy(value, -1);
        assertEquals(65535, incrementedValue.getUnsignedValue());
    }
}