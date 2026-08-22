package com.example.e_commerce.Service;

import com.example.e_commerce.DTO.Address.AddressRequestDTO;
import com.example.e_commerce.DTO.Address.AddressResponseDTO;
import com.example.e_commerce.Exception.ResourceNotFoundException;
import com.example.e_commerce.Model.Address;
import com.example.e_commerce.Repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl {

    private final ModelMapper modelMapper;
    private final AddressRepository addressRepository;

    public AddressResponseDTO saveAddress(AddressRequestDTO requestDTO){
        Address address = modelMapper.map(requestDTO, Address.class);

        Address address1 = addressRepository.save(address);

        return modelMapper.map(address1, AddressResponseDTO.class);
    }

    public AddressResponseDTO getAddressById(Long id){
        Address address = addressRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Address Not found with id : " + id));

        return AddressResponseDTO.builder()
                .id(address.getId())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .addressType(address.getAddressType())
                .isDefault(address.getIsDefault())
                .build();
    }

    public List<AddressResponseDTO> getAllAddress(){
        List<Address> address = addressRepository.findAll();

        return address.stream().map(address1 -> AddressResponseDTO.builder()
                .id(address1.getId())
                .addressLine1(address1.getAddressLine1())
                .addressLine2(address1.getAddressLine2())
                .city(address1.getCity())
                .state(address1.getState())
                .country(address1.getCountry())
                .zipCode(address1.getZipCode())
                .addressType(address1.getAddressType())
                .isDefault(address1.getIsDefault())
                .build())
                .toList();
    }

    public AddressResponseDTO updateAddress(Long id, AddressRequestDTO requestDTO){
        Address address1 = addressRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Address Not found with id : " + id));

        modelMapper.map(requestDTO, address1);
        Address address = addressRepository.save(address1);

        return AddressResponseDTO.builder()
                .id(address.getId())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .state(address.getState())
                .country(address.getCountry())
                .zipCode(address.getZipCode())
                .addressType(address.getAddressType())
                .isDefault(address.getIsDefault())
                .build();
    }
    public String deleteAddress(Long id){
        Address address = addressRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Address Not found with id :" + id));

        addressRepository.delete(address);
        return "Address Deleted Successfully....";
    }
}
