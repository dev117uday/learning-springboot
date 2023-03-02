package com.example.camelmsa.routes;

import com.example.camelmsa.utils.DeciderBean;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class FileRouter extends RouteBuilder {

    @Autowired
    private DeciderBean deciderBean;

    @Override
    public void configure() throws Exception {
//        from("file:files/input")
//                .to("file:files/output");

        from("direct://log-file-values")
                .log("${messageHistory} ${headers.CamelFileAbsolute}")
                .log("${file:name} ${file:name.ext} ${file:size}");

        from("file:files/input")
                .routeId("Files-Input-Route")
                .transform().body(String.class)

                .choice()

                //.when(simple("${file:ext} ends with 'xml'"))
                .when(method(deciderBean))
                .log("XML FILE")

                .when(simple("${body} contains 'USD'"))
                .log("Contains USD")
                .otherwise()
                .log("No Condition met")
                .end()

                // ignore this intellj warning

                .to("direct://log-file-values")
                .to("file:files/output");
    }
}

