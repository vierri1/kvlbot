package ru.poponinav.kvlbot.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.poponinav.kvlbot.callback.CallbackApiHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RestController
public class MainController {

    private static final String OK_RESPONSE_MESSAGE = "ok";
    private Gson gson;
    private CallbackApiHandler callbackApiHandler;

    @Autowired
    public MainController(Gson gson, CallbackApiHandler callbackApiHandler) {
        this.gson = gson;
        this.callbackApiHandler = callbackApiHandler;
    }

    @PostMapping(value = "/event")
    public void confirm(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject jsonObject = gson.fromJson(request.getReader(), JsonObject.class);
        callbackApiHandler.parse(jsonObject);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(OK_RESPONSE_MESSAGE);
    }
}
