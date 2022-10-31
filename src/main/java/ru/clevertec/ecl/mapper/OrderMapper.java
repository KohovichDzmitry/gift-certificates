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

    @Mapping(source = "readOrderDto.id", target = "id")
    @Mapping(target = "purchaseTimestamp", expression = "java(LocalDateTime.now().withNano(0))")
    @Mapping(source = "userDto", target = "user")
    @Mapping(source = "giftCertificateDto", target = "giftCertificate")
    Order toEntity(ReadOrderDto readOrderDto, UserDto userDto, GiftCertificateDto giftCertificateDto);

    @Mapping(source = "order.giftCertificate.id", target = "giftCertificateId")
    @Mapping(source = "order.user.id", target = "userId")
    OrderDto toDto(Order order);

    List<OrderDto> toDtoList(List<Order> orderList);
}
