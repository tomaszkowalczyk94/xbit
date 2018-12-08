package org.tomaszkowalczyk94.xbit;


public abstract class XBit {

    public abstract short getSize();

    protected int valueContainer;

     /**
     * @param index of bit. Bit numbering by LSB 0.
     * @see <a href="https://en.wikipedia.org/wiki/Bit_numbering">https://en.wikipedia.org/wiki/Bit_numbering</a>
     */
    public boolean getBit(int index) {
        if(index <0 || index > getSize()-1) {
            throw new NumberFormatException();
        }

        return ((valueContainer >> index) & 1) == 1;
    }

    /**
     * Bit numbering by LSB 0. For example, for 8bit value:
     * Least significant bit have index 0. Most significant bit have bit index 7
     *
     * @param startIndexBit including this bit
     * @param stopIndexBit including this bit
     * @return unsigned int value
     * @see <a href="https://en.wikipedia.org/wiki/Bit_numbering">https://en.wikipedia.org/wiki/Bit_numbering</a>
     */
    public int getValueOfBits(int startIndexBit, int stopIndexBit) {
        if(startIndexBit<stopIndexBit) {
            throw new NumberFormatException();
        }

        int buff = valueContainer >>> stopIndexBit;
        return buff & (~(Integer.MAX_VALUE<<(startIndexBit-stopIndexBit+1)));
    }

    public int getValueContainer() {
        return valueContainer;
    }
}
