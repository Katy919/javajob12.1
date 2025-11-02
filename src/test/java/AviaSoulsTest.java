import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Comparator;


public class AviaSoulsTest {

    Ticket ticket1 = new Ticket("Москва", "Санкт-Петербург", 3000, 11, 13);
    Ticket ticket2 = new Ticket("Москва", "Саратов", 2000, 13, 16);
    Ticket ticket3 = new Ticket("Москва", "Саратов", 2000, 12, 15);


    @Test
    public void compareToMin() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);

        int expected = -1;
        int actual = ticket2.compareTo(ticket1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void compareToMax() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);

        int expected = 1;
        int actual = ticket1.compareTo(ticket2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void compareToEqual() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket2);
        manager.add(ticket3);

        int expected = 0;
        int actual = ticket2.compareTo(ticket3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void compareEqualThePrice() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] expected = {ticket2, ticket3};
        Ticket[] actual = manager.search("Москва", "Саратов");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void compareTimeMin() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = -1;
        int actual = comparator.compare(ticket1, ticket2);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void compareTimeMax() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 1;
        int actual = comparator.compare(ticket2, ticket1);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void compareTimeEqual() {
        TicketTimeComparator comparator = new TicketTimeComparator();

        int expected = 0;
        int actual = comparator.compare(ticket2, ticket3);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void testSearchAndSortByPriceComparator() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);


        Comparator<Ticket> priceComparator = Comparator.comparingInt(Ticket::getPrice);
        Ticket[] actual = manager.searchAndSortBy("Москва", "Саратов", priceComparator);
        Ticket[] expected = {ticket2, ticket3};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchAndSortByNoTicketsFound() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);

        TicketTimeComparator timeComparator = new TicketTimeComparator();
        Ticket[] expected = new Ticket[0];
        Ticket[] actual = manager.searchAndSortBy("Саратов", "Москва", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void searchNoMatches() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] result = manager.search("Санкт-Петербург", "Москва");
        Ticket[] expected = new Ticket[0];

        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void searchNoMatchesDeparture() {
        AviaSouls manager = new AviaSouls();
        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);


        Ticket[] result = manager.search("Москва", "Владивосток");
        Ticket[] expected = new Ticket[0];

        Assertions.assertArrayEquals(expected, result);
    }
}