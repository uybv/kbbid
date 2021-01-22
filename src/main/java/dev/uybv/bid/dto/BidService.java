package dev.uybv.bid.dto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.uybv.bid.dao.Media;
import dev.uybv.bid.dao.MediaRepository;
import dev.uybv.bid.dao.MemberRepository;
import dev.uybv.bid.IAuthenticationFacade;
import dev.uybv.bid.dao.Bid;
import dev.uybv.bid.dto.core.BaseService;
import dev.uybv.bid.dto.exception.RecordNotFoundException;
import dev.uybv.bid.dto.model.MemberDto;
import dev.uybv.bid.dto.model.BidDto;

@Service
public class BidService extends BaseService<Bid, Integer, BidDto> implements IBidService {

	@Autowired
	public BidService(ModelMapper modelMapper) {
		super(modelMapper);
		/*
		 * this.modelMapper.addMappings(new PropertyMap<BidDto, Bid>() {
		 * 
		 * @Override protected void configure() { skip(destination.getCreatedAt());
		 * skip(destination.getLastUpdatedAt()); } });
		 */
	}

	@Autowired
	private IAuthenticationFacade authFacade;

	@Autowired
	private MediaRepository mediaRepos;

	@Autowired
	private MemberRepository memberRepos;

	@Autowired
	private MemberService memberSrv;

	@Override
	public BidDto createFromModel(BidDto model) {
		Bid bid = create(toEntity(model));
		BidDto result = toObject(bid);
		addOwnerToModel(result);

		List<Media> mediaFiles = model.getMediaFiles();
		if (mediaFiles != null && !mediaFiles.isEmpty()) {
			result.setMediaFiles(mediaRepos.saveAll(mediaFiles.stream().map(f -> {
				f.setBidId(bid.getId());
				return f;
			}).collect(Collectors.toList())));
		}

		return result;
	}

	@Override
	public BidDto updateFromModel(Integer id, BidDto model) {
		Bid bid = update(id, toEntity(model));
		BidDto result = toObject(bid);
		addOwnerToModel(result);

		List<Media> mediaFiles = model.getMediaFiles();
		List<Media> currentMediaFiles = mediaRepos.findByBidId(bid.getId());
		// Remove old media
		mediaRepos.deleteAll(currentMediaFiles.stream().filter(f -> {
			return mediaFiles != null && !mediaFiles.stream().anyMatch(_f -> {
				return f.getId() == _f.getId();
			});
		}).collect(Collectors.toList()));
		// Create/Update media
		if (mediaFiles != null) {
			mediaRepos.saveAll(mediaFiles.stream().map(f -> {
				f.setBidId(bid.getId());
				return f;
			}).collect(Collectors.toList()));
		}

		addMediaFileToModel(result);
		return result;
	}

	@Override
	public void beginCreate(Bid entity) {
		entity.setOwnerId(authFacade.getCurrent().getId());
		entity.setCreatedAt(Instant.now().getEpochSecond());
	}

	@Override
	public void beginUpdate(Integer id, Bid entity) {
		if (entity.getOwnerId() != authFacade.getCurrent().getId()) {
			throw new RecordNotFoundException();
		}
		entity.setId(id);
		entity.setLastUpdatedAt(Instant.now().getEpochSecond());
	}

	public void addOwnerToModel(BidDto model) {
		MemberDto owner = memberSrv.toObject(memberSrv.get(model.getOwner().getId()));
		model.setOwner(owner);
	}

	public void addMediaFileToModel(BidDto model) {
		model.setMediaFiles(mediaRepos.findByBidId(model.getId()));
	}

	public void addOwnerToModel(List<BidDto> models) {
		List<MemberDto> members = memberSrv.toObjects(memberRepos.findByIds(models.stream().map(model -> {
			return model.getOwner() != null ? model.getOwner().getId() : 0;
		}).distinct().collect(Collectors.toList())));

		models.forEach(model -> {
			model.setOwner(members.stream().filter(m -> {
				return model.getOwner() != null && m.getId() == model.getOwner().getId();
			}).findFirst().get());
		});
	}

	public void addMediaFileToModel(List<BidDto> models) {
		List<Media> mediaFiles = mediaRepos.findByBidIds(models.stream().map(model -> {
			return model.getId();
		}).collect(Collectors.toList()));

		models.forEach(model -> {
			model.setMediaFiles(mediaFiles.stream().filter(m -> {
				return m.getBidId() == model.getId();
			}).collect(Collectors.toList()));
		});
	}
}
