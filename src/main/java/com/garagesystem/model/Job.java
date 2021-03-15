package com.garagesystem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_sequence")
    @SequenceGenerator(name = "job_sequence", sequenceName = "job_sequence", allocationSize = 2)
    @Column(name = "job_id")
    private long id;

    @Column(name = "booking_id")
    private long bookingId;

    @Column(name = "part_id")
    private long partId;

    @Column(name = "technician_id")
    private long technicianId;

    @Column(name = "job_parts_used")
    private long partsUsed;

    @Column(name = "job_hours_worked")
    private long hoursWorked;

    @Column(name = "job_date")
    private Date date;


    public Job() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public long getPartId() {
        return partId;
    }

    public void setPartId(long partId) {
        this.partId = partId;
    }

    public long getTechnicianId() {
        return technicianId;
    }

    public void setTechnicianId(long technicianId) {
        this.technicianId = technicianId;
    }

    public long getPartsUsed() {
        return partsUsed;
    }

    public void setPartsUsed(long partsUsed) {
        this.partsUsed = partsUsed;
    }

    public long getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(long hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Job(long bookingId, long partId, long technicianId, long partsUsed, long hoursWorked, Date date) {
        this.bookingId = bookingId;
        this.partId = partId;
        this.technicianId = technicianId;
        this.partsUsed = partsUsed;
        this.hoursWorked = hoursWorked;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Job{" +
                "id=" + id +
                ", bookingId=" + bookingId +
                ", partId=" + partId +
                ", technicianId=" + technicianId +
                ", partsUsed=" + partsUsed +
                ", hoursWorked=" + hoursWorked +
                ", date=" + date +
                '}';
    }
}
