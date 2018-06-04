package com.codecool.web.model;

public final class User {

    private final int supplierId;
//    private final String companyName;
//    private final String contactName;
//    private final String contactTitle;
//    private final String address;
//    private final String city;
//    private final String region;
//    private final String postal_code;
//    private final String country;
//    private final String phone;
//    private final String fax;
//    private final String homepage;

    public User(int supplierId) {
        this.supplierId = supplierId;
    }

//    public User(int supplierId, String companyName, String contactName, String contactTitle, String address, String city, String region, String postal_code, String country, String phone, String fax, String homepage) {
//        this.supplierId = supplierId;
//        this.companyName = companyName;
//        this.contactName = contactName;
//        this.contactTitle = contactTitle;
//        this.address = address;
//        this.city = city;
//        this.region = region;
//        this.postal_code = postal_code;
//        this.country = country;
//        this.phone = phone;
//        this.fax = fax;
//        this.homepage = homepage;
//    }

    public int getSupplierId() {
        return supplierId;
    }

//    public String getCompany_name() {
//        return companyName;
//    }
//
//    public String getContact_name() {
//        return contactName;
//    }
//
//    public String getContact_title() {
//        return contactTitle;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public String getRegion() {
//        return region;
//    }
//
//    public String getPostal_code() {
//        return postal_code;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public String getFax() {
//        return fax;
//    }
//
//    public String getHomepage() {
//        return homepage;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
//        User user = (User) o;
//        return Objects.equals(email, user.email) &&
//            Objects.equals(password, user.password);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), email, password);
//    }
}
