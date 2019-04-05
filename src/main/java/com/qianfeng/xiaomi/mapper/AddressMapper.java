package com.qianfeng.xiaomi.mapper;

import com.qianfeng.xiaomi.address.pojo.Address;

import java.util.List;

public interface AddressMapper {
    List<Address> findAddressByUid(Integer uid);

    Address findaddressByAid(int id);

    List<Address> findAddressAll(Integer uid);

    void updateAddressLevelById(Integer id);

    void deleteAddressById(Integer id);

    void addAddress(Address address);

    void updateAddressOriLevelById(Integer id);

    void updateAddress(Address address);
}
