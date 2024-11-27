//package dev.test_generators;
//
//import generators.PayDeckChooseSystem;
//import models.Client;
//import models.PayDeck;
//import models.privileges.PrivilegeType;
//import org.junit.jupiter.api.Test;
//
//import java.lang.reflect.Method;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class PayDeckChooseSystemTest {
//
//    @Test
//    public void testChoosePayDeck()
//    {
//        PayDeck paydeck1 = new PayDeck(1);
//        paydeck1.addClient(new Client(1, 1, null, PrivilegeType.Disabled));
//        paydeck1.addClient(new Client(2, 1, null, PrivilegeType.Military));
//
//        PayDeck paydeck2 = new PayDeck(1);
//        paydeck2.addClient(new Client(3, 1, null, PrivilegeType.Military));
//        paydeck2.addClient(new Client(4, 1, null, PrivilegeType.Military));
//
//        PayDeck paydeck3 = new PayDeck(1);
//        paydeck3.addClient(new Client(5, 1, null, PrivilegeType.Interrupted));
//
//        PayDeck paydeck4 = new PayDeck(1);
//        paydeck4.addClient(new Client(6, 1, null, PrivilegeType.Military));
//
//
//        var tester = new Client(6, 1, null, PrivilegeType.Interrupted);
//
//        List<PayDeck> paydecks = Arrays.asList(paydeck1, paydeck2, paydeck3, paydeck4);
//        PayDeck result = PayDeckChooseSystem.choosePaydeck(paydecks,tester);
//
//        assertEquals(paydeck3, result);
//    }
//
//    @Test
//    public void testChoosePaydeckConvenientButScary()
//    {
//        PayDeck paydeck1 = new PayDeck(1);
//        paydeck1.addClient(new Client(1, 1, null, PrivilegeType.None));
//        paydeck1.addClient(new Client(2, 1, null, PrivilegeType.None));
//        paydeck1.addClient(new Client(3, 1, null, PrivilegeType.None));
//        paydeck1.addClient(new Client(4, 1, null, PrivilegeType.None));
//        paydeck1.addClient(new Client(5, 1, null, PrivilegeType.None));
//        paydeck1.addClient(new Client(6, 1, null, PrivilegeType.None));
//        paydeck1.addClient(new Client(7, 1, null, PrivilegeType.None));
//        paydeck1.addClient(new Client(8, 1, null, PrivilegeType.None));
//        paydeck1.addClient(new Client(9, 1, null, PrivilegeType.None));
//
//        PayDeck paydeck2 = new PayDeck(2);
//        paydeck2.addClient(new Client(10, 1, null, PrivilegeType.Military));
//        paydeck2.addClient(new Client(11, 1, null, PrivilegeType.Military));
//
//        PayDeck paydeck3 = new PayDeck(3);
//        paydeck3.addClient(new Client(12, 1, null, PrivilegeType.Interrupted));
//
//        PayDeck paydeck4 = new PayDeck(4);
//        paydeck4.addClient(new Client(13, 1, null, PrivilegeType.Military));
//
//
//        var tester = new Client(6, 1, null, PrivilegeType.Interrupted);
//
//        List<PayDeck> paydecks = Arrays.asList(paydeck1, paydeck2, paydeck3, paydeck4);
//        PayDeck result = PayDeckChooseSystem.choosePaydeck(paydecks,tester);
//
//        assertEquals(paydeck3, result);
//    }
//
//    @Test
//    public void testChoosePaydeckTicketMadness()
//    {
//        PayDeck paydeck1 = new PayDeck(1);
//        paydeck1.addClient(new Client(10, 5, null, PrivilegeType.Military));
//        paydeck1.addClient(new Client(2, 2, null, PrivilegeType.None));
//
//
//        PayDeck paydeck2 = new PayDeck(2);
//        paydeck2.addClient(new Client(3, 7, null, PrivilegeType.Military));
//        paydeck2.addClient(new Client(4, 4, null, PrivilegeType.Military));
//
//        PayDeck paydeck3 = new PayDeck(3);
//        paydeck3.addClient(new Client(5, 2, null, PrivilegeType.Interrupted));
//        paydeck3.addClient(new Client(6, 2, null, PrivilegeType.Interrupted));
//
//        PayDeck paydeck4 = new PayDeck(4);
//        paydeck4.addClient(new Client(7, 1, null, PrivilegeType.Military));
//        paydeck4.addClient(new Client(8, 100, null, PrivilegeType.Military));
//
//
//        var tester = new Client(6, 1, null, PrivilegeType.Interrupted);
//
//        List<PayDeck> paydecks = Arrays.asList(paydeck1, paydeck2, paydeck3, paydeck4);
//        PayDeck result = PayDeckChooseSystem.choosePaydeck(paydecks,tester);
//
//        assertEquals(paydeck3, result);
//    }
//}
