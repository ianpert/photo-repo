package com.ianpert;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="photo")
public class Photo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //private Photo() {}

    public Photo(byte[] imageData) {
        this.imageData = imageData;
    }

    private byte[] imageData;

    public Long getId() {
        return this.id;
    }

    public byte[] getImageData() {
        return this.imageData;
    }
}
