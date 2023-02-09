package serializable_objects;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

public class Order {
    private long Id;
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private String[] color;
    private long track;

    public Order() {

    }

    public Order(boolean isRandom) {
        if (isRandom) {
            setRandomFirstName();
            setRandomLastName();
            setRandomMetroStation();
            setRandomAddress();
            setRandomPhone();
            setRandomRentTime();
            setRandomDeliveryDate();
            setRandomComment();
        }
    }

    public static Order getInstance() {

        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader("src/test/resources/OrderExample"));
            return gson.fromJson(reader, Order.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public long getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setRandomFirstName() {
        Random random = new Random();
        String name = String.format("firstname-%d", random.nextInt());
        setFirstName(name);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRandomLastName() {
        Random random = new Random();
        String name = String.format("lastname-%d", random.nextInt());
        setLastName(name);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public void setRandomAddress() {
        Random random = new Random();
        String address = String.format("address-%d", random.nextInt());
        setAddress(address);
    }


    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public void setRandomMetroStation() {
        Random random = new Random();
        String station = String.format("%d", random.nextInt(40));
        setMetroStation(station);
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRandomPhone() {
        Random random = new Random();
        String phone = String.format("+%d", 79661350000L + random.nextInt(10000));
        setPhone(phone);
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public void setRandomRentTime() {
        Random random = new Random();
        int rent = random.nextInt(6);
        setRentTime(rent);
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setRandomDeliveryDate() {
        Random random = new Random();
        String date = "2023-02-01";
        setDeliveryDate(date);
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRandomComment() {
        Random random = new Random();
        String comment = String.format("comment-", random.nextInt());
        setComment(comment);
    }


    public String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    public long getTrack() {
        return track;
    }

    public void setTrack(long track) {
        this.track = track;
    }
}