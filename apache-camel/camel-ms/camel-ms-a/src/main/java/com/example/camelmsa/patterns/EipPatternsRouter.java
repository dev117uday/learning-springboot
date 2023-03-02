package com.example.camelmsa.patterns;

import com.example.camelmsa.model.CurrencyExchange;
import com.example.camelmsa.utils.DynamicRouterBean;
import com.example.camelmsa.utils.SpliterComponent;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class EipPatternsRouter extends RouteBuilder {


    @Autowired
    private SpliterComponent spliterComponent;

    @Autowired
    private DynamicRouterBean dynamicRouterBean;

    @Override
    public void configure() throws Exception {

        // ## pipeline

        // ## multicast

        from("timer:multicast?period=10000")
                .multicast()
                .to("log:something-1", "log:something-2");


        // ## SPLIT

        from("file:files/csv")
                .unmarshal().csv()
                .split(body())
                .to("log:split-files");

        from("file:files/csv")
                .convertBodyTo(String.class)
                .split(body(), ",")
                .to("log:split-files");

        from("file:files/csv")
                .convertBodyTo(String.class)
                .split(method(spliterComponent))
                .to("log:split-files");

        // Aggregate

        from("file:files/agg-json")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .aggregate(simple("${body.to}"), (oldExchange, newExchange) -> {

                    Object newBody = newExchange.getIn().getBody();
                    ArrayList lst = null;

                    if (oldExchange == null) {
                        lst = new ArrayList<Object>();
                        lst.add(newBody);
                        newExchange.getIn().setBody(lst);
                        return newExchange;
                    } else {
                        // ignore
                        lst = oldExchange.getIn().getBody(ArrayList.class);
                        lst.add(newBody);
                        return oldExchange;
                    }

                }).completionSize(3)
                .to("log:agg-json");

        // routing slip

        String routingSlip = "direct:endpoint1,direct:endpoint2";

        from("timer:routingSlip?period={{time-period}}")
                .transform().constant("my message")
                .routingSlip(simple(routingSlip));

        from("direct:endpoint1").to("{{endpoint-for-logging}}");

        from("direct:endpoint2").to("log:endpoint2");

        from("direct:endpoint3").to("log:endpoint3");

        // dynamic routing

        from("timer:routingSlip?period=10000")
                .transform().constant("my message")
                .dynamicRouter(method(dynamicRouterBean));

        // to enable tracing
        getContext().setTracing(true);

        // not make sure no message is lost
        errorHandler(deadLetterChannel("activemq:dead-letter-queue"));

        // to tap message while in camel context
        from("").wireTap("");

    }
}
