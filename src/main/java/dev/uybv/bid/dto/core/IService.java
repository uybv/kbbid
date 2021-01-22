package dev.uybv.bid.dto.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<E, K, M> {
	public abstract E create(E entity);

	public abstract M createFromModel(M model);

	public abstract E update(K id, E entity);

	public abstract M updateFromModel(K id, M model);

	public abstract boolean delete(K id);

	public abstract E get(K id);

	public abstract List<E> gets();

	public abstract Page<E> gets(Pageable pageable);

	public abstract PageResult<M> toPageResult(Page<E> page);

	public abstract <D> PageResult<D> toPageResult(Page<E> page, Class<D> destinationType);

	public abstract M toObject(E entity);

	public abstract <D> D toObject(E entity, Class<D> destinationType);

	public abstract List<M> toObjects(List<E> entities);

	public abstract <D> List<D> toObjects(List<E> entities, Class<D> destinationType);

	public abstract E toEntity(M model);

	public abstract <S> E toEntityFromOther(S model);

	public abstract List<E> toEntities(List<M> models);

	public abstract <S> List<E> toEntitiesFromOther(List<S> models);

	public abstract void beginCreate(E entity);

	public abstract void beginUpdate(K id, E entity);
}
