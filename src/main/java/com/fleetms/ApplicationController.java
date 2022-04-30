package com.fleetms;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {

        @GetMapping("/index")
        public String home()
        {
            return "index";
        }

        @GetMapping("/layout")
        public String layout()
        {
            return "layout";
        }

    @GetMapping("/hr")
    public String hr()
    {
        return "/hr/index";
    }

    @GetMapping("/accounts")
    public String account()
    {
        return "/accounts/index";
    }

    @GetMapping("/vehicle")
    public String vehicle()
    {
        return "/vehicle/index";
    }

    @GetMapping("/payroll")
    public String payroll()
    {
        return "/payroll/index";
    }

    @GetMapping("/fleet")
    public String fleet()
    {
        return "/fleet/index";
    }

    @GetMapping("/helpdesk")
    public String helpdesk()
    {
        return "/helpdesk/index";
    }

    @GetMapping("/parameters")
    public String parameter()
    {
        return "/parameters/index";
    }

    @GetMapping("/reports")
    public String reports()
    {
        return "/reports/index";
    }

    @GetMapping("/security")
    public String security()
    {
        return "/security/index";
    }
}
