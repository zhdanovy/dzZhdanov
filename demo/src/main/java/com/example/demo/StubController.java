package com.example.demo;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/ok")
public class StubController {

    @PostMapping("/process")
    public Object processRequest(@RequestBody RequestObject request) throws InterruptedException {
        if (isNumeric(request.getFieldName())) {
            int number = Integer.parseInt(request.getFieldName());
            int randomValue = new Random().nextInt(5) + 1;

            if (randomValue == 3) {
                return new ErrorResponse("Попробуйте еще раз, ошибка");
            }

            int result = number * number + randomValue;

            // Задержка выполнения на случайное количество секунд от 1 до 3
            int delayInSeconds = new Random().nextInt(3) + 1;
            Thread.sleep(delayInSeconds * 1000);

            return new ResponseObject(result);
        } else {
            return new ErrorResponse("Передайте число");
        }
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}