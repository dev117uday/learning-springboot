package com.example.camelmsa.routes;

import com.example.camelmsa.utils.GetCurrentTimeBean;
import com.example.camelmsa.utils.SimpleLoggingProcessingComponent;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;

//@Component
public class MyTimerRoute extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;
    @Autowired
    private SimpleLoggingProcessingComponent simpleLoggingProcessingComponent;


    @Override
    public void configure() throws Exception {

        // queue :- timer
        // transformation
        // database :- log

        // from : starting route
        // to   : the final destination

        // processing : doesnt change the message
        // transform : does change the message

        from("timer:first-timer")

                // example 1
                //.transform()
                //.constant("My message at : "+ LocalDateTime.now())

                // example 2
                //.bean(getCurrentTimeBean)

                // example 3
                .log("${body}")
                .transform().constant("My message")
                .log("${body}")
                .bean(getCurrentTimeBean)
                .log("${body}")
                .bean(simpleLoggingProcessingComponent)
                .log("${body}")

                .to("log:first-timer");

    }
}




