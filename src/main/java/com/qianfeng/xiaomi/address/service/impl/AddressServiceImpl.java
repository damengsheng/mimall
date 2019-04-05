package com.qianfeng.xiaomi.address.service.impl;

import com.qianfeng.xiaomi.address.pojo.Address;
import com.qianfeng.xiaomi.address.service.AddressService;
import com.qianfeng.xiaomi.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> findAddressByUid(Integer uid) {
        return addressMapper.findAddressByUid(uid);
    }

    @Override
    public Address findaddressByAid(int aid) {
        return addressMapper.findaddressByAid(aid);
    }

    @Override
    public List<Address> findAddressAll(Integer uid) {
        return addressMapper.findAddressAll(uid);
    }

    @Override
    public void defaultAddress(Integer id) {
        addressMapper.updateAddressOriLevelById(id);
        addressMapper.updateAddressLevelById(id);
    }

    @Override
    public void deleteAddressById(Integer id) {
        addressMapper.deleteAddressById(id);
    }

    @Override
    public void addAddress(Address address) {
        addressMapper.addAddress(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressMapper.updateAddress(address);
    }
}
