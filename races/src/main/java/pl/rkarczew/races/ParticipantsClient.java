
package pl.rkarczew.races;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@FeignClient("participants")
public interface ParticipantsClient {
	
    @RequestMapping(method = RequestMethod.GET, value="/races/{raceId}")
    List<Participant> getParticipants(@PathVariable("raceId") String raceId);
}