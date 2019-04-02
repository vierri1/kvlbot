package ru.poponinav.kvlbot.controller;

import com.vk.api.sdk.callback.objects.messages.CallbackConfirmationMessage;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.TransformedMultiValuedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.poponinav.kvlbot.callback.CallbackApiHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MainController {

    private static final String OK_RESPONSE_MESSAGE = "ok";
    private final ResponseEntity<String> okResponse;

    private CallbackApiHandler callbackApiHandler;

    @Autowired
    public MainController(CallbackApiHandler callbackApiHandler) {
        this.callbackApiHandler = callbackApiHandler;
        this.okResponse = new ResponseEntity<>(OK_RESPONSE_MESSAGE, HttpStatus.OK);
    }

    @PostMapping(value = "/confirm", produces = "text/plain")
    public String confirm(@RequestBody CallbackConfirmationMessage confirmationMessage) {
        return "a6c4ef0f";
    }

    @PostMapping("/event")
    public ResponseEntity<String> event(@RequestBody String payload) {
        callbackApiHandler.parse(payload);
        return okResponse;
    }
}
