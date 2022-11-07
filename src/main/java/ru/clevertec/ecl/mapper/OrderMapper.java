package ru.clevertec.ecl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.clevertec.ecl.dto.GiftCertificateDto;
import ru.clevertec.ecl.dto.OrderDto;
import ru.clevertec.ecl.dto.ReadOrderDto;
import ru.clevertec.ecl.dto.UserDto;
import ru.clevertec.ecl.entity.Order;

import java.time.LocalDateTime;
import java.util.List;

@Mapper(imports = LocalDateTime.class, uses = {TagMapper.class, GiftCertificateMapper.class, UserMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    @Mapping(target = "purchaseTimestamp", expression = "java(LocalDateTime.now().withNano(0))")
    @Mapping(target = "cost", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "giftCertificate", ignore = true)
    Order toEntity(ReadOrderDto readOrderDto);

    @Mapping(source = "order.id", target = "id")
    @Mapping(target = "user", source = "userDto")
    @Mapping(target = "giftCertificate", source = "giftCertificateDto")
    Order toFinalEntity(Order order, UserDto userDto, GiftCertificateDto giftCertificateDto);

    @Mapping(source = "giftCertificate.id", target = "giftCertificateId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(target = "purchaseTimestamp", expression = "java(LocalDateTime.now().withNano(0))")
    OrderDto toDto(Order order);

    List<OrderDto> toDtoList(List<Order> orderList);
}
