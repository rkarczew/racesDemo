package pl.rkarczew.races;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class RacesApplication implements CommandLineRunner{
	
	private static List<Race> races = new ArrayList<Race>();
	
	@Autowired
	private ParticipantsClient participantsClient;

	public static void main(String[] args) {
		SpringApplication.run(RacesApplication.class, args);
	}
	
    @Override
    public void run(String... arg0) throws Exception {
        races.add(new Race("Spartan Beast", "123", "MA", "Boston"));
        races.add(new Race("Tough Mudder RI", "456", "RI", "Providence"));
    }

    @RequestMapping("/")
    public List<Race> getRaces() {
        return races;
    }
    
    @RequestMapping("/participants")
    public List<RaceWithParticipants> getRacesWithParticipants() {
        List<RaceWithParticipants> returnRaces = new ArrayList<RaceWithParticipants>();
        for(Race r : races) {
            returnRaces.add(new RaceWithParticipants(r, participantsClient.getParticipants(r.getId())));
        }
        return returnRaces;
    }
}
