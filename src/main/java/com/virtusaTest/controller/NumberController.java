package com.virtusaTest.controller;

import com.virtusaTest.exceptions.IncorrectInputException;
import com.virtusaTest.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/v1/num")
public class NumberController {

    @Autowired
    NumberService numberService;

    @RequestMapping("/toText")
    public String convertNumberToString(@RequestParam("number") String inputNum){
        System.out.println("in controller input:"+inputNum);
        try {
            Long longInput = Long.parseLong(inputNum);
            if(longInput<0){
                throw new IncorrectInputException("Input Positive Integer");
            }
            return numberService.convertToText(longInput);
        }catch (NumberFormatException n){
            return "Input should be number";
        }
        catch (IncorrectInputException e){
            return e.getMessage();
        }

    }
}
