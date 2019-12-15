package net.techeco.numbergame.core;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGame implements Serializable {
    private final int numberArrayLength;
    private List<Integer> numberArray = null;

    public NumberGame(int numberArrayLength) {
        this.numberArrayLength = numberArrayLength;
    }

    public NumberGame generateNewArray() {
        List<Integer> array = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < this.numberArrayLength; i++) {
            array.add(random.nextInt(10) + 1);
        }

        this.numberArray = array;
        return this;
    }

    public String checkResult(int userInput) {
        int count = 0;
        for (Integer integer : this.numberArray) {
            if (userInput % integer == 0) {
                count++;
            }
        }
        if (count < this.numberArrayLength) {
            return count + "/" + this.numberArrayLength;
        }
        return "Win";
    }

    public List<Integer> getNumberArray() {
        return numberArray;
    }

    public void setNumberArray(List<Integer> numberArray) {
        this.numberArray = numberArray;
    }
}
