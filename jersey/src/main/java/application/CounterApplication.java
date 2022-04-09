package application;

import resource.CounterResource;

import javax.ws.rs.core.Application;
import java.util.Set;

public class CounterApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(CounterResource.class);
    }
}
