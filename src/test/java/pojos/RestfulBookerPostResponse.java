package pojos;

public class RestfulBookerPostResponse {
    private Integer bookingid;
    private RestfulBookerPayload booking;

    public RestfulBookerPostResponse() {}

    public RestfulBookerPostResponse(Integer bookingid, RestfulBookerPayload booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public RestfulBookerPayload getBooking() {
        return booking;
    }

    public void setBooking(RestfulBookerPayload booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "RestfulBookerPostResponse{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}