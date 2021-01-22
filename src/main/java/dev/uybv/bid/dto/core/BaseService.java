package dev.uybv.bid.dto.core;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.uybv.bid.dto.exception.RecordNotFoundException;

public abstract class BaseService<E, K, M> implements IService<E, K, M> {

	protected final Class<M> mType;
	protected final Class<E> eType;

	protected final ModelMapper modelMapper;

	@Autowired
	protected JpaRepository<E, K> repos;

	@SuppressWarnings("unchecked")
	@Autowired
	public BaseService(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.eType = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.mType = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
	}

	public E create(E entity) {
		beginCreate(entity);
		return repos.save(entity);
	}

	public M createFromModel(M model) {
		E entity = create(toEntity(model));
		return toObject(entity);
	}

	public E update(K id, E entity) {
		if (repos.existsById(id)) {
			beginUpdate(id, entity);
			return repos.save(entity);
		}
		throw new RecordNotFoundException();
	}

	public M updateFromModel(K id, M model) {
		E entity = update(id, toEntity(model));
		return toObject(entity);
	}

	public boolean delete(K id) {
		if (repos.existsById(id)) {
			repos.deleteById(id);
			return true;
		}
		throw new RecordNotFoundException();
	}

	public E get(K id) {
		return repos.findById(id).orElseThrow(() -> new RecordNotFoundException());
	}

	public List<E> gets() {
		return repos.findAll();
	}

	public Page<E> gets(Pageable pageable) {
		return repos.findAll(pageable);
	}

	public PageResult<M> toPageResult(Page<E> page) {
		PageResult<M> result = new PageResult<>();
		result.setCurrentPage(page.getNumber());
		result.setPageSize(page.getSize());
		result.setTotalItems(page.getTotalElements());
		result.setTotalPages(page.getTotalPages());
		result.setItems(toObjects(page.getContent()));
		return result;
	}

	public <D> PageResult<D> toPageResult(Page<E> page, Class<D> destinationType) {
		PageResult<D> result = new PageResult<>();
		result.setCurrentPage(page.getNumber());
		result.setPageSize(page.getSize());
		result.setTotalItems(page.getTotalElements());
		result.setTotalPages(page.getTotalPages());
		result.setItems(toObjects(page.getContent(), destinationType));
		return result;
	}

	public M toObject(E entity) {
		return modelMapper.map(entity, mType);
	}

	public <D> D toObject(E entity, Class<D> destinationType) {
		return modelMapper.map(entity, destinationType);
	}

	public List<M> toObjects(List<E> entities) {
		return entities.stream().map(this::toObject).collect(Collectors.toList());
	}

	public <D> List<D> toObjects(List<E> entities, Class<D> destinationType) {
		return entities.stream().map(e -> {
			return toObject(e, destinationType);
		}).collect(Collectors.toList());
	}

	public E toEntity(M model) {
		return modelMapper.map(model, eType);
	}

	public <S> E toEntityFromOther(S model) {
		return modelMapper.map(model, eType);
	}

	public List<E> toEntities(List<M> models) {
		return models.stream().map(this::toEntity).collect(Collectors.toList());
	}

	public <S> List<E> toEntitiesFromOther(List<S> models) {
		return models.stream().map(this::toEntityFromOther).collect(Collectors.toList());
	}

	public void beginCreate(E entity) {
	}

	public void beginUpdate(K id, E entity) {
	}

}
