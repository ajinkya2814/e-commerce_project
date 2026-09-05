package com.example.e_commerce.Service;

import com.example.e_commerce.Constants.ErrorMessage;
import com.example.e_commerce.DTO.Address.AddressRequestDTO;
import com.example.e_commerce.DTO.Address.AddressResponseDTO;
import com.example.e_commerce.Exception.ForbirddenException;
import com.example.e_commerce.Exception.ResourceNotFoundException;
import com.example.e_commerce.Model.Address;
import com.example.e_commerce.Model.Users;
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

    public AddressResponseDTO saveAddress(Users user, AddressRequestDTO requestDTO) {

        Address address = modelMapper.map(requestDTO, Address.class);
        address.setUsers(user); // link it to the logged-in user — this was missing before

        Address saved = addressRepository.save(address);

        return toResponseDTO(saved);
    }

    public AddressResponseDTO getAddressById(Users user, Long id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.ADDRESS_NOT_FOUND + id));

        checkOwnership(user, address);

        return toResponseDTO(address);
    }

    public List<AddressResponseDTO> getAllAddress(Users user) {

        return addressRepository.findAll().stream()
                .filter(address -> address.getUsers().getId().equals(user.getId())) // only this user's own addresses
                .map(this::toResponseDTO)
                .toList();
    }

    public AddressResponseDTO updateAddress(Users user, Long id, AddressRequestDTO requestDTO) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.ADDRESS_NOT_FOUND + id));

        checkOwnership(user, address);

        modelMapper.map(requestDTO, address);
        Address updated = addressRepository.save(address);

        return toResponseDTO(updated);
    }

    public String deleteAddress(Users user, Long id) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorMessage.ADDRESS_NOT_FOUND + id));

        checkOwnership(user, address);

        addressRepository.delete(address);
        return "Address Deleted Successfully....";
    }

    // helper methods, avoids repeating the same builder block

    private void checkOwnership(Users user, Address address) {
        if (!address.getUsers().getId().equals(user.getId())) {
            throw new ForbirddenException("You are not allowed to access this address");
        }
    }

    private AddressResponseDTO toResponseDTO(Address address) {
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
}