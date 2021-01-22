package dev.uybv.bid.dto;

import java.util.List;

import dev.uybv.bid.dao.Bid;
import dev.uybv.bid.dto.core.IService;
import dev.uybv.bid.dto.model.BidDto;

public interface IBidService extends IService<Bid, Integer, BidDto> {
	public void addOwnerToModel(BidDto model);

	public void addOwnerToModel(List<BidDto> models);

	public void addMediaFileToModel(BidDto model);

	public void addMediaFileToModel(List<BidDto> models);
}
