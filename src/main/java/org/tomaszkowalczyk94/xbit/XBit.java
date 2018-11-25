package org.tomaszkowalczyk94.xbit;


public abstract class XBit {

    public abstract short getSize();

    protected int valueContainer;

     /**
     * @param index
     * @return
     */
    public boolean getBit(int index) {
        if(index <0 || index > getSize()-1) {
            throw new NumberFormatException();
        }

        return ((valueContainer >> index) & 1) == 1;
    }

    public int getValueContainer() {
        return valueContainer;
    }
}
