package Adapters;

import Models.Vehicle;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

class VehicleAdapter extends TypeAdapter<Vehicle> {
    private final TypeAdapter<Vehicle> delegate;

    public VehicleAdapter(Gson gson) {
        this.delegate = gson.getAdapter(Vehicle.class);
    }

    @Override
    public void write(JsonWriter out, Vehicle value) throws IOException {
        delegate.write(out, value);
    }

    @Override
    public Vehicle read(JsonReader in) throws IOException {
        Vehicle vehicle = delegate.read(in);
        if (vehicle != null && vehicle.validate()) {
            return vehicle;
        } else {
            return null;
        }
    }
}


