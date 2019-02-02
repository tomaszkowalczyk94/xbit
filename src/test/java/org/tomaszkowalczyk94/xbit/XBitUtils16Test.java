package org.tomaszkowalczyk94.xbit;

import junit.framework.TestCase;

public class XBitUtils16Test extends TestCase {

    public void testValueOfUnsigned() {
        assertEquals(0, XBit16.valueOfUnsigned(0).getValueContainer());
        assertEquals(-1, XBit16.valueOfUnsigned(65535).getValueContainer());
        assertEquals(1, XBit16.valueOfUnsigned(1).getValueContainer());
        assertEquals(32767, XBit16.valueOfUnsigned(32767).getValueContainer());
        assertEquals(-32768, XBit16.valueOfUnsigned(32768).getValueContainer());
        assertEquals(-25536, XBit16.valueOfUnsigned(40000).getValueContainer());

        assertEquals(256, XBit16.valueOfUnsigned(256).getUnsignedValue());

        testNumberFormatException(()-> XBit16.valueOfUnsigned(-1));
        testNumberFormatException(()-> XBit16.valueOfUnsigned(65536));
    }

    public void testValueOfSigned() {
        assertEquals(0, XBit16.valueOfSigned(0).getValueContainer());
        assertEquals(1, XBit16.valueOfSigned(1).getValueContainer());
        assertEquals(127, XBit16.valueOfSigned(127).getValueContainer());
        assertEquals(100, XBit16.valueOfSigned(100).getValueContainer());

        testNumberFormatException(()-> XBit16.valueOfSigned(-32769));
        testNumberFormatException(()-> XBit16.valueOfSigned(32768));
    }

    public void testGetBit() {
        assertEquals(false, XBit16.valueOfSigned(0).getBit(0));
        assertEquals(false, XBit16.valueOfUnsigned(0).getBit(0));

        assertEquals(true, XBit16.valueOfSigned(1).getBit(0));
        assertEquals(false, XBit16.valueOfSigned(1).getBit(1));

        assertEquals(true, XBit16.valueOfUnsigned(1).getBit(0));
        assertEquals(false, XBit16.valueOfUnsigned(1).getBit(1));

        assertEquals(false, XBit16.valueOfUnsigned(255).getBit(8));
        assertEquals(true, XBit16.valueOfUnsigned(255).getBit(7));
        assertEquals(true, XBit16.valueOfUnsigned(255).getBit(6));
        assertEquals(true, XBit16.valueOfUnsigned(255).getBit(5));
        assertEquals(true, XBit16.valueOfUnsigned(255).getBit(4));
        assertEquals(true, XBit16.valueOfUnsigned(255).getBit(3));
        assertEquals(true, XBit16.valueOfUnsigned(255).getBit(2));
        assertEquals(true, XBit16.valueOfUnsigned(255).getBit(1));
        assertEquals(true, XBit16.valueOfUnsigned(255).getBit(0));

        assertEquals(false, XBit16.valueOfUnsigned(100).getBit(7));
        assertEquals(true, XBit16.valueOfUnsigned(100).getBit(6));
        assertEquals(true, XBit16.valueOfUnsigned(100).getBit(5));
        assertEquals(false, XBit16.valueOfUnsigned(100).getBit(4));
        assertEquals(false, XBit16.valueOfUnsigned(100).getBit(3));
        assertEquals(true, XBit16.valueOfUnsigned(100).getBit(2));
        assertEquals(false, XBit16.valueOfUnsigned(100).getBit(1));
        assertEquals(false, XBit16.valueOfUnsigned(100).getBit(0));

    }

    public void testGetSignedValue() {
        assertEquals(100, XBit16.valueOfUnsigned(100).getSignedValue());
        assertEquals(-25536, XBit16.valueOfUnsigned(40000).getSignedValue());

    }

    public void testGetUnsignedValue() {
        assertEquals(200, XBit16.valueOfUnsigned(200).getUnsignedValue());
        assertEquals(65480, XBit16.valueOfSigned(-56).getUnsignedValue());

    }

    public void testGetHighByte() {
        assertEquals(1, XBit16.valueOfUnsigned(257).getFirstByte().getUnsignedValue());
        assertEquals(1, XBit16.valueOfUnsigned(256).getFirstByte().getUnsignedValue());
        assertEquals(0, XBit16.valueOfUnsigned(255).getFirstByte().getUnsignedValue());
        assertEquals(255, XBit16.valueOfUnsigned(0xFFFF).getFirstByte().getUnsignedValue());
    }

    public void testGetLowByte() {
        assertEquals(1, XBit16.valueOfUnsigned(257).getSecondByte().getUnsignedValue());
        assertEquals(0, XBit16.valueOfUnsigned(256).getSecondByte().getUnsignedValue());
        assertEquals(255, XBit16.valueOfUnsigned(255).getSecondByte().getUnsignedValue());
        assertEquals(255, XBit16.valueOfUnsigned(0xFFFF).getSecondByte().getUnsignedValue());
    }

    public void testValueOfHighAndLow() {
        assertEquals(256,
                XBit16.valueOfHighAndLow(
                        XBit8.valueOfUnsigned(1),
                        XBit8.valueOfUnsigned(0)
                ).getUnsignedValue()
        );

        assertEquals(255,
                XBit16.valueOfHighAndLow(
                        XBit8.valueOfUnsigned(0),
                        XBit8.valueOfUnsigned(255)
                ).getUnsignedValue()
        );
        assertEquals(0xFFFF,
                XBit16.valueOfHighAndLow(
                        XBit8.valueOfUnsigned(255),
                        XBit8.valueOfUnsigned(255)
                ).getUnsignedValue()
        );
    }


    private void testNumberFormatException(Runnable function) {
        try{
            function.run();
            fail();
        } catch (NumberFormatException e) {
            return;
        }

    }



}