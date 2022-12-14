package com.gls.parcelshop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    @EqualsAndHashCode.Include
    private Long id;
    @ToString.Include
    @EqualsAndHashCode.Include
    private String street;
    @ToString.Include
    @EqualsAndHashCode.Include
    private String zip;
    @ToString.Include
    @EqualsAndHashCode.Include
    private String number;
    @ToString.Include
    @EqualsAndHashCode.Include
    private String consignee;

    @OneToOne(mappedBy = "address")
    @JsonIgnore
    private Parcel parcel;
}
