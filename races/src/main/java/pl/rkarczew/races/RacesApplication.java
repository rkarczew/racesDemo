package pl.rkarczew.races;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class RacesApplication implements CommandLineRunner{
	
	private static List<Race> races = new ArrayList<Race>();
	
    @Autowired
    private ParticipantsInnerBean participantsBean;

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
            returnRaces.add(new RaceWithParticipants(r, participantsBean.getParticipantsHS(r.getId())));
            //returnRaces.add(new RaceWithParticipants(r, participantsBean.getParticipantsWithTryCatch(r.getId())));
        }
        return returnRaces;
    }
}

@FeignClient("participants")
interface ParticipantsInnerClient {
	
    @RequestMapping(method = RequestMethod.GET, value="/races/{raceId}")
    List<Participant> getParticipants(@PathVariable("raceId") String raceId);
}

@Component
class ParticipantsInnerBean {
   
   @Autowired
   private ParticipantsInnerClient participantsClient;

   

   @HystrixCommand(fallbackMethod = "defaultParticipants")
   public List<Participant> getParticipantsWithTryCatch(String raceId) {  
		try{
       return participantsClient.getParticipants(raceId);
		}
		catch(Exception e){
			e.printStackTrace();
			return defaultParticipants(raceId);
		}
   }
   
   //@HystrixCommand(fallbackMethod = "defaultParticipants")
   public List<Participant> getParticipantsHS(String raceId) {
       return participantsClient.getParticipants(raceId);
   }
   
   public List<Participant> defaultParticipants(String raceId) {
       return new ArrayList<Participant>();
   }

}
