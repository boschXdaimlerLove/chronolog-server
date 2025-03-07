import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/status")
public class Status {

    @GET
    public Response getStatus() {
        return Response
                .status(200)
                .entity("Service is running")
                .build();
    }
}
