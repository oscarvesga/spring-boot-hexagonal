package com.example.demo.posts.posts.infraestructure.outbound.database;

import com.example.demo.posts.posts.domain.model.PostCommand;
import com.example.demo.posts.posts.domain.model.PostQuery;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface IPostMapper {
    @Mappings({
            @Mapping(source = "idPost", target = "id"),
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "body", target = "body"),
            @Mapping(source = "idUser", target = "userId"),
    })
    PostQuery toPostQuery(PostEntity entity);
    List<PostQuery> toPostQueryLst(List<PostEntity> entityLst);

    @Mappings({
            @Mapping(source = "title", target = "title"),
            @Mapping(source = "body", target = "body"),
            @Mapping(source = "idUser", target = "userId"),
    })
    PostCommand toPostCommand(PostEntity entity);

    @InheritInverseConfiguration
    PostEntity toPostEntity(PostQuery post);

    @InheritInverseConfiguration
    PostEntity toPostEntity(PostCommand post);
}
