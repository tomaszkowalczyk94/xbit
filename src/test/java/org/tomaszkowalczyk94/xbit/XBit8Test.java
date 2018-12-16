package org.tomaszkowalczyk94.xbit;

import junit.framework.TestCase;

public class XBit8Test extends TestCase {

    public void testSetBit() {

        assertEquals(0b001,
                XBit8.valueOfUnsigned(0b001).setBit(1, false).getUnsignedValue()
        );

        assertEquals(0b001,
                XBit8.valueOfUnsigned(0b001).setBit(0, true).getUnsignedValue()
        );

        assertEquals(0b011,
                XBit8.valueOfUnsigned(0b001).setBit(1, true).getUnsignedValue()
        );

        assertEquals(0b01111111,
                XBit8.valueOfUnsigned(0b11111111).setBit(7, false).getUnsignedValue()
        );

        assertEquals(0b11111111,
                XBit8.valueOfUnsigned(0b01111111).setBit(7, true).getUnsignedValue()
        );

        assertEquals(0b10100000,
                XBit8.valueOfUnsigned(0b10000000).setBit(5, true).getUnsignedValue()
        );
    }
}