package resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import counter.Counter;
import dto.CounterDTO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Arrays;

@Path("/counter")
public class CounterResource {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @GET
    @Produces("application/json")
    public Response getCounter() throws JsonProcessingException {
        CounterDTO counterDTO = new CounterDTO(LocalDateTime.now(), Counter.getCounter());
        String s = mapper.writeValueAsString(counterDTO);
        return Response.ok(mapper.writeValueAsString(counterDTO)).build();
    }

    @POST
    public Response increaseCounter()
    {
        Counter.increaseCounter();
        return Response.ok("Counter value increased to " + Counter.getCounter().toString()).build();
    }

    @DELETE
    public Response decreaseCounter(@Context HttpServletRequest request)
    {
        int subtractionValue;
        try{
            subtractionValue = Integer.parseInt(request.getHeader("Subtraction-Value"));
        }
        catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Counter.decreaseCounterByValue(subtractionValue);
        return Response.ok("Counter value decreased to " + Counter.getCounter().toString()).build();
    }

    @POST
    @Path("/clear")
    public Response clearCounter(@Context HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("hh-auth"))
                .anyMatch(cookie -> cookie.getValue().length() > 10))
        {
            Counter.clearCounter();
            return Response.ok("Counter value reset to " + Counter.getCounter().toString()).build();
        }
        else
            return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
