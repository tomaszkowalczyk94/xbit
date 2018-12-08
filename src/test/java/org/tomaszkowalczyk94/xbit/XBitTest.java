package org.tomaszkowalczyk94.xbit;

import junit.framework.TestCase;

public class XBitTest extends TestCase {

    public void testGetValueOfBits() {

        assertEquals(0b11,
                XBit8.valueOfUnsigned(0b00000011).getValueOfBits(1, 0)
        );
        assertEquals(0b00000011,
                XBit8.valueOfUnsigned(0b00000011).getValueOfBits(7, 0)
        );

        assertEquals(0b00100001,
                XBit8.valueOfUnsigned(0b01000011).getValueOfBits(7, 1)
        );

        assertEquals(0b010,
                XBit8.valueOfUnsigned(0b10111111).getValueOfBits(7, 6)
        );

        assertEquals(0xA,
                XBit16.valueOfUnsigned(0xABCD).getValueOfBits(15, 12)
        );

        assertEquals(0xB,
                XBit16.valueOfUnsigned(0xABCD).getValueOfBits(11, 8)
        );

        assertEquals(0xC,
                XBit16.valueOfUnsigned(0xABCD).getValueOfBits(7, 4)
        );

        assertEquals(0xD,
                XBit16.valueOfUnsigned(0xABCD).getValueOfBits(3, 0)
        );
    }
}