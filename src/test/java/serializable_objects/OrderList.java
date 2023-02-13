package serializable_objects;

import java.util.ArrayList;
import java.util.List;

public class OrderList {

    List<Order> orders;
    PageInfo pageInfo;
    List<MetroStation> stations;

    public OrderList() {
    }

    public OrderList(List<Order> orders, PageInfo pageInfo, List<MetroStation> stations) {
        this.orders = orders;
        this.pageInfo = pageInfo;
        this.stations = stations;
    }


    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public List<MetroStation> getStations() {
        return stations;
    }

    public void setStations(List<MetroStation> stations) {
        this.stations = stations;
    }

    public boolean ifContainsGivenOrder(Order order) {
        for (Order anotherOrder : orders) {
            long track1 = order.getTrack();
            long track2 = anotherOrder.getTrack();

            if (track1 == track2) {
                return true;
            }
        }
        return false;
    }

    public List<Long> getTrackIds() {
        List<Long> trackIds = new ArrayList<>();
        for(Order order : orders){
            trackIds.add(order.getTrack());
        }
        return trackIds;
    }
}
