import java.util.*;

public class TicketSystem {

    private final List <String> availableBerths = new ArrayList<>(Arrays.asList("L", "U" ," M"));

    private final Queue <Passenger> racQueue = new LinkedList<>();

    private final Queue <Passenger> waitingListQueue = new LinkedList<>();

    private final List <Passenger> confirmedPassengers = new ArrayList<>();

    private int ticketCounter = 1;


    //Booking ticket Method

    public void bookTicket(String name , int age , String gender , String berthPreferance)
    {
        berthPreferance = berthPreferance.toUpperCase();
        String ticketId = "T" + ticketCounter++;
        Passenger passenger;
        if(!availableBerths.isEmpty())
        {
            String allocatedBerth = allocateBerth(age , berthPreferance);
            passenger = new Passenger(name, age, gender, berthPreferance, allocatedBerth, ticketId);
            confirmedPassengers.add(passenger);
            availableBerths.remove(allocatedBerth);
            System.out.println("Ticket Confirmed: "+ passenger);
        }
        else if(racQueue.size() < 1)
        {
            passenger = new Passenger(name, age, gender, berthPreferance,  "RAC", ticketId);
            racQueue.offer(passenger);
            System.out.println("Ticket in RAC: "+ passenger);
        }
        else if(waitingListQueue.size() < 1)
        {
            passenger = new Passenger(name, age, gender, berthPreferance,  "Waiting List", ticketId);
            waitingListQueue.offer(passenger);
            System.out.println("Ticket in Waiting List: "+ passenger);
        }
        
    }
    private String allocateBerth(int age , String berthPreferance) 
    {
        if(age > 60 && availableBerths.contains("L"))
        {
            return "L";
        }
        if(availableBerths.contains(berthPreferance))
            return berthPreferance;

        return availableBerths.get(0);    
    }




    //Cancel ticket Method

    public void cancelTicket(String ticketId)
    {
        Optional<Passenger> passengerOpt = confirmedPassengers.stream()
                .filter(p -> p.ticketId.equals(ticketId))
                .findFirst();
        if(passengerOpt.isPresent())
        {
            Passenger passenger = passengerOpt.get();
            confirmedPassengers.remove(passenger);
            availableBerths.add(passenger.allottedBerth);

            if(!racQueue.isEmpty())
            {
                Passenger racPassenger = racQueue.poll();
                String allocatedBerth = allocateBerth(racPassenger.age , racPassenger.berthPreferance);
                racPassenger.allottedBerth = allocatedBerth;
                confirmedPassengers.add(racPassenger);
                availableBerths.remove(allocatedBerth);
                System.out.println("RAC ticket moved to confirmed: "+ racPassenger);
            }

            if(!waitingListQueue.isEmpty())
            {
                Passenger WLpassenger = waitingListQueue.poll();
                racQueue.offer(WLpassenger);
                WLpassenger.allottedBerth = "RAC";
                System.out.println("Waiting List ticket moved to RAC: " + WLpassenger);

            }
            System.out.println("Ticket cancelled Successfully for ID: " + ticketId);
        } 
        else
        {
            System.out.println("Ticket Id not found");
        }       
        
        
        

    }


    // print the booked tickets

    public void printBookedTickets()
    {
        if(confirmedPassengers.isEmpty())
        {
            System.out.println("No confirmed Tickets");
        }
        else
        {
            System.out.println("Confirmed Tickets: ");
            for(Passenger passenger : confirmedPassengers)
            {
                System.out.println(passenger);
            }
        }
        if(racQueue.isEmpty())
        {
            System.out.println("No ticktes in RAC");
        }
        else
        {
            System.out.println("RAC tickets: ");
            Passenger passenger = racQueue.peek(); 
            System.out.println(passenger);
        }
        if(waitingListQueue.isEmpty())
        {
            System.out.println("No ticktes in WaitingList");
        }
        else
        {
            System.out.println("Waiting List: ");
            Passenger passenger = waitingListQueue.peek(); 
            System.out.println(passenger);
        }
    }

    
    //print the available tickets

    public void printAvailableTickets()
    {
        System.out.println("Available Berths: " + availableBerths.size());
        System.out.println("Available RAC tickets: " + (1-racQueue.size()));
        System.out.println("Available Waiting List tickets: " + (1-waitingListQueue.size()));
    }
    


}