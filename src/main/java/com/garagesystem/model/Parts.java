package com.garagesystem.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "parts_department")
public class Parts {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "part_sequence")
    @SequenceGenerator(name = "part_sequence", sequenceName = "part_sequence", allocationSize = 2)
    @Column(name = "part_id")
    private long id;

    @Column(name = "garage_id")
    private long garageId;

    @Column(name = "part_updated_by")
    private long partUpdatedBy;

    @Column(name = "part_updated_date")
    private Date partUpdatedDate;

    @Column(name = "part_purchase_price")
    private long partPurchasePrice;

    @Column(name = "part_retail_price")
    private long partRetailPrice;

    @Column(name = "part_name")
    private String partName;

    public Parts() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGarageId() {
        return garageId;
    }

    public void setGarageId(long garageId) {
        this.garageId = garageId;
    }

    public long getPartUpdatedBy() {
        return partUpdatedBy;
    }

    public void setPartUpdatedBy(long partUpdatedBy) {
        this.partUpdatedBy = partUpdatedBy;
    }

    public Date getPartUpdatedDate() {
        return partUpdatedDate;
    }

    public void setPartUpdatedDate(Date partUpdatedDate) {
        this.partUpdatedDate = partUpdatedDate;
    }

    public long getPartPurchasePrice() {
        return partPurchasePrice;
    }

    public void setPartPurchasePrice(long partPurchasePrice) {
        this.partPurchasePrice = partPurchasePrice;
    }

    public long getPartRetailPrice() {
        return partRetailPrice;
    }

    public void setPartRetailPrice(long partRetailPrice) {
        this.partRetailPrice = partRetailPrice;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Parts(long garageId, long partUpdatedBy, Date partUpdatedDate, long partPurchasePrice, long partRetailPrice, String partName) {
        this.garageId = garageId;
        this.partUpdatedBy = partUpdatedBy;
        this.partUpdatedDate = partUpdatedDate;
        this.partPurchasePrice = partPurchasePrice;
        this.partRetailPrice = partRetailPrice;
        this.partName = partName;
    }

    @Override
    public String toString() {
        return "Parts{" +
                "id=" + id +
                ", garageId=" + garageId +
                ", partUpdatedBy=" + partUpdatedBy +
                ", partUpdatedDate=" + partUpdatedDate +
                ", partPurchasePrice='" + partPurchasePrice + '\'' +
                ", partRetailPrice='" + partRetailPrice + '\'' +
                ", partName='" + partName + '\'' +
                '}';
    }
}
