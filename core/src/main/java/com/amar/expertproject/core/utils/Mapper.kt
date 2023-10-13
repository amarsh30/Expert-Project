package com.amar.expertproject.core.utils

interface Mapper<Entity, Model, Response> {
    fun mapEntitiesToDomain(type: Entity): Model
    fun mapDomainToEntity(type: Model): Entity
    fun mapResponseToEntities(type: Response): Entity
}