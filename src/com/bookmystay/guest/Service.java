package com.bookmystay.guest;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private List<String> services;

    public Service() {
        services = new ArrayList<>();
    }

    public void addService(String service) {
        services.add(service);
    }

    public List<String> getServices() {
        return services;
    }

    @Override
    public String toString() {
        return services.isEmpty() ? "No add-ons" : String.join(", ", services);
    }
}
