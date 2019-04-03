package ru.poponinav.kvlbot.callback;

import com.vk.api.sdk.callback.CallbackApi;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.messages.Message;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

@Getter
@Component
public class CallbackApiHandler extends CallbackApi {

    private String VK_API_KEY;
    private Integer GROUP_ID;

    @Autowired
    public CallbackApiHandler(VkApiClient vkApiClient,
                              @Value("${VK_API_KEY}") String vkApiKey,
                              @Value("${GROUP_ID}") Integer groupId) {
        this.vkApiClient = vkApiClient;
        this.VK_API_KEY = vkApiKey;
        this.GROUP_ID = groupId;
    }

    private VkApiClient vkApiClient;

    @Override
    public void messageNew(Integer groupId, String secret, Message message) {
        GroupActor groupActor = new GroupActor(GROUP_ID, VK_API_KEY);
        try {
            vkApiClient
                    .messages()
                    .send(groupActor)
                    .message(message.getText())
                    .userId(message.getFromId())
                    .randomId(new Random().nextInt(10000)).execute();
        } catch (ApiException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void confirmation(Integer groupId) {
    }
}
