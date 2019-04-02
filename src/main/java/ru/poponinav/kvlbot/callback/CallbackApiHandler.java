package ru.poponinav.kvlbot.callback;

import com.vk.api.sdk.callback.CallbackApi;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.objects.messages.Message;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class CallbackApiHandler extends CallbackApi {

    @Autowired
    public CallbackApiHandler(VkApiClient vkApiClient) {
        this.vkApiClient = vkApiClient;
    }

    private VkApiClient vkApiClient;

    @Override
    public void messageNew(Integer groupId, String secret, Message message) {
    }

    @Override
    public void confirmation(Integer groupId) {

    }
}
