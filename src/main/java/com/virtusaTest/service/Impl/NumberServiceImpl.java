package com.virtusaTest.service.Impl;

import com.virtusaTest.constants.NumbersEnum;
import com.virtusaTest.service.NumberService;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

@Service
public class NumberServiceImpl implements NumberService {
    @Override
    public String convertToText(long inNum) {
        int inputSize = String.valueOf(inNum).length();
        String inNumStr = String.valueOf(inNum);
        int count = inputSize;
        //long currLoopNum = inNum;
        StringJoiner sbOut = new StringJoiner(" "); //space is a by default added
        if(count==1){
            sbOut.add(this.printSingleDigit(Math.toIntExact(inNum)));
        }else {
            while (count > 1) {
                switch (count%3){
                    case 1:
                        sbOut.add(this.printSingleDigit(inNumStr.charAt(inputSize-count)-'0'));
                        sbOut.add(this.prinHigherOrder(count));
                        count--;
                        break;
                    case 2:
                        int ones = inNumStr.charAt(inputSize-count+1)-'0';
                        int tens = inNumStr.charAt(inputSize-count)-'0';
                        //10-19
                        if(tens == 1){
                            this.print10To19(ones);
                        }else{
                            String tenths = tens!=0?this.printTwoDigitRound(tens):"";
                            sbOut.add(tenths);
                            String zeroth = ones!=0?this.printSingleDigit(ones):"";
                            sbOut.add(zeroth);
                        }
                        sbOut.add(this.prinHigherOrder(count));
                        count -=2;
                        break;
                    case 0:
                        int hunrends = inNumStr.charAt(inputSize-count) - '0';
                        if(hunrends!=0){
                            sbOut.add(this.printSingleDigit(hunrends));
                            sbOut.add(this.prinHigherOrder(3));
                        }
                        count--;
                        break;
                }

            }
        }
        return sbOut.toString().trim();
    }

    private String printTwoDigitRound(int tenthsDigit){
        String val = NumbersEnum.twoDigitRounds[tenthsDigit];
        System.out.println(val);
        return val;
    }
    private String printSingleDigit(int oneDigitNum){
        String val = NumbersEnum.zeroth[oneDigitNum];
        System.out.println( val);
        return val;
    }

    private String print10To19(int reminder){
        String val = NumbersEnum.tens[reminder];
        System.out.println(val);
        return val;

    }

    private String prinHigherOrder(int order){
        String val = "";
        if(order>=13&&order<=15){
            val = NumbersEnum.higherOrders[4];
        }
        else if(order>=10&&order<=12){
            val = NumbersEnum.higherOrders[3];
        }
        else if(order>=7 && order<=9) {
             val = NumbersEnum.higherOrders[2];
        }else if(order>=4 && order<=6){
            val = NumbersEnum.higherOrders[1];
        }else if(order==3){
            val = NumbersEnum.higherOrders[0];
        }
        System.out.println(val);
        return val;
    }

}