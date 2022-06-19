package com.example.hotelreservationsystem;

import com.example.hotelreservationsystem.model.Capacity;
import com.example.hotelreservationsystem.model.RoomType;
import com.example.hotelreservationsystem.model.User;
import com.example.hotelreservationsystem.repos.CapacityRepository;
import com.example.hotelreservationsystem.repos.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableSwagger2

public class HotelReservationSystemApplication {
private Map<RoomType,Integer>  initialCapacities = new HashMap<>() {
    {
        put(RoomType.SINGLE,20);
        put(RoomType.DOUBLE,4);
        put(RoomType.TRIPLE,1);
}
};
    public static void main(String[] args) {
        SpringApplication.run(HotelReservationSystemApplication.class, args);
    }
    @Bean
    public CommandLineRunner loadData(UserRepository userRepository,
                                      CapacityRepository capacityRepository){
        return (args) -> {
            userRepository.save(new User("Omar saeid","admin", bCryptPasswordEncoder().encode("admon")));
            for (RoomType roomType : initialCapacities.keySet()){
capacityRepository.save(new Capacity(roomType,initialCapacities.get(roomType)));
            }

        };
    }

@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
