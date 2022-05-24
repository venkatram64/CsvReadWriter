package com.shamanthaka.rl.mbean;


import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedOperationParameters;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(
        objectName = "hello:bean=HelloService, type=service",
        description = "Hello World Bean"
)
public class HelloService {
    public static volatile boolean invokeMBean = true;


    @ManagedOperation(description = "which prints the message")
    @ManagedOperationParameters(@ManagedOperationParameter(name="message",
        description="Passing message")
    )
    public void triggerMessage(String message){
        System.out.println("Message is " + message);
        invokeMBean = false;
    }

    /*public static void main(String[] args) {
        while (HelloService.invokeMBean) {}
    }*/
}
