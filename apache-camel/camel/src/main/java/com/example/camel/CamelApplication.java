package com.example.camel;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class CamelApplication extends RouteBuilder {

    public static void main(String[] args) {
        SpringApplication.run(CamelApplication.class, args);
    }

    @Override
    public void configure() throws Exception {
        log.info("---- starting ----");
        moveAllFile();
        //moveSpecificFile("file");
        //moveSpecificFileWithContent("hello");
        //processFile();
        //multiFileProcessor();
        log.info("---- end ----");
    }

    public void moveAllFile() {
        from("file:/home/uday/a").log("--- moved ---")
                .to("file:/home/uday/b");
    }

    public void moveSpecificFile(String fileName) {
        from("file:/home/uday/a")
                .filter(header(Exchange.FILE_NAME).startsWith(fileName))
                .to("file:/home/uday/b");
    }

    public void moveSpecificFileWithContent(String content) {
        from("file:/home/uday/a")
                .filter(body().startsWith(content))
                .to("file:/home/uday/b");
    }

    public void processFile() {
        from("file:/home/uday/a")
                .process(p -> {
                    String body = p.getIn().getBody(String.class);
                    StringBuilder sb = new StringBuilder();
                    Arrays.stream(body.split(" ")).forEach(s -> sb.append(s).append(","));
                    p.getIn().setBody(sb);
                }).to("file:/home/uday/b");
    }

    public void multiFileProcessor() {
        from("file:/home/uday/a/").unmarshal().csv().split(body().tokenize(",")).choice()
                .when(body().contains("closed")).to("file:/home/uday/b?fileName=closed.csv")
                .when(body().contains("pending")).to("file:/home/uday/b?fileName=pending.csv")
                .when(body().contains("waiting")).to("file:/home/uday/b?fileName=waiting.csv");
    }

}
