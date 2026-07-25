package com.example.e_commerce.Service;


import com.example.e_commerce.DTO.Role.RoleRequestDTO;
import com.example.e_commerce.DTO.Role.RoleResponseDTO;
import com.example.e_commerce.Exception.ResourceNotFoundException;
import com.example.e_commerce.Model.Role;
import com.example.e_commerce.Repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;


    public RoleResponseDTO createRole (RoleRequestDTO requestDTO){

        Role role = modelMapper.map(requestDTO , Role.class);

        Role saverole = roleRepository.save(role);

        return modelMapper.map(saverole, RoleResponseDTO.class);
    }

    public RoleResponseDTO getRole (Long id){
        Role role = roleRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Role Resource Not Found With Id : " + id));

        return RoleResponseDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public List<RoleResponseDTO> getAll() {

        return roleRepository.findAll()
                .stream()
                .map(role -> modelMapper.map(role, RoleResponseDTO.class))
                .toList();
    }

    public RoleResponseDTO update(Long id, RoleRequestDTO dto) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product not found"));

        modelMapper.map(dto, role);

        Role updatedRole = roleRepository.save(role);

        return RoleResponseDTO.builder()
                .id(updatedRole.getId())
                .name(updatedRole.getName())
                .build();
    }
}
