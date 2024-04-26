package user.address;

public class Address {
    private String street, city, state, country;
    private short postalCode;

    public Address() {
        this("", "", "", "", (short) 0);
    }

    public Address(String country, String state, String city, String street, short postalCode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
        this.postalCode = postalCode;
    }

    public Address(user.address.Address other) {
        this.country = other.country;
        this.state = other.state;
        this.city = other.city;
        this.street = other.street;
        this.postalCode = other.postalCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public short getPostalCode() {
        return postalCode;
    }

    public void setStreet(String street) {
        this.street = formatAddress(street);
    }

    public void setCity(String city) {
        this.city = formatAddress(city);
    }

    public void setState(String state) {
        this.state = capitalize(state);
    }

    public void setCountry(String country) {
        this.country = capitalize(country);
    }

    public void setPostalCode(short postalCode) {
        if (postalCode < 1000 || postalCode > 9999)
            this.postalCode = 9999;

        else
            this.postalCode = postalCode;
    }

    private String formatAddress(String addressString) {
        //Създава се нов обект от тип StringBuilder с име formattedAddress. Този обект се използва за построяване на форматирания адрес.
        StringBuilder formattedAddress = new StringBuilder();

        //За всеки символ c в подадения низ addressString се извършва следната проверка:
        for (char c : addressString.toCharArray()) {
            //Ако символът c е буква, се проверява дали предишният символ във форматирания адрес не е също буква.
            //Ако това е така, това означава, че трябва да бъде добавен интервал между тези два символа, за да се разделят думите. След това текущият символ се добавя във форматирания адрес,
            //като се преобразува в главна буква с метода Character.toUpperCase(c).
            if (Character.isLetter(c)) {
                if (formattedAddress.length() > 0 && !Character.isLetter(formattedAddress.charAt(formattedAddress.length() - 1)))
                    formattedAddress.append(' ');

                formattedAddress.append(Character.toUpperCase(c));
            }

            ////В противен случай, ако символът c не е буква, той се добавя направо във форматирания адрес без промяна.
            else
                formattedAddress.append(c);
        }

        //Накрая, форматираният адрес се връща като низ, използвайки метода toString() на StringBuilder.
        return formattedAddress.toString();
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty())
            return str;

        return Character.toUpperCase(str.charAt(0)) + str.substring(1).toLowerCase();
    }
}
