package org.example;

public class BitOps {


    public int getBit(int number, int position) {
        return (number >> (position)) & 1;
    }

    public int setBit(int number, int position) {
        return (1 << position) | number;
    }


    public int clearBit(int number, int position) {
        return number & ~(1 << position);
    }

    public int updateBit(int number, int position, boolean value) {
        if (value) {
            return (1 << position) | number;
        } else {
            return clearBit(number, position);
        }
    }
}
