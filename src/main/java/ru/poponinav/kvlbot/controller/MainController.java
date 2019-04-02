package ru.poponinav.kvlbot.controller;

import com.vk.api.sdk.callback.objects.messages.CallbackConfirmationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.poponinav.kvlbot.callback.CallbackApiHandler;

@Controller
public class MainController {

    private static final String OK_RESPONSE_MESSAGE = "ok";
    private final ResponseEntity<String> okResponse;

    private CallbackApiHandler callbackApiHandler;

    @Autowired
    public MainController(CallbackApiHandler callbackApiHandler) {
        this.callbackApiHandler = callbackApiHandler;
        this.okResponse = new ResponseEntity<>(OK_RESPONSE_MESSAGE, HttpStatus.OK);
    }


    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(@RequestBody CallbackConfirmationMessage confirmationMessage) {
        return new ResponseEntity<>("a6c4ef0f", HttpStatus.OK);
    }

    @PostMapping("/event")
    public ResponseEntity<String> event(@RequestBody String payload) {
        callbackApiHandler.parse(payload);
        return okResponse;
    }
}
