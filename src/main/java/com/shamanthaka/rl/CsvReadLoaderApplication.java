package com.shamanthaka.rl;

import com.shamanthaka.rl.mbean.HelloService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
To JMX Connection between different servers add the following params to your jvm
eg:
	java -Dcom.sun.management.jmxremote.port=8090 \
		 -Dcom.sun.management.jmxremote.authenticate=false \
		 -jar csv-read-loader-0.0.1-SNAPSHOT.jar
 */
@SpringBootApplication
public class CsvReadLoaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvReadLoaderApplication.class, args);
	}

	/*public void run(String... args) throws Exception{
		while(HelloService.invokeMBean){}
	}*/

}
