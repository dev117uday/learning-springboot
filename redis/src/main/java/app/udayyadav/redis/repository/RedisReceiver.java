package app.udayyadav.redis.repository;

import app.udayyadav.redis.entity.Product;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

@Slf4j
public class RedisReceiver implements MessageListener {


    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            var messageString = getObject(message.getBody());
            Gson gson = new Gson();
            var user = gson.fromJson((String) messageString, Product.class);
            log.info("Consumed event {}", user.toString());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }

    private static Object getObject(byte[] byteArr) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArr);
        ObjectInput in = new ObjectInputStream(bis);
        return in.readObject();
    }


}
