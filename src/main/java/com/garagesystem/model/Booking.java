package com.garagesystem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 2)
    @Column(name = "booking_id")
    private long id;

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "garage_id")
    private long garageId;

    @Column(name = "vehicle_id")
    private long vehicleId;

    @Column(name = "service_advisor_id")
    private long serviceAdvisorId;

    @Column(name = "booking_date")
    private Date bookingDate;

    @Column(name = "booking_updated_date")
    private Date bookingUpdatedDate;

    @Column(name = "booking_comments")
    private String bookingComments;

    public long getId() {
        return id;
    }

    public Booking(long customerId, long garageId, long vehicleId, long serviceAdvisorId, Date bookingDate, Date bookingUpdatedDate, String bookingComments) {
        this.customerId = customerId;
        this.garageId = garageId;
        this.vehicleId = vehicleId;
        this.serviceAdvisorId = serviceAdvisorId;
        this.bookingDate = bookingDate;
        this.bookingUpdatedDate = bookingUpdatedDate;
        this.bookingComments = bookingComments;
    }

    public Booking() {

    }


    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", garageId=" + garageId +
                ", vehicleId=" + vehicleId +
                ", serviceAdvisorId=" + serviceAdvisorId +
                ", bookingDate=" + bookingDate +
                ", bookingUpdatedDate=" + bookingUpdatedDate +
                ", bookingComments='" + bookingComments + '\'' +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getGarageId() {
        return garageId;
    }

    public void setGarageId(long garageId) {
        this.garageId = garageId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getServiceAdvisorId() {
        return serviceAdvisorId;
    }

    public void setServiceAdvisorId(long serviceAdvisorId) {
        this.serviceAdvisorId = serviceAdvisorId;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Date getBookingUpdatedDate() {
        return bookingUpdatedDate;
    }

    public void setBookingUpdatedDate(Date bookingUpdatedDate) {
        this.bookingUpdatedDate = bookingUpdatedDate;
    }

    public String getBookingComments() {
        return bookingComments;
    }

    public void setBookingComments(String bookingComments) {
        this.bookingComments = bookingComments;
    }


}
