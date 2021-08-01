import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DemoContactBaseTest {
    private Contact aContactFromBase = new Contact("Пахом", "Пахомыч", "+7931-7422816", Contact.Group.WORK);
    private final String exampleNumber = aContactFromBase.getNumber();            // данные связаны с содержанием getExampleBase()
    private final String exampleShortName = aContactFromBase.getShortString();    // соответствуют первой записи из него

    private final String numberAbsentFromBase = "+7-000";

    private ContactBase cb;

    @BeforeEach
    void setUp() {
        cb = ContactBase.getBaseExample();
    }

    @Test
    void contact_adds() {
        Contact newContact = new Contact("Другой", "Знакомый", "+7-000", Contact.Group.FRIENDS);
        cb.addContact(newContact);
        assertTrue(cb.containsNumber("+7-000"));
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
        cb.removeContact(aContactFromBase.getNumber());
        assertFalse(cb.containsNumber(exampleNumber));
    }
    @Test
    void contact_removes_byNS() {
        cb.removeContact(exampleNumber);
        assertFalse(cb.containsNumber(exampleNumber));
    }

    @Test
    void contactInfo_gets_ByNumber() {
        assertEquals(aContactFromBase.toString(),
                cb.getContactInfoByNumber(exampleNumber));
    }
    @Test
    void warning_ifNoEntity_toGetInfoOf() {
        assertEquals("Контакт с таким номером не найден",
                cb.getContactInfoByNumber(numberAbsentFromBase));
    }

    @Test
    void shows_ifContents() {
        assertTrue(cb.containsNumber(exampleNumber));
    }

    @Test
    void shows_ifDoesNotContent() {
        assertFalse(cb.containsNumber(numberAbsentFromBase));
    }

    @Test
    void getContactByNumber() {
        assertEquals(aContactFromBase,
                cb.getContactByNumber(exampleNumber));
    }

    @Test
    void getContactByNameSurname() {
        assertEquals(aContactFromBase,
                cb.getContactByNameSurname(aContactFromBase.getName(), aContactFromBase.getSurname()));
    }



}