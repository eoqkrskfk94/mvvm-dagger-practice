package com.mobinity.mvvm_dagger_practice.room

import com.mobinity.mvvm_dagger_practice.model.Blog
import com.mobinity.mvvm_dagger_practice.util.EntityMapper
import javax.inject.Inject

class CacheMapper
@Inject
constructor(): EntityMapper<BlogCacheEntity, Blog> {

    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            id = entity.id,
            title = entity.title,
            body = entity.body,
            category = entity.category,
            image =  entity.image
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            id = domainModel.id,
            title = domainModel.title,
            body = domainModel.body,
            category = domainModel.category,
            image =  domainModel.image
        )
    }

    fun mapFromEntityList(entities: List<BlogCacheEntity>): List<Blog> {
        return entities.map { mapFromEntity(it) }
    }

}