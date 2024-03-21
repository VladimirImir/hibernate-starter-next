package com.dev.service;

import com.dev.dao.UserRepository;
import com.dev.dto.UserCreateDto;
import com.dev.dto.UserReadDto;
import com.dev.entity.User;
import com.dev.mapper.Mapper;

import com.dev.mapper.UserCreateMapper;
import com.dev.mapper.UserReadMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.graph.GraphSemantic;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateMapper userCreateMapper;

    @Transactional
    public Long create(UserCreateDto userDto) {
        // validation
        var userEntity = userCreateMapper.mapFrom(userDto);
        return userRepository.save(userEntity).getId();
    }

    @Transactional
    public Optional<UserReadDto> findById(Long id) {
        return findById(id, userReadMapper);
    }

    @Transactional
    public <T> Optional<T> findById(Long id, Mapper<User, T> mapper) {
        Map<String, Object> properties = Map.of(
                GraphSemantic.LOAD.getJpaHintName(), userRepository.getEntityManager().getEntityGraph("WithCompany")
        );
        return userRepository.findById(id, properties)
                .map(mapper::mapFrom);
    }

    @Transactional
    public boolean delete(Long id) {
        var maybeUser = userRepository.findById(id);
        maybeUser.ifPresent(user -> userRepository.delete(user.getId()));
        return maybeUser.isPresent();
    }

}
