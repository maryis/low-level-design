package parking;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ParkingLot {
    Map<SpotType, List<Spot>> spots;
    Map<SpotType, Integer> spotSizes;
    Map<SpotType, Integer> spotPrices;
    Map<VehicleType, Integer> vehicles;

    boolean isFull;
    AvaialabilityService avaialabilityService;

    public ParkingLot() {
        init();
    }

    private void init() {
    }

    public Ticket enter(Vehicle vehicle, Date time) {
        if (isFull()) return null;
        List<Spot> spots = getSpots(vehicle);
        if (spots.isEmpty()) return null; //log
        vehicles.put(vehicle.type, vehicles.get(vehicle.type) + 1);
        vehicle.setSpots(spots);
        return new Ticket(vehicle, spots, time);
    }

    public long exit(Ticket ticket, Date time) {
        ticket.setExitTime(time);
        return calculatePrice(ticket);
    }

    private long calculatePrice(Ticket ticket) {
        int spotPrice = spotPrices.get(ticket.spots.get(0).spotType);
        Date d1 = ticket.enterTime;
        Date d2 = ticket.exitTime;
        long timeDiff = TimeUnit.MINUTES.toMinutes(d2.getTime() - d1.getTime());
        ticket.price = timeDiff * spotPrice * ticket.spots.size();
        return ticket.price;
    }

//    private List<Spot> getSpots(Vehicle vehicle) { // -> moved to AvailabilityService SOLID
//        List<Spot> spots;
//        switch (vehicle.type) {
//            case CAR:
//                spots = getNextSpots(VehicleType.CAR, 1);
//                if (spots.isEmpty())
//                    return getNextSpots(VehicleType.MOTOR_CYCLE, 2);
//                return spots;
//            case VAN:
//                return getNextSpots(VehicleType.VAN, 1);
//            case MOTOR_CYCLE:
//                spots = getNextSpots(VehicleType.MOTOR_CYCLE, 1);
//                if (spots.isEmpty())
//                    spots = getNextSpots(VehicleType.CAR, 1);
//                return spots;
//        }
//        return Collections.emptyList();
//    }

    private List<Spot> getSpots(Vehicle vehicle) { // -> moved to AvailabilityService SOLID
        avaialabilityService = AvaialabilityFactory.getInstance(vehicle.type);
        return avaialabilityService.getSpots(vehicle, spotSizes, spots);
    }

    public boolean isFull() {
        return isFull;
    }
    public boolean isEmpty() {
        return !isFull;
    }
}

class Ticket {
    Vehicle vehicle;
    List<Spot> spots;
    Date enterTime;
    Date exitTime;
    long price;

    public Ticket(Vehicle v, List<Spot> spots, Date t) {
        vehicle = v;
        enterTime = t;
    }

    public void setExitTime(Date time) {
        exitTime = time;
    }
}

enum SpotType {
    Regular,
    Compact,
    Large;
}

enum VehicleType {
    CAR,
    VAN,
    MOTOR_CYCLE
}

class Spot {
    SpotType spotType;
    boolean occupied;

    public void setOccupied() {
        occupied = true;
    }

    public boolean isOccupied() {
        return occupied;
    }
}

class Vehicle {
    List<Spot> spots;
    VehicleType type;

    public void setSpots(List<Spot> spots) {
        this.spots = spots;
    }
}

interface AvaialabilityService {
    static final Map<VehicleType, SpotType> typeMap = new HashMap<>();
    List<Spot> getSpots(Vehicle vehicle, Map<SpotType, Integer> spotSizes, Map<SpotType, List<Spot>> spots);

    default List<Spot> getNextSpots(VehicleType vehicleType, Map<SpotType, Integer> spotSizes,
                            Map<SpotType, List<Spot>> spots, int k) {
        SpotType spotType = typeMap.get(vehicleType);
        List<Spot> result;
        if (spotSizes.get(spotType) >= k) {
            result = spots.get(spotType).stream().filter(s -> !s.isOccupied()).limit(k).collect(Collectors.toList());
            result.stream().forEach( s -> s.setOccupied());
            spotSizes.put(spotType, spotSizes.get(spotType)-k);
            return result;
        }
        return Collections.emptyList();
    }
}

class CarService implements AvaialabilityService {
    @Override
    public List<Spot> getSpots(Vehicle vehicle, Map<SpotType, Integer> spotSizes, Map<SpotType, List<Spot>> spots) {
        List<Spot> list = getNextSpots(VehicleType.CAR, spotSizes, spots, 1);
        if (list.isEmpty())
            list = getNextSpots(VehicleType.MOTOR_CYCLE, spotSizes, spots, 2);
        return list;
    }
}

class VanService implements AvaialabilityService {
    @Override
    public List<Spot> getSpots(Vehicle vehicle, Map<SpotType, Integer> spotSizes, Map<SpotType, List<Spot>> spots) {
        List<Spot> list = getNextSpots(VehicleType.VAN, spotSizes, spots, 1);
        if (list.isEmpty())
            list = getNextSpots(VehicleType.CAR, spotSizes, spots, 2);
        return list;
    }
}

class MotorService implements AvaialabilityService {
    @Override
    public List<Spot> getSpots(Vehicle vehicle, Map<SpotType, Integer> spotSizes, Map<SpotType, List<Spot>> spots) {
        List<Spot> list = getNextSpots(VehicleType.MOTOR_CYCLE, spotSizes, spots, 1);
        if (list.isEmpty())
            list = getNextSpots(VehicleType.CAR, spotSizes, spots, 1);
        return list;
    }
}

class AvaialabilityFactory {
    public static AvaialabilityService getInstance(VehicleType vehicleType) {
        switch (vehicleType) {
            case VAN:
                return new VanService();
            case MOTOR_CYCLE:
                return new MotorService();
            case CAR:
                return new CarService();
        }
        return null;
    }
}


