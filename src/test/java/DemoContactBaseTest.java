import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoContactBaseTest {
    private final String exampleNumber = "+7931-7422816";   // данные связаны с содержанием getExampleBase()
    private final String exampleShortName = "Пахом Пахомыч";    // соответствуют первой записи из него

    private final String numberAbsentFromBase = "+7-000";

    private ContactBase cb;

    @BeforeEach
    void setUp() {
        cb = ContactBase.getBaseExample();
    }

    @Test
    void contact_adds() {
    }

    @Test
    void resolves_name_ifPresent() {
        assertEquals(exampleShortName,
                cb.tryToGetNameFor(exampleNumber));
    }
    @Test
    void bypasses_number_ifAbsent() {
        assertEquals(numberAbsentFromBase,
                cb.tryToGetNameFor(numberAbsentFromBase));
    }

    @Test
    void contact_removes_byNumber() {

    }
    @Test
    void contact_removes_byNS() {

    }

    @Test
    void contact_info_gets_ByNumber() {

    }

    @Test
    void shows_ifContents() {
        assertTrue(cb.containsNumber("+7931-7422816"));
    }

    @Test
    void shows_ifDoesNotContent() {
        assertTrue(cb.containsNumber("+7931-7422816"));
    }

    @Test
    void getContactByNumber() {
    }

    @Test
    void getContactByNameSurname() {
    }

    @Test
    void testRemoveContact() {
    }
}