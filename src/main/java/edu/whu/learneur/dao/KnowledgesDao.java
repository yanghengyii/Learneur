package edu.whu.learneur.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.whu.learneur.domain.Books;
import edu.whu.learneur.domain.Knowledges;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.whu.learneur.domain.Lessons;
import edu.whu.learneur.domain.Projects;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 知识点 Mapper 接口
 * </p>
 *
 * @author Learneur
 * @since 2022-12-01
 */
@Mapper
public interface KnowledgesDao extends BaseMapper<Knowledges> {
    @Select("        select *\n" +
            "        from knowledges_resources as kr, resources as r, books as b\n" +
            "        where kr.id_knowledge = #{idKnowledge} and\n" +
            "            r.id_resources = kr.id_resources and\n" +
            "            r.type = 1 and\n" +
            "            b.id_book = r.id_specific" +
            "        order by b.title\n" )
    Page<Books> selectBooksByKnowledge(
            @Param("idKnowledge") Long idKnowledge,
            @Param("page") Page<Books> page
    );

    @Select("        select *\n" +
            "        from knowledges_resources as kr, resources as r, lessons as l\n" +
            "        where kr.id_knowledge = #{idKnowledge} and\n" +
            "            r.id_resources = kr.id_resources and\n" +
            "            r.type = 2 and\n" +
            "            l.id_lesson = r.id_specific" +
            "        order by l.title\n" )
    Page<Lessons> selectLessonsByKnowledge(
            @Param("idKnowledge") Long idKnowledge,
            @Param("page") Page<Books> page
    );

    @Select("        select *\n" +
            "        from knowledges_resources as kr, resources as r, projects as p\n" +
            "        where kr.id_knowledge = #{idKnowledge} and\n" +
            "            r.id_resources = kr.id_resources and\n" +
            "            r.type = 3 and\n" +
            "            p.id_project = r.id_specific" +
            "        order by p.star_gazers\n" )
    Page<Projects> selectProjectsByKnowledge(
            @Param("idKnowledge") Long idKnowledge,
            @Param("page") Page<Books> page
    );
}
