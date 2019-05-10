package com.kostenko.andrey.testconcord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RequestController {

    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/encode", method = RequestMethod.GET)
    @ResponseBody
    public CryptoObject encode(@RequestParam(value = "value", defaultValue = "null") String value) {
        System.out.println(value);
        return new CryptoObject.Builder((String)context.getBean("key"), value, true)
                .messageFormatBase64(false)
                .stringResponseBase64(true)
                .build();
    }

    @RequestMapping(value = "/decode", method = RequestMethod.GET)
    @ResponseBody
    public CryptoObject decode(@RequestParam(value = "value", defaultValue = "null") String value)  {
        System.out.println(value);
        return new CryptoObject.Builder((String)context.getBean("key"),  value, false)
                .messageFormatBase64(true)
                .build();
    }

}
