package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConverterController {

    @GetMapping("/converter-view")
    public String converterView(Model model) {
        model.addAttribute("number", 10000);
        model.addAttribute("ipPort", new IpPort("127.0.0.1", 8000));

        return "converter-view";
    }

    @GetMapping("/converter/edit")
    public String converterForm(Model model) {
        IpPort ipPort = new IpPort("127.0.0.1", 8000);
        Form form = new Form(ipPort);

        model.addAttribute("form", form);

        return "converter-form";
    }

    @AllArgsConstructor
    @Data
    public static class Form {
        IpPort ipPort;
    }

    /**
     * 왜 로그에 StringToIpPortConverter 가 두개 찍히는가??...
     */
    @PostMapping("/converter/edit")
    public String converterEdit(@ModelAttribute Form form, Model model) {
        IpPort ipPort = form.getIpPort();

        model.addAttribute("ipPort", ipPort);

        return "converter-view";
    }
}