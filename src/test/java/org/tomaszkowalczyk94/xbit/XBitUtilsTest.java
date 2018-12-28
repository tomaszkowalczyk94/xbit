package org.tomaszkowalczyk94.xbit;

import junit.framework.TestCase;
import org.junit.Assert;

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

    public void testNegativeOf() {
        assertEquals(-10,XBitUtils.negativeOf(
                XBit8.valueOfSigned(10)
        ).getSignedValue());

        assertEquals(-127,XBitUtils.negativeOf(
                XBit8.valueOfSigned(127)
        ).getSignedValue());

        assertEquals(127,XBitUtils.negativeOf(
                XBit8.valueOfSigned(-127)
        ).getSignedValue());

        assertEquals(-128,XBitUtils.negativeOf(
                XBit8.valueOfSigned(-128)
        ).getSignedValue());
    }

    public void testAddTwo8bits() {
        XBitUtils.Arithmetic8bitResult arithmetic8bitResult;

        // ================
        arithmetic8bitResult = XBitUtils.addTwo8bits(
                XBit8.valueOfUnsigned(255),
                XBit8.valueOfUnsigned(255)
        );
        Assert.assertEquals(254, arithmetic8bitResult.result.getUnsignedValue());
        Assert.assertEquals(true, arithmetic8bitResult.carry);
        Assert.assertEquals(false, arithmetic8bitResult.overflow);
        // ================
        arithmetic8bitResult = XBitUtils.addTwo8bits(
                XBit8.valueOfSigned(127),
                XBit8.valueOfSigned(127)
        );
        Assert.assertEquals(-2, arithmetic8bitResult.result.getSignedValue());
        Assert.assertEquals(false, arithmetic8bitResult.carry);
        Assert.assertEquals(true, arithmetic8bitResult.overflow);
        // ================
        arithmetic8bitResult = XBitUtils.addTwo8bits(
                XBit8.valueOfUnsigned(0),
                XBit8.valueOfUnsigned(255)
        );
        Assert.assertEquals(255, arithmetic8bitResult.result.getUnsignedValue());
        Assert.assertEquals(false, arithmetic8bitResult.carry);
        Assert.assertEquals(false, arithmetic8bitResult.overflow);
        // ================
        arithmetic8bitResult = XBitUtils.addTwo8bits(
                XBit8.valueOfSigned(-20),
                XBit8.valueOfSigned(-10)
        );
        Assert.assertEquals(-30, arithmetic8bitResult.result.getSignedValue());
        Assert.assertEquals(true, arithmetic8bitResult.carry);
        Assert.assertEquals(false, arithmetic8bitResult.overflow);
        // ================
        arithmetic8bitResult = XBitUtils.addTwo8bits(
                XBit8.valueOfSigned(-128),
                XBit8.valueOfSigned(-128)
        );
        Assert.assertEquals(0, arithmetic8bitResult.result.getSignedValue());
        Assert.assertEquals(true, arithmetic8bitResult.carry);
        Assert.assertEquals(true, arithmetic8bitResult.overflow);
    }

    public void testSubTwo8bits() {
        XBitUtils.Arithmetic8bitResult arithmetic8bitResult;

        // ================
        arithmetic8bitResult = XBitUtils.subTwo8bits(
                XBit8.valueOfSigned(50),
                XBit8.valueOfSigned(10)
        );
        Assert.assertEquals(40, arithmetic8bitResult.result.getSignedValue());
        Assert.assertEquals(true, arithmetic8bitResult.carry);
        Assert.assertEquals(false, arithmetic8bitResult.overflow);
    }

}