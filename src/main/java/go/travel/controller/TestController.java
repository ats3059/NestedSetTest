package go.travel.controller;

import go.travel.dto.ShopDto;
import go.travel.service.TestService;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;
    @GetMapping("/test1")
    public ResultBody menuTest() {
        ResultBody<ShopDto> resultBody = new ResultBody<>();
        resultBody.setBody(testService.testShop());
        return resultBody;
    }


    @Getter
    @Setter
    static class ResultBody<T>{
        private T body;
        private LocalDateTime responseTime = LocalDateTime.now();
    }

}
