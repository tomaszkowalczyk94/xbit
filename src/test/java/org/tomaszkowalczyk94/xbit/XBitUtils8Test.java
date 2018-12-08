package org.tomaszkowalczyk94.xbit;

import junit.framework.TestCase;


public class XBitUtils8Test extends TestCase {



    public void testValueOfUnsigned() {
        assertEquals(0, XBit8.valueOfUnsigned(0).getValueContainer());
        assertEquals(-1, XBit8.valueOfUnsigned(255).getValueContainer());
        assertEquals(1, XBit8.valueOfUnsigned(1).getValueContainer());
        assertEquals(127, XBit8.valueOfUnsigned(127).getValueContainer());
        assertEquals(-128, XBit8.valueOfUnsigned(128).getValueContainer());

        testNumberFormatException(()-> XBit8.valueOfUnsigned(-1));
        testNumberFormatException(()-> XBit8.valueOfUnsigned(256));
    }

    public void testValueOfSigned() {
        assertEquals(0, XBit8.valueOfSigned(0).getValueContainer());
        assertEquals(1, XBit8.valueOfSigned(1).getValueContainer());
        assertEquals(127, XBit8.valueOfSigned(127).getValueContainer());
        assertEquals(100, XBit8.valueOfSigned(100).getValueContainer());

        testNumberFormatException(()-> XBit8.valueOfSigned(-129));
        testNumberFormatException(()-> XBit8.valueOfSigned(128));
    }

    public void testValueOfBooleanArray() {
        testNumberFormatException(()-> XBit8.valueOfBooleanArray(new boolean[]{true}));

        assertEquals(255, XBit8.valueOfBooleanArray(new boolean[]{true,true,true,true,true,true,true,true}).getUnsignedValue());
        assertEquals(-84, XBit8.valueOfBooleanArray(new boolean[]{true,false,true,false,true,true,false,false}).getSignedValue());
    }

    public void testGetBit() {
        assertEquals(false, XBit8.valueOfSigned(0).getBit(0));
        assertEquals(false, XBit8.valueOfUnsigned(0).getBit(0));

        assertEquals(true, XBit8.valueOfSigned(1).getBit(0));
        assertEquals(false, XBit8.valueOfSigned(1).getBit(1));

        assertEquals(true, XBit8.valueOfUnsigned(1).getBit(0));
        assertEquals(false, XBit8.valueOfUnsigned(1).getBit(1));

        assertEquals(true, XBit8.valueOfUnsigned(255).getBit(7));
        assertEquals(true, XBit8.valueOfUnsigned(255).getBit(6));
        assertEquals(true, XBit8.valueOfUnsigned(255).getBit(5));
        assertEquals(true, XBit8.valueOfUnsigned(255).getBit(4));
        assertEquals(true, XBit8.valueOfUnsigned(255).getBit(3));
        assertEquals(true, XBit8.valueOfUnsigned(255).getBit(2));
        assertEquals(true, XBit8.valueOfUnsigned(255).getBit(1));
        assertEquals(true, XBit8.valueOfUnsigned(255).getBit(0));

        assertEquals(false, XBit8.valueOfUnsigned(100).getBit(7));
        assertEquals(true, XBit8.valueOfUnsigned(100).getBit(6));
        assertEquals(true, XBit8.valueOfUnsigned(100).getBit(5));
        assertEquals(false, XBit8.valueOfUnsigned(100).getBit(4));
        assertEquals(false, XBit8.valueOfUnsigned(100).getBit(3));
        assertEquals(true, XBit8.valueOfUnsigned(100).getBit(2));
        assertEquals(false, XBit8.valueOfUnsigned(100).getBit(1));
        assertEquals(false, XBit8.valueOfUnsigned(100).getBit(0));

    }

    public void testGetSignedValue() {
        assertEquals(100, XBit8.valueOfUnsigned(100).getSignedValue());
        assertEquals(-56, XBit8.valueOfUnsigned(200).getSignedValue());

    }

    public void testGetUnsignedValue() {
        assertEquals(200, XBit8.valueOfUnsigned(200).getUnsignedValue());
        assertEquals(200, XBit8.valueOfSigned(-56).getUnsignedValue());

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