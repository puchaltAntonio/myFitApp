package com.learning.myfitapp.common.seeders;

import com.learning.myfitapp.MyfitappApplication;
import com.learning.myfitapp.modules.profile.infrastructure.seeders.ProfileMockSeeder;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile(MyfitappApplication.DEV_PROFILE)
@Component
@RequiredArgsConstructor
@Slf4j
public class MockSeeder {
    private final ProfileMockSeeder profileMockSeeder;

    @PostConstruct
    private void onStartUp() {
        log.info("Initializing JPA Mock Data Seeder");

        profileMockSeeder.createData();

        log.info("Seeding Finished");
    }
}
