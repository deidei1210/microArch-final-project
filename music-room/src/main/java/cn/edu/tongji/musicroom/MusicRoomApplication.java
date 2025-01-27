package cn.edu.tongji.musicroom;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableEncryptableProperties
public class MusicRoomApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicRoomApplication.class, args);
	}

}