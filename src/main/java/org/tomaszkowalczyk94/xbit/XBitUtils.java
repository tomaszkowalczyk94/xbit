package org.tomaszkowalczyk94.xbit;


/**
 * Arithmetic binary operations
 */
@SuppressWarnings("WeakerAccess")
public class XBitUtils {

    private XBitUtils() {
        throw new IllegalStateException("Utility class");
    }

    @SuppressWarnings("WeakerAccess")
    public static class Arithmetic8bitResult {
        public XBit8 result;
        public boolean carry = false;
        public boolean overflow = false;
    }

    public static class Arithmetic16bitResult {
        public XBit16 result;
        public boolean carry = false;
        public boolean overflow = false;
    }

    public static XBit8 increment(XBit8 value) {
        return incrementBy(value, 1);
    }

    public static XBit16 increment(XBit16 value) {
        return incrementBy(value, 1);
    }

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

    public static XBit16 negativeOf(XBit16 value) {
        int buf = (~value.getUnsignedValue()) & XBit16.MAX_UNSIGNED_VALUE;

        return XBitUtils.incrementBy(
                XBit16.valueOfUnsigned(buf),
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

        ret.overflow = calculateOverflowFlagForAdd(value1.getSignBit(), value2.getSignBit(), ret.result.getSignBit());

        return ret;
    }

    /**
     * Subtracting two values, and return result, carry flag and overflow flag.
     * carry flag will be set when value1 < value2 (like in 8080, Z80, 8051, x86[1] and 68k families)
     * @param value1 first argument
     * @param value2 second argument
     */
    public static Arithmetic8bitResult subTwo8bits(XBit8 value1, XBit8 value2) {

        Arithmetic8bitResult ret =  addTwo8bits(
                value1,
                negativeOf(value2)
        );

        ret.overflow = calculateOverflowFlagForSub(
                value1.getSignBit(),
                value2.getSignBit(),
                ret.result.getSignBit()
        );

        ret.carry = (value1.getUnsignedValue() < value2.getUnsignedValue());

        return ret;
    }

    /**
     * Calculating overflow flag after adding. Method described
     * there: http://teaching.idallen.com/dat2343/10f/notes/040_overflow.txt
     *
     * ADDITION SIGN BITS
     *     num1sign num2sign sumsign
     *    ---------------------------
     *         0 0 0
     *  *OVER* 0 0 1 (adding two positives should be positive)
     *         0 1 0
     *         0 1 1
     *         1 0 0
     *         1 0 1
     *  *OVER* 1 1 0 (adding two negatives should be negative)
     *         1 1 1
     *
     * @param signBitFirstAdder most significant byte (it is sign byte) of first addition operand
     * @param signBitSecondAdder most significant byte (it is sign byte) of second addition operand
     * @param signBitResultOfAdding most significant byte (it is sign byte) of result of addition
     * @return overflow flag
     */
    private static boolean calculateOverflowFlagForAdd(
            boolean signBitFirstAdder,
            boolean signBitSecondAdder,
            boolean signBitResultOfAdding
    ) {
        return (
                (signBitFirstAdder && signBitSecondAdder && !signBitResultOfAdding) ||
                (!signBitFirstAdder && !signBitSecondAdder && signBitResultOfAdding)
        );
    }

    /**
     * Calculating overflow flag after subtraction. Method described
     * there: http://teaching.idallen.com/dat2343/10f/notes/040_overflow.txt
     * SUBTRACTION SIGN BITS
     *     num1sign num2sign sumsign
     *    ---------------------------
     *         0 0 0
     *         0 0 1
     *         0 1 0
     *  *OVER* 0 1 1 (subtracting a negative is the same as adding a positive)
     *  *OVER* 1 0 0 (subtracting a positive is the same as adding a negative)
     *         1 0 1
     *         1 1 0
     *         1 1 1
     *
     * @param signBitFirstSubOperand most significant byte (it is sign byte) of first subtraction operand
     * @param signBitSecondSubOperand most significant byte (it is sign byte) of second subtraction operand
     * @param signBitResultOfSubtraction most significant byte (it is sign byte) of result of subtraction
     * @return overflow flag
     */
    private static boolean calculateOverflowFlagForSub(
            boolean signBitFirstSubOperand,
            boolean signBitSecondSubOperand,
            boolean signBitResultOfSubtraction
    ) {
        return (
                (!signBitFirstSubOperand && signBitSecondSubOperand && signBitResultOfSubtraction) ||
                (signBitFirstSubOperand && !signBitSecondSubOperand && !signBitResultOfSubtraction)
        );
    }

    public static Arithmetic16bitResult addTwo16bits(XBit16 value1, XBit16 value2) {
        //@todo not implemented yet
        return null;
    }

    public static Arithmetic16bitResult subTwo16bits(XBit16 value1, XBit16 value2) {
        //@todo not implemented yet
        return null;
    }

    public static XBit8 not8bit(XBit8 value) {
        return XBit8.valueOfUnsigned(
                (~value.getUnsignedValue()) & XBit8.MAX_UNSIGNED_VALUE
        );
    }

    public static XBit16 not16bit(XBit8 value) {
        return XBit16.valueOfUnsigned(
                (~value.getUnsignedValue()) & XBit16.MAX_UNSIGNED_VALUE
        );
    }

    public static XBit8 and8bit(XBit8 value1, XBit8 value2) {
        return XBit8.valueOfUnsigned(
            (value1.getUnsignedValue() & value2.getUnsignedValue()) & XBit8.MAX_UNSIGNED_VALUE
        );
    }

    public static XBit16 and16bit(XBit16 value1, XBit16 value2) {
        return XBit16.valueOfUnsigned(
                (value1.getUnsignedValue() & value2.getUnsignedValue()) & XBit16.MAX_UNSIGNED_VALUE
        );
    }

    public static XBit8 or8bit(XBit8 value1, XBit8 value2) {
        return XBit8.valueOfUnsigned(
                (value1.getUnsignedValue() | value2.getUnsignedValue()) & XBit8.MAX_UNSIGNED_VALUE
        );
    }

    public static XBit16 or16bit(XBit16 value1, XBit16 value2) {
        return XBit16.valueOfUnsigned(
                (value1.getUnsignedValue() | value2.getUnsignedValue()) & XBit16.MAX_UNSIGNED_VALUE
        );
    }

    public static XBit8 xor8bit(XBit8 value1, XBit8 value2) {
        //@todo not implemented yet
        return null;
    }

    public static XBit16 xor16bit(XBit16 value1, XBit16 value2) {
        //@todo not implemented yet
        return null;
    }

    public static XBit8 shift8bit(XBit8 value1, int position) {
        //@todo not implemented yet
        return null;
    }

    public static XBit8 shift16bit(XBit8 value1, int position) {
        //@todo not implemented yet
        return null;
    }
}
