package com.learning.myfitapp;

import com.learning.myfitapp.common.config.TimeZoneConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class MyfitappApplication {
	public static final String DEV_PROFILE = "dev";

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone(TimeZoneConfig.DEFAULT_TIME_ZONE) );
		SpringApplication.run(MyfitappApplication.class, args);
	}

}
