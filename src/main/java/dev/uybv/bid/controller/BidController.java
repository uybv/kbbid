package dev.uybv.bid.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.uybv.bid.dao.Bid;
import dev.uybv.bid.dto.IBidService;
import dev.uybv.bid.dto.core.PageResult;
import dev.uybv.bid.dto.model.BidDto;

@RestController
@RequestMapping("/api/bid")
public class BidController {

	@Autowired
	private IBidService bidSrv;

	@GetMapping
	public ResponseEntity<PageResult<BidDto>> gets(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(name = "withOwner", defaultValue = "0") boolean withOwner,
			@RequestParam(name = "withMedia", defaultValue = "1") boolean withMedia) {
		Page<Bid> pageBid = bidSrv.gets(PageRequest.of(page, size));
		PageResult<BidDto> result = bidSrv.toPageResult(pageBid);
		if (withOwner) {
			bidSrv.addOwnerToModel(result.getItems());
		}
		if (withMedia) {
			bidSrv.addMediaFileToModel(result.getItems());
		}
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BidDto> get(@PathVariable Integer id,
			@RequestParam(name = "withOwner", defaultValue = "0") boolean withOwner,
			@RequestParam(name = "withMedia", defaultValue = "1") boolean withMedia) {
		Bid bid = bidSrv.get(id);
		BidDto result = bidSrv.toObject(bid);
		if (withOwner) {
			bidSrv.addOwnerToModel(result);
		}
		if (withMedia) {
			bidSrv.addMediaFileToModel(result);
		}

		return ResponseEntity.ok(result);
	}

	@PostMapping
	public ResponseEntity<BidDto> create(@Valid @RequestBody BidDto model) {
		return ResponseEntity.ok(bidSrv.createFromModel(model));
	}

	@PutMapping("/{id}")
	public ResponseEntity<BidDto> update(@Valid @RequestBody BidDto model, @PathVariable Integer id) {
		return ResponseEntity.ok(bidSrv.updateFromModel(id, model));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
		if (bidSrv.delete(id)) {
			return ResponseEntity.ok(true);
		}
		return ResponseEntity.ok(false);
	}
}
