<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${BasePackageName}dao.${DaoPackageName}.${ClassName}Mapper">

    <resultMap id="${EntityName}ResultMap" type="${BasePackageName}dao.${EntityPackageName}.${ClassName}">
        ${ResultMap}
        ${Association}
        ${Collection}
    </resultMap>

    <sql id="${EntityName}Columns">
        ${ColumnMap}
    </sql>

    <sql id="${EntityName}Joins">
        ${Joins}
    </sql>


    <select id="listByRequest">
        SELECT
        <include refid="${EntityName}Columns" />
        FROM ${TableName} <include refid="${EntityName}Joins" />
        where available = 1

        ${SelectProperties}
        order by create_time desc
    </select>


    <select id="findList" resultMap="${EntityName}ResultMap">
        SELECT
        <include refid="${EntityName}Columns" />
        FROM ${TableName} <include refid="${EntityName}Joins" />
        WHERE available = 1
        ${SelectProperties}
    </select>

    <select id="findById" resultMap="${EntityName}ResultMap">
        SELECT
        <include refid="${EntityName}Columns" />
        FROM ${TableName} <include refid="${EntityName}Joins" />
        <where>
            ${TableName}.${PrimaryKey} = ${Id}
        </where>
    </select>

    <insert id="insert">
        INSERT INTO ${TableName}(
            ${InsertProperties}
        )
        VALUES (
            ${InsertValues}
        )
    </insert>

    <insert id="insertBatch">
        INSERT INTO ${TableName}(
            ${InsertProperties}
        )
        VALUES
        <foreach collection ="list" item="${EntityName}" separator =",">
        (
            ${InsertBatchValues}
        )
        </foreach>
    </insert>

    <update id="update">
        UPDATE ${TableName}
             <set>
               ${UpdateProperties}
            </set>
         WHERE ${PrimaryKey} = ${WhereId}
    </update>

    <delete id="deleteById">
        UPDATE ${TableName} SET available = 0 WHERE id = #{id};
    </delete>

    <select id="listByIds" resultMap="${EntityName}ResultMap">
        select * from ${TableName} where available = 1 and id in
        <foreach collection="ids" separator="," item="item" close=")" open="(">
            #/{item}
        </foreach>
    </select>


</mapper>