package com.qianfeng.xiaomi.address.service;

import com.qianfeng.xiaomi.address.pojo.Address;

import java.util.List;

public interface AddressService {

    List<Address> findAddressByUid(Integer uid);

    Address findaddressByAid(int aid);

    List<Address> findAddressAll(Integer uid);

    void defaultAddress(Integer id);

    void deleteAddressById(Integer id);

    void addAddress(Address address);

    void updateAddress(Address address);
}
