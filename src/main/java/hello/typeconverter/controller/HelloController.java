package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request) {
        String data = request.getParameter("data");
        Integer intValue = Integer.valueOf(data);
        log.info("V1 - intValue = {}", intValue);

        return "ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data) {
        log.info("V2 - intValue = {}", data);

        return "ok";
    }

    @GetMapping("hello-v3")
    public String helloV3(@ModelAttribute UserData data) {
        log.info("V3 - data = {}", data.getData());

        return "ok";
    }

    @Data
    public static class UserData {
        Integer data;
    }

    @GetMapping("hello-v4/users/{userId}")
    public String helloV4(@PathVariable Integer userId) {
        log.info("V4 - data = {}", userId);

        return "ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort) {
        log.info("ipPort ip = {}", ipPort.getIp());
        log.info("ipPort port = {}", ipPort.getPort());

        return "ok";
    }
}