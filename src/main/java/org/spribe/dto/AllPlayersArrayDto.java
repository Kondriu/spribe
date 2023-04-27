package org.spribe.dto;

import lombok.Data;

import java.util.List;

@Data
public class AllPlayersArrayDto {
    private List<PlayerItemDto> players;
}
